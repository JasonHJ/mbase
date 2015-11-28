package com.mbase.monch.utils.log;

/**
 * Created by monch on 15/11/12.
 */
public final class LogManager {

    private LogManager(){}

    public static Loggers getLogger() {
        return new Loggers();
    }

    public static Loggers getLogger(String tag) {
        return new Loggers(tag);
    }

    public static Loggers getLogger(Class<?> clazz) {
        return new Loggers(clazz);
    }

}
