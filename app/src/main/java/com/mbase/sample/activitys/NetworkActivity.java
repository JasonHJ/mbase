package com.mbase.sample.activitys;

import android.os.SystemClock;

import com.mbase.monch.BaseApp;
import com.mbase.monch.exception.LoginException;
import com.mbase.monch.network.ApiDownloadCallback;
import com.mbase.monch.network.ApiMessage;
import com.mbase.monch.network.ApiRequest;
import com.mbase.monch.network.ApiRequestCallback;
import com.mbase.monch.network.ApiResult;
import com.mbase.monch.network.BaseRequest;
import com.mbase.monch.network.Failed;
import com.mbase.monch.network.Params;
import com.mbase.monch.utils.DateUtils;
import com.mbase.monch.utils.ThreadUtils;
import com.mbase.monch.utils.log.LogManager;
import com.mbase.monch.utils.log.Loggers;
import com.mbase.monch.utils.toast.T;
import com.mbase.sample.BaseActivity;

import org.json.JSONException;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.concurrent.ExecutorService;

/**
 * Created by monch on 15/11/27.
 */
public class NetworkActivity extends BaseActivity {

    private Loggers logger = LogManager.getLogger(NetworkActivity.class);

    private static final String HTTP = "http://www.baidu.com";
    private static final String HTTP_PHOTO = "https://ss0.bdstatic.com/5aV1bjqh_Q23odCf/static/superman/img/logo/bd_logo1_31bdc765.png";
    private BaseRequest request = new BaseRequest();
    private ExecutorService executor = ThreadUtils.createSingleExecutor();

    private String[] buttons = new String[]{
            "异步：GET请求",
            "异步：POST请求",
            "异步：DOWNLOAD请求",
            "异步：启动/停止请求",
            "同步：GET请求",
            "同步：POST请求",
            "同步：DOWNLOAD请求"
    };

    @Override
    protected String[] getButtonTexts() {
        return buttons;
    }

    @Override
    protected void click(int id) {
        switch (id) {
            case 0:
                Params getParams = new Params();
                getParams.put("key1", "value1");
                getParams.put("key2", "参数2");
                // 此处可以直接清加需要的请求头
                getParams.putHeader("User-Agent", "MBaseSample/1.0");
                request.get(HTTP, getParams, new ApiRequestCallback() {
                    @Override
                    protected void onStart(String url, int method, Params params) {
                        super.onStart(url, method, params);
                        logger.info("异步：GET请求，请求开始"
                                + "\nURL:" + url
                                + "\nmethod:" + method
                                + "\nparams:" + params
                                + "\n完整URL："
                                + (params != null ? params.getAssembledUrl(url) : url));
                    }

                    @Override
                    protected ApiResult onParseCallback(String result) throws JSONException, LoginException {
                        logger.info("异步：GET请求，子线程结果回调\n" + result);
                        ApiResult apiResult = new ApiResult();
                        apiResult.message = new ApiMessage();
                        apiResult.message.code = 0;
                        apiResult.add(0, result);
                        return apiResult;
                    }

                    @Override
                    protected void onComplete(ApiResult result) {
                        logger.info("异步：GET请求，主线程请求完成回调\n" + result);
                    }

                    @Override
                    protected void onFailed(Failed error, Throwable ex) {
                        logger.error("异步：GET请求，主线程请求失败回调："
                                + (error != null ? error.getError() : null), ex);
                    }
                });
                break;
            case 1:
                Params postParams = new Params();
                postParams.put("key1", "value1");
                postParams.put("key2", "参数2");
                // 此处可以直接清加需要的请求头
                postParams.putHeader("User-Agent", "MBaseSample/1.0");
                // 此处可以直接添加需要上传的文件参数
                // postParams.put("fileKey", new File(BaseApp.getCacheDir(), "test.jpg"));
                request.post(HTTP, postParams, new ApiRequestCallback() {
                    @Override
                    protected ApiResult onParseCallback(String result) throws JSONException, LoginException {
                        // 此处为子线程，主要用于解析返回结果，请不要在此处操作任何UI
                        // 此处的设计思路为：自主解析有更高的可扩展性，相较于使用GSON等解析框架
                        // 如需要使用GSON，可自主复写ApiRequestCallback实现
                        logger.info("异步：POST请求，子线程结果回调\n" + result);
                        ApiResult apiResult = new ApiResult();
                        apiResult.message = new ApiMessage();
                        apiResult.message.code = 0;
                        apiResult.add(0, result);
                        return apiResult;
                    }

                    @Override
                    protected void onComplete(ApiResult result) {
                        logger.info("异步：POST请求，主线程请求完成回调\n" + result);
                    }

                    @Override
                    protected void onFailed(Failed error, Throwable ex) {
                        logger.error("异步：POST请求，主线程请求失败回调："
                                + (error != null ? error.getError() : null), ex);
                    }
                });
                break;
            case 2:
                request.download(HTTP_PHOTO, new ApiDownloadCallback("bd.png") {
                    @Override
                    protected void onComplete(ApiResult result) {
                        logger.info("异步：DOWNLOAD请求，主线程请求完成回调\n" + result);
                    }

                    @Override
                    protected void onFailed(Failed error, Throwable ex) {
                        logger.error("异步：DOWNLOAD请求，主线程请求失败回调："
                                + (error != null ? error.getError() : null), ex);
                    }
                });
                break;
            case 3:
                startAndStopRequestClass.executor();
                break;
            case 4:
                if (!executor.isShutdown())
                    executor.submit(new SyncGetRequestRunnable());
                break;
            case 5:
                if (!executor.isShutdown())
                    executor.submit(new SyncPostRequestRunnable());
                break;
            case 6:
                if (!executor.isShutdown())
                    executor.submit(new SyncDownloadRequestRunnable());
                break;
            default:
                logger.error("异常");
                break;
        }
    }

    /**
     * 以下是异步：启动/停止异步请求
     **/
    private StartAndStopRequestClass startAndStopRequestClass = new StartAndStopRequestClass();

    class StartAndStopRequestClass {
        boolean isStart = false;
        ApiRequest apiRequest;

        void executor() {
            if (isStart && apiRequest != null) {
                T.ss("现在停止请求");
                apiRequest.cancel();
                isStart = false;
            } else {
                T.ss("现在开始请求");
                apiRequest = request.get(HTTP, new ApiRequestCallback() {
                    @Override
                    protected ApiResult onParseCallback(String result) throws JSONException, LoginException {
                        logger.info("请求已经回到解析线程");
                        logger.info("即将进入眨眼15秒");
                        SystemClock.sleep(15000);
                        logger.info("睡眠完成");
                        return new ApiResult();
                    }

                    @Override
                    protected void onComplete(ApiResult result) {
                        logger.info("请求已经到到主线程");
                    }

                    @Override
                    protected void onFailed(Failed error, Throwable ex) {
                        logger.error("请求异常" + error.getError(), ex);
                    }
                });
                isStart = true;
            }
        }
    }

    /**
     * 以下是同步：网络请求
     **/

    class SyncGetRequestRunnable implements Runnable {

        @Override
        public void run() {
            try {
                Params params = new Params();
                params.put("key1", "value1");
                params.put("key2", "参数2");
                String result = request.getSync(HTTP, params);
                logger.info("同步：GET请求：成功结果\n" + result);
            } catch (IOException e) {
                logger.error("同步：GET请求：失败结果", e);
            }
        }
    }

    class SyncPostRequestRunnable implements Runnable {

        @Override
        public void run() {
            try {
                Params params = new Params();
                params.put("key1", "value1");
                params.put("key2", "参数2");
                String result = request.postSync(HTTP, params);
                logger.info("同步：POST请求：成功结果\n" + result);
            } catch (IOException e) {
                logger.error("同步：POST请求：失败结果", e);
            }
        }
    }

    class SyncDownloadRequestRunnable implements Runnable {

        @Override
        public void run() {
            try {
                byte[] bytes = request.downloadSync(HTTP_PHOTO);
                String path = BaseApp.getCacheDir().getAbsolutePath();
                String name = "temp" + DateUtils.currentTimeMillis() + ".png";
                File file = doParse(bytes, path, name);
                if (file != null && file.exists()) {
                    logger.info("同步：DOWNLOAD请求：成功结果\n" + file.getAbsolutePath());
                } else {
                    logger.info("同步：DOWNLOAD请求：失败结果");
                }
            } catch (IOException e) {
                logger.error("同步：DOWNLOAD请求：失败结果", e);
            }
        }

        private File doParse(byte[] data, String fileSavePath, String fileSaveName) throws IOException {
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        executor.shutdown();
    }
}
