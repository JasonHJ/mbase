package com.mbase.monch.utils;

import android.os.Handler;
import android.os.Looper;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by monch on 15/11/11.
 */
public final class ThreadUtils {

    private static AtomicLong threadId = new AtomicLong(1L);

    private ThreadUtils() { }

    /**
     * 创建一个主线程的Handler
     *
     * @return
     */
    public static Handler createHandler() {
        return new Handler(Looper.getMainLooper());
    }

    /**
     * 创建一个主线程的Handler
     *
     * @param callback
     * @return
     */
    public static Handler createMainHandler(Handler.Callback callback) {
        return createHandler(Looper.getMainLooper(), callback);
    }

    /**
     * 创建一个Handler
     *
     * @param looper
     * @param callback
     * @return
     */
    public static Handler createHandler(Looper looper, Handler.Callback callback) {
        return new Handler(looper, callback);
    }

    /**
     * 创建一条线程
     *
     * @param runnable
     * @return
     */
    public static Thread createThread(Runnable runnable) {
        return createThread("Thread-Single-" + getThreadId(), runnable);
    }

    /**
     * 创建一条线程
     *
     * @param threadName
     * @return
     */
    public static Thread createThread(String threadName, Runnable runnable) {
        return new Thread(runnable, threadName);
    }

    /**
     * 创建一个线程工厂
     *
     * @return
     */
    public static ThreadFactory createThreadFactory() {
        return createThreadFactory("ThreadFactory-Single-" + getThreadId());
    }

    /**
     * 创建一个线程工厂
     *
     * @param threadName
     * @return
     */
    public static ThreadFactory createThreadFactory(final String threadName) {
        return new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                return createThread(threadName, r);
            }
        };
    }

    /**
     * 创建一个单条线程的线程池
     *
     * @return
     */
    public static ExecutorService createSingleExecutor() {
        return createSingleExecutor(createThreadFactory());
    }

    /**
     * 创建一个单条线程的线程池
     *
     * @param factory
     * @return
     */
    public static ExecutorService createSingleExecutor(ThreadFactory factory) {
        return Executors.newSingleThreadExecutor(factory);
    }

    /**
     * 获取当前线程
     *
     * @return
     */
    public static Thread currentThread() {
        return Thread.currentThread();
    }

    /**
     * 获取当前线程名
     *
     * @return
     */
    public static String currentThreadName() {
        Thread thread = currentThread();
        return thread.getName();
    }

    /**
     * 获取当前线程ID
     *
     * @return
     */
    public static long currentThreadId() {
        Thread thread = currentThread();
        return thread.getId();
    }

    /**
     * 获取线程ID，自增值
     *
     * @return
     */
    public static long getThreadId() {
        return threadId.incrementAndGet();
    }

}
