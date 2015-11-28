package com.mbase.monch.utils.encrypt;

import com.mbase.monch.BaseApp;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

/**
 * Created by monch on 15/11/14.
 */
public class URLDecoderUtil {

    public static String decoder(String string) {
        return decoder(string, BaseApp.getCharset().name());
    }

    public static String decoder(String string, String charset) {
        try {
            return URLDecoder.decode(string, charset);
        } catch (UnsupportedEncodingException e) {
            return URLDecoder.decode(string);
        }
    }

}
