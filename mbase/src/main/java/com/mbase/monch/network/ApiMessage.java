package com.mbase.monch.network;

/**
 * Created by monch on 15/11/14.
 */
public class ApiMessage {

    // 返回码
    public int code;
    // 返回消息
    public String message;

    @Override
    public String toString() {
        return "ApiMessage{" +
                "code=" + code +
                ", message='" + message + '\'' +
                '}';
    }
}
