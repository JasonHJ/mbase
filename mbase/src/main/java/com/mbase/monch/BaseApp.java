package com.mbase.monch;

import android.content.Context;

import com.mbase.monch.database.db.DataBase;

import java.io.File;
import java.nio.charset.Charset;

/**
 * Created by monch on 15/11/16.
 */
public class BaseApp {

    private static Context mContext;
    private static BaseConfig mConfig;

    private BaseApp(){}

    protected static void init(Context context, BaseConfig config) {
        mContext = context;
        mConfig = config;
    }

    public static Context getContext() {
        return mContext;
    }

    public static boolean debug() {
        return mConfig.isDebug();
    }

    public static String getPackageName() {
        return mConfig.getPackageName();
    }

    public static File getCacheDir() {
        return mConfig.getCacheDir();
    }

    public static DataBase getDatabase() {
        return mConfig.getDatabase();
    }

    public static Charset getCharset() {
        return mConfig.getCharset();
    }

}
