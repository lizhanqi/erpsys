package com.suxuantech.erpsys.ui.fragment;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.suxuantech.erpsys.App;
import com.suxuantech.erpsys.R;
import com.suxuantech.erpsys.entity.HomeDataTodayCustomerEntity;
import com.suxuantech.erpsys.entity.MakeAnIntoStoreEntity;
import com.suxuantech.erpsys.entity.RegisterEntity;
import com.suxuantech.erpsys.entity.SearchOrderEntity;
import com.suxuantech.erpsys.entity.TodayCustomerEntity;
import com.suxuantech.erpsys.entity.TodayMoneyEntity;
import com.suxuantech.erpsys.nohttp.Contact;
import com.suxuantech.erpsys.nohttp.HttpListener;
import com.suxuantech.erpsys.nohttp.JavaBeanRequest;
import com.suxuantech.erpsys.presenter.RegsiterPresenter;
import com.suxuantech.erpsys.presenter.SearchOrderPresenter;
import com.suxuantech.erpsys.presenter.connector.ISearchOrderPresenter;
import com.suxuantech.erpsys.ui.activity.CustomerDetailsActivity;
import com.suxuantech.erpsys.ui.activity.OrderDetailActivity;
import com.suxuantech.erpsys.ui.adapter.QuickAdapter;
import com.suxuantech.erpsys.ui.widget.DefaultItemDecoration;
import com.suxuantech.erpsys.ui.widget.ErrorView;
import com.suxuantech.erpsys.utils.DateUtil;
import com.suxuantech.erpsys.utils.GlideRoundTransform;
import com.suxuantech.erpsys.utils.MyString;
import com.suxuantech.erpsys.utils.StringUtils;
import com.suxuantech.erpsys.utils.ToastUtils;
import com.yanzhenjie.nohttp.RequestMethod;
import com.yanzhenjie.nohttp.rest.Response;

import java.util.ArrayList;
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
    /**
     * 今日时间
     */
    String nowDate = DateUtil.getNowDate(DateUtil.DatePattern.JUST_DAY_NUMBER);
    /**
     * 随机图
     */

    ArrayList<Drawable> drawables = new ArrayList<>();

    /**
     * 获取随机图
     *
     * @return
     */
    public Drawable randomDrawable() {
        int x = (int) (Math.random() * 50);
        int i = x % drawables.size();
        return drawables.get(i);
    }

    /**
     * Glide 圆角
     */
    RequestOptions options2 = new RequestOptions()
            .centerCrop()
            .placeholder(R.drawable.icon_head)//预加载图片
            .error(R.drawable.icon_head)//加载失败显示图片
            .priority(Priority.HIGH)//优先级
            .diskCacheStrategy(DiskCacheStrategy.ALL)//缓存策略
            .transform(new GlideRoundTransform(10));//转化为圆形

    /**
     * 跳转到客户详情
     *
     * @param data     数据
     * @param isRefesh 是否是刷新
     * @param hasMore  是否还有更多
     */
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
     * 今日客资量(首页)
     */
    void todayCustomers(int index) {
        String Url = Contact.getFullUrl(Contact.SEARCH_TODAY_CUSTOMER, Contact.TOKEN, nowDate, nowDate, index, pageSize, App.getApplication().getUserInfor().getShop_code());
        JavaBeanRequest<HomeDataTodayCustomerEntity> todayMoneyEntityJavaBeanRequest = new JavaBeanRequest<HomeDataTodayCustomerEntity>(Url, HomeDataTodayCustomerEntity.class);
        HttpListener<HomeDataTodayCustomerEntity> searchByCustmor = new HttpListener<HomeDataTodayCustomerEntity>() {
            @Override
            public void onSucceed(int what, Response<HomeDataTodayCustomerEntity> response) {
                if (response.get().isOK()) {
                    if (response.get().getData() != null && response.get().getData().size() == pageSize) {
                        smartRefreshLayout.finishRefresh();
                        pageIndex++;
                    } else {
                        smartRefreshLayout.finishLoadMoreWithNoMoreData();
                    }
                    List<HomeDataTodayCustomerEntity.DataBean> data = response.get().getData();
                    setTodayAdapter(data);
                } else {
                    if (pageIndex == 0) {
                        smartRefreshLayout.finishRefresh(false);
                        setTodayAdapter(null);
                    } else {
                        smartRefreshLayout.finishLoadMoreWithNoMoreData();
                    }
                }
            }

            @Override
            public void onFailed(int what, Response<HomeDataTodayCustomerEntity> response) {
                if (pageIndex == 0) {
                    smartRefreshLayout.finishRefresh(false);

                } else {
                    smartRefreshLayout.finishLoadMoreWithNoMoreData();
                }
            }
        };
        request(0, todayMoneyEntityJavaBeanRequest, searchByCustmor, false, false);
    }

    /**
     * 今日收款
     */
    public void getTodayCollectionMoney(int index) {
        String url = Contact.getFullUrl(Contact.TODAY_COLLECTION_MONEY, Contact.TOKEN, nowDate, nowDate, index, pageSize, App.getApplication().getUserInfor().getShop_code());
        //请求实体
        JavaBeanRequest<TodayMoneyEntity> todayMoneyEntityJavaBeanRequest = new JavaBeanRequest<TodayMoneyEntity>(url, TodayMoneyEntity.class);
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
                        setTodayCollectionMoneyAdapter(null);
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

    /**
     * 今日客资适配器
     *
     * @param data
     */
    public void setTodayAdapter(List<HomeDataTodayCustomerEntity.DataBean> data) {
        if (data == null || data.size() < pageSize) {
            smartRefreshLayout.setEnableLoadMore(false);
        } else {
            smartRefreshLayout.setEnableLoadMore(true);
        }
        if (pageIndex <= 1) {
            smartRefreshLayout.finishRefresh();
        } else {
            smartRefreshLayout.finishLoadMore();
        }
        errorView.reset();
        if (adapter == null) {
            adapter = new QuickAdapter<HomeDataTodayCustomerEntity.DataBean>(R.layout.item_register_info, data) {
                @Override
                public void convert(BaseViewHolder helper, HomeDataTodayCustomerEntity.DataBean item) {
                    TextView name = helper.getView(R.id.tv_register_name);
                    TextView type = helper.getView(R.id.tv_register_type);
                    TextView phone = helper.getView(R.id.tv_register_phone);
                    TextView cotype = helper.getView(R.id.tv_register_consumption_type);
                    TextView zone = helper.getView(R.id.tv_register_zone);
                    TextView date = helper.getView(R.id.tv_register_date);
                    TextView staff = helper.getView(R.id.tv_register_staff);
                    TextView reason = helper.getView(R.id.tv_run_reason);
                    LinearLayout wrapContant = helper.getView(R.id.ll_content);
                    LinearLayout lossing = helper.getView(R.id.ll_lossing);

                    LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) wrapContant.getLayoutParams();
                    layoutParams.setMargins(0, 0, 0, 0);
                    name.setText(StringUtils.safetyString(item.getXingming()));
                    phone.setText("手\u2000机\u2000号:" + StringUtils.safetyString(item.getShouji()));
                    cotype.setText("消费类型:" + StringUtils.safetyString(item.getConsultation_type()));
                    zone.setText("客户分区:" + StringUtils.safetyString(item.getCustomer_area()));
                    date.setText("进店日期:" + StringUtils.subDate(StringUtils.safetyString(item.getYjd_day())));
                    staff.setText("接待人:" + StringUtils.safetyString(item.getSales_staff()));
                    lossing.setVisibility(View.GONE);
                }
            };

            adapter.setOnItemClickListener((adapter, view, position) -> {
                HomeDataTodayCustomerEntity.DataBean d = (HomeDataTodayCustomerEntity.DataBean) adapter.getData().get(position);
                gotoDetails(d.getId() + "");
            });
            searchItemDecoration = new DefaultItemDecoration(getResources().getColor(R.color.gray_f9), 0, 30).offSetX(0);
            recyclerView.setAdapter(adapter);
            adapter.setEmptyView(errorView);
        } else {
            if (pageIndex <= 1) {
                adapter.updateAll(data);
            } else {
                adapter.apped(data);
            }
        }
    }

    /**
     * 今日收款
     *
     * @param data
     */
    public void setTodayCollectionMoneyAdapter(List<TodayMoneyEntity.DataBean> data) {
        if (data == null || data.size() < pageSize) {
            smartRefreshLayout.setEnableLoadMore(false);
        } else {
            smartRefreshLayout.setEnableLoadMore(true);
        }
        if (pageIndex <= 1) {
            smartRefreshLayout.finishRefresh();
        }
        errorView.reset();
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
            recyclerView.setAdapter(adapter);
            adapter.setEmptyView(errorView);
        } else {
            if (pageIndex <= 1) {
                adapter.updateAll(data);
            } else {
                adapter.apped(data);
            }
        }
    }

    ErrorView errorView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        drawables.add(getResources().getDrawable(R.drawable.image_1));
        drawables.add(getResources().getDrawable(R.drawable.image_2));
        drawables.add(getResources().getDrawable(R.drawable.image_3));
        drawables.add(getResources().getDrawable(R.drawable.image_4));
        //这里一定要ButterKnife和返回的view是同一个,不然布局显示不出来
        mSearchOrderPresenter = new SearchOrderPresenter(this);
        View inflate = inflater.inflate(R.layout.smartrefresh_and_recyclerview, container, false);
        errorView = new ErrorView(getContext());
        errorView.setBackgroundColor(getResources().getColor(R.color.white));
        errorView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                errorView.setProgressBarVisible(true);
                smartRefreshLayout.autoRefresh();
            }
        });
        recyclerView = inflate.findViewById(R.id.recycler_view);
        smartRefreshLayout = inflate.findViewById(R.id.srl_fresh);
        smartRefreshLayout.setOnRefreshListener(refreshLayout -> {
            if (adapter != null) {
                adapter.getData().clear();
                adapter.notifyDataSetChanged();
            }
            getdata();
        });
        smartRefreshLayout.setOnLoadMoreListener(loadme -> {
            getTodayCollectionMoney(pageIndex);
            String[] titles = getResources().getStringArray(R.array.home_title);
            Bundle arguments = getArguments();
            if (arguments == null) {
                return;
            }
            //传过来的标题
            String title = arguments.getString("title");
            if (title.equals("今日收款")) {
                getTodayCollectionMoney(pageIndex);
            } else if (title.equals("今日客资")) {
                todayCustomers(pageIndex);
            }
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

    /**
     * 获取数据总控
     */
    public void getdata() {
        String[] titles = getResources().getStringArray(R.array.home_title);
        Bundle arguments = getArguments();
        if (arguments == null) {
            return;
        }
        //传过来的标题
        String title = arguments.getString("title");
        if (title.equals(titles[0])) {
            getMakeAnIntoStore(Contact.TODAY_APPOINTMENT_TIME, 4);
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
        } else if (title.equals("今日客资")) {
            pageIndex = 0;
            todayCustomers(pageIndex);
        }
    }

    /**
     * 获取今日数据
     * (,拍照客户,礼服客户,化妆客户,选片客户,取件客户)
     */
    public void getTodayData(String url, int what) {
        String nowDate = DateUtil.getNowDate(DateUtil.DatePattern.JUST_DAY_NUMBER);
        String fullUrl = Contact.getFullUrl(url, Contact.TOKEN, nowDate, nowDate, App.getApplication().getUserInfor().getShop_code());
        JavaBeanRequest<TodayCustomerEntity> requst = new JavaBeanRequest<TodayCustomerEntity>(fullUrl, RequestMethod.POST, TodayCustomerEntity.class);
        HttpListener<TodayCustomerEntity> httpListener = new HttpListener<TodayCustomerEntity>() {
            @Override
            public void onSucceed(int what, Response<TodayCustomerEntity> response) {
                if (response.get().isOK()) {
                    smartRefreshLayout.finishRefresh();
                    List<TodayCustomerEntity.DataBean> data = response.get().getData();
                    setTodayCustomerAdaputer(data);
                    smartRefreshLayout.finishLoadMoreWithNoMoreData();
                } else {
                    setTodayCustomerAdaputer(null);
                    smartRefreshLayout.finishRefresh(false);
                }
            }

            @Override
            public void onFailed(int what, Response<TodayCustomerEntity> response) {
                smartRefreshLayout.finishRefresh(false);
                setTodayCustomerAdaputer(null);
                smartRefreshLayout.finishLoadMore();
            }
        };
        request(what, requst, httpListener, false, false);
    }

    /**
     * 预约进店
     */

    public void getMakeAnIntoStore(String url, int what) {
        String nowDate = DateUtil.getNowDate(DateUtil.DatePattern.JUST_DAY_NUMBER);
        String fullUrl = Contact.getFullUrl(url, Contact.TOKEN, nowDate, nowDate, App.getApplication().getUserInfor().getShop_code());
        JavaBeanRequest<MakeAnIntoStoreEntity> requst = new JavaBeanRequest<MakeAnIntoStoreEntity>(fullUrl, MakeAnIntoStoreEntity.class);
        HttpListener<MakeAnIntoStoreEntity> httpListener = new HttpListener<MakeAnIntoStoreEntity>() {
            @Override
            public void onSucceed(int what, Response<MakeAnIntoStoreEntity> response) {
                if (response.get().isOK()) {
                    smartRefreshLayout.finishRefresh();
                    List<MakeAnIntoStoreEntity.DataBean> data = response.get().getData();
                    setMakeAnIntoStoreAdaputer(data);
                    smartRefreshLayout.finishLoadMoreWithNoMoreData();
                } else {
                    setMakeAnIntoStoreAdaputer(null);
                    smartRefreshLayout.finishRefresh(false);
                }
            }

            @Override
            public void onFailed(int what, Response<MakeAnIntoStoreEntity> response) {
                smartRefreshLayout.finishRefresh(false);
                setMakeAnIntoStoreAdaputer(null);
                smartRefreshLayout.finishLoadMore();
            }
        };
        request(what, requst, httpListener, false, false);
    }

    /***
     * 今日数据适配器
     * @param data
     * (拍照客户,礼服客户,化妆客户,选片客户,取件客户)
     */
    void setTodayCustomerAdaputer(List<TodayCustomerEntity.DataBean> data) {

        smartRefreshLayout.setEnableLoadMore(false);
        errorView.reset();
        if (adapter == null) {
            adapter = new QuickAdapter<TodayCustomerEntity.DataBean>(R.layout.item_today_customer, data) {
                @Override
                public void convert(BaseViewHolder helper, TodayCustomerEntity.DataBean item) {
                    TextView name = helper.getView(R.id.tv1);
                    TextView date = helper.getView(R.id.tv2);
                    TextView info = helper.getView(R.id.tv3);
                    TextView info2 = helper.getView(R.id.tv4);
                    ImageView img = helper.getView(R.id.img_today_head);

                    Glide.with(img).load(randomDrawable()).apply(options2).into(img);
                    date.setText(TextUtils.isEmpty(item.getPhotodate()) ? "" : item.getPhotodate());
                    name.setText("姓名:" + StringUtils.safetyString(item.getXingming()));
                    if (!StringUtils.empty(StringUtils.safetyString(item.getOrderId1()))) {
                        date.setText("订单编号:" + StringUtils.safetyString(item.getOrderId1()));
                    }
                    info.setText("套系名称:" + StringUtils.safetyString(item.getPackage_name()));
                    info2.setText("订单日期:" + StringUtils.safetyString(item.getTargetdate()));

                }
            };
            adapter.setEmptyView(errorView);
        } else {
            adapter.updateAll(data);
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

    /**
     * 去客户资料详情
     *
     * @param key
     */
    public void gotoDetails(String key) {
        String nowDate = DateUtil.getNowDate(DateUtil.DatePattern.JUST_DAY_NUMBER);
        RegsiterPresenter regsiterPresenter = new RegsiterPresenter();
        regsiterPresenter.setiSearchOrderPresenter(new RegsiterPresenter.RegisterSearchResult() {
            @Override
            public void registerSearchOk(List<RegisterEntity.DataBean> data, boolean isFresh, boolean hasMore) {
                if (data != null && data.size() == 1) {
                    RegisterEntity.DataBean dataBean = data.get(0);
                    Intent it = new Intent(getActivity(), CustomerDetailsActivity.class);
                    Bundle vb = new Bundle();
                    vb.putString("orderId", dataBean.getCustomer_number());
                    vb.putParcelable("data", dataBean);
                    it.putExtra("bundle", vb);
                    startActivity(it);
                }
            }

            @Override
            public void registerSearchFailed(Response<RegisterEntity> response, int pageIndex) {

            }
        });
        regsiterPresenter.exactsearch(true, nowDate, key);
    }

    public void setMakeAnIntoStoreAdaputer(List<MakeAnIntoStoreEntity.DataBean> makeAnIntoStoreAdaputer) {
        smartRefreshLayout.setEnableLoadMore(false);
        errorView.reset();
        if (adapter == null) {
            adapter = new QuickAdapter<MakeAnIntoStoreEntity.DataBean>(R.layout.item_today_customer, makeAnIntoStoreAdaputer) {
                @Override
                public void convert(BaseViewHolder helper, MakeAnIntoStoreEntity.DataBean item) {
                    TextView name = helper.getView(R.id.tv1);
                    TextView date = helper.getView(R.id.tv2);
                    TextView info = helper.getView(R.id.tv3);
                    TextView info2 = helper.getView(R.id.tv4);
                    ImageView img = helper.getView(R.id.img_today_head);
                    Glide.with(img).load(randomDrawable()).apply(options2).into(img);
                    date.setText("");
                    name.setText("姓名:" + StringUtils.safetyString(item.getXingming()));
                    info.setText("咨询类型:" + StringUtils.safetyString(item.getConsultation_type()));
                    info2.setText("预约进店:" + DateUtil.String2String(item.getYjd_day(), DateUtil.DatePattern.RB_DATE_TIME_SYMBOL, DateUtil.DatePattern.ALL_TIME));
                }
            };
            adapter.setEmptyView(errorView);
        } else {
            adapter.updateAll(makeAnIntoStoreAdaputer);
        }
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                MakeAnIntoStoreEntity.DataBean d = (MakeAnIntoStoreEntity.DataBean) adapter.getData().get(position);
                gotoDetails(d.getId() + "");
            }
        });
        recyclerView.setAdapter(adapter);
    }
}
