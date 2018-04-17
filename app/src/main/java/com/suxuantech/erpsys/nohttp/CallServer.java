/*
 * Copyright © YOLANDA. All Rights Reserved
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.suxuantech.erpsys.nohttp;

import android.content.Context;

        import com.yanzhenjie.nohttp.NoHttp;
        import com.yanzhenjie.nohttp.rest.Request;
        import com.yanzhenjie.nohttp.rest.RequestQueue;

/**
 * Created in Mar 6, 2016 9:12:17 PM.
 *
 * @author YOLANDA;
 */
public class CallServer {
    private RequestQueue queue;
    public  void  cancelAll(){
        queue.cancelAll();
    }
    public  void  cancelBySign(Object sign){
        queue.cancelBySign(sign);
    }
    public CallServer setQueue(RequestQueue queue){
        this.queue=queue;
        return this;
    }
    /**
     * 添加一个请求到请求队列
     * @param context 上下文
     * @param request 请求对象
     * @param callback 接受回调结果
     * @param what what，当多个请求用同一个responseListener接受结果时，用来区分请求
     * @param canCancel 请求是否能被用户取消
     * @param isLoading 是否显示dialog
     */
    public <T> void add(Context context, Request<T> request, HttpListener<T> callback, int what,  boolean canCancel, boolean  isLoading) {
        if (queue==null){
            queue = NoHttp.getRequestQueueInstance();
        }
        queue.add(what, request, new HttpResponseListener<T>( context, request, callback, canCancel, isLoading));
    }

    /**
     *添加一个请求到请求队列
     * @param context
     * @param request
     * @param callback
     * @param what
     * @param canCancel
     * @param isLoading
     * @param isShowError
     */
    public <T> void add(Context context, Request<T> request, HttpListener<T> callback, int what,  boolean canCancel, boolean  isLoading,boolean isShowError) {
        queue.add(what, request, new HttpResponseListener<>( context, request, callback, canCancel, isLoading,isShowError));
    }
}


///**
// * <p>针对队列的一个全局单例封装。</p>
// * Created by YanZhenjie on 2017/6/18.
// */
//public class CallServer {
//
//	private static CallServer sInstance;
//
//	public static CallServer getInstance() {
//		if (sInstance == null) {
//			synchronized (CallServer.class) {
//				if (sInstance == null) {
//					sInstance = new CallServer();
//				}
//			}
//		}
//		return sInstance;
//	}
//
//	private RequestQueue mRequestQueue;
//	private DownloadQueue mDownloadQueue;
//
//
//	private CallServer() {
//		mRequestQueue = NoHttp.newRequestQueue(5);
//		mDownloadQueue = NoHttp.newDownloadQueue(3);
//	}
//
//	public <T> void request(int what, Request<T> request, OnResponseListener<T> listener) {
//		mRequestQueue.add(what, request, listener);
//	}
//
//	public <T> void request(Activity activity, int what, Request<T> request, HttpListener<T> callback, boolean canCancel, boolean isLoading) {
//		mRequestQueue.add(what, request, new HttpResponseListener<>(activity, request, callback, canCancel, isLoading));
//	}
//
//	public void download(int what, DownloadRequest request, DownloadListener listener) {
//		mDownloadQueue.add(what, request, listener);
//	}
//
//}
