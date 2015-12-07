package com.mbase.sample.activitys;

import android.content.Intent;

import com.mbase.sample.BaseActivity;

/**
 * Created by monch on 15/11/17.
 */
public class MainActivity extends BaseActivity {

    private String[] buttons = new String[]{
            "网络请求测试",
            "本地数据库测试",
            "图片加载测试",
            "其它测试"
    };

    @Override
    protected String[] getButtonTexts() {
        return buttons;
    }

    @Override
    protected void click(int id) {
        Intent intent;
        switch (id) {
            case 0:
                intent = new Intent(this, NetworkActivity.class);
                intent.putExtra("titleText", buttons[id]);
                startActivity(intent);
                break;
            case 1:
                // todo 本地数据库测试
                intent = new Intent(this, SqliteActivity.class);
                startActivity(intent);
                break;
            case 2:
                intent = new Intent(this, ImageActivity.class);
                startActivity(intent);
                break;
            case 3:
                intent = new Intent(this, OtherActivity.class);
                intent.putExtra("titleText", buttons[id]);
                startActivity(intent);
                break;
        }
    }
}
