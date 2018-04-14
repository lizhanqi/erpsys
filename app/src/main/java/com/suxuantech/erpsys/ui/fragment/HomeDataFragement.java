package com.suxuantech.erpsys.ui.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseViewHolder;
import com.suxuantech.erpsys.App;
import com.suxuantech.erpsys.R;
import com.suxuantech.erpsys.entity.TodayCustomerEntity;
import com.suxuantech.erpsys.nohttp.Contact;
import com.suxuantech.erpsys.nohttp.HttpListener;
import com.suxuantech.erpsys.nohttp.HttpResponseListener;
import com.suxuantech.erpsys.nohttp.JavaBeanRequest;
import com.suxuantech.erpsys.ui.adapter.QuickAdapter;
import com.suxuantech.erpsys.ui.widget.DefaultItemDecoration;
import com.yanzhenjie.nohttp.RequestMethod;
import com.yanzhenjie.nohttp.rest.Response;

import java.util.List;

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
 * @author Created by 李站旗 on 2018/4/10 0010 15:02 .
 *         QQ:1032992210
 *         E-mail:lizhanqihd@163.com
 * @Description: todo(用一句话描述该文件做什么)
 */

public class HomeDataFragement extends BaseSupportFragment {
    RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //这里一定要ButterKnife和返回的view是同一个,不然布局显示不出来
        recyclerView = new RecyclerView(getActivity());
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.addItemDecoration(new DefaultItemDecoration(getResources().getColor(R.color.line)));
        getdata();
        return recyclerView;
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {

        }
    }

    public void getdata() {
        String[] titles = getResources().getStringArray(R.array.home_title);
        Bundle arguments = getArguments();
        //传过来的标题
        String title = arguments.getString("title");
        if (title == null) {
            return;
        }
        if (title.equals(titles[0])) {
            todayTakePhoto(Contact.Today_TakePhoto, 0);
        } else if (title.equals(titles[1])) {
            todayTakePhoto(Contact.TODAY_PICK_UP_PHOTO, 1);
        } else if (title.equals(titles[2])) {
            todayTakePhoto(Contact.TODAY_FULL_DRESS, 2);
        } else if (title.equals(titles[3])) {
            todayTakePhoto(Contact.TODAY_MAKE_UP, 3);
        } else if (title.equals(titles[4])) {
            todayTakePhoto(Contact.TODAY_APPOINTMENT_TIME, 4);
        } else if (title.equals(titles[5])) {
            todayTakePhoto(Contact.TODAY_OPTION_PANEL, 5);
        } else if (title.equals(titles[6])) {
            // TODO: 2018/4/13 0013  我的客户接口暂时没有
            //  todayTakePhoto(Contact.TODAY_MAKE_UP,6);
        }
    }

    /**
     * 今日拍照
     */
    public void todayTakePhoto(String url, int what) {
        String fullUrl = Contact.getFullUrl(url, Contact.TOKEN, "20180401", "20180413", App.getApplication().getUserInfor().getData().get(0).getShop_code());
        JavaBeanRequest requst = new JavaBeanRequest(fullUrl, RequestMethod.POST, TodayCustomerEntity.class);
        HttpListener<TodayCustomerEntity> httpListener = new HttpListener<TodayCustomerEntity>() {
            @Override
            public void onSucceed(int what, Response<TodayCustomerEntity> response) {
                if (response.get().isOK()) {
                    List<TodayCustomerEntity.DataBean> data = response.get().getData();
                    setTodayCustomerPhotoAdaputer(data);
                }
            }

            @Override
            public void onFailed(int what, Response<TodayCustomerEntity> response) {
            }
        };
        HttpResponseListener httpResponseListener = new HttpResponseListener(getActivity(), requst, httpListener, false, false);
        addRequestQueue(what, requst, httpResponseListener);
    }

    //    public void get(String fullUrl, int i){
//        fullUrl = Contact.getFullUrl(fullUrl, Contact.TOKEN, "20180401", "20180413", App.getApplication().getUserInfor().getData().get(0).getShop_code());
//        StringRequest stringRequest = new StringRequest(fullUrl, RequestMethod.POST);
//        HttpListener httpListener = new HttpListener() {
//            @Override
//            public void onSucceed(int what, Response response) {
//            }
//            @Override
//            public void onFailed(int what, Response response) {
//            }
//        };
//        HttpResponseListener httpResponseListener = new HttpResponseListener(getActivity(), stringRequest,httpListener , false, false);
//        addRequestQueue(0, stringRequest, httpResponseListener);
//    }
    QuickAdapter adapter;
    void setTodayCustomerPhotoAdaputer(List<TodayCustomerEntity.DataBean> data) {
        if (adapter == null) {
            adapter = new QuickAdapter<TodayCustomerEntity.DataBean>(R.layout.item_home_data, data) {
                @Override
                protected void convert(BaseViewHolder helper, TodayCustomerEntity.DataBean item) {
                    super.convert(helper, item);
                    TextView name = helper.getView(R.id.tv_home_name);
                    TextView date = helper.getView(R.id.tv_home_date);
                    TextView info = helper.getView(R.id.tv_home_info);
                    date.setText(TextUtils.isEmpty(item.getPhotodate()) ? "" : item.getPhotodate());
                    name.setText(android.text.TextUtils.isEmpty(item.getOrderId()) ? item.getOrderId1() : item.getOrderId() + item.getXingming());
                    if (getArguments().getString("title").equals(getResources().getStringArray(R.array.home_title)[4])) {
                        info.setText(item.getConsumption_type());
                    } else {
                        info.setText(item.getPackage_name());
                    }
                }
            };

        }
        recyclerView.setAdapter(adapter);
    }
}
