package com.mbase.monch.network;

import com.squareup.okhttp.Call;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.squareup.okhttp.ResponseBody;

import java.io.IOException;

/**
 * Created by monch on 15/11/16.
 */
class ApiDownloadRequest extends ApiRequest {

    @Override
    protected ApiRequest request() {
        OkHttpClient client = getClient();
        String url = getUrl();
        Params params = getParams();
        url = params != null ? params.getAssembledUrl(url) : url;
        Object tag = getTag();
        BaseCallback callback = getCallback();
        callback.setApiRequest(this);
        callback.onStart(url, METHOD_DOWNLOAD, params);
        Request.Builder builder = new Request.Builder();
        builder.url(url);
        builder.tag(tag);
        builder.get();
        makeHeader(builder, params);
        Request request = builder.build();
        Call call = client.newCall(request);
        this.setCall(call);
        call.enqueue(callback);
        if (params != null) params.clear();
        return this;
    }

    @Override
    protected ResponseBody requestSync() throws IOException {
        OkHttpClient client = getClient();
        String url = getUrl();
        Params params = getParams();
        url = params != null ? params.getAssembledUrl(url) : url;
        Request.Builder builder = new Request.Builder();
        builder.url(url);
        builder.get();
        makeHeader(builder, params);
        Request request = builder.build();
        Call call = client.newCall(request);
        Response response = call.execute();
        if (response == null || !response.isSuccessful())
            throw new IOException("Network error：Unexpected code " + response);
        return response.body();
    }

}
