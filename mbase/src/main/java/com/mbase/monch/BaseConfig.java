package com.mbase.monch;

import android.content.Context;
import android.os.Environment;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.core.ImagePipelineConfig;
import com.mbase.monch.database.LiteOrm;
import com.mbase.monch.database.db.DataBase;
import com.mbase.monch.database.db.DataBaseConfig;
import com.mbase.monch.database.db.assit.SQLiteHelper;

import java.io.File;
import java.nio.charset.Charset;

/**
 * Created by monch on 15/11/11.
 */
public class BaseConfig {

    // 这是一个设置DEBUG模式的默认参数
    private static final boolean DEFAULT_DEBUG = true;
    // 这是一个设置本地数据库版本号的默认参数
    private static final int DEFAULT_DB_VERSION = 1;
    // 这是一个设置本地数据库名称的默认参数
    private static final String DEFAULT_DB_NAME = "com.mbase.monch.db";
    // 这是一个设置本地编码的默认参数
    private static final Charset DEFAULT_CHARSET = Charset.forName("UTF-8");

    private final boolean debug;
    private final String packageName;
    private final File cacheDir;
    private final DataBase db;
    private final Charset charset;

    private BaseConfig(Context context, Builder builder) {
        this.debug = builder.debug;
        this.packageName = context.getPackageName();
        this.cacheDir =
                builder.cacheDir == null ?
                        getDefaultCacheDir(context) :
                        builder.cacheDir;
        if (!this.cacheDir.exists()) this.cacheDir.mkdirs();
        DataBaseConfig dbConfig = new DataBaseConfig(context, builder.dbName, builder.dbVersion, builder.onDBUpgradeListener);
        db = LiteOrm.newSingleInstance(dbConfig);
        this.charset = builder.charset;
        if (builder.frescoImagePipelineConfig == null) Fresco.initialize(context);
        else Fresco.initialize(context, builder.frescoImagePipelineConfig);
    }

    // 获取默认的缓存文件路径
    private static File getDefaultCacheDir(Context context) {
        File result = null;
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())
                || !Environment.isExternalStorageRemovable()) {
            result = context.getExternalCacheDir();
        }
        if (result == null) {
            result = context.getCacheDir();
        }
        return result;
    }

    protected boolean isDebug() {
        return debug;
    }

    protected String getPackageName() {
        return packageName;
    }

    protected File getCacheDir() {
        return cacheDir;
    }

    protected DataBase getDatabase() {
        return db;
    }

    protected Charset getCharset() {
        return charset;
    }

    public static class Builder {
        private Context context;
        private boolean debug = DEFAULT_DEBUG;
        private File cacheDir;
        private int dbVersion = DEFAULT_DB_VERSION;
        private String dbName = DEFAULT_DB_NAME;
        private SQLiteHelper.OnDBUpgradeListener onDBUpgradeListener;
        private Charset charset = DEFAULT_CHARSET;
        private ImagePipelineConfig frescoImagePipelineConfig;

        public Builder(Context context) {
            this.context = context;
        }

        public Builder setDebug(boolean debug) {
            this.debug = debug;
            return this;
        }

        public Builder setCacheDir(File cacheDir) {
            this.cacheDir = cacheDir;
            return this;
        }

        public Builder setDbVersion(int dbVersion) {
            this.dbVersion = dbVersion;
            return this;
        }

        public Builder setDbName(String dbName) {
            this.dbName = dbName;
            return this;
        }

        public Builder setOnDBUpdateListener(SQLiteHelper.OnDBUpgradeListener listener) {
            this.onDBUpgradeListener = listener;
            return this;
        }

        public Builder setCharset(Charset charset) {
            this.charset = charset;
            return this;
        }

        public Builder setFrescoConfig(ImagePipelineConfig config) {
            this.frescoImagePipelineConfig = config;
            return this;
        }

        public BaseConfig builder() {
            return new BaseConfig(context, this);
        }
    }

}
