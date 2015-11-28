package com.mbase.monch.network;

import com.squareup.okhttp.Call;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.ResponseBody;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * Created by monch on 15/11/14.
 */
public abstract class ApiRequest {

    private static final int TIMEOUT_SECONDS = 30;
    private static final OkHttpClient client;

    public static final int METHOD_GET = 8001;
    public static final int METHOD_POST = 8002;
    public static final int METHOD_UPLOAD = 8003;
    public static final int METHOD_DOWNLOAD = 8004;

    static {
        client = new OkHttpClient();
        client.setConnectTimeout(TIMEOUT_SECONDS, TimeUnit.SECONDS);
        client.setReadTimeout(TIMEOUT_SECONDS, TimeUnit.SECONDS);
        client.setWriteTimeout(TIMEOUT_SECONDS, TimeUnit.SECONDS);
    }

    protected OkHttpClient getClient() {
        return client;
    }

    private Call call;

    private Object tag;
    private String url;
    private Params params;
    private BaseCallback callback;

    protected void setCall(Call call) {
        this.call = call;
    }

    protected void setTag(Object tag) {
        this.tag = tag;
    }

    protected void setUrl(String url) {
        this.url = url;
    }

    protected void setParams(Params params) {
        this.params = params;
    }

    protected void setCallback(BaseCallback callback) {
        this.callback = callback;
    }

    public Call getCall() {
        return call;
    }

    public Object getTag() {
        return tag;
    }

    public String getUrl() {
        return url;
    }

    public Params getParams() {
        return params;
    }

    public BaseCallback getCallback() {
        return callback;
    }

    public void cancel() {
        if (call != null) call.cancel();
    }

    public boolean isCanceled() {
        return call != null && call.isCanceled();
    }

    protected abstract ApiRequest request();
    protected abstract ResponseBody requestSync() throws IOException;

    protected Object getLogMessage() {
        StringBuilder sb = new StringBuilder();
        sb.append("网络请求：\n");
        sb.append("URL：").append(url).append("\n");
        sb.append("TAG：").append(tag).append("\n");
        sb.append("PARAMS：").append(params).append("\n");
        sb.append("完整请求路径：").append(params != null ? params.getAssembledUrl(url) : url);
        return sb.toString();
    }

}
