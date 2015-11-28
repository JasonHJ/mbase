package com.mbase.monch.network;

import com.mbase.monch.common.Preconditions;
import com.squareup.okhttp.ResponseBody;

import java.io.IOException;

/**
 * Created by monch on 15/11/14.
 */
public class BaseRequest {

    public ApiRequest get(String url, BaseCallback callback) {
        return get(url, null, null, callback);
    }

    public ApiRequest get(String url, Params params, BaseCallback callback) {
        return get(url, params, null, callback);
    }

    public ApiRequest get(String url, Params params, Object tag, BaseCallback callback) {
        Preconditions.checkNotNull(url, "网络请求的URL地址不允许为空");
        Preconditions.checkNotNull(callback, "网络请求的回调函数不允许为空");
        ApiRequest request = new ApiGetRequest();
        request.setUrl(url);
        request.setParams(params);
        request.setTag(tag);
        request.setCallback(callback);
        return request.request();
    }

    public String getSync(String url) throws IOException {
        return getSync(url, null);
    }

    public String getSync(String url, Params params) throws IOException {
        Preconditions.checkNotNull(url, "网络请求的URL地址不允许为空");
        ApiRequest request = new ApiGetRequest();
        request.setUrl(url);
        request.setParams(params);
        ResponseBody body = request.requestSync();
        return body != null ? body.string() : null;
    }

    public ApiRequest post(String url, BaseCallback callback) {
        return post(url, null, null, callback);
    }

    public ApiRequest post(String url, Params params, BaseCallback callback) {
        return post(url, params, null, callback);
    }

    public ApiRequest post(String url, Params params, Object tag, BaseCallback callback) {
        Preconditions.checkNotNull(url, "网络请求的URL地址不允许为空");
        Preconditions.checkNotNull(callback, "网络请求的回调函数不允许为空");
        ApiRequest request = new ApiPostRequest();
        request.setUrl(url);
        request.setParams(params);
        request.setTag(tag);
        request.setCallback(callback);
        return request.request();
    }

    public String postSync(String url) throws IOException {
        return postSync(url, null);
    }

    public String postSync(String url, Params params) throws IOException {
        Preconditions.checkNotNull(url, "网络请求的URL地址不允许为空");
        ApiRequest request = new ApiPostRequest();
        request.setUrl(url);
        request.setParams(params);
        ResponseBody body = request.requestSync();
        return body != null ? body.string() : null;
    }

    public ApiRequest download(String url, BaseCallback callback) {
        return download(url, null, null, callback);
    }

    public ApiRequest download(String url, Params params, BaseCallback callback) {
        return download(url, params, null, callback);
    }

    public ApiRequest download(String url, Params params, Object tag, BaseCallback callback) {
        Preconditions.checkNotNull(url, "网络下载的URL地址不允许为空");
        Preconditions.checkNotNull(callback, "网络下载的回调函数不允许为空");
        ApiRequest request = new ApiDownloadRequest();
        request.setUrl(url);
        request.setParams(params);
        request.setTag(tag);
        request.setCallback(callback);
        return request.request();
    }

    public byte[] downloadSync(String url) throws IOException {
        return downloadSync(url, null);
    }

    public byte[] downloadSync(String url, Params params) throws IOException {
        Preconditions.checkNotNull(url, "网络请求的URL地址不允许为空");
        ApiRequest request = new ApiDownloadRequest();
        request.setUrl(url);
        request.setParams(params);
        ResponseBody body = request.requestSync();
        return body != null ? body.bytes() : null;
    }

}
