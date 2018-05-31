package com.suxuantech.erpsys.ui.activity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bigkoo.alertview.AlertView;
import com.bigkoo.alertview.OnItemClickListener;
import com.bigkoo.pickerview.TimePickerView;
import com.chad.library.adapter.base.BaseViewHolder;
import com.scwang.smartrefresh.header.WaveSwipeHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.suxuantech.erpsys.App;
import com.suxuantech.erpsys.R;
import com.suxuantech.erpsys.entity.BaseScheme;
import com.suxuantech.erpsys.entity.HistoryEntity;
import com.suxuantech.erpsys.entity.PhotoSchemeSearchEntity;
import com.suxuantech.erpsys.entity.SearchOptionPanelEntity;
import com.suxuantech.erpsys.entity.SearchOrderEntity;
import com.suxuantech.erpsys.presenter.SearchOrderPresenter;
import com.suxuantech.erpsys.presenter.connector.ISearchOptionPanelScheme;
import com.suxuantech.erpsys.presenter.connector.ISearchOrderPresenter;
import com.suxuantech.erpsys.presenter.connector.ISearchPhotoScheme;
import com.suxuantech.erpsys.ui.TypeFlag;
import com.suxuantech.erpsys.ui.activity.base.TitleNavigationActivity;
import com.suxuantech.erpsys.ui.adapter.BaseRecyclerAdapter;
import com.suxuantech.erpsys.ui.adapter.QuickAdapter;
import com.suxuantech.erpsys.ui.adapter.RecyclerHolder;
import com.suxuantech.erpsys.ui.widget.AdjustDrawableTextView;
import com.suxuantech.erpsys.ui.widget.DefaultItemDecoration;
import com.suxuantech.erpsys.ui.widget.OneKeyClearAutoCompleteText;
import com.suxuantech.erpsys.ui.widget.TextViewDrawableClickView;
import com.suxuantech.erpsys.utils.DateUtil;
import com.suxuantech.erpsys.utils.GroupByKt;
import com.suxuantech.erpsys.utils.KeyBoardUtils;
import com.suxuantech.erpsys.utils.MyString;
import com.suxuantech.erpsys.utils.StringUtils;
import com.suxuantech.erpsys.utils.ToastUtils;
import com.xys.libzxing.zxing.activity.CaptureActivity;
import com.yanzhenjie.nohttp.rest.Response;
import com.yanzhenjie.permission.Permission;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

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
 * @author Created by 李站旗 on 2017/11/18 0018 16:16 .
 * QQ:1032992210
 * E-mail:lizhanqihd@163.com
 * @Description: 订单搜索页面
 */
public class SearchOrderActivity extends TitleNavigationActivity implements ISearchOptionPanelScheme, ISearchPhotoScheme, ISearchOrderPresenter, OneKeyClearAutoCompleteText.LeftDrawableClickListen {
    @BindView(R.id.tv_nav_search_left)
    AdjustDrawableTextView mTvNavLeft;
    @BindView(R.id.tiet_nav_search)
    OneKeyClearAutoCompleteText mTietNavSearch;
    @BindView(R.id.tv_nav_search_right)
    AdjustDrawableTextView mTvNavRight;
    @BindView(R.id.tv_soso_storefront)
    TextView mTvSosoStorefront;
    @BindView(R.id.btn_start_date)
    Button mBtnStartDate;
    @BindView(R.id.btn_end_date)
    Button mBtnEndDate;
    @BindView(R.id.tv_nearly_search)
    TextViewDrawableClickView mTvNearlySearch;
    @BindView(R.id.in_head_search)
    LinearLayout mLlSearch;
    @BindView(R.id.in_search_nav)
    LinearLayout mLlSearchNav;
    @BindView(R.id.smr_history)
    RecyclerView mSmrHistory;
    @BindView(R.id.smart_refresh)
    SmartRefreshLayout smartRefreshLayout;
    @BindView(R.id.wv_head)
    WaveSwipeHeader freshHead;

    private BaseRecyclerAdapter<HistoryEntity> historyAdapter;
    private SearchOrderPresenter mSearchOrderPresenter;
    private BaseRecyclerAdapter<SearchOrderEntity.DataBean> searchResultAdaputer;
    private DefaultItemDecoration histroyItemDecoration;
    private DefaultItemDecoration searchItemDecoration;
    private QuickAdapter quickAdapter;
    private BaseScheme schemeData;
    TypeFlag searchType = TypeFlag.NOMAL;
    private QuickAdapter<ArrayList<SearchOptionPanelEntity.DataBean>> optionPanelAdaputer;
    private QuickAdapter<ArrayList<PhotoSchemeSearchEntity.DataBean>> photoSchemeAdaputer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_order);
        useButterKnife();
        if (getIntent().hasExtra("type")) {
            searchType = (TypeFlag) getIntent().getSerializableExtra("type");
        }
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            schemeData = (BaseScheme) extras.getParcelable("data");
        }
        mSearchOrderPresenter = new SearchOrderPresenter(this);
        mSearchOrderPresenter.setiSearchOptionPanelScheme(this);
        mSearchOrderPresenter.setiSearchPhotoScheme(this);
        initView();
        initHistoryAdapter();
        initSearchEditTextView();
        initDataAdapter();
        if (getIntent().getBooleanExtra("hideSearch", false)) {
            supportToolbar();
            if (getIntent().hasExtra("title")) {
                String title = getIntent().getStringExtra("title");
                setTitle(title);
            }
            mBtnStartDate.setText(DateUtil.getNowDate(DateUtil.DatePattern.JUST_DAY_NUMBER));
            mBtnEndDate.setText(DateUtil.getNowDate(DateUtil.DatePattern.JUST_DAY_NUMBER));
            mLlSearch.setVisibility(View.GONE);
            mLlSearchNav.setVisibility(View.GONE);
            search(true);
        }
        smartRefreshLayout.setOnLoadMoreListener(load -> {
            mSearchOrderPresenter.sosoNetLoadmore(searchType);
        });
        smartRefreshLayout.setOnRefreshListener(load -> {
            search(false);
        });

    }

    /**
     * 初始化适配器
     */
    private void initDataAdapter() {
        if (TypeFlag.OPTION_PANEL == searchType) {
            optionPanelAdaputer = new QuickAdapter<ArrayList<SearchOptionPanelEntity.DataBean>>(R.layout.item_search_option_panel, null) {
                @Override
                protected void convert(BaseViewHolder helper, ArrayList<SearchOptionPanelEntity.DataBean> item) {
                    int parentPosition = optionPanelAdaputer.getData().lastIndexOf(item);
                    TextView tvOrderId = (TextView) helper.getView(R.id.tv_order_id);
                    TextView tvCustomerNames = (TextView) helper.getView(R.id.tv_customer_names);
                    TextView tvCustomerInfos = (TextView) helper.getView(R.id.tv_customer_infos);
                    LinearLayout llScheme = (LinearLayout) helper.getView(R.id.ll_scheme);
                    TextView tvAddScheme = (TextView) helper.getView(R.id.tv_add_scheme);
                    TextView tvChangeScheme = (TextView) helper.getView(R.id.tv_change_scheme);
                    TextView tvCustomerInfos2 = (TextView) helper.getView(R.id.tv_customer_infos2);
                    RecyclerView rvAllSchemed = (RecyclerView) helper.getView(R.id.rv_all_schemed);
                    tvAddScheme.setOnClickListener((View view) -> {
                        optionPanelScheme(item, true, -1);
                    });
                    SearchOptionPanelEntity.DataBean dataBean = item.get(0);
                    String orderid = StringUtils.safetyString(dataBean.getOrderid());
                    String names = StringUtils.safetyString(dataBean.getXingming());
                    String date = StringUtils.safetyString(dataBean.getTargetdate());
                    String Area = StringUtils.safetyString(dataBean.getArea());
                    tvCustomerInfos.setText("订单日期:" + date);
                    tvCustomerInfos.append("\n客户分区:" + Area);
                    tvOrderId.setText(orderid);
                    tvCustomerNames.setText(names);
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getBaseContext());
                    linearLayoutManager.setSmoothScrollbarEnabled(false);
                    DefaultItemDecoration defaultItemDecoration = new DefaultItemDecoration(getResources().getColor(R.color.mainNavline_e7));
                    defaultItemDecoration.setJustLeftOffsetX(50);
                    rvAllSchemed.addItemDecoration(defaultItemDecoration);
                    rvAllSchemed.setLayoutManager(linearLayoutManager);
                    QuickAdapter childenAdaputer = new QuickAdapter<SearchOptionPanelEntity.DataBean>(R.layout.item_search_childen_scheme, item) {
                        @Override
                        protected void convert(BaseViewHolder helper, SearchOptionPanelEntity.DataBean item) {
                            ArrayList<SearchOptionPanelEntity.DataBean> dataBeans = optionPanelAdaputer.getData().get(parentPosition);
                            int childenPosstion = dataBeans.lastIndexOf(item);
                            TextView tvType = (TextView) helper.getView(R.id.tv_type);
                            TextView tvDate = (TextView) helper.getView(R.id.tv_date);
                            TextView tvChange = (TextView) helper.getView(R.id.tv_change);
                            tvType.setText("选片类型:" + StringUtils.safetyString(item.getSelect_order_name()));
                            tvDate.setText(StringUtils.subDate(StringUtils.safetyString(item.getSelectday())));
                            tvChange.setOnClickListener((View view) -> {
                                optionPanelScheme(dataBeans, false, childenPosstion);
                                ToastUtils.snackbarShort(parentPosition + "------" + childenPosstion);
                            });
                        }
                    };
                    rvAllSchemed.setAdapter(childenAdaputer);
                }
            };
        } else if (TypeFlag.PHOTOGRAPH == searchType) {
            ArrayList<ArrayList<PhotoSchemeSearchEntity.DataBean>> lists = new ArrayList<>();
            photoSchemeAdaputer = new QuickAdapter<ArrayList<PhotoSchemeSearchEntity.DataBean>>(R.layout.item_search_option_panel, lists) {
                @Override
                protected void convert(BaseViewHolder helper, ArrayList<PhotoSchemeSearchEntity.DataBean> item) {
                    int parentPosition = photoSchemeAdaputer.getData().lastIndexOf(item);
                    TextView tvOrderId = (TextView) helper.getView(R.id.tv_order_id);
                    TextView tvCustomerNames = (TextView) helper.getView(R.id.tv_customer_names);
                    TextView tvCustomerInfos = (TextView) helper.getView(R.id.tv_customer_infos);
                    LinearLayout llScheme = (LinearLayout) helper.getView(R.id.ll_scheme);
                    TextView tvAddScheme = (TextView) helper.getView(R.id.tv_add_scheme);
                    TextView tvChangeScheme = (TextView) helper.getView(R.id.tv_change_scheme);
                    TextView tvCustomerInfos2 = (TextView) helper.getView(R.id.tv_customer_infos2);
                    RecyclerView rvAllSchemed = (RecyclerView) helper.getView(R.id.rv_all_schemed);
                    tvAddScheme.setOnClickListener((View view) -> {
                        photoScheme(item, true, -1);
                    });
                    PhotoSchemeSearchEntity.DataBean dataBean = item.get(0);
                    String orderid = StringUtils.safetyString(dataBean.getOrderid());
                    String names = StringUtils.safetyString(dataBean.getXingming());
                    String date = StringUtils.safetyString(dataBean.getTargetdate());
                    String Area = StringUtils.safetyString(dataBean.getArea());
                    tvCustomerInfos.setText("订单日期:" + date);
                    tvCustomerInfos.append("\n客户分区:" + Area);
                    tvOrderId.setText(orderid);
                    tvCustomerNames.setText(names);
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getBaseContext());
                    linearLayoutManager.setSmoothScrollbarEnabled(false);
                    DefaultItemDecoration defaultItemDecoration = new DefaultItemDecoration(getResources().getColor(R.color.mainNavline_e7));
                    defaultItemDecoration.setJustLeftOffsetX(50);
                    rvAllSchemed.addItemDecoration(defaultItemDecoration);
                    rvAllSchemed.setLayoutManager(linearLayoutManager);
                    QuickAdapter childenAdaputer = new QuickAdapter<PhotoSchemeSearchEntity.DataBean>(R.layout.item_search_childen_scheme, item) {
                        @Override
                        protected void convert(BaseViewHolder helper, PhotoSchemeSearchEntity.DataBean item) {
                            ArrayList<PhotoSchemeSearchEntity.DataBean> dataBeans = photoSchemeAdaputer.getData().get(parentPosition);
                            int childenPosstion = dataBeans.lastIndexOf(item);
                            TextView tvType = (TextView) helper.getView(R.id.tv_type);
                            TextView tvDate = (TextView) helper.getView(R.id.tv_date);
                            TextView tvChange = (TextView) helper.getView(R.id.tv_change);
                            tvType.setText("拍照类型:" + StringUtils.safetyString(item.getConsumption_type()));
                            tvDate.setText(StringUtils.subDate(StringUtils.safetyString(item.getPhotodate())));
                            tvChange.setOnClickListener((View view) -> {
                                photoScheme(dataBeans, false, childenPosstion);
                            });
                        }
                    };
                    rvAllSchemed.setAdapter(childenAdaputer);
                }
            };

        } else {
            quickAdapter = new QuickAdapter<SearchOrderEntity.DataBean>(R.layout.item_new_search_order_info, null) {
                @Override
                protected void convert(BaseViewHolder helper, SearchOrderEntity.DataBean item) {
                    TextView tvName = (TextView) helper.getView(R.id.tv_name);
                    TextView tvMoney = (TextView) helper.getView(R.id.tv_money);
                    TextView tvOrderId = (TextView) helper.getView(R.id.tv_order_id);
                    TextView tvStatus = (TextView) helper.getView(R.id.tv_status);
                    TextView tvConsumptionType = (TextView) helper.getView(R.id.tv_consumption_type);
                    TextView tvPackageName = (TextView) helper.getView(R.id.tv_package_name);
                    TextView tvOrderDate = (TextView) helper.getView(R.id.tv_order_date);
                    tvName.setText(item.getXingming());
                    tvMoney.setText(new MyString("¥ " + StringUtils.moneyFormat(item.getNopayment_money())).setColor(getResources().getColor(R.color.colorAccent)));
                    tvOrderId.setText(item.getOrderId());
                    tvStatus.setText(item.getNopayment_money() > 0 ? "欠款" : "");
                    tvConsumptionType.setText(new MyString("消费类型:").setColor(getResources().getColor(R.color.hintColor)));
                    tvConsumptionType.append(new MyString(StringUtils.safetyString(item.getConsumption_type())).setColor(getResources().getColor(R.color.textColor)));
                    tvOrderDate.setText(new MyString("订单日期:").setColor(getResources().getColor(R.color.hintColor)));
                    tvOrderDate.append(new MyString(DateUtil.String2String(item.getTargetdate(),DateUtil.DatePattern.JUST_DAY_NUMBER,DateUtil.DatePattern.ONLY_DAY)).setColor(getResources().getColor(R.color.textColor)));
                    tvPackageName.setText(new MyString("包套名称:").setColor(getResources().getColor(R.color.hintColor)));
                    tvPackageName.append(new MyString(StringUtils.safetyString(item.getPackage_name() + "")).setColor(getResources().getColor(R.color.textColor)));
                }
            };
            quickAdapter.setOnItemClickListener((adapter, view, position) -> {
                Intent intent = new Intent(SearchOrderActivity.this, OrderDetailActivity.class);
                SearchOrderEntity.DataBean dataBean = (SearchOrderEntity.DataBean) quickAdapter.getData().get(position);
                intent.putExtra("orderId", dataBean.getOrderId());
                intent.putExtra("customerId", dataBean.getCustomerid());
                Bundle bundle = new Bundle();
                bundle.putParcelable("data", dataBean);
                intent.putExtras(bundle);
                startActivity(intent);
            });
        }
    }

    private void photoScheme(ArrayList<PhotoSchemeSearchEntity.DataBean> item, boolean isAdd, int changeIndex) {
        if (schemeData.getArea().equals("全部") || item.get(0).getArea().equals(schemeData.getArea())) {
            if (isAdd) {
                for (PhotoSchemeSearchEntity.DataBean dataBean : item) {
                    if (StringUtils.empty(dataBean.getPhotodate())) {
                        ToastUtils.snackbarShort("您有未安排拍摄日期的排程,请先修改该排程", "确定");
                        return;
                    }
                }
            }
            Intent parentActivityIntent = new Intent(this, SchemeCommintActivity.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable("type", searchType);
            bundle.putSerializable("schemeData", schemeData);
            bundle.putParcelableArrayList("photoSchemeHisory", item);
            bundle.putInt("changeIndex", changeIndex);
            bundle.putBoolean("isAdd", isAdd);
            bundle.putParcelable(ScheduleActivity.ONE_SCHEME_ALL_INFO, getIntent().getExtras().getParcelable(ScheduleActivity.ONE_SCHEME_ALL_INFO));
            bundle.putParcelable("schemeData", schemeData);
            parentActivityIntent.putExtras(bundle);
            startActivity(parentActivityIntent);
        } else {
            ToastUtils.snackbarShort("客户分区排程分区不一致!", "确定");
        }
    }

    private void optionPanelScheme(ArrayList<SearchOptionPanelEntity.DataBean> item, boolean isAdd, int changeIndex) {
        if (schemeData.getArea().equals("全部") || item.get(0).getArea().equals(schemeData.getArea())) {
            if (isAdd) {
                for (SearchOptionPanelEntity.DataBean dataBean : item) {
                    if (StringUtils.empty(dataBean.getSelectday())) {
                        ToastUtils.snackbarShort("您有未安排选片日期的排程,请先修改该排程", "确定");
                        return;
                    }
                }
            }
            Intent parentActivityIntent = new Intent(this, SchemeCommintActivity.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable("type", searchType);
            bundle.putSerializable("schemeData", schemeData);
            bundle.putParcelableArrayList("optionPanelSchemeHisory", item);
            bundle.putInt("changeIndex", changeIndex);
            bundle.putParcelable(ScheduleActivity.ONE_SCHEME_ALL_INFO, getIntent().getExtras().getParcelable("allSchemeData"));
            bundle.putBoolean("isAdd", isAdd);
            parentActivityIntent.putExtras(bundle);
            startActivity(parentActivityIntent);
        } else {
            ToastUtils.snackbarShort("客户分区排程分区不一致!", "确定");
        }
    }

    @Override
    public void onBackPressedSupport() {
        if (!getIntent().getBooleanExtra("hideSearch", false)) {
            if (mSmrHistory != null) {
                if (mSmrHistory.getAdapter() == null || mSmrHistory.getAdapter() != historyAdapter) {
                    initHistoryAdapter();
                    return;
                }
            }
        }
        super.onBackPressedSupport();
    }

    @Override
    public void initImmersionBar() {
        getLineView().setVisibility(View.GONE);
        setStatusBarFollow(mLlSearchNav);
        super.initImmersionBar();
    }

    private void initView() {
        mTvNearlySearch.setDrawableRightClick(new TextViewDrawableClickView.DrawableRightClickListener() {
            @Override
            public void onDrawableRightClickListener(View view) {
                alterDelete();
            }
        });
        histroyItemDecoration = new DefaultItemDecoration(getResources().getColor(R.color.mainNavline_e7), 0, 2);
        histroyItemDecoration.offSetX(55);
        //   histroyItemDecoration.lastRawDontDraw(true);
        searchItemDecoration = new DefaultItemDecoration(getResources().getColor(R.color.gray_f9), 0, 30).offSetX(0);
        // searchItemDecoration.lastRawDontDraw(true);
        Drawable drawable = getResources().getDrawable(R.drawable.icon_simple_data);
        drawable.setBounds(0, 0, (int) drawable.getMinimumWidth(), drawable.getMinimumWidth());//必须设置图片大小，否则不显示
        mTvNavRight.setCompoundDrawables(null, null, drawable, null);
        drawable = getResources().getDrawable(R.drawable.icon_qrcode_scan);
        //2:先调用DrawableCompat的wrap方法
        drawable = DrawableCompat.wrap(drawable);
        //3:再调用DrawableCompat的setTint方法，为Drawable实例进行着色
        DrawableCompat.setTint(drawable, getResources().getColor(R.color.themeColor));
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());//必须设置图片大小，否则不显示
        mTvNavRight.setCompoundDrawables(null, null, drawable, null);
        mSmrHistory.setLayoutManager(new LinearLayoutManager(getBaseContext()));
    }

    /**
     * 初始化搜索文本框
     */
    private void initSearchEditTextView() {
        mTietNavSearch.setLeftDrawableClickListen(this);
        mTietNavSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    search(true);
                    return true;
                }
                return false;
            }
        });
        mTietNavSearch.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                search(true);
            }
        });
        mTietNavSearch.setAdapter(new ArrayAdapter<String>(this, R.layout.simple_list_item_1, mSearchOrderPresenter.getSearchHosieryArray()));
    }

    /**
     * 初始化历史适配器(并显示)
     */
    private void initHistoryAdapter() {
        smartRefreshLayout.setEnabled(false);
        smartRefreshLayout.setEnableLoadMore(false);
        mLlSearch.setVisibility(View.VISIBLE);
        resetRecycleView(false);
        if (mSearchOrderPresenter.getSearchHosiery().size() <= 0) {
            mTvNearlySearch.setCompoundDrawables(null, null, null, null);
        }
        historyAdapter = new BaseRecyclerAdapter<HistoryEntity>(mSmrHistory, mSearchOrderPresenter.getSearchHosiery(), R.layout.item_search_history) {
            @Override
            public void convert(RecyclerHolder holder, HistoryEntity item, int position, boolean isScrolling) {
                TextView view = holder.getView(R.id.tv_history);
                view.setText(item.getName());
            }
        };
        historyAdapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, Object data, int position) {
                mTietNavSearch.setText(mSearchOrderPresenter.getSearchHosiery().get(position).getName());
                mTietNavSearch.setSelection(mSearchOrderPresenter.getSearchHosiery().get(position).getName().length());
                mTietNavSearch.dismissDropDown();
                search(true);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == GET_ORDER_ID && resultCode == RESULT_OK) {
            Bundle bundle = data.getExtras();
            String result = bundle.getString("result");
            mTietNavSearch.setText(StringUtils.safetyString(result));
            mTietNavSearch.setSelection(mTietNavSearch.getText().toString().length());
            search(true);
        }
    }

    final int GET_ORDER_ID = 153;

    @OnClick({R.id.tv_nav_search_left, R.id.tv_nav_search_right, R.id.tiet_nav_search, R.id.tv_soso_storefront, R.id.btn_start_date, R.id.btn_end_date, R.id.tv_nearly_search})
    public void onClicks(View v) {
        switch (v.getId()) {
            case R.id.tv_nav_search_right:
                if (!hasPermission(Permission.Group.CAMERA)) {
                    requestPermission(Permission.Group.CAMERA);
                } else {
                    Intent intent = new Intent(this, CaptureActivity.class);
                    startActivityForResult(intent, GET_ORDER_ID);
                }
                break;
            case R.id.tv_nav_search_left:
                finish();
                break;
            case R.id.tiet_nav_search:
                mTietNavSearch.setCursorVisible(true);
                mTietNavSearch.setFocusableInTouchMode(true);
                mTietNavSearch.setSelection(mTietNavSearch.getText().toString().length());
                initHistoryAdapter();
                break;
            default:
            case R.id.btn_start_date:
                showDataSelect(mBtnStartDate);
                break;
            case R.id.btn_end_date:
                showDataSelect(mBtnEndDate);
                break;
        }
    }

    /**
     * 订单的详情拼接
     *
     * @param text
     * @param color
     * @return
     */
    public String colorText(Map<String, String> text, @IdRes int... color) {
        StringBuffer sb = new StringBuffer();
        int i = 0;
        for (Map.Entry<String, String> entry : text.entrySet()) {
            sb.append("<font color='" + getResources().getColor(color[0]) + "'>" + entry.getKey() + "</font> <font color='" + getResources().getColor(color[1]) + "'>" + entry.getValue() + "</font><br/>");
            if (i == text.size()) {
                sb.append("<br/>");
            }
            i++;
        }
        return sb.toString();
    }

    /**
     * 弹窗确认删除历史
     */
    public void alterDelete() {
        KeyBoardUtils.closeKeybord(mTietNavSearch, this);
        new AlertView("清除历史记录", "确认清除历史记录?", null, new String[]{"取消", "确定"}, null, this, AlertView.Style.ALERT, new OnItemClickListener() {
            @Override
            public void onItemClick(Object o, int position) {
                recoverImmersionBar();
                if (position == 0) {
                    mTvNearlySearch.setCompoundDrawables(null, null, null, null);
                    mSearchOrderPresenter.clear();
                    historyAdapter.refresh(mSearchOrderPresenter.clear());
                    mTietNavSearch.setAdapter(null);
                    mTietNavSearch.setAdapter(new ArrayAdapter<String>(SearchOrderActivity.this, R.layout.simple_list_item_1, mSearchOrderPresenter.getSearchHosieryArray()));
                }
            }
        }).setAlertRightColor(getResources().getColor(R.color.themeColor)).show();
        navigationBarAlpha();
    }

    /**
     * 搜索
     */
    private void search(boolean showWindow) {
        mTietNavSearch.dismissDropDown();
        //隐藏键盘
        KeyBoardUtils.closeKeybord(mTietNavSearch, this);
        //输入框的光标不可见
        mTietNavSearch.setCursorVisible(false);
        //失去焦点
        mTietNavSearch.setFocusableInTouchMode(false);
        String key = mTietNavSearch.getText().toString().trim();
        //搜索内容不是空的就添加到最近搜索历史数据库中并更新页面
        if (!key.isEmpty()) {
            historyAdapter.refresh(mSearchOrderPresenter.insert(key));
            mTietNavSearch.setAdapter(new ArrayAdapter<String>(this, R.layout.simple_list_item_1, mSearchOrderPresenter.getSearchHosieryArray()));
            Drawable drawable = getResources().getDrawable(R.drawable.icon_delete_bucket);
            drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getMinimumHeight());
            mTvNearlySearch.setCompoundDrawables(null, null, drawable, null);
            mTvNearlySearch.postInvalidate();
        }
        if (quickAdapter != null) {
            quickAdapter.setEnableLoadMore(false);
        }

        //网络搜搜
        if (searchType == TypeFlag.NOMAL) {
            if (App.getApplication().hasPermission("A2")) {
                mSearchOrderPresenter.sosoNetOrder(key, mBtnStartDate.getText().toString(), mBtnEndDate.getText().toString(), true, showWindow);
            } else {
                ToastUtils.snackbarShort("无权查询","确定");
            }
        } else if (searchType == TypeFlag.OPTION_PANEL) {
            mSearchOrderPresenter.sosoOptionPanelScheme(key, mBtnStartDate.getText().toString(), mBtnEndDate.getText().toString(), true, showWindow);
        } else if (searchType == TypeFlag.PHOTOGRAPH) {
            mSearchOrderPresenter.sosoPhotoScheme(key, mBtnStartDate.getText().toString(), mBtnEndDate.getText().toString(), true, showWindow);
        }
        hiddenHistory();
    }

    @Override
    public void onClickLeftDrawable() {
        search(true);
    }

    /**
     * 时间选择
     */
    public void showDataSelect(final TextView showOn) {
        KeyBoardUtils.closeKeybord(mTietNavSearch, this);
        TimePickerView.OnTimeSelectListener timPic = new TimePickerView.OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {//选中事件回调
                showOn.setText(DateUtil.dateToString(date, DateUtil.DatePattern.ONLY_DAY));
            }
        };
        //年月日时分秒 的显示与否，不设置则默认全部显示
        TimePickerView.Builder timePickerView = new TimePickerView.Builder(this, timPic) //年月日时分秒 的显示与否，不设置则默认全部显示
                .setTitleText(showOn == mBtnStartDate ? getString(R.string.start_time) : getString(R.string.end_time))
                .setLineSpacingMultiplier(4).setType(new boolean[]{true, true, true, false, false, false})
                .setTitleColor(getResources().getColor(R.color.textHint_99)).setTitleBgColor(getResources().getColor(R.color.white));
        timePickerView.setDate(Calendar.getInstance());//注：根据需求来决定是否使用该方法（一般是精确到秒的情况），此项可以在弹出选择器的时候重新设置当前时间，避免在初始化之后由于时间已经设定，导致选中时间与当前时间不匹配的问题。
        timePickerView.build().show();
    }

    @Override
    public void searchSucceed(List<SearchOrderEntity.DataBean> data, boolean isRefesh, boolean hasMore) {
        if (smartRefreshLayout == null) {
            return;
        }
        smartRefreshLayout.setEnabled(true);
        smartRefreshLayout.setEnableRefresh(true);
        smartRefreshLayout.setEnableLoadMore(hasMore);
        smartRefreshLayout.setEnableAutoLoadMore(hasMore);
        if (isRefesh) {
            smartRefreshLayout.finishRefresh();
            resetRecycleView(true);
            mSmrHistory.setAdapter(quickAdapter);
            quickAdapter.updateAll(data);
            quickAdapter.notifyDataSetChanged();
        } else {
            smartRefreshLayout.finishLoadMore();
            quickAdapter.apped(data);
            quickAdapter.notifyDataSetChanged();
            smartRefreshLayout.finishLoadMore();
        }
        if (!hasMore) {
            //完成加载并标记没有更多数据 1.0.4
            smartRefreshLayout.finishLoadMoreWithNoMoreData();
        }
    }

    @Override
    public void searchFailed(Response<SearchOrderEntity> response, int pageIndex) {
        if (pageIndex==0){
        }
        smartRefreshLayout.setEnabled(true);
        smartRefreshLayout.setEnableRefresh(true);
        //  if (pageIndex!=0){
        ///结束加载（加载失败）
        smartRefreshLayout.finishLoadMore(false);//结束加载（加载失败）
        //  }
    }


    /**
     * 重置
     */
    private void resetRecycleView(boolean formData) {
        mSmrHistory.setAdapter(null);
        //移除所有的横线
        mSmrHistory.removeItemDecoration(histroyItemDecoration);
        mSmrHistory.removeItemDecoration(searchItemDecoration);
        if (formData) {
            mSmrHistory.addItemDecoration(searchItemDecoration);
        } else {
            mSmrHistory.addItemDecoration(histroyItemDecoration);
        }
    }

    /**
     * 隐藏搜索历史
     */
    private void hiddenHistory() {
        mSmrHistory.removeAllViews();
        mLlSearch.setVisibility(View.GONE);
        mSmrHistory.setAdapter(null);
        mSmrHistory.removeItemDecoration(histroyItemDecoration);
        mSmrHistory.removeItemDecoration(searchItemDecoration);
    }

    /**
     * 搜索选片排程回调
     *
     * @param data     数据
     * @param isRefesh 是否是刷新
     * @param hasMore  是否还有更多
     */
    @Override
    public void searchOptionPaneSucceed(List<SearchOptionPanelEntity.DataBean> data, boolean isRefesh, boolean hasMore) {
        ArrayList<ArrayList<SearchOptionPanelEntity.DataBean>> lists = GroupByKt.groupOptionPanelSchedule(data);
        smartRefreshLayout.setEnabled(true);
        smartRefreshLayout.setEnableRefresh(true);
        smartRefreshLayout.setEnableLoadMore(true);
        smartRefreshLayout.setEnableAutoLoadMore(true);
        if (isRefesh) {
            smartRefreshLayout.finishRefresh();
            resetRecycleView(true);
            mSmrHistory.setAdapter(optionPanelAdaputer);
            optionPanelAdaputer.updateAll(lists);
        } else {
            optionPanelAdaputer.apped(lists);
            smartRefreshLayout.finishLoadMore();
        }
        if (!hasMore) {
            //完成加载并标记没有更多数据 1.0.4
            smartRefreshLayout.finishLoadMoreWithNoMoreData();
        }
    }

    @Override
    public void searchOptionPaneFailed(Response<SearchOptionPanelEntity> response, int pageIndex) {
        if (smartRefreshLayout != null) {
            smartRefreshLayout.setEnabled(true);
            smartRefreshLayout.setEnableRefresh(true);
            smartRefreshLayout.finishLoadMore(false);
        }
    }

    /**
     * 拍照排程回调
     *
     * @param data     数据
     * @param isRefesh 是否是刷新
     * @param hasMore  是否还有更多
     */
    @Override
    public void searchPhotoSchemeSucceed(List<PhotoSchemeSearchEntity.DataBean> data, boolean isRefesh, boolean hasMore) {
        ArrayList<ArrayList<PhotoSchemeSearchEntity.DataBean>> lists = GroupByKt.groupPhotoSchedule((ArrayList<PhotoSchemeSearchEntity.DataBean>) data);
        smartRefreshLayout.setEnabled(true);
        smartRefreshLayout.setEnableRefresh(true);
        smartRefreshLayout.setEnableLoadMore(true);
        smartRefreshLayout.setEnableAutoLoadMore(true);
        if (isRefesh) {
            smartRefreshLayout.finishRefresh();
            resetRecycleView(true);
            mSmrHistory.setAdapter(photoSchemeAdaputer);
            photoSchemeAdaputer.updateAll(lists);
        } else {
            photoSchemeAdaputer.apped(lists);
            smartRefreshLayout.finishLoadMore();
        }
        if (!hasMore) {
            //完成加载并标记没有更多数据 1.0.4
            smartRefreshLayout.finishLoadMoreWithNoMoreData();
        }
    }

    @Override
    public void searchPhotoSchemeFailed(Response<PhotoSchemeSearchEntity> response, int pageIndex) {
        smartRefreshLayout.setEnabled(true);
        smartRefreshLayout.setEnableRefresh(true);
        //  if (pageIndex!=0){
        ///结束加载（加载失败）
        smartRefreshLayout.finishLoadMore(false);//结束加载（加载失败）
    }

}
