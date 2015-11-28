package com.mbase.monch.utils.log;

import android.util.Log;

import com.mbase.monch.BaseApp;
import com.mbase.monch.common.Preconditions;
import com.mbase.monch.utils.DateUtils;
import com.mbase.monch.utils.ThreadUtils;

/**
 * Created by monch on 15/11/11.
 */
public final class Loggers {

    private static final boolean debug = BaseApp.debug();

    private final String tag;

    protected Loggers() {
        tag = BaseApp.getPackageName();
    }

    protected Loggers(String tag) {
        this.tag = tag;
    }

    protected Loggers(Class<?> clazz) {
        this.tag = clazz.getName();
    }

    public void debug(Object messageObject) {
        debug(messageObject, null);
    }

    public void debug(Object messageObject, Throwable ex) {
        if (debug) Log.d(tag, makeMessage(messageObject), ex);
    }

    public void info(Object messageObject) {
        info(messageObject, null);
    }

    public void info(Object messageObject, Throwable ex) {
        if (debug) Log.i(tag, makeMessage(messageObject), ex);
    }

    public void warn(Object messageObject) {
        warn(messageObject, null);
    }

    public void warn(Object messageObject, Throwable ex) {
        if (debug) Log.w(tag, makeMessage(messageObject), ex);
    }

    public void error(Object messageObject) {
        error(messageObject, null);
    }

    public void error(Object messageObject, Throwable ex) {
        Log.e(tag, makeMessage(messageObject), ex);
    }

    private String makeMessage(Object messageObject) {
        if (Preconditions.isNotNull(messageObject)) {
            return makeLogInfo(tag, messageObject);
        }
        return null;
    }

    private String makeLogInfo(String tag, Object messageObject) {
        String time = DateUtils.currentTime("yyyy-MM-dd HH:mm:ss.SSS");
        long threadId = ThreadUtils.currentThreadId();
        String threadName = ThreadUtils.currentThreadName();
        StringBuffer sb = new StringBuffer();
        sb.append("tag=").append(tag).append("\n");
        sb.append("[")
                .append("time=").append(time)
                .append(",threadId=").append(threadId)
                .append(",threadName=").append(threadName)
                .append("]").append("\n");
        sb.append("info[")
                .append(String.valueOf(messageObject))
                .append("]");
        return sb.toString();
    }

}
