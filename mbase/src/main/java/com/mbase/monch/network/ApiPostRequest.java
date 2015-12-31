package com.mbase.monch.network;

import com.mbase.monch.BaseApp;
import com.mbase.monch.utils.encrypt.URLEncoderUtil;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.Headers;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.MultipartBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;
import com.squareup.okhttp.ResponseBody;

import java.io.File;
import java.io.IOException;
import java.net.FileNameMap;
import java.net.URLConnection;
import java.util.Map;

/**
 * Created by monch on 15/11/14.
 */
class ApiPostRequest extends ApiRequest {

    @Override
    protected ApiRequest request() {
        OkHttpClient client = getClient();
        String url = getUrl();
        Params params = getParams();
        Object tag = getTag();
        BaseCallback callback = getCallback();
        callback.setApiRequest(this);
        callback.onStart(url, METHOD_POST, params);
        RequestBody body = fromBody(params);
        Request.Builder builder = new Request.Builder();
        builder.url(url);
        builder.tag(tag);
        builder.post(body);
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
        RequestBody body = fromBody(params);
        Request.Builder builder = new Request.Builder();
        builder.url(url);
        builder.post(body);
        makeHeader(builder, params);
        Request request = builder.build();
        Call call = client.newCall(request);
        Response response = call.execute();
        if (response == null || !response.isSuccessful())
            throw new IOException("Network error：Unexpected code " + response);
        return response.body();
    }

    /** 默认参数 **/
    private static final String DEFAULT_CONTENT = "Content-Disposition";

    /** 拼装字符串参数和文件参数 **/
    private RequestBody fromBody(Params params) {
        MultipartBuilder builder = new MultipartBuilder().type(MultipartBuilder.FORM);
        if (params == null) return builder.build();
        if (params.hasParams()) {
            Map<String, String> P = params.getParams();
            for (Map.Entry<String, String> entry : P.entrySet()) {
                String key = URLEncoderUtil.encoder(entry.getKey());
                String value = URLEncoderUtil.encoder(entry.getValue());
                builder.addPart(Headers.of(DEFAULT_CONTENT, getParamValue(key)),
                        RequestBody.create(MediaType.parse(BaseApp.getCharset().name()), value));
            }
        }
        if (params.hasFileParams()) {
            Map<String, File> F = params.getFileParams();
            for (Map.Entry<String, File> entry : F.entrySet()) {
                String name = URLEncoderUtil.encoder(entry.getKey());
                File file = entry.getValue();
                String fileName = file.getName();
                builder.addPart(Headers.of(DEFAULT_CONTENT, getFileValue(name, fileName)),
                        RequestBody.create(MediaType.parse(guessMimeType(fileName)), file));
            }
        }
        return builder.build();
    }

    private static String guessMimeType(String path) {
        FileNameMap fileNameMap = URLConnection.getFileNameMap();
        String contentTypeFor = fileNameMap.getContentTypeFor(path);
        if (contentTypeFor == null) {
            contentTypeFor = "application/octet-stream";
        }
        return contentTypeFor;
    }

    private static String getParamValue(String name) {
        return "form-data; name=\"" + name + "\"";
    }

    private static String getFileValue(String name, String fileName) {
        return "form-data; name=\"" + name + "\"; filename=\"" + fileName + "\"";
    }

}
