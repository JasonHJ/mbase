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
    protected void setApiRequest(ApiRequest request) {
        this.apiRequest = request;
    }

    protected void onStart(String url, int method, Params params) {}

    protected abstract ApiResult onParseCallback(String result) throws JSONException, LoginException;

    protected abstract void onComplete(ApiResult result);

    protected abstract void onFailed(Failed error, Throwable ex);

    protected void onLoginError() {}

    /** 网络请求成功处理线程 **/
    protected class ResponseSuccessRunnable implements Runnable {

        private final ApiResult result;

        public ResponseSuccessRunnable(ApiResult result) {
            this.result = result;
        }

        @Override
        public void run() {
            if (apiRequest.isCanceled()) return;
            onComplete(result);
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
            if (apiRequest.isCanceled()) return;
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
