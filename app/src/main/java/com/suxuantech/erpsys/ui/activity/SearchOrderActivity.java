package com.suxuantech.erpsys.ui.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
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
import com.lizhanqi.www.stepview.HorizontalStepView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.suxuantech.erpsys.R;
import com.suxuantech.erpsys.entity.HistoryEntity;
import com.suxuantech.erpsys.entity.SearchOrderEntity;
import com.suxuantech.erpsys.presenter.SearchOrderPresenter;
import com.suxuantech.erpsys.presenter.connector.ISearchOrderPresenter;
import com.suxuantech.erpsys.ui.activity.base.TitleNavigationActivity;
import com.suxuantech.erpsys.ui.adapter.BaseRecyclerAdapter;
import com.suxuantech.erpsys.ui.adapter.QuickAdapter;
import com.suxuantech.erpsys.ui.adapter.RecyclerHolder;
import com.suxuantech.erpsys.ui.widget.AdjustDrawableTextView;
import com.suxuantech.erpsys.ui.widget.DefaultItemDecoration;
import com.suxuantech.erpsys.ui.widget.DefineLoadMoreView;
import com.suxuantech.erpsys.ui.widget.OneKeyClearAutoCompleteText;
import com.suxuantech.erpsys.ui.widget.TextViewDrawableClickView;
import com.suxuantech.erpsys.utils.DateUtil;
import com.suxuantech.erpsys.utils.KeyBoardUtils;
import com.suxuantech.erpsys.utils.ToastUtils;
import com.yanzhenjie.nohttp.rest.Response;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
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
public class SearchOrderActivity extends TitleNavigationActivity implements ISearchOrderPresenter, OneKeyClearAutoCompleteText.LeftDrawableClickListen {
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
    private BaseRecyclerAdapter<HistoryEntity> historyAdapter;
    private SearchOrderPresenter mSearchOrderPresenter;
    private BaseRecyclerAdapter<SearchOrderEntity.DataBean> searchResultAdaputer;
    private DefaultItemDecoration histroyItemDecoration;
    private DefaultItemDecoration searchItemDecoration;
    private DefineLoadMoreView defineLoadMoreView;
    boolean isShowSimple = true;
    private QuickAdapter quickAdapter;

    public enum SearchType {
        NOMAL, OPTION_PANEL, PHOTOGRAPH
    }

    SearchType searchType = SearchType.NOMAL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_order);
        useButterKnife();
        if (getIntent().hasExtra("type")) {
            searchType = (SearchType) getIntent().getSerializableExtra("type");
        }
        mSearchOrderPresenter = new SearchOrderPresenter(this);
        initView();
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
            mSearchOrderPresenter.sosoNetLoadmore();
        });
        smartRefreshLayout.setOnRefreshListener(load -> {
            search(false);
        });
        hideUserDefinedNav();
    }

    /**
     * 初始化适配器
     */
    private void initDataAdapter() {
        if (SearchType.OPTION_PANEL == searchType || SearchType.PHOTOGRAPH == searchType) {
            quickAdapter = new QuickAdapter<SearchOrderEntity.DataBean>(R.layout.item_search_option_panel, null) {
                @Override
                protected void convert(BaseViewHolder helper, SearchOrderEntity.DataBean item) {
                    TextView tvOrderId = (TextView) helper.getView(R.id.tv_order_id);
                    TextView tvCustomerNames = (TextView) helper.getView(R.id.tv_customer_names);
                    TextView tvCustomerInfos = (TextView) helper.getView(R.id.tv_customer_infos);
                    tvCustomerNames.setText("" + item.getXingming());
                    tvOrderId.setText("订单编号" + item.getOrderId());
                    if (SearchType.OPTION_PANEL == searchType) {
                        tvCustomerInfos.setText("拍照日期:" + item.getPhotodate());
                        tvCustomerInfos.append("\n选片日期:" + item.getSelectday());
                    } else {
                        tvCustomerInfos.setText("订单日期:" + item.getTargetdate());
                        tvCustomerInfos.append("\n拍照日期:" + item.getPhotodate());
                    }
                    tvCustomerInfos.append("\n客户分区:" + item.getArea());
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
                    tvMoney.setText("¥ " + item.getNopayment_money());
                    tvOrderId.setText(item.getOrderId());
                    tvStatus.setText(item.getNopayment_money() > 0 ? "欠款" : "");
                    tvConsumptionType.setText("消费类型:" + item.getConsumption_type());
                    tvOrderDate.setText("订单日期:" + item.getTargetdate());
                    tvPackageName.setText("包套名称:" + item.getPayment_money());
                }
            };
        }
        quickAdapter.setOnItemClickListener( (adapter,   view,   position)->{
                Intent intent = new Intent(SearchOrderActivity.this, OrderDetailActivity.class);
                SearchOrderEntity.DataBean dataBean  = (SearchOrderEntity.DataBean) quickAdapter.getData().get(position);
                intent.putExtra("orderId",dataBean .getOrderId());
                Bundle bundle = new Bundle();
                bundle.putParcelable("data",dataBean);
                intent.putExtras( bundle);
                startActivity(intent);
        });
    }

    @Override
    public void onBackPressedSupport() {
        //
        if (!getIntent().getBooleanExtra("hideSearch", false) ){
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
        searchItemDecoration = new DefaultItemDecoration(getResources().getColor(R.color.gray_f9), 0, 30).offSetX(0);
        defineLoadMoreView = new DefineLoadMoreView(this);
        Drawable drawable = getResources().getDrawable(R.drawable.icon_simple_data);
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());//必须设置图片大小，否则不显示
        mTvNavRight.setCompoundDrawables(null, null, drawable, null);
        drawable = getResources().getDrawable(R.drawable.icon_qrcode_scan);
        //2:先调用DrawableCompat的wrap方法
        drawable = DrawableCompat.wrap(drawable);
        //3:再调用DrawableCompat的setTint方法，为Drawable实例进行着色
        DrawableCompat.setTint(drawable, getResources().getColor(R.color.themeColor));
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());//必须设置图片大小，否则不显示
        mTvNavRight.setCompoundDrawables(null, null, drawable, null);
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
        mTietNavSearch.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {//获取到焦点后设置搜索历史的适配器
                    initHistoryAdapter();
                }
            }
        });
    }

    /**
     * 初始化历史适配器(并显示)
     */
    private void initHistoryAdapter() {
        smartRefreshLayout.setEnabled(false);
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

    @OnClick({R.id.tv_nav_search_left, R.id.tv_nav_search_right, R.id.tiet_nav_search, R.id.tv_soso_storefront, R.id.btn_start_date, R.id.btn_end_date, R.id.tv_nearly_search})
    public void onClicks(View v) {
        switch (v.getId()) {
            case R.id.tv_nav_search_right:
                startActivity(QRCodeScanActivity.class);
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
        immersionBarDark();
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
        mSearchOrderPresenter.sosoNetOrder(key, mBtnStartDate.getText().toString(), mBtnEndDate.getText().toString(), true, showWindow);
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
        smartRefreshLayout.setEnabled(true);
        smartRefreshLayout.setEnableRefresh(true);
        smartRefreshLayout.setEnableLoadMore(true);
        smartRefreshLayout.setEnableAutoLoadMore(true);
        if (isRefesh) {
            smartRefreshLayout.finishRefresh();
            resetRecycleView(true);
            mSmrHistory.setAdapter(quickAdapter);
            quickAdapter.updateAll(data);
        } else {
            smartRefreshLayout.finishLoadMore();
            quickAdapter.apped(data);
            smartRefreshLayout.finishLoadMore();
        }
        if (!hasMore){
            //完成加载并标记没有更多数据 1.0.4
            smartRefreshLayout.finishLoadMoreWithNoMoreData();
        }
    }

    @Override
    public void searchFailed(Response<SearchOrderEntity> response, int pageIndex) {
        smartRefreshLayout.setEnabled(true);
        smartRefreshLayout.setEnableRefresh(true);
      //  if (pageIndex!=0){
        ///结束加载（加载失败）
        smartRefreshLayout.finishLoadMore(false);//结束加载（加载失败）
      //  }
    }
    /**
     * 搜索结果适配器
     * 另一个版本的item
     */
    private void searchResultAdapter(List<SearchOrderEntity.DataBean> data, boolean isRefesh) {
        searchResultAdaputer = new BaseRecyclerAdapter<SearchOrderEntity.DataBean>(mSmrHistory, data, R.layout.item_custrom_order) {
            @SuppressLint("ResourceType")
            @Override
            public void convert(RecyclerHolder holder, SearchOrderEntity.DataBean item, int position, boolean isScrolling) {
                TextView mTvOrderId = holder.getView(R.id.tv_order_id);
                TextView mTvUserName = holder.getView(R.id.tv_user_name);
                TextView mTvConsumeType = holder.getView(R.id.tv_consume_type);
                mTvOrderId.setText(item.getCustomerid());
                mTvUserName.setText(item.getWname() + "\t" + item.getMname());
                mTvConsumeType.setText(item.getConsumption_type());
                if (isShowSimple) {
                    holder.getView(R.id.ll_details).setVisibility(View.GONE);
                } else {
                    LinkedHashMap<String, String> stringStringMap = new LinkedHashMap<>();
                    stringStringMap.put("服务店面:", item.getShop_name() == null ? "" : item.getShop_name());
                    stringStringMap.put("消费类型:", item.getConsumption_type() == null ? "" : item.getConsumption_type());
                    stringStringMap.put("开单日期:", item.getTargetdate() == null ? "" : item.getTargetdate());
                    stringStringMap.put("套餐名称:", item.getPackage_name() == null ? "" : item.getPackage_name());
                    TextView mtvInfor = holder.getView(R.id.tv_infor);
                    mtvInfor.setText(Html.fromHtml(colorText(stringStringMap, R.color.textHint_99, R.color.myValue_33)));
                    TextView mTvMoney = holder.getView(R.id.tv_money_details);
                    mTvMoney.setText(Html.fromHtml("<font color='" + getResources().getColor(R.color.myValue_33) + "'>¥" + item.getTotal_money() + "</font> <font color='" + getResources().getColor(R.color.textHint_99) + "'><br/>总价</font><br/>"
                            + "<font color='" + getResources().getColor(R.color.myValue_33) + "'>¥" + item.getPayment_money() + "</font> <font color='" + getResources().getColor(R.color.textHint_99) + "'><br/>已付</font><br/>"
                            + "<font color='" + getResources().getColor(R.color.myValue_33) + "'>¥" + item.getNopayment_money() + "</font> <font color='" + getResources().getColor(R.color.color_f1403b) + "'><br/>欠款</font><br/>"
                    ));
                    holder.getView(R.id.ll_details).setVisibility(View.VISIBLE);
                }
                final HorizontalStepView view = holder.getView(R.id.horizontalSteps);
                view.setStepsViewIndicatorComplectingPosition(2);
                view.setTag(position);
                view.setOnItemClickList(new HorizontalStepView.ItemClick() {
                    @Override
                    public void onItemClick(int position, boolean isfinish, String text) {
                        ToastUtils.showShort(view.getTag() + text + position + isfinish);
                    }
                });
                view.setStepViewTexts(new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.steps))))//总步骤
                        .setmCircleRadius(23)
                        .setTextMarginTop(-10)
                        .fixPointPadding(false)
                        .setComplete(1, 5)//间断完成（与连续完成只有一种生效）
                        .setStepsViewIndicatorComplectingPosition(2)//连续完成步数
                        .setStepsViewIndicatorCompletedLineColor(ContextCompat.getColor(SearchOrderActivity.this, R.color.themeColor))//设置StepsViewIndicator完成线的颜色
                        .setStepsViewIndicatorUnCompletedLineColor(ContextCompat.getColor(SearchOrderActivity.this, R.color.textHint_99))//设置StepsViewIndicator未完成线的颜色
                        .setStepViewComplectedTextColor(ContextCompat.getColor(SearchOrderActivity.this, R.color.themeColor))//设置StepsView text完成线的颜色
                        .setStepViewUnComplectedTextColor(ContextCompat.getColor(SearchOrderActivity.this, R.color.textHint_99))//设置StepsView text未完成线的颜色
                        .setStepsViewIndicatorCompleteIcon(ContextCompat.getDrawable(SearchOrderActivity.this, R.drawable.icon_finished))//设置StepsViewIndicator CompleteIcon
                        .setStepsViewIndicatorDefaultIcon(ContextCompat.getDrawable(SearchOrderActivity.this, R.drawable.icon_unfinished))//设置StepsViewIndicator DefaultIcon
                        .setStepsViewIndicatorAttentionIcon(ContextCompat.getDrawable(SearchOrderActivity.this, R.drawable.icon_current));//设置StepsViewIndicator AttentionIcon

            }
        };
    }

    /**
     * 重置
     */
    private void resetRecycleView(boolean formData) {
        mSmrHistory.setAdapter(null);
        //移除所有的横线
        mSmrHistory.removeItemDecoration(histroyItemDecoration);
        mSmrHistory.removeItemDecoration(searchItemDecoration);
        if (formData){
            mSmrHistory.addItemDecoration(searchItemDecoration);
        }else {
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

}
