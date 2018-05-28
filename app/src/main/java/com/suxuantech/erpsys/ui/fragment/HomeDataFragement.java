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
import com.suxuantech.erpsys.utils.ToastUtils;
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
 * QQ:1032992210
 * E-mail:lizhanqihd@163.com
 * @Description: todo(用一句话描述该文件做什么)
 */

public class HomeDataFragement extends BaseSupportFragment implements ISearchOrderPresenter {
    RecyclerView recyclerView;
    SmartRefreshLayout smartRefreshLayout;
    int showOnPosition; //跳转到详情的时候显示那个页面
    QuickAdapter adapter;
    int pageIndex, pageSize = 20;
    SearchOrderPresenter mSearchOrderPresenter;
    DefaultItemDecoration searchItemDecoration;

    @Override
    public void searchSucceed(List<SearchOrderEntity.DataBean> data, boolean isRefesh, boolean hasMore) {
        if (data == null || data.size() == 0) {
            ToastUtils.snackbarShort("未找到客户资料");
        } else if (data.size() > 1) {
            ToastUtils.snackbarShort("找到多条资料,无法进行跳转");
        } else {
            Intent intent = new Intent(getActivity(), OrderDetailActivity.class);
            SearchOrderEntity.DataBean dataBean = data.get(0);
            intent.putExtra("orderId", dataBean.getOrderId());
            intent.putExtra("showOnPosition", showOnPosition);
            Bundle bundle = new Bundle();
            bundle.putParcelable("data", dataBean);
            intent.putExtras(bundle);
            startActivity(intent);
        }
    }

    @Override
    public void searchFailed(Response<SearchOrderEntity> response, int pageIndex) {
    }

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
                    if (response.get().getData() != null && response.get().getData().size() == pageSize) {
                        smartRefreshLayout.finishRefresh();
                        pageIndex++;
                    } else {
                        smartRefreshLayout.finishLoadMoreWithNoMoreData();
                    }
                    List<TodayMoneyEntity.DataBean> data = response.get().getData();
                    setTodayCollectionMoneyAdapter(data);
                } else {
                    if (pageIndex == 0) {
                        smartRefreshLayout.finishRefresh(false);
                    } else {
                        smartRefreshLayout.finishLoadMoreWithNoMoreData();
                    }
                }
            }

            @Override
            public void onFailed(int what, Response<TodayMoneyEntity> response) {
                if (pageIndex == 0) {
                    smartRefreshLayout.finishRefresh(false);
                } else {
                    smartRefreshLayout.finishLoadMoreWithNoMoreData();
                }
            }
        };
        request(0, todayMoneyEntityJavaBeanRequest, searchByCustmor, false, false);
    }

    public void setTodayCollectionMoneyAdapter(List<TodayMoneyEntity.DataBean> data) {
        if (pageIndex <= 1) {
            smartRefreshLayout.finishRefresh();
        }
        if (adapter == null) {
            adapter = new QuickAdapter<TodayMoneyEntity.DataBean>(R.layout.item_search_option_panel, data) {
                @Override
                public void convert(BaseViewHolder helper, TodayMoneyEntity.DataBean item) {
                    TextView tvOrderId = (TextView) helper.getView(R.id.tv_order_id);
                    TextView tvCustomerNames = (TextView) helper.getView(R.id.tv_customer_names);
                    TextView tvCustomerInfos = (TextView) helper.getView(R.id.tv_customer_infos);
                    TextView tvCustomerInfos2 = (TextView) helper.getView(R.id.tv_customer_infos2);
                    helper.getView(R.id.tv_add_scheme).setVisibility(View.GONE);


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
                    tvCustomerInfos2.setText("¥:" + item.getPayment_money() + "\n");
                    tvCustomerInfos2.append(new MyString(StringUtils.strChangeNull(item.getPaytype())).setColor(getActivity().getResources().getColor(R.color.noticeOrange)));
                }
            };
            adapter.setOnItemClickListener((adapter, view, position) -> {
                mSearchOrderPresenter.sosoNetOrder(data.get(position).getOrderId(),
                        App.getContext().getResources().getString(R.string.start_time),
                        App.getContext().getResources().getString(R.string.end_time), true, false);
            });
            searchItemDecoration = new DefaultItemDecoration(getResources().getColor(R.color.gray_f9), 0, 30).offSetX(0);
            //      recyclerView.addItemDecoration(searchItemDecoration);
            recyclerView.setAdapter(adapter);
        } else {
            if (pageIndex <= 1) {
                adapter.updateAll(data);
            } else {
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
        smartRefreshLayout.setOnLoadMoreListener(loadme -> {
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
            getTodayData(Contact.TODAY_APPOINTMENT_TIME, 4);
        } else if (title.equals(titles[1])) {
            getTodayData(Contact.Today_TakePhoto, 0);
            showOnPosition = 4;
        } else if (title.equals(titles[2])) {
            getTodayData(Contact.TODAY_FULL_DRESS, 2);
            showOnPosition = 2;
        } else if (title.equals(titles[3])) {
            showOnPosition = 3;
            getTodayData(Contact.TODAY_MAKE_UP, 3);
        } else if (title.equals(titles[4])) {
            getTodayData(Contact.TODAY_OPTION_PANEL, 5);
            showOnPosition = 5;
        } else if (title.equals(titles[5])) {
            getTodayData(Contact.TODAY_PICK_UP_PHOTO, 1);
            showOnPosition = 6;
        } else if (title.equals("今日收款")) {
            pageIndex = 0;
            showOnPosition = 7;
            getTodayCollectionMoney(pageIndex);
        }
    }

    /**
     *
     */
    public void getTodayData(String url, int what) {
        String nowDate = DateUtil.getNowDate(DateUtil.DatePattern.JUST_DAY_NUMBER);
        String fullUrl = Contact.getFullUrl(url, Contact.TOKEN, nowDate, nowDate, App.getApplication().getUserInfor().getShop_code());
        JavaBeanRequest requst = new JavaBeanRequest(fullUrl, RequestMethod.POST, TodayCustomerEntity.class);
        HttpListener<TodayCustomerEntity> httpListener = new HttpListener<TodayCustomerEntity>() {
            @Override
            public void onSucceed(int what, Response<TodayCustomerEntity> response) {
                if (response.get().isOK()) {
                    smartRefreshLayout.finishRefresh();
                    List<TodayCustomerEntity.DataBean> data = response.get().getData();
                    setTodayCustomerAdaputer(data);
                    smartRefreshLayout.finishLoadMoreWithNoMoreData();
                } else {
                    smartRefreshLayout.finishRefresh(false);
                }
            }

            @Override
            public void onFailed(int what, Response<TodayCustomerEntity> response) {
                smartRefreshLayout.finishRefresh(false);
                smartRefreshLayout.finishLoadMore();
            }
        };
        HttpResponseListener httpResponseListener = new HttpResponseListener(getActivity(), requst, httpListener, false, false);
        addRequestQueue(what, requst, httpResponseListener);
    }


    void setTodayCustomerAdaputer(List<TodayCustomerEntity.DataBean> data) {
        if (adapter == null) {
            adapter = new QuickAdapter<TodayCustomerEntity.DataBean>(R.layout.item_today_customer, data) {
                @Override
                public void convert(BaseViewHolder helper, TodayCustomerEntity.DataBean item) {
                    TextView name = helper.getView(R.id.tv1);
                    TextView date = helper.getView(R.id.tv2);
                    TextView info = helper.getView(R.id.tv3);
                    TextView info2 = helper.getView(R.id.tv4);
                    date.setText(TextUtils.isEmpty(item.getPhotodate()) ? "" : item.getPhotodate());
                    name.setText("姓名:" + StringUtils.safetyString(item.getXingming()));
                    date.setText("订单编号:" + StringUtils.safetyString(item.getOrderId1()));
                    info.setText("套系名称:" + StringUtils.safetyString(item.getPackage_name()));
                    info2.setText("订单日期:" + StringUtils.safetyString(item.getTargetdate()));
                }
            };
        }
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                String[] titles = getResources().getStringArray(R.array.home_title);
                Bundle arguments = getArguments();
                if (arguments == null) {
                    return;
                }
                //传过来的标题
                String title = arguments.getString("title");
                if (title.equals(titles[0])) {
                    //   startActivity(CustomerDetailsActivity.);
                } else {
                    mSearchOrderPresenter.sosoNetOrder(data.get(position).getOrderId(),
                            App.getContext().getResources().getString(R.string.start_time),
                            App.getContext().getResources().getString(R.string.end_time), true, false);
                }
            }
        });
        recyclerView.setAdapter(adapter);
    }
}
