package com.suxuantech.erpsys.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.suxuantech.erpsys.nohttp.HttpListener;
import com.suxuantech.erpsys.nohttp.HttpResponseListener;
import com.yanzhenjie.nohttp.NoHttp;
import com.yanzhenjie.nohttp.rest.OnResponseListener;
import com.yanzhenjie.nohttp.rest.Request;
import com.yanzhenjie.nohttp.rest.RequestQueue;

import java.util.List;

import me.yokeyword.fragmentation.SupportFragment;
import solid.ren.skinlibrary.IDynamicNewView;
import solid.ren.skinlibrary.attr.base.DynamicAttr;
import solid.ren.skinlibrary.base.SkinBaseActivity;
import solid.ren.skinlibrary.loader.SkinInflaterFactory;

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

public class BaseSupportFragment extends SupportFragment    implements IDynamicNewView{

    /**
     * 网络队列,默认每个Activity都应该有一个队列,用这个队列在页面销毁可以全部取消major
     */
    private RequestQueue requestQueue;
    Object object=new Object();
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
    /**
     * 发起请求。
     *
     * @param what      what.
     * @param request   请求对象。
     * @param callback  回调函数。
     * @param canCancel 是否能被用户取消。
     * @param isLoading 实现显示加载框。
     * @param <T>       想请求到的数据类型。
     */
    public <T> void request(int what, Request<T> request, HttpListener<T> callback, boolean canCancel, boolean isLoading) {
        request.setCancelSign(object);
        requestQueue.add(what, request, new HttpResponseListener (getContext(), request, callback, canCancel, isLoading));
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        requestQueue.cancelAll();
        requestQueue.stop();
    }

/*-----------------------------------换肤---------------------------------------------*/
        private IDynamicNewView mIDynamicNewView;

        @Override
        public void onAttach(Context context) {
            super.onAttach(context);
            try {
                mIDynamicNewView = (IDynamicNewView) context;
            } catch (ClassCastException e) {
                mIDynamicNewView = null;
            }
        }

        @Override
        public final void dynamicAddView(View view, List< DynamicAttr > pDAttrs) {
            if (mIDynamicNewView == null) {
                throw new RuntimeException("IDynamicNewView should be implements !");
            } else {
                mIDynamicNewView.dynamicAddView(view, pDAttrs);
            }
        }

        @Override
        public final void dynamicAddView(View view, String attrName, int attrValueResId) {
            mIDynamicNewView.dynamicAddView(view, attrName, attrValueResId);
        }

        @Override
        public final void dynamicAddFontView(TextView textView) {
            mIDynamicNewView.dynamicAddFontView(textView);
        }

        public final SkinInflaterFactory getSkinInflaterFactory() {
            if (getActivity() instanceof SkinBaseActivity) {
                return ((SkinBaseActivity) getActivity()).getInflaterFactory();
            }
            return null;
        }

        @Override
        public void onDestroyView() {
            removeAllView(getView());
            super.onDestroyView();
        }

        protected void removeAllView(View v) {
            if (v instanceof ViewGroup) {
                ViewGroup viewGroup = (ViewGroup) v;
                for (int i = 0; i < viewGroup.getChildCount(); i++) {
                    removeAllView(viewGroup.getChildAt(i));
                }
                removeViewInSkinInflaterFactory(v);
            } else {
                removeViewInSkinInflaterFactory(v);
            }
        }

        private void removeViewInSkinInflaterFactory(View v) {
            if (getSkinInflaterFactory() != null) {
                //此方法用于Activity中Fragment销毁的时候，移除Fragment中的View
                getSkinInflaterFactory().removeSkinView(v);
            }
        }
}
