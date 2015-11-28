package com.mbase.monch.network;

import com.mbase.monch.BaseApp;
import com.mbase.monch.exception.LoginException;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.squareup.okhttp.ResponseBody;

import org.json.JSONException;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by monch on 15/11/14.
 */
public abstract class ApiDownloadCallback extends BaseCallback {

    private final String fileSavePath;
    private final String fileSaveName;

    public ApiDownloadCallback(String name) {
        this.fileSavePath = BaseApp.getCacheDir().getAbsolutePath();
        this.fileSaveName = name;
    }

    public ApiDownloadCallback(String path, String name) {
        this.fileSavePath = path;
        this.fileSaveName = name;
    }

    @Override
    public void onFailure(Request request, IOException e) {
        mResponsePoster.execute(
                new ResponseFailedRunnable("Network error：Unexpected code " + request, e));
    }

    @Override
    public void onResponse(Response response) throws IOException {
        if (response != null && response.isSuccessful()) {
            ResponseBody body = response.body();
            try {
                byte[] bytes = body != null ? body.bytes() : null;
                File file = doParse(bytes);
                if (file == null || !file.exists())
                    throw new IllegalArgumentException("Download result is empty");
                ApiResult apiResult = new ApiResult();
                apiResult.add(0, file.getAbsolutePath());
                mResponsePoster.execute(new ResponseSuccessRunnable(apiResult));
            } catch (Exception e) {
                mResponsePoster.execute(
                        new ResponseFailedRunnable("Download result is empty", e));
            } finally {
                if (body != null) body.close();
            }
        } else {
            throw new IOException("Network error：Unexpected code " + response);
        }
    }

    @Override
    protected ApiResult onParseCallback(String result) throws JSONException, LoginException {
        return null;
    }

    private File doParse(byte[] data) throws IOException {
        if (data == null) return null;
        File file = new File(fileSavePath, fileSaveName);
        if (!file.getParentFile().exists() && file.getParentFile().isDirectory()) {//判断文件目录是否存在
            file.getParentFile().mkdirs();
        }
        FileOutputStream fos = new FileOutputStream(file);
        BufferedOutputStream bos = new BufferedOutputStream(fos);
        try {
            bos.write(data);
        } finally {
            bos.close();
            fos.close();
        }
        return file;
    }

}
