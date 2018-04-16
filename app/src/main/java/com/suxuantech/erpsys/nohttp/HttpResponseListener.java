/*
 * Copyright 2015 Yan Zhenjie
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.suxuantech.erpsys.nohttp;

import android.content.Context;
import android.content.DialogInterface;

import com.suxuantech.erpsys.App;
import com.suxuantech.erpsys.R;
import com.suxuantech.erpsys.ui.dialog.WaitDialog;
import com.suxuantech.erpsys.utils.ToastUtils;
import com.yanzhenjie.nohttp.error.NetworkError;
import com.yanzhenjie.nohttp.error.NotFoundCacheError;
import com.yanzhenjie.nohttp.error.TimeoutError;
import com.yanzhenjie.nohttp.error.URLError;
import com.yanzhenjie.nohttp.error.UnKnownHostError;
import com.yanzhenjie.nohttp.rest.OnResponseListener;
import com.yanzhenjie.nohttp.rest.Request;
import com.yanzhenjie.nohttp.rest.Response;

import org.json.JSONException;

/**
 * Created in Nov 4, 2015 12:02:55 PM.
 *
 * @author Yan Zhenjie.
 */
public class HttpResponseListener<T> implements OnResponseListener<T> {
    private Context mContext;
    /**
     * Dialog.
     */
    private WaitDialog mWaitDialog;
    /**
     * Request.
     */
    private Request<?> mRequest;
    /**
     * 结果回调.
     */
    private HttpListener<T> callback;
    /**
     * 是否提示错误信息
     */
    boolean isShowError;
    /**
     * @param context      context用来实例化dialog.
     * @param request      请求对象.
     * @param httpCallback 回调对象.
     * @param canCancel    是否允许用户取消请求.
     * @param isLoading    是否显示dialog.
     */
    public HttpResponseListener(Context context, Request<?> request, HttpListener<T> httpCallback, boolean canCancel, boolean isLoading) {
        this(context, request, httpCallback, canCancel, isLoading, true);
    }
    /**
     * @param context      context用来实例化dialog.
     * @param request      请求对象.
     * @param httpCallback 回调对象.
     * @param canCancel    是否允许用户取消请求.
     * @param isLoading    是否显示dialog.
     * @param isShowError  是否提示错误信息
     */
    public HttpResponseListener(Context context, Request<?> request, HttpListener<T> httpCallback, boolean canCancel, boolean isLoading, boolean isShowError) {
        if (context instanceof App){
            context =((App) context).getTopActivity();
        }
        this.mContext = context;
        this.mRequest = request;
        this.isShowError = isShowError;
        if (context != null && isLoading) {
            mWaitDialog = new WaitDialog(context);
            mWaitDialog.setCancelable(canCancel);
            mWaitDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                @Override
                public void onCancel(DialogInterface dialog) {
                    mRequest.cancel();
                }
            });
        }
        this.callback = httpCallback;
    }

    /**
     * 开始请求, 这里显示一个dialog.
     */
    @Override
    public void onStart(int what) {
        if (mWaitDialog != null && !mWaitDialog.isShowing()){
            mWaitDialog.show();
        }

    }

    /**
     * 结束请求, 这里关闭dialog.
     */
    @Override
    public void onFinish(int what) {
        if (mWaitDialog != null && mWaitDialog.isShowing()){
            mWaitDialog.dismiss();
        }

    }
    /**
     * 成功回调.
     */
    @Override
    public void onSucceed(int what, Response<T> response) {
        if (callback != null) {
            // 这里判断一下http响应码，这个响应码问下你们的服务端你们的状态有几种，一般是200成功。
            // w3c标准http响应码：http://www.w3school.com.cn/tags/html_ref_httpmessages.asp
            if (response.get()!=null){
                callback.onSucceed(what, response);
            }else {
               onFailed(what,response);
            }
        }
    }

    /**
     * 失败回调.
     */
    @Override
    public void onFailed(int what, Response<T> response) {
        Exception exception = response.getException();

        if (isShowError) {
            if (exception instanceof NetworkError) {
                ToastUtils.showShort(R.string.error_please_check_network);
            } else if (exception instanceof TimeoutError) {
                ToastUtils.showShort(R.string.error_timeout);
            } else if (exception instanceof UnKnownHostError) {
                ToastUtils.showShort(R.string.error_not_found_server);
            } else if (exception instanceof URLError) {
                ToastUtils.showShort(R.string.error_url_error);
            } else if (exception instanceof NotFoundCacheError) {
                // 这个异常只会在仅仅查找缓存时没有找到缓存时返回
                ToastUtils.showShort(R.string.error_not_found_cache);
            } else if (exception instanceof JSONException || exception instanceof com.alibaba.fastjson.JSONException) {
                // 这个异常只会在解析数据出现问题后提示
                ToastUtils.showShort(R.string.error_data_analysis);
            } else if (response.getHeaders().getResponseCode()>=500){
                ToastUtils.showShort(App.getApplication().getString(R.string.error_service)+response.getHeaders().getResponseCode());
            }  else{
                ToastUtils.showShort(response.getHeaders().getResponseCode()+response.getException().getMessage()+App.getApplication().getString(R.string.error_unknow));
            }
        }
        if (callback != null){
            callback.onFailed(what, response);
        }
    }

}
