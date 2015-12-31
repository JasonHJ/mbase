package com.mbase.monch.network;

import android.os.Handler;
import android.os.Looper;

import com.mbase.monch.exception.LoginException;
import com.squareup.okhttp.Callback;

import org.json.JSONException;

import java.io.IOException;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeoutException;

/**
 * Created by monch on 15/11/16.
 */
public abstract class BaseCallback implements Callback {

    private static Handler mResponseHandler = new Handler(Looper.getMainLooper());
    protected static Executor mResponsePoster = new Executor() {
        @Override
        public void execute(Runnable command) {
            mResponseHandler.post(command);
        }
    };

    private ApiRequest apiRequest;
    /** 设置ApiRequest实例 **/
    void setApiRequest(ApiRequest request) {
        this.apiRequest = request;
    }
    /** 请求开始回调 **/
    protected void onStart(String url, int method, Params params) {}
    /** 请求子线程解析回调 **/
    protected abstract ApiResult onParseCallback(String result) throws JSONException, LoginException;
    /** 请求返回结果回调 **/
    protected abstract void onComplete(ApiResult result);
    /** 请求失败回调 **/
    protected abstract void onFailed(Failed error, Throwable ex);
    /** 登录异常回调 **/
    protected void onLoginError() {}
    /** 请求取消回调 **/
    protected void onCancel() {}

    /** 网络请求成功处理线程 **/
    protected class ResponseSuccessRunnable implements Runnable {

        private final ApiResult result;

        public ResponseSuccessRunnable(ApiResult result) {
            this.result = result;
        }

        @Override
        public void run() {
            if (apiRequest.isCanceled()) {
                onCancel();
            } else {
                onComplete(result);
            }
            result.clear();
        }
    }

    /** 网络请求失败处理线程 **/
    protected class ResponseFailedRunnable implements Runnable {

        private final Object error;
        private final Throwable ex;

        public ResponseFailedRunnable(Object error, Throwable ex) {
            this.error = error;
            this.ex = ex;
        }

        @Override
        public void run() {
            if (apiRequest.isCanceled()) {
                onCancel();
            } else {
                Failed F = Failed.OTHER;
                if (ex != null) {
                    if (ex instanceof IOException) {
                        F = Failed.NETWORK_ERROR;
                    } else if (ex instanceof TimeoutException) {
                        F = Failed.TIMEOUT_ERROR;
                    } else if (ex instanceof JSONException) {
                        F = Failed.PARSE_ERROR;
                    } else if (ex instanceof LoginException) {
                        onLoginError();
                        return;
                    }
                }
                onFailed(F, ex);
            }
        }
    }

}
