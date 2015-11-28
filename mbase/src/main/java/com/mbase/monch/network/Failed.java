package com.mbase.monch.network;

/**
 * Created by monch on 15/11/14.
 */
public enum Failed {
    TIMEOUT_ERROR("网络请求超时"),
    NETWORK_ERROR("网络请求失败"),
    PARSE_ERROR("数据解析失败"),
    DOWNLOAD_ERROR("文件下载失败"),
    LOGIN_ERROR("登录帐号错误"),
    OTHER("网络请求未知错误");
    private String error;
    Failed(String error) { this.error = error; }
    public String getError() { return error; }
}
