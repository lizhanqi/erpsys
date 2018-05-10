package com.suxuantech.erpsys.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.suxuantech.erpsys.App;
import com.suxuantech.erpsys.R;
import com.suxuantech.erpsys.entity.SearchOrderEntity;
import com.suxuantech.erpsys.entity.TodayCustomerEntity;
import com.suxuantech.erpsys.entity.TodayMoneyEntity;
import com.suxuantech.erpsys.nohttp.Contact;
import com.suxuantech.erpsys.nohttp.HttpListener;
import com.suxuantech.erpsys.nohttp.HttpResponseListener;
import com.suxuantech.erpsys.nohttp.JavaBeanRequest;
import com.suxuantech.erpsys.presenter.SearchOrderPresenter;
import com.suxuantech.erpsys.presenter.connector.ISearchOrderPresenter;
import com.suxuantech.erpsys.ui.activity.OrderDetailActivity;
import com.suxuantech.erpsys.ui.adapter.QuickAdapter;
import com.suxuantech.erpsys.ui.widget.DefaultItemDecoration;
import com.suxuantech.erpsys.utils.DateUtil;
import com.suxuantech.erpsys.utils.MyString;
import com.suxuantech.erpsys.utils.StringUtils;
import com.yanzhenjie.nohttp.RequestMethod;
import com.yanzhenjie.nohttp.rest.Response;

import java.io.Serializable;
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
 * QQ:1032992210
 * E-mail:lizhanqihd@163.com
 * @Description: todo(用一句话描述该文件做什么)
 */

public class HomeDataFragement extends BaseSupportFragment implements ISearchOrderPresenter {

    RecyclerView recyclerView;
    SmartRefreshLayout smartRefreshLayout;
      int showOnPosition;
    QuickAdapter adapter;
    @Override
    public void searchSucceed(List<SearchOrderEntity.DataBean> data, boolean isRefesh, boolean hasMore) {
        Intent intent = new Intent(getActivity(), OrderDetailActivity.class);
        //    SearchOrderInforEntity.DataBean dataBean = data.get(position);
        SearchOrderEntity.DataBean dataBean = data.get(0);
        intent.putExtra("orderId", dataBean.getOrderId());
        intent.putExtra("showOnPosition", showOnPosition);
        Bundle bundle = new Bundle();
        bundle.putParcelable("data", dataBean);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    @Override
    public void searchFailed(Response<SearchOrderEntity> response, int pageIndex) {

    }

    //         <item>拍照客户</item>1   摄影资料4
//         <item>礼服客户</item>2   礼服资料 2
//         <item>化妆客户</item>3   化妆资料 3
//         <item>选片客户</item>4   选片资料5
//         <item>取件客户</item>5   取件资料6
    public enum WhichInData implements Serializable {
        A(0, "预约进店", -1), B(1, "拍照客户", 4), C(2, "礼服客户", 2), D(3, "化妆客户", 3), E(4, "选片客户", 5),
        F(5, "取件客户", 6), G(6, "我的客户", -1);
        int id;
        int showOnPosition;
        String name;
        WhichInData(int id, String name, int showOnPosition) {
            this.showOnPosition = showOnPosition;
            this.name = name;
            this.id = id;
        }

        public String getName() {
            return name;
        }
    }

    int pageIndex, pageSize = 20;

    /**
     * 今日收款
     */
    public void getTodayCollectionMoney(int index) {
        String nowDate = DateUtil.getNowDate(DateUtil.DatePattern.JUST_DAY_NUMBER);
        nowDate = Contact.getFullUrl(Contact.TODAY_COLLECTION_MONEY, Contact.TOKEN, nowDate, nowDate, index, pageSize, App.getApplication().getUserInfor().getShop_code());
        //请求实体
        JavaBeanRequest<TodayMoneyEntity> todayMoneyEntityJavaBeanRequest = new JavaBeanRequest<TodayMoneyEntity>(nowDate, TodayMoneyEntity.class);
        HttpListener<TodayMoneyEntity> searchByCustmor = new HttpListener<TodayMoneyEntity>() {
            @Override
            public void onSucceed(int what, Response<TodayMoneyEntity> response) {
                if (response.get().isOK()) {
                    if (response.get().getData()!=null&&response.get().getData().size()==pageSize){
                        pageIndex++;
                    }else {
                        smartRefreshLayout.finishLoadMoreWithNoMoreData();
                    }
                    List<TodayMoneyEntity.DataBean> data = response.get().getData();
                    setTodayCollectionMoneyAdapter(data);
                }
            }
            @Override
            public void onFailed(int what, Response<TodayMoneyEntity> response) {
            }
        };
        request(0, todayMoneyEntityJavaBeanRequest, searchByCustmor, false, false);
    }
    SearchOrderPresenter mSearchOrderPresenter;
    public void setTodayCollectionMoneyAdapter( List<TodayMoneyEntity.DataBean> data) {
        if (pageIndex<=1){
            smartRefreshLayout.finishRefresh();
        }
        if (adapter==null){
            adapter = new QuickAdapter<TodayMoneyEntity.DataBean>(R.layout.item_search_option_panel, data) {
                @Override
                public void convert(BaseViewHolder helper, TodayMoneyEntity.DataBean item) {
                    TextView tvOrderId = (TextView) helper.getView(R.id.tv_order_id);
                    TextView tvCustomerNames = (TextView) helper.getView(R.id.tv_customer_names);
                    TextView tvCustomerInfos = (TextView) helper.getView(R.id.tv_customer_infos);
                    TextView tvCustomerInfos2 = (TextView) helper.getView(R.id.tv_customer_infos2);
                    tvCustomerInfos2.setVisibility(View.VISIBLE);
                    tvCustomerNames.setText("" + item.getXingming());
                    tvOrderId.setText("订单编号" + item.getOrderId());
                    tvCustomerInfos.setText("付款日期:");
                    tvCustomerInfos.append(new MyString(StringUtils.strChangeNull(item.getPaymentdate())).setColor(tvCustomerInfos.getResources().getColor(R.color.myValue_33)));
                    tvCustomerInfos.append("\n款\u3000\u3000项:");
                    tvCustomerInfos.append(new MyString(StringUtils.strChangeNull(item.getFundname())).setColor(tvCustomerInfos.getResources().getColor(R.color.myValue_33)));
                    tvCustomerInfos.append("\n收款类型:");
                    tvCustomerInfos.append(new MyString(StringUtils.strChangeNull(item.getPayclass())).setColor(tvCustomerInfos.getResources().getColor(R.color.myValue_33)));
                    tvCustomerInfos.append("\n收\u2000款\u2000人:");
                    tvCustomerInfos.append(new MyString(StringUtils.strChangeNull(item.getCashierman())).setColor(tvCustomerInfos.getResources().getColor(R.color.myValue_33)));
                    tvCustomerInfos2.setText("¥:"+item.getPayment_money()+"\n");
                    tvCustomerInfos2.append( new MyString(StringUtils.strChangeNull(item.getPaytype())).setColor(getActivity().getResources().getColor(R.color.noticeOrange)));
                }
            };
            recyclerView.setAdapter(adapter);
        }else {
            if (pageIndex<=1){
                adapter.updateAll(data);
            }else {
                adapter.apped(data);
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //这里一定要ButterKnife和返回的view是同一个,不然布局显示不出来
        mSearchOrderPresenter = new SearchOrderPresenter(this);
        View inflate = inflater.inflate(R.layout.fun_game_battle_city_refresh, container, false);
        recyclerView = inflate.findViewById(R.id.recycler_view);
        smartRefreshLayout = inflate.findViewById(R.id.srl_fresh);
        smartRefreshLayout.setOnRefreshListener(refreshLayout -> {
            getdata();
        });
        smartRefreshLayout.setOnLoadMoreListener(loadme->{
            getTodayCollectionMoney(pageIndex);
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setBackgroundColor(getResources().getColor(R.color.gray_f9));
        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(new DefaultItemDecoration(getResources().getColor(R.color.gray_f9), 30, 30));
        return inflate;
    }
    @Override
    public void onNewBundle(Bundle args) {
        super.onNewBundle(args);
        setArguments(args);
        getdata();
    }
    public void getdata() {
        String[] titles = getResources().getStringArray(R.array.home_title);
        Bundle arguments = getArguments();
        if (arguments == null) {
            return;
        }
        //传过来的标题
        String title = arguments.getString("title");
        if (title.equals(titles[0])) {
            todayTakePhoto(Contact.TODAY_APPOINTMENT_TIME, 4);
        } else if (title.equals(titles[1])) {
            todayTakePhoto(Contact.Today_TakePhoto, 0);
            showOnPosition = 4;
        } else if (title.equals(titles[2])) {
            todayTakePhoto(Contact.TODAY_FULL_DRESS, 2);
            showOnPosition = 2;
        } else if (title.equals(titles[3])) {
            showOnPosition = 3;
            todayTakePhoto(Contact.TODAY_MAKE_UP, 3);
        } else if (title.equals(titles[4])) {
            todayTakePhoto(Contact.TODAY_OPTION_PANEL, 5);
            showOnPosition = 5;
        } else if (title.equals(titles[5])) {
            todayTakePhoto(Contact.TODAY_PICK_UP_PHOTO, 1);
            showOnPosition = 6;
        } else if (title.equals(titles[6])) {
            // TODO: 2018/4/13 0013  我的客户接口暂时没有
        } else if (title.equals("今日收款")) {
            pageIndex = 0;
            getTodayCollectionMoney(pageIndex);
        }
    }
    /**
     * 今日拍照
     */
    public void todayTakePhoto(String url, int what) {
        String fullUrl = Contact.getFullUrl(url, Contact.TOKEN, "20180401", "20180413", App.getApplication().getUserInfor().getShop_code());
        JavaBeanRequest requst = new JavaBeanRequest(fullUrl, RequestMethod.POST, TodayCustomerEntity.class);
        HttpListener<TodayCustomerEntity> httpListener = new HttpListener<TodayCustomerEntity>() {
            @Override
            public void onSucceed(int what, Response<TodayCustomerEntity> response) {
                if (response.get().isOK()) {
                    List<TodayCustomerEntity.DataBean> data = response.get().getData();
                    setTodayCustomerPhotoAdaputer2(data);
                    smartRefreshLayout.finishLoadMoreWithNoMoreData();
                }
            }

            @Override
            public void onFailed(int what, Response<TodayCustomerEntity> response) {
                smartRefreshLayout.finishLoadMore();
            }
        };
        HttpResponseListener httpResponseListener = new HttpResponseListener(getActivity(), requst, httpListener, false, false);
        addRequestQueue(what, requst, httpResponseListener);
    }

    void setTodayCustomerPhotoAdaputer(List<TodayCustomerEntity.DataBean> data) {
        if (adapter == null) {
            adapter = new QuickAdapter<TodayCustomerEntity.DataBean>(R.layout.item_home_data, data) {
                @Override
                public void convert(BaseViewHolder helper, TodayCustomerEntity.DataBean item) {
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

    void setTodayCustomerPhotoAdaputer2(List<TodayCustomerEntity.DataBean> data) {
        if (adapter == null) {
            adapter = new QuickAdapter<TodayCustomerEntity.DataBean>(R.layout.item_today_customer, data) {
                @Override
                public void convert(BaseViewHolder helper, TodayCustomerEntity.DataBean item) {
                    TextView name = helper.getView(R.id.tv1);
                    TextView date = helper.getView(R.id.tv2);
                    TextView info = helper.getView(R.id.tv3);
                    TextView info2 = helper.getView(R.id.tv4);
                    date.setText(TextUtils.isEmpty(item.getPhotodate()) ? "" : item.getPhotodate());
                    name.setText("姓名:" + item.getXingming());
                    date.setText("订单编号:" + item.getOrderId1());
                    info.setText("套系名称:" + item.getPackage_name());
                    info2.setText("订单日期:" + item.getTargetdate());
                }
            };

        }

        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                mSearchOrderPresenter.sosoNetOrder(data.get(position).getOrderId(),
                        App.getContext().getResources().getString(R.string.start_time),
                        App.getContext().getResources().getString(R.string.end_time), true, false);
            }
        });
        recyclerView.setAdapter(adapter);
    }
}
