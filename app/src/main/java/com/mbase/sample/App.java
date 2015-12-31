package com.mbase.sample;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;

import com.facebook.imagepipeline.core.ImagePipelineConfig;
import com.mbase.monch.BaseConfig;
import com.mbase.monch.MBase;
import com.mbase.monch.database.db.assit.SQLiteHelper;
import com.mbase.monch.utils.log.LogManager;
import com.mbase.monch.utils.log.Loggers;

import java.nio.charset.Charset;

/**
 * Created by monch on 15/11/17.
 */
public class App extends Application {

    private static final boolean DEBUG = true;
    private static final String DB_NAME = "com.mbase.sample.db";
    private static final int DB_VERSION = 1;

    /** 本地数据库更新回调 **/
    private static SQLiteHelper.OnDBUpgradeListener onDBUpgradeListener = new SQLiteHelper.OnDBUpgradeListener() {
        @Override
        public void onUpdate(SQLiteDatabase db, int oldVersion, int newVersion) {
            Loggers logger = LogManager.getLogger(App.class);
            logger.info("进入本地数据库升级回调，老版本号码：" + oldVersion + "，新版本号码：" + newVersion);
        }
    };

    @Override
    public void onCreate() {
        super.onCreate();
        BaseConfig config = new BaseConfig.Builder(this)
                .setDebug(true) // DEBUG模式
                .setDbName(DB_NAME) // 本地数据库名称
                .setDbVersion(DB_VERSION)   // 本地数据库版本
                .setCharset(Charset.forName("UTF-8"))   // 默认编码方式
                .setOnDBUpdateListener(onDBUpgradeListener) // 本地数据库升级回调
                .setFrescoConfig(ImagePipelineConfig.newBuilder(this).build()) // Fresco的配置
                .builder();
        MBase.initialize(this, config);
    }

}
