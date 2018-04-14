package com.suxuantech.erpsys.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.yanzhenjie.nohttp.NoHttp;
import com.yanzhenjie.nohttp.rest.OnResponseListener;
import com.yanzhenjie.nohttp.rest.Request;
import com.yanzhenjie.nohttp.rest.RequestQueue;

import me.yokeyword.fragmentation.SupportFragment;

/**
 * ......................我佛慈悲....................
 * ......................_oo0oo_.....................
 * .....................o8888888o....................
 * .....................88" . "88....................
 * .....................(| -_- |)....................
 * .....................0\  =  /0....................
 * ...................___/`---'\___..................
 * ..................' \\|     |// '.................
 * ................./ \\|||  :  |||// \..............
 * .............../ _||||| -卍-|||||- \..............
 * ..............|   | \\\  -  /// |   |.............
 * ..............| \_|  ''\---/''  |_/ |.............
 * ..............\  .-\__  '-'  ___/-. /.............
 * ............___'. .'  /--.--\  `. .'___...........
 * .........."" '<  `.___\_<|>_/___.' >' ""..........
 * ........| | :  `- \`.;`\ _ /`;.`/ - ` : | |.......
 * ........\  \ `_.   \_ __\ /__ _/   .-` /  /.......
 * ....=====`-.____`.___ \_____/___.-`___.-'=====....
 * ......................`=---='.....................
 * ..................佛祖开光 ,永无BUG................
 *
 * @author Created by 李站旗 on 2018/4/13 0013 15:14 .
 *         QQ:1032992210
 *         E-mail:lizhanqihd@163.com
 * @Description: todo(用一句话描述该文件做什么)
 */

public class BaseSupportFragment extends SupportFragment {
    /**
     * 网络队列,默认每个Activity都应该有一个队列,用这个队列在页面销毁可以全部取消major
     */
    private RequestQueue requestQueue;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestQueue = NoHttp.newRequestQueue();
    }

    /**
     * 获取本页面的队列
     * @return RequestQueue 队列
     */
    public RequestQueue getRequestQueue(){
        return requestQueue;
    }

    /**
     * 添加一个请求到本页的请求对列
     * @param what
     * @param request
     * @param listener
     * @param <T>
     */
    public <T>   void    addRequestQueue(int what, Request<T> request, OnResponseListener<T> listener){

        getRequestQueue().add(what,request,listener);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        requestQueue.cancelAll();
        requestQueue.stop();
    }
}
