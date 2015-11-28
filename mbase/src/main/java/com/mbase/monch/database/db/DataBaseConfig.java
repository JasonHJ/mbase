package com.mbase.monch.database.db;

import android.content.Context;

import com.mbase.monch.database.db.assit.Checker;
import com.mbase.monch.database.db.assit.SQLiteHelper;

/**
 * 数据操作配置
 *
 * @author MaTianyu
 *         2013-6-2下午4:36:16
 */
public class DataBaseConfig {
    public static final String DEFAULT_DB_NAME = "liteorm.db";
    public static final int DEFAULT_DB_VERSION = 1;

    public Context context;
    public String dbName = DEFAULT_DB_NAME;
    public int dbVersion = DEFAULT_DB_VERSION;
    public SQLiteHelper.OnDBUpgradeListener onUpdateListener;

    public DataBaseConfig(Context context) {
        this(context, DEFAULT_DB_NAME);
    }

    public DataBaseConfig(Context context, String dbName) {
        this(context, dbName, DEFAULT_DB_VERSION, null);
    }

    public DataBaseConfig(Context context, String dbName, int dbVersion, SQLiteHelper.OnDBUpgradeListener onUpdateListener) {
        this.context = context.getApplicationContext();
        if (!Checker.isEmpty(dbName))
            this.dbName = dbName;
        if (dbVersion > 1)
            this.dbVersion = dbVersion;
        this.onUpdateListener = onUpdateListener;
    }

    @Override
    public String toString() {
        return "DataBaseConfig [mContext=" + context + ", mDbName=" + dbName + ", mDbVersion="
               + dbVersion + ", mOnUpdateListener=" + onUpdateListener + "]";
    }

}
