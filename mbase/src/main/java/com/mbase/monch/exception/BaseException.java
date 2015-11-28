package com.mbase.monch.exception;

import com.mbase.monch.utils.log.LogManager;
import com.mbase.monch.utils.log.Loggers;

/**
 * Created by monch on 15/11/14.
 */
public class BaseException extends Exception {
    private static Loggers logger = LogManager.getLogger(BaseException.class);

    public static void printError(Throwable ex) {
        logger.error(ex);
    }

    private Object message;

    public BaseException(){}

    public BaseException(Object message) {
        this.message = message;
    }

    public void print() {
        logger.error(message, this);
    }
}
