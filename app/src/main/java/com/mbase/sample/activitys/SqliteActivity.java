package com.mbase.sample.activitys;

import com.mbase.monch.BaseApp;
import com.mbase.monch.database.db.DataBase;
import com.mbase.monch.utils.ThreadUtils;
import com.mbase.monch.utils.log.LogManager;
import com.mbase.monch.utils.log.Loggers;
import com.mbase.monch.utils.toast.T;
import com.mbase.sample.BaseActivity;
import com.mbase.sample.entity.Student;

import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by monch on 15/12/7.
 */
public class SqliteActivity extends BaseActivity {

    private Loggers logger = LogManager.getLogger(SqliteActivity.class);

    // 计数器
    private static AtomicInteger index = new AtomicInteger(1);

    private String[] buttons = new String[]{
            "插入",
            "插入or修改",
            "删除",
            "修改",
            "查询"
    };

    private ExecutorService executor = ThreadUtils.createSingleExecutor();

    private boolean checkTaskExecutor() {
        return executor != null && !executor.isShutdown();
    }

    @Override
    protected String[] getButtonTexts() {
        return buttons;
    }

    private volatile Student lastOperationEntity;

    @Override
    protected void click(int id) {
        if (!checkTaskExecutor()) return;
        final String name = buttons[id];
        switch (id) {
            case 0:
                executor.submit(new Runnable() {
                    @Override
                    public void run() {
                        DataBase db = BaseApp.getDatabase();
                        Student s = new Student(getStudentName(), getStudentGender());
                        long id = db.insert(s);
                        logger.info("[" + name + "]" + "插入ID：" + id + "，信息：" + s.toString());
                        lastOperationEntity = s;
                    }
                });
                break;
            case 1:
                executor.submit(new Runnable() {
                    @Override
                    public void run() {
                        DataBase db = BaseApp.getDatabase();
                        Student s = new Student(getStudentName(), getStudentGender());
                        s.id = 1;
                        long id = db.save(s);
                        logger.info("[" + name + "]" + "插入or修改ID：" + id + "，信息：" + s.toString());
                        lastOperationEntity = s;
                    }
                });
                break;
            case 2:
                executor.submit(new Runnable() {
                    @Override
                    public void run() {
                        if (lastOperationEntity == null) {
                            T.ss("请插入一条信息后再执行删除");
                            return;
                        }
                        DataBase db = BaseApp.getDatabase();
                        int count = db.delete(lastOperationEntity);
                        logger.info("[" + name + "]" + "共删除数量为：" + count);
                        lastOperationEntity = null;
                    }
                });
                break;
            case 3:
                executor.submit(new Runnable() {
                    @Override
                    public void run() {
                        if (lastOperationEntity == null) {
                            T.ss("请插入一条信息后再执行修改");
                            return;
                        }
                        logger.info("[" + name + "前]" + lastOperationEntity.toString());
                        lastOperationEntity.name = getStudentName();
                        lastOperationEntity.gender = getStudentGender();
                        DataBase db = BaseApp.getDatabase();
                        int count = db.update(lastOperationEntity);
                        logger.info("[" + name + "后]操作数：" + count + "，信息：" + lastOperationEntity.toString());
                    }
                });
                break;
            case 4:
                executor.submit(new Runnable() {
                    @Override
                    public void run() {
                        List<Student> data = BaseApp.getDatabase().query(Student.class);
                        logger.info("[" + name + "]" + "查询结果：" + data);
                    }
                });
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (executor != null) executor.shutdown();
    }

    private String getStudentName() {
        return "学生测试姓名" + index.getAndIncrement();
    }

    private int getStudentGender() {
        return new Random().nextBoolean() ? 1 : 0;
    }

    private void print(String name, Object... objects) {
        logger.info("[" + name + "]" + objects);
    }

}
