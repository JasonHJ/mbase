package com.mbase.monch.utils.encrypt;

import com.mbase.monch.BaseApp;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * Created by monch on 15/11/14.
 */
public class URLEncoderUtil {

    public static String encoder(String string) {
        return encoder(string, BaseApp.getCharset().name());
    }

    public static String encoder(String string, String charset) {
        try {
            return URLEncoder.encode(string, charset);
        } catch (UnsupportedEncodingException e) {
            return URLEncoder.encode(string);
        }
    }

}
