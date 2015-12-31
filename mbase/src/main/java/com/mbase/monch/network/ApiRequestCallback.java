package com.mbase.monch.network;

import com.mbase.monch.exception.LoginException;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.squareup.okhttp.ResponseBody;

import org.json.JSONException;

import java.io.IOException;

/**
 * Created by monch on 15/11/14.
 */
public abstract class ApiRequestCallback extends BaseCallback {

    @Override
    public void onFailure(Request request, IOException e) {
        mResponsePoster.execute(
                new ResponseFailedRunnable("Network error：Unexpected code " + request, e));
    }

    @Override
    public void onResponse(Response response) throws IOException {
        if (response == null)
            throw new IOException("Network error：Response is NULL");
        if (!response.isSuccessful())
            throw new IOException("Network error：Unexpected code " + response);
        ResponseBody body = response.body();
        try {
            String result = body != null ? body.string() : null;
            ApiResult apiResult = onParseCallback(result);
            mResponsePoster.execute(new ResponseSuccessRunnable(apiResult));
        } catch (JSONException e) {
            mResponsePoster.execute(
                    new ResponseFailedRunnable("Parse error", e));
        } catch (LoginException e) {
            mResponsePoster.execute(
                    new ResponseFailedRunnable("Login error", e));
        } catch (Exception e) {
            mResponsePoster.execute(
                    new ResponseFailedRunnable("onParseCallback error", e));
        } finally {
            if (body != null) body.close();
        }
    }

}
