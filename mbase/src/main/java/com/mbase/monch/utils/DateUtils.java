package com.mbase.monch.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by monch on 15/11/11.
 */
public final class DateUtils {
    private DateUtils(){}
    private static final String FORMAT_DATE_TIME = "yyyy-MM-dd HH:mm:ss";
    private static final String FORMAT_DATE = "yyyy-MM-dd";
    private static final String FORMAT_TIME = "HH:mm:ss";

    /**
     * 获取当前时间毫秒数
     * @return
     */
    public static long currentTimeMillis() {
        return System.currentTimeMillis();
    }

    /**
     * 获取当前日期时间
     * @return
     */
    public static String currentTime() {
        return currentTime(FORMAT_DATE_TIME);
    }

    /**
     * 获取当前日期时间
     * @param format
     * @return
     */
    public static String currentTime(String format) {
        return getDateTime(currentTimeMillis(), format);
    }

    /**
     * 获取日期
     * @param millis
     * @return
     */
    public static String getDate(long millis) {
        return getDateTime(millis, FORMAT_DATE);
    }

    /**
     * 获取时间
     * @param millis
     * @return
     */
    public static String getTime(long millis) {
        return getDateTime(millis, FORMAT_TIME);
    }

    /**
     * 获取日期时间
     * @param millis
     * @return
     */
    public static String getDateTime(long millis) {
        return getDateTime(millis, FORMAT_DATE_TIME);
    }

    /**
     * 获取日期时间
     * @param millis
     * @param format
     * @return
     */
    public static String getDateTime(long millis, String format) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format, Locale.getDefault());
        return simpleDateFormat.format(new Date(millis));
    }

    /**
     * 获取当前年份
     * @return
     */
    public static int currentYear() {
        return getYear(currentTimeMillis());
    }

    /**
     * 获取年份
     * @param millis
     * @return
     */
    public static int getYear(long millis) {
        Calendar c = Calendar.getInstance(Locale.getDefault());
        c.setTimeInMillis(millis);
        return c.get(Calendar.YEAR);
    }

    /**
     * 获取当前月份
     * @return
     */
    public static int currentMonth() {
        return getMonth(currentTimeMillis());
    }

    /**
     * 获取月份
     * @param millis
     * @return
     */
    public static int getMonth(long millis) {
        Calendar c = Calendar.getInstance(Locale.getDefault());
        c.setTimeInMillis(millis);
        return c.get(Calendar.MONTH) + 1;
    }

    /**
     * 获取当前日期
     * @return
     */
    public static int currentDay() {
        return getDay(currentTimeMillis());
    }

    /**
     * 获取日期
     * @param millis
     * @return
     */
    public static int getDay(long millis) {
        Calendar c = Calendar.getInstance(Locale.getDefault());
        c.setTimeInMillis(millis);
        return c.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * 传入的时间是否为今天
     * @param millis
     * @return
     */
    public static boolean isToday(long millis) {
        return currentYear() == getYear(millis)
                && currentMonth() == getMonth(millis)
                && currentDay() == getDay(millis);
    }

}
