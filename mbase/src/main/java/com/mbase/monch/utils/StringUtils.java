package com.mbase.monch.utils;

import android.net.Uri;

import com.mbase.monch.BaseApp;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.text.DecimalFormat;

/**
 * Created by monch on 15/11/11.
 */
public final class StringUtils {

    private StringUtils() {
    }

    public static boolean isEmpty(CharSequence cs) {
        return cs == null || cs.length() == 0;
    }

    public static boolean isNotEmpty(CharSequence cs) {
        return !isEmpty(cs);
    }

    public static String trim(String str) {
        return isEmpty(str) ? str : str.trim();
    }

    public static boolean equals(String str1, String str2) {
        if (str1 == null && str2 == null) return true;
        if (isNotEmpty(str1) && str1.equals(str2)) return true;
        if (isNotEmpty(str2) && str2.equals(str1)) return true;
        return false;
    }

    public static int indexOf(String str, String searchChar) {
        return indexOf(str, searchChar, 0);
    }

    public static int indexOf(String str, String searchChar, int startPos) {
        if (isEmpty(str) || isEmpty(searchChar)) return -1;
        return str.indexOf(searchChar, startPos);
    }

    public static int lastIndexOf(String str, String searchChar) {
        if (isEmpty(str) || isEmpty(searchChar)) return -1;
        return str.lastIndexOf(searchChar);
    }

    public static int lastIndexOf(String str, String searchChar, int startPos) {
        if (isEmpty(str) || isEmpty(searchChar)) return -1;
        return str.lastIndexOf(searchChar, startPos);
    }

    public static boolean contains(String str, String searchChar) {
        return isNotEmpty(str) && isNotEmpty(searchChar) && indexOf(str, searchChar) >= 0;
    }

    public static String substring(String str, int start, int end) {
        if (str == null) return null;
        if (start < 0 || start >= str.length()) start = 0;
        if (end < 0 || end >= str.length()) end = str.length();
        return start > end ? "" : str.substring(start, end);
    }

    public static String replace(String str, String searchChar, String replaceChar) {
        if (isEmpty(str) || isEmpty(searchChar)) return str;
        return str.replace(searchChar, replaceChar);
    }

    public static String upperCase(String str) {
        return str == null ? null : str.toUpperCase();
    }

    public static String lowerCase(String str) {
        return str == null ? null : str.toLowerCase();
    }

    public static String toString(byte[] bytes) {
        return new String(bytes, BaseApp.getCharset());
    }

    public static String toStringUTF8(byte[] bytes) {
        return new String(bytes, Charset.forName("UTF-8"));
    }

    public static String toString(byte[] bytes, String charsetName) {
        if (bytes == null) return null;
        try {
            return charsetName != null ? new String(bytes, charsetName) : new String(bytes, BaseApp.getCharset());
        } catch (UnsupportedEncodingException e) {
            return new String(bytes, Charset.defaultCharset());
        }
    }

    public static boolean isNumber(String str) {
        if (isEmpty(str)) return false;
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static double getNumber(String str) {
        if (isNumber(str)) {
            return Double.parseDouble(str);
        }
        return 0d;
    }

    public static int getInt(String str) {
        if (isNumber(str)) {
            return Integer.parseInt(str);
        }
        return 0;
    }

    public static long getLong(String str) {
        if (isNumber(str)) {
            return Long.parseLong(str);
        }
        return 0l;
    }

    public static String getDouble(double number, int index) {
        String str = "##0";
        if (index > 0) {
            str += ".";
            for (int i = 0; i < index; i++) {
                str += "0";
            }
        }
        DecimalFormat fnum = new DecimalFormat(str);
        return fnum.format(number);
    }

    public static String getFloat(float number, int index) {
        return getDouble(number, index);
    }

    public static Uri getUri(String str) {
        if (isNotEmpty(str)) {
            return Uri.parse(str);
        }
        return null;
    }

    public static File getFile(String path) {
        if (isNotEmpty(path)) {
            File file = new File(path);
            if (file != null && file.exists()) return file;
        }
        return null;
    }

    public static boolean isFile(String path) {
        return getFile(path) != null;
    }

}
