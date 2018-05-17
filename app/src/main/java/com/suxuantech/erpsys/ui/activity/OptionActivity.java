package com.suxuantech.erpsys.ui.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.BottomSheetDialog;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.View;
import android.widget.CheckedTextView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.suxuantech.erpsys.App;
import com.suxuantech.erpsys.R;
import com.suxuantech.erpsys.common.OptionHelp;
import com.suxuantech.erpsys.entity.ConsumptionTypeEntity;
import com.suxuantech.erpsys.entity.CustomerIntentionEntity;
import com.suxuantech.erpsys.entity.CustomerSourceEntity;
import com.suxuantech.erpsys.entity.CustomerZoneEntity;
import com.suxuantech.erpsys.entity.NewOrderTypeEntity;
import com.suxuantech.erpsys.entity.OptionPanelTypeEntity;
import com.suxuantech.erpsys.entity.OrderReceivingSiteEntity;
import com.suxuantech.erpsys.entity.OutletsReceptionEntity;
import com.suxuantech.erpsys.entity.PackageEntity;
import com.suxuantech.erpsys.entity.PhotoShopEntity;
import com.suxuantech.erpsys.entity.PhotoTypeEntity;
import com.suxuantech.erpsys.entity.ProductEntity;
import com.suxuantech.erpsys.entity.SimpleEntity;
import com.suxuantech.erpsys.entity.ThemeEntity;
import com.suxuantech.erpsys.eventmsg.BaseMsg;
import com.suxuantech.erpsys.eventmsg.SmpileEventMsg;
import com.suxuantech.erpsys.nohttp.Contact;
import com.suxuantech.erpsys.nohttp.HttpListener;
import com.suxuantech.erpsys.nohttp.JavaBeanRequest;
import com.suxuantech.erpsys.ui.activity.base.TitleNavigationActivity;
import com.suxuantech.erpsys.ui.adapter.BaseRecyclerAdapter;
import com.suxuantech.erpsys.ui.adapter.RecyclerHolder;
import com.suxuantech.erpsys.ui.widget.DefaultItemDecoration;
import com.suxuantech.erpsys.ui.widget.DefineLoadMoreView;
import com.suxuantech.erpsys.utils.MyString;
import com.suxuantech.erpsys.utils.StringUtils;
import com.yanzhenjie.alertdialog.AlertDialog;
import com.yanzhenjie.nohttp.RequestMethod;
import com.yanzhenjie.nohttp.rest.Response;
import com.yanzhenjie.recyclerview.swipe.SwipeItemClickListener;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;

/**
 * 跳转选择的页面这里
 */
public class OptionActivity extends TitleNavigationActivity {
    @BindView(R.id.recycler_view)
    SwipeMenuRecyclerView mRecyclerView;
    @BindView(R.id.rotate_header_grid_view_frame)
    PtrClassicFrameLayout mRotateHeaderGridViewFrame;
    @BindView(R.id.ll_sum)
    LinearLayout mLlsum;
    @BindView(R.id.img_shopping)
    ImageButton btnShopping;
    @BindView(R.id.tv_product_sum_info)
    TextView productSumInfo;
    @BindView(R.id.tv_add_product)
    TextView addProduct;
    /**
     * checkedData当前选中的，单选的话只认下表为0的那个才是
     * mAllData全部选项
     */
    ArrayList<String> checkedData, mAllData, currentChecked;
    /**
     * 网络的数据，暂时没有留在
     */
    String Url;
    /**
     * 是否是多选
     */
    private boolean mMultiple;
    private List<ConsumptionTypeEntity.DataBean> consumptionTypeData;
    private List<OrderReceivingSiteEntity.DataBean> orderReceivingSiteData;
    private List<OutletsReceptionEntity.DataBean> outletsReceptionData;
    private List<CustomerZoneEntity.DataBean> customerZoneData;
    private List<CustomerSourceEntity.DataBean> customerSourceData;
    private List<CustomerIntentionEntity.DataBean> customerIntentionData;
    private List<PhotoShopEntity.DataBean> photoShopData;
    private List<NewOrderTypeEntity.DataBean> newOrderTyepData;
    private List<ThemeEntity.DataBean> themeData;
    private List<OptionPanelTypeEntity.DataBean> optionPanelTypeData;
    private List<PhotoTypeEntity.DataBean> photoTypeData;

    BaseRecyclerAdapter<String> stringBaseRecyclerAdapter;
    /**
     * 加载更多。
     */
    private SwipeMenuRecyclerView.LoadMoreListener mLoadMoreListener = new SwipeMenuRecyclerView.LoadMoreListener() {
        @Override
        public void onLoadMore() {
            mRecyclerView.postDelayed(new Runnable() {
                @Override
                public void run() {
                    mRecyclerView.loadMoreFinish(false, true);
                    // 如果加载失败调用下面的方法，传入errorCode和errorMessage。
                    // errorCode随便传，你自定义LoadMoreView时可以根据errorCode判断错误类型。
                    // errorMessage是会显示到loadMoreView上的，用户可以看到。
                    // mRecyclerView.loadMoreError(0, "请求网络失败");
                }
            }, 1000);
        }
    };
    private OptionHelp.UrlTag urlTag;
    private String tag;
    private String title;
    private List<PackageEntity.DataBean> packageData;
    private List<ProductEntity.DataBean> productData;
    private List<ProductEntity.DataBean> productDataAdded = new ArrayList<>();
    private BaseRecyclerAdapter<ProductEntity.DataBean> productAdapter, shoppingCartAdapter;
    private RecyclerView addedRecy;
    TextView tvPopAddProduct;
    TextView tvPopProductSumInfo;
    TextView btnPopDeleteAll;

    @Override
    public void widgetClick(View v) {
        switch (v.getId()) {
            default:
            case R.id.img_shopping:
                shoppingCartAdapter.refresh(productDataAdded);
                shoppingCartAdapter.notifyDataSetChanged();
                dialog.show();
                break;
            case R.id.tv_add_product:
                if (productDataAdded.size() > 0) {
                    addProduct();
                }
                break;
        }
    }

    private void setMultipleResult() {
        if (themeData != null) {
            ArrayList<ThemeEntity.DataBean> dataBeans = new ArrayList<>();
            for (String s : currentChecked) {
                for (ThemeEntity.DataBean dataBean : themeData) {
                    if (dataBean.getTopic().equals(s)) {
                        dataBeans.add(dataBean);
                        break;
                    }
                }
            }
            BaseMsg<ThemeEntity.DataBean> dataBeanBaseMsg = new BaseMsg(themeData, dataBeans, mMultiple);
            dataBeanBaseMsg.setmTitle(title);
            dataBeanBaseMsg.setTag(tag);
            dataBeanBaseMsg.setUrl(Url);
            dataBeanBaseMsg.setUrlTag(urlTag);
            EventBus.getDefault().post(dataBeanBaseMsg);
        } else {
            ArrayList<ProductEntity.DataBean> dataBeans = new ArrayList<>();
            for (String f : currentChecked) {
                int i = Integer.parseInt(f);
                for (ProductEntity.DataBean pd : productData) {
                    if (pd.getId() == i) {
                        dataBeans.add(pd);
                        break;
                    }
                }
            }
            BaseMsg<ProductEntity.DataBean> dataBeanBaseMsg = new BaseMsg<>(productData, dataBeans, mMultiple);
            dataBeanBaseMsg.setmTitle(title);
            dataBeanBaseMsg.setTag(tag);
            dataBeanBaseMsg.setUrl(Url);
            dataBeanBaseMsg.setUrlTag(urlTag);
            EventBus.getDefault().post(dataBeanBaseMsg);
        }
        finish();
    }

    BottomSheetDialog dialog;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.refresh_and_recyclerview);
        supportToolbar();
        useButterKnife();
        idSetOnClick(R.id.img_shopping);
        idSetOnClick(R.id.tv_add_product);
        //初始化传过来的IntentData
        initIntentData(getIntent());
        //初始化recycleview
        initRecycleView();
        /**
         * 设置一下刷新控件属性
         */
        initRefreshView();
        //如果路径不为空直接调用自动刷新（这个一定要在initIntentData后执行，Url有没有数据是那里获取到的）
        if (urlTag != null) {
            getUrlAndDataCtrl(false);
            mRotateHeaderGridViewFrame.postDelayed(new Runnable() {
                @Override
                public void run() {
                    mRotateHeaderGridViewFrame.autoRefresh();
                }
            }, 0);
        } else {
            mAllData = getIntent().getStringArrayListExtra("All");
            setAdapterCtrl();
        }
    }

    /**
     * 初始化设置刷新View
     */
    private void initRefreshView() {
        mRotateHeaderGridViewFrame.setLastUpdateTimeRelateObject(this);
        // the following are default settings
        mRotateHeaderGridViewFrame.setResistance(1.7f);
        mRotateHeaderGridViewFrame.setRatioOfHeaderHeightToRefresh(1.2f);
        mRotateHeaderGridViewFrame.setDurationToClose(200);
        mRotateHeaderGridViewFrame.setDurationToCloseHeader(1000);
        // default is false
        mRotateHeaderGridViewFrame.setPullToRefresh(false);
        // default is true
        mRotateHeaderGridViewFrame.setKeepHeaderWhenRefresh(true);
        mRotateHeaderGridViewFrame.setPtrHandler(new PtrHandler() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                currentChecked = (ArrayList<String>) checkedData.clone();
                //开始刷新如果是本地有个2秒延迟
                if (Url == null) {
                    mRotateHeaderGridViewFrame.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            mRotateHeaderGridViewFrame.refreshComplete();
                            stringBaseRecyclerAdapter.notifyDataSetChanged();
                        }
                    }, 2000);
                } else {
                    //网络直接获取数据
                    getUrlAndDataCtrl(true);
                }
            }

            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                return PtrDefaultHandler.checkContentCanBePulledDown(frame, content, header);
            }
        });
    }

    /**
     * 初始化RecycleView
     */
    private void initRecycleView() {
        mRecyclerView.addItemDecoration(new DefaultItemDecoration(getResources().getColor(R.color.mainNavline_e7)));
        DefineLoadMoreView defineLoadMoreView = new DefineLoadMoreView(this);
        mRecyclerView.addFooterView(defineLoadMoreView);
        mRecyclerView.setLoadMoreView(defineLoadMoreView);
        mRecyclerView.setLoadMoreListener(mLoadMoreListener);
        mRecyclerView.setSwipeItemClickListener(new SwipeItemClickListener() {
            @Override
            public void onItemClick(View itemView, int position) {
                switch (urlTag) {
                    case PACKAGE:
                        shoPop(position);
                        break;
                    default:
                        singleResult(position);
                        break;
                }
            }
        });
    }

    public void shoPop(int index) {
        PackageEntity.DataBean dataBean = packageData.get(index);
        View inflate = getLayoutInflater().inflate(R.layout.pop_price,null);
        EditText editText = inflate.findViewById(R.id.et_price);
        editText.setText(dataBean.getPackage_price()+"");
        editText.setSelection(editText.getText().toString().length());
        AlertDialog.newBuilder(this).setTitle("包套确认:")
                .setView(inflate)
                .setMessage(dataBean.getPackage_name())
                .setPositiveButton("确定", (DialogInterface var1, int var2) -> {
                    if (editText.getText().toString()!=null){
                        int pr = Integer.parseInt(editText.getText().toString());
                        packageData.get(index).setPackage_price(pr);
                        singleResult(index);
                    }else {
                        return;
                    }
                })
                .setNegativeButton("取消", (DialogInterface var1, int var2) -> {
                }).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (mMultiple) {
            menu.add(Menu.NONE, 0, 0, getString(R.string.complete)).setOnMenuItemClickListener(menuItem -> {
                setMultipleResult();
                return true;
            });
        }
        return super.onCreateOptionsMenu(menu);
    }

    /**
     * 获取传递过来的的信息
     *
     * @param intent
     */
    private void initIntentData(Intent intent) {
        //判断是否为空
        if (intent != null) {
            //获取是否是多选
            mMultiple = intent.getBooleanExtra("Multiple", false);
            tag = intent.getStringExtra("Tag");
            title = intent.getStringExtra("Title");
            //设置标题
            setCenterTitle(title);
            checkedData = intent.getStringArrayListExtra("Checked");
            //已经选中的
            if (checkedData == null) {
                checkedData = new ArrayList<>();
            }
            //当前选中的
            currentChecked = (ArrayList<String>) checkedData.clone();
            //给个地址
            urlTag = (OptionHelp.UrlTag) intent.getSerializableExtra("UrlTag");
        }
    }

    /**
     * 获取网络路径和是否获取数据的总控
     */
    public void getUrlAndDataCtrl(boolean getData) {
        if (mAllData == null) {
            mAllData = new ArrayList<>();
        }
        switch (urlTag) {
            default:

            case PRODUCT:
                initBottomSheet();
                if (menu != null) {
                    menu.clear();
                }
                if (getMImmersionBar() != null) {
                    getMImmersionBar().navigationBarColor(R.color.mainNav_66).init();
                }
                //产品
                Url = Contact.getFullUrl(Contact.PRODUCT, Contact.TOKEN, App.getApplication().getUserInfor().getShop_code());
                if (getData) {
                    getProduct();
                }
                break;
            case PACKAGE:
                //包套
                Url = Contact.getFullUrl(Contact.PACKAGE, Contact.TOKEN, App.getApplication().getUserInfor().getShop_code());
                if (getData) {
                    getPackage();
                }
                break;
            case CONSUMPTION_TYPE:
                //消费类型
                Url = Contact.getFullUrl(Contact.CONSUMPTION_TYPE, Contact.TOKEN, 0);
                if (getData) {
                    getConsumptionType();
                }
                break;
            case OUTLETS_RECEPTION:
                //门市接待
                Url = Contact.getFullUrl(Contact.OUTLETS_RECEPTION, Contact.TOKEN, App.getApplication().getUserInfor().getShop_code());
                if (getData) {
                    getOutletsReception();
                }
                break;
            case ORDER_RECEIVING_SITE:
                //接单点
                Url = Contact.getFullUrl(Contact.ORDER_RECEIVING_SITE, Contact.TOKEN, 0);
                if (getData) {
                    getOrderReceivingSite();
                }
                break;
            case CUSTOMER_ZONE:
                //客户分区
                Url = Contact.getFullUrl(Contact.CUSTOMER_ZONE, Contact.TOKEN, 0);
                if (getData) {
                    getCustomerZone();
                }
                break;
            case CUSTOMER_SOURCE:
                //客户来源
                Url = Contact.getFullUrl(Contact.CUSTOMER_SOURCE, Contact.TOKEN, 0);
                if (getData) {
                    getCustomerSource();
                }
                break;

            case CUSTOMER_INTENTION:
                //客户意向
                Url = Contact.getFullUrl(Contact.CUSTOMER_INTENTION, Contact.TOKEN, 0);
                if (getData) {
                    getCustomerIntention();
                }
                break;

            case RECEPTION_MARKET:
                //网销门市
                Url = Contact.getFullUrl(Contact.RECEPTION_MARKET, Contact.TOKEN, App.getApplication().getUserInfor().getShop_code());
                if (getData) {
                    getOutletsReception();
                }
                break;

            case PHOTO_SHOP:
                //拍摄店面
                Url = Contact.getFullUrl(Contact.PHOTO_SHOP, Contact.TOKEN, App.getApplication().getUserInfor().getBrandclass());
                if (getData) {
                    getPhotoShop();
                }
                break;
            case NEW_ORDER_TYPE:
                //新单类型
                Url = Contact.getFullUrl(Contact.NEW_ORDER_TYPE, Contact.TOKEN, App.getApplication().getUserInfor().getBrandclass(), getIntent().getStringExtra("ConsumptionType"));
                if (getData) {
                    getNewOrderType();
                }
                break;
            case SHOOT_THEME:
                //摄影主题
                Url = Contact.getFullUrl(Contact.SHOOT_THEME, Contact.TOKEN, App.getApplication().getUserInfor().getShop_code());
                if (getData) {
                    getThemeData();
                }
                break;

            case DRESS_THEME:
                //礼服主题
                Url = Contact.getFullUrl(Contact.DRESS_THEME, Contact.TOKEN, App.getApplication().getUserInfor().getShop_code());
                if (getData) {
                    getThemeData();
                }
                break;
            case OPTION_PANEL_TYPE_SET:
                //选片类型
                Url = Contact.getFullUrl(Contact.OPTION_PANEL_TYPE_SET, Contact.TOKEN, App.getApplication().getUserInfor().getShop_code());
                if (getData) {
                    getOptionPanelType();
                }
                break;
            case PHOTO_TYPE_SET:
                //拍照类型
                Url = Contact.getFullUrl(Contact.PHOTO_TYPE_SET, Contact.TOKEN, App.getApplication().getUserInfor().getShop_code());
                if (getData) {
                    getPhotoType();
                }
                break;
        }
    }

    /**
     * 初始化弹窗
     */
    private void initBottomSheet() {
        if (dialog != null) {
            return;
        }
        mLlsum.setVisibility(View.VISIBLE);
        btnShopping.setVisibility(View.VISIBLE);
        dialog = new BottomSheetDialog(this);
        dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                resetNumber();
                for (ProductEntity.DataBean dataBean : productDataAdded) {
                    int id = dataBean.getId();
                    for (ProductEntity.DataBean dd : productData) {
                        if (dd.getId() == id) {
                            dd.setNumber(dd.getNumber() + dataBean.getNumber());
                            break;
                        } else {
                            continue;
                        }
                    }
                }
                productAdapter.notifyDataSetChanged();
                getSum();
                dialog.dismiss();
            }
        });
        View inflate = getLayoutInflater().inflate(R.layout.bottom_sheet_product, null);
        tvPopProductSumInfo = (TextView) inflate.findViewById(R.id.tv_product_sum_info);
        tvPopAddProduct = (TextView) inflate.findViewById(R.id.tv_add_product);
        btnPopDeleteAll = (TextView) inflate.findViewById(R.id.btn_delete_all);
        btnPopDeleteAll.setOnClickListener(i -> {
            productDataAdded.clear();
            shoppingCartAdapter.refresh(productDataAdded);
            getSum();
        });
        addedRecy = inflate.findViewById(R.id.rv_product);
        productShoppingCart();
        addedRecy.addItemDecoration(new DefaultItemDecoration(getResources().getColor(R.color.mainNavline_e7)));
        dialog.setContentView(inflate);
    }

    /**
     * 单选结果发送
     *
     * @param position
     */
    private void singleResult(int position) {
        if (!mMultiple) {
            //activity result 也可以设置
            Intent intent1 = new Intent();
            intent1.putExtra("result", mAllData.get(position));
            toastShort(mAllData.get(position));
            setResult(RESULT_OK, intent1);
            //发送EventBus事件也可以接收
            EventBus.getDefault().post(mAllData.get(position));
            EventBus.getDefault().post(new SmpileEventMsg(urlTag, mAllData.get(position)));
            if (Url != null) {
                if (consumptionTypeData != null) {
                    //消费类型
                    ArrayList<ConsumptionTypeEntity.DataBean> dataBeans = new ArrayList<>();
                    dataBeans.add(consumptionTypeData.get(position));
                    BaseMsg<ConsumptionTypeEntity.DataBean> dataBeanBaseMsg = new BaseMsg<>(consumptionTypeData, dataBeans, mMultiple);
                    dataBeanBaseMsg.setmTitle(title);
                    dataBeanBaseMsg.setTag(tag);
                    dataBeanBaseMsg.setUrl(Url);
                    dataBeanBaseMsg.setUrlTag(urlTag);
                    EventBus.getDefault().post(dataBeanBaseMsg);
                } else if (orderReceivingSiteData != null) {
                    //接单点
                    ArrayList<OrderReceivingSiteEntity.DataBean> dataBeans = new ArrayList<>();
                    dataBeans.add(orderReceivingSiteData.get(position));
                    BaseMsg<OrderReceivingSiteEntity.DataBean> dataBeanBaseMsg = new BaseMsg<>(orderReceivingSiteData, dataBeans, mMultiple);
                    dataBeanBaseMsg.setmTitle(title);
                    dataBeanBaseMsg.setTag(tag);
                    dataBeanBaseMsg.setUrl(Url);
                    dataBeanBaseMsg.setUrlTag(urlTag);
                    EventBus.getDefault().post(dataBeanBaseMsg);
                } else if (outletsReceptionData != null) {
                    //门市接待
                    ArrayList<OutletsReceptionEntity.DataBean> dataBeans = new ArrayList<>();
                    dataBeans.add(outletsReceptionData.get(position));
                    BaseMsg<OutletsReceptionEntity.DataBean> dataBeanBaseMsg = new BaseMsg<>(outletsReceptionData, dataBeans, mMultiple);
                    dataBeanBaseMsg.setmTitle(title);
                    dataBeanBaseMsg.setTag(tag);
                    dataBeanBaseMsg.setUrl(Url);
                    dataBeanBaseMsg.setUrlTag(urlTag);
                    EventBus.getDefault().post(dataBeanBaseMsg);
                } else if (customerZoneData != null) {
                    //客户分区
                    ArrayList<CustomerZoneEntity.DataBean> dataBeans = new ArrayList<>();
                    dataBeans.add(customerZoneData.get(position));
                    BaseMsg<CustomerZoneEntity.DataBean> dataBeanBaseMsg = new BaseMsg<>(customerZoneData, dataBeans, mMultiple);
                    dataBeanBaseMsg.setmTitle(title);
                    dataBeanBaseMsg.setTag(tag);
                    dataBeanBaseMsg.setUrl(Url);
                    dataBeanBaseMsg.setUrlTag(urlTag);
                    EventBus.getDefault().post(dataBeanBaseMsg);
                } else if (packageData != null) {
                    //包套
                    ArrayList<PackageEntity.DataBean> dataBeans = new ArrayList<>();
                    dataBeans.add(packageData.get(position));
                    BaseMsg<PackageEntity.DataBean> dataBeanBaseMsg = new BaseMsg<>(packageData, dataBeans, mMultiple);
                    dataBeanBaseMsg.setmTitle(title);
                    dataBeanBaseMsg.setTag(tag);
                    dataBeanBaseMsg.setUrl(Url);
                    dataBeanBaseMsg.setUrlTag(urlTag);
                    EventBus.getDefault().post(dataBeanBaseMsg);
                } else if (customerSourceData != null) {
                    //客户来源
                    ArrayList<CustomerSourceEntity.DataBean> dataBeans = new ArrayList<>();
                    dataBeans.add(customerSourceData.get(position));
                    BaseMsg<CustomerSourceEntity.DataBean> dataBeanBaseMsg = new BaseMsg<CustomerSourceEntity.DataBean>(customerSourceData, dataBeans, mMultiple);
                    dataBeanBaseMsg.setmTitle(title);
                    dataBeanBaseMsg.setTag(tag);
                    dataBeanBaseMsg.setUrl(Url);
                    dataBeanBaseMsg.setUrlTag(urlTag);
                    EventBus.getDefault().post(dataBeanBaseMsg);
                } else if (customerIntentionData != null) {
                    //客户来源
                    ArrayList<CustomerIntentionEntity.DataBean> dataBeans = new ArrayList<>();
                    dataBeans.add(customerIntentionData.get(position));
                    BaseMsg<CustomerIntentionEntity.DataBean> dataBeanBaseMsg = new BaseMsg<CustomerIntentionEntity.DataBean>(customerIntentionData, dataBeans, mMultiple);
                    dataBeanBaseMsg.setmTitle(title);
                    dataBeanBaseMsg.setTag(tag);
                    dataBeanBaseMsg.setUrl(Url);
                    dataBeanBaseMsg.setUrlTag(urlTag);
                    EventBus.getDefault().post(dataBeanBaseMsg);
                } else if (photoShopData != null) {
                    //客户来源
                    ArrayList<PhotoShopEntity.DataBean> dataBeans = new ArrayList<>();
                    dataBeans.add(photoShopData.get(position));
                    BaseMsg<PhotoShopEntity.DataBean> dataBeanBaseMsg = new BaseMsg<PhotoShopEntity.DataBean>(photoShopData, dataBeans, mMultiple);
                    dataBeanBaseMsg.setmTitle(title);
                    dataBeanBaseMsg.setTag(tag);
                    dataBeanBaseMsg.setUrl(Url);
                    dataBeanBaseMsg.setUrlTag(urlTag);
                    EventBus.getDefault().post(dataBeanBaseMsg);
                } else if (newOrderTyepData != null) {
                    //新单类型
                    ArrayList<NewOrderTypeEntity.DataBean> dataBeans = new ArrayList<>();
                    dataBeans.add(newOrderTyepData.get(position));
                    BaseMsg<NewOrderTypeEntity.DataBean> dataBeanBaseMsg = new BaseMsg<NewOrderTypeEntity.DataBean>(newOrderTyepData, dataBeans, mMultiple);
                    dataBeanBaseMsg.setmTitle(title);
                    dataBeanBaseMsg.setTag(tag);
                    dataBeanBaseMsg.setUrl(Url);
                    dataBeanBaseMsg.setUrlTag(urlTag);
                    EventBus.getDefault().post(dataBeanBaseMsg);
                } else if (themeData != null) {
                    //新单类型
                    ArrayList<ThemeEntity.DataBean> dataBeans = new ArrayList<>();
                    dataBeans.add(themeData.get(position));
                    BaseMsg<ThemeEntity.DataBean> dataBeanBaseMsg = new BaseMsg<ThemeEntity.DataBean>(themeData, dataBeans, mMultiple);
                    dataBeanBaseMsg.setmTitle(title);
                    dataBeanBaseMsg.setTag(tag);
                    dataBeanBaseMsg.setUrl(Url);
                    dataBeanBaseMsg.setUrlTag(urlTag);
                    EventBus.getDefault().post(dataBeanBaseMsg);
                } else if (optionPanelTypeData != null) {
                    //选片类型
                    ArrayList<OptionPanelTypeEntity.DataBean> dataBeans = new ArrayList<>();
                    dataBeans.add(optionPanelTypeData.get(position));
                    BaseMsg<OptionPanelTypeEntity.DataBean> dataBeanBaseMsg = new BaseMsg<OptionPanelTypeEntity.DataBean>(optionPanelTypeData, dataBeans, mMultiple);
                    dataBeanBaseMsg.setmTitle(title);
                    dataBeanBaseMsg.setTag(tag);
                    dataBeanBaseMsg.setUrl(Url);
                    dataBeanBaseMsg.setUrlTag(urlTag);
                    EventBus.getDefault().post(dataBeanBaseMsg);
                } else if (photoTypeData != null) {
                    //选片类型
                    ArrayList<PhotoTypeEntity.DataBean> dataBeans = new ArrayList<>();
                    dataBeans.add(photoTypeData.get(position));
                    BaseMsg<PhotoTypeEntity.DataBean> dataBeanBaseMsg = new BaseMsg<PhotoTypeEntity.DataBean>(photoTypeData, dataBeans, mMultiple);
                    dataBeanBaseMsg.setmTitle(title);
                    dataBeanBaseMsg.setTag(tag);
                    dataBeanBaseMsg.setUrl(Url);
                    dataBeanBaseMsg.setUrlTag(urlTag);
                    EventBus.getDefault().post(dataBeanBaseMsg);
                }

            }
            finish();
        }
    }

    /**
     * 设置数据到适配器总控
     */
    private void setAdapterCtrl() {
        //适配器不为空刷新反复
        if (stringBaseRecyclerAdapter != null) {
            stringBaseRecyclerAdapter.notifyDataSetChanged();
            return;
        }
        if (productAdapter != null) {
            productAdapter.refresh(productData);
            return;
        }
        if (urlTag == OptionHelp.UrlTag.PRODUCT) {
            productAdapterNew();
        } else if (urlTag == OptionHelp.UrlTag.PACKAGE) {
            packageAdapter();
        } else {
            //多选的
            if (mMultiple) {
                multipleAdapter();
            } else {
                singleSetAdapter();
            }
        }
        mRecyclerView.loadMoreFinish(false, false);
    }
    //------------------网络专区-----------------------------------------

    /**
     * 获取产品
     */
    private void getProduct() {
        //请求实体
        JavaBeanRequest<ProductEntity> districtBeanJavaBeanRequest = new JavaBeanRequest<ProductEntity>(Url, RequestMethod.POST, ProductEntity.class);
        HttpListener<ProductEntity> searchByCustmor = new HttpListener<ProductEntity>() {
            @Override
            public void onSucceed(int what, Response<ProductEntity> response) {
                mRotateHeaderGridViewFrame.refreshComplete();
                boolean ok = response.get().isOK();
                if (ok) {
                    mAllData.clear();
                    productData = response.get().getData();
                    productDataAdded.clear();
                    getSum();
                    if (productData != null) {
                        setAdapterCtrl();
                    } else {
                        mRecyclerView.loadMoreFinish(true, false);
                    }
                } else {
                    mRecyclerView.loadMoreError(0, getString(R.string.data_load_error));
                }
            }

            @Override
            public void onFailed(int what, Response<ProductEntity> response) {
                mRotateHeaderGridViewFrame.refreshComplete();
                mRecyclerView.loadMoreError(0, getString(R.string.data_load_error));
            }
        };
        request(0, districtBeanJavaBeanRequest, searchByCustmor, false, false);
    }

    /**
     * 获取包套
     */
    private void getPackage() {
        //请求实体
        JavaBeanRequest<PackageEntity> districtBeanJavaBeanRequest = new JavaBeanRequest<PackageEntity>(Url, RequestMethod.POST, PackageEntity.class);
        HttpListener<PackageEntity> searchByCustmor = new HttpListener<PackageEntity>() {
            @Override
            public void onSucceed(int what, Response<PackageEntity> response) {
                mRotateHeaderGridViewFrame.refreshComplete();
                boolean ok = response.get().isOK();
                if (ok) {
                    mAllData.clear();
                    packageData = response.get().getData();
                    if (packageData != null) {
                        for (PackageEntity.DataBean da : packageData) {
                            mAllData.add(da.getPackage_name());
                        }
                        setAdapterCtrl();
                    } else {
                        mRecyclerView.loadMoreFinish(true, false);
                    }
                } else {
                    mRecyclerView.loadMoreError(0, getString(R.string.data_load_error));
                }
            }

            @Override
            public void onFailed(int what, Response<PackageEntity> response) {
                mRotateHeaderGridViewFrame.refreshComplete();
                mRecyclerView.loadMoreError(0, getString(R.string.data_load_error));
            }
        };
        request(2, districtBeanJavaBeanRequest, searchByCustmor, false, false);
    }

    /**
     * 获取消费类型
     */
    private void getConsumptionType() {
        //请求实体
        JavaBeanRequest<ConsumptionTypeEntity> districtBeanJavaBeanRequest = new JavaBeanRequest<ConsumptionTypeEntity>(Url, RequestMethod.POST, ConsumptionTypeEntity.class);
        HttpListener<ConsumptionTypeEntity> searchByCustmor = new HttpListener<ConsumptionTypeEntity>() {
            @Override
            public void onSucceed(int what, Response<ConsumptionTypeEntity> response) {
                mRotateHeaderGridViewFrame.refreshComplete();
                boolean ok = response.get().isOK();
                if (ok) {
                    mAllData.clear();
                    consumptionTypeData = response.get().getData();
                    if (consumptionTypeData != null) {
                        for (ConsumptionTypeEntity.DataBean da : consumptionTypeData) {
                            mAllData.add(da.getConsumption_name());
                        }
                        setAdapterCtrl();
                    } else {
                        mRecyclerView.loadMoreFinish(true, false);
                    }
                } else {
                    mRecyclerView.loadMoreError(0, getString(R.string.data_load_error));
                }
            }

            @Override
            public void onFailed(int what, Response<ConsumptionTypeEntity> response) {
                mRotateHeaderGridViewFrame.refreshComplete();
                mRecyclerView.loadMoreError(0, getString(R.string.data_load_error));
            }
        };
        request(3, districtBeanJavaBeanRequest, searchByCustmor, false, false);
    }

    /**
     * 获取接单点
     */
    private void getOrderReceivingSite() {
        //请求实体
        JavaBeanRequest<OrderReceivingSiteEntity> districtBeanJavaBeanRequest = new JavaBeanRequest<OrderReceivingSiteEntity>(Url, RequestMethod.POST, OrderReceivingSiteEntity.class);
        HttpListener<OrderReceivingSiteEntity> searchByCustmor = new HttpListener<OrderReceivingSiteEntity>() {
            @Override
            public void onSucceed(int what, Response<OrderReceivingSiteEntity> response) {
                mRotateHeaderGridViewFrame.refreshComplete();
                boolean ok = response.get().isOK();
                if (ok) {
                    mAllData.clear();
                    orderReceivingSiteData = response.get().getData();
                    if (orderReceivingSiteData != null) {
                        for (OrderReceivingSiteEntity.DataBean da : orderReceivingSiteData) {
                            mAllData.add(da.getAcceptoraddress_name());
                        }
                        setAdapterCtrl();
                    } else {
                        mRecyclerView.loadMoreFinish(true, false);
                    }
                } else {
                    mRecyclerView.loadMoreError(0, getString(R.string.data_load_error));
                }
            }

            @Override
            public void onFailed(int what, Response<OrderReceivingSiteEntity> response) {
                mRotateHeaderGridViewFrame.refreshComplete();
                mRecyclerView.loadMoreError(0, getString(R.string.data_load_error));
            }
        };
        request(4, districtBeanJavaBeanRequest, searchByCustmor, false, false);
    }

    /**
     * 门市接待
     */
    public void getOutletsReception() {
        //请求实体
        JavaBeanRequest<OutletsReceptionEntity> districtBeanJavaBeanRequest = new JavaBeanRequest<OutletsReceptionEntity>(Url, RequestMethod.POST, OutletsReceptionEntity.class);
        HttpListener<OutletsReceptionEntity> searchByCustmor = new HttpListener<OutletsReceptionEntity>() {
            @Override
            public void onSucceed(int what, Response<OutletsReceptionEntity> response) {
                mRotateHeaderGridViewFrame.refreshComplete();
                boolean ok = response.get().isOK();
                if (ok) {
                    mAllData.clear();
                    outletsReceptionData = response.get().getData();
                    if (outletsReceptionData != null) {
                        for (OutletsReceptionEntity.DataBean da : outletsReceptionData) {
                            mAllData.add(da.getStaffName());
                        }
                        setAdapterCtrl();
                    } else {
                        mRecyclerView.loadMoreFinish(true, false);
                        toastShort(response.get().getMsg());
                    }
                } else {
                    mRecyclerView.loadMoreError(0, getString(R.string.data_load_error));
                    toastShort(response.get().getMsg());
                }
            }

            @Override
            public void onFailed(int what, Response<OutletsReceptionEntity> response) {
                mRotateHeaderGridViewFrame.refreshComplete();
                mRecyclerView.loadMoreError(0, getString(R.string.data_load_error));
            }
        };
        request(5, districtBeanJavaBeanRequest, searchByCustmor, false, false);

    }

    /**
     * 客户分区
     */
    public void getCustomerZone() {
        //请求实体
        JavaBeanRequest<CustomerZoneEntity> districtBeanJavaBeanRequest = new JavaBeanRequest<CustomerZoneEntity>(Url, RequestMethod.POST, CustomerZoneEntity.class);
        HttpListener<CustomerZoneEntity> searchByCustmor = new HttpListener<CustomerZoneEntity>() {
            @Override
            public void onSucceed(int what, Response<CustomerZoneEntity> response) {
                mRotateHeaderGridViewFrame.refreshComplete();
                boolean ok = response.get().isOK();
                if (ok) {
                    mAllData.clear();
                    customerZoneData = response.get().getData();
                    if (customerZoneData != null) {
                        for (CustomerZoneEntity.DataBean da : customerZoneData) {
                            mAllData.add(da.getArea_name());
                        }
                        setAdapterCtrl();
                    } else {
                        mRecyclerView.loadMoreFinish(true, false);
                        toastShort(response.get().getMsg());
                    }
                } else {
                    mRecyclerView.loadMoreError(0, getString(R.string.data_load_error));
                    toastShort(response.get().getMsg());
                }
            }

            @Override
            public void onFailed(int what, Response<CustomerZoneEntity> response) {
                mRecyclerView.loadMoreError(0, getString(R.string.data_load_error));
                mRotateHeaderGridViewFrame.refreshComplete();
            }
        };
        request(6, districtBeanJavaBeanRequest, searchByCustmor, false, false);

    }


    /**
     * 客户来源
     */
    public void getCustomerSource() {
        //请求实体
        JavaBeanRequest<CustomerSourceEntity> districtBeanJavaBeanRequest = new JavaBeanRequest<CustomerSourceEntity>(Url, RequestMethod.POST, CustomerSourceEntity.class);
        HttpListener<CustomerSourceEntity> searchByCustmor = new HttpListener<CustomerSourceEntity>() {
            @Override
            public void onSucceed(int what, Response<CustomerSourceEntity> response) {
                mRotateHeaderGridViewFrame.refreshComplete();
                boolean ok = response.get().isOK();
                if (ok) {
                    mAllData.clear();
                    customerSourceData = response.get().getData();
                    if (customerSourceData != null) {
                        for (CustomerSourceEntity.DataBean da : customerSourceData) {
                            mAllData.add(da.getCus_name());
                        }
                        setAdapterCtrl();
                    } else {
                        mRecyclerView.loadMoreFinish(true, false);
                        toastShort(response.get().getMsg());
                    }
                } else {
                    mRecyclerView.loadMoreError(0, getString(R.string.data_load_error));
                    toastShort(response.get().getMsg());
                }
            }

            @Override
            public void onFailed(int what, Response<CustomerSourceEntity> response) {
                mRecyclerView.loadMoreError(0, getString(R.string.data_load_error));
                mRotateHeaderGridViewFrame.refreshComplete();
            }
        };
        request(7, districtBeanJavaBeanRequest, searchByCustmor, false, false);
    }

    /**
     * 客户意向
     */
    public void getCustomerIntention() {
        //请求实体
        JavaBeanRequest<CustomerIntentionEntity> districtBeanJavaBeanRequest = new JavaBeanRequest<CustomerIntentionEntity>(Url, RequestMethod.POST, CustomerIntentionEntity.class);
        HttpListener<CustomerIntentionEntity> searchByCustmor = new HttpListener<CustomerIntentionEntity>() {
            @Override
            public void onSucceed(int what, Response<CustomerIntentionEntity> response) {
                mRotateHeaderGridViewFrame.refreshComplete();
                boolean ok = response.get().isOK();
                if (ok) {
                    mAllData.clear();
                    customerIntentionData = response.get().getData();
                    if (customerIntentionData != null) {
                        for (CustomerIntentionEntity.DataBean da : customerIntentionData) {
                            mAllData.add(da.getIntention_name());
                        }
                        setAdapterCtrl();
                    } else {
                        mRecyclerView.loadMoreFinish(true, false);
                        toastShort(response.get().getMsg());
                    }
                } else {
                    mRecyclerView.loadMoreError(0, getString(R.string.data_load_error));
                }
            }

            @Override
            public void onFailed(int what, Response<CustomerIntentionEntity> response) {
                mRecyclerView.loadMoreError(0, getString(R.string.data_load_error));
                mRotateHeaderGridViewFrame.refreshComplete();
            }
        };
        request(8, districtBeanJavaBeanRequest, searchByCustmor, false, false);

    }

    /**
     * 获取店面
     */
    public void getPhotoShop() {
        //请求实体
        JavaBeanRequest<PhotoShopEntity> districtBeanJavaBeanRequest = new JavaBeanRequest<PhotoShopEntity>(Url, RequestMethod.POST, PhotoShopEntity.class);
        HttpListener<PhotoShopEntity> searchByCustmor = new HttpListener<PhotoShopEntity>() {
            @Override
            public void onSucceed(int what, Response<PhotoShopEntity> response) {
                mRotateHeaderGridViewFrame.refreshComplete();
                boolean ok = response.get().isOK();
                if (ok) {
                    mAllData.clear();
                    photoShopData = response.get().getData();
                    if (photoShopData != null) {
                        for (PhotoShopEntity.DataBean da : photoShopData) {
                            //   if (da.getIs_photo().equals("0")){
                            mAllData.add(da.getBelong_shop_name());
                            // }
                        }
                        setAdapterCtrl();
                    } else {
                        mRecyclerView.loadMoreFinish(true, false);
                        toastShort(response.get().getMsg());
                    }
                } else {
                    toastShort(response.get().getMsg());
                    mRecyclerView.loadMoreError(0, getString(R.string.data_load_error));
                }
            }

            @Override
            public void onFailed(int what, Response<PhotoShopEntity> response) {
                mRecyclerView.loadMoreError(0, getString(R.string.data_load_error));
                mRotateHeaderGridViewFrame.refreshComplete();
            }
        };
        request(8, districtBeanJavaBeanRequest, searchByCustmor, false, false);

    }

    /**
     * 获取新单类型
     */
    public void getNewOrderType() {
        //请求实体
        JavaBeanRequest<NewOrderTypeEntity> districtBeanJavaBeanRequest = new JavaBeanRequest<NewOrderTypeEntity>(Url, RequestMethod.POST, NewOrderTypeEntity.class);
        HttpListener<NewOrderTypeEntity> searchByCustmor = new HttpListener<NewOrderTypeEntity>() {
            @Override
            public void onSucceed(int what, Response<NewOrderTypeEntity> response) {
                mRotateHeaderGridViewFrame.refreshComplete();
                boolean ok = response.get().isOK();
                if (ok) {
                    mAllData.clear();
                    newOrderTyepData = response.get().getData();
                    if (newOrderTyepData != null) {
                        for (NewOrderTypeEntity.DataBean da : newOrderTyepData) {
                            mAllData.add(da.getOrdertype());
                        }
                        setAdapterCtrl();
                    } else {
                        mRecyclerView.loadMoreFinish(true, false);
                        toastShort(response.get().getMsg());
                    }
                } else {
                    toastShort(response.get().getMsg());
                    mRecyclerView.loadMoreError(0, getString(R.string.data_load_error));
                }
            }

            @Override
            public void onFailed(int what, Response<NewOrderTypeEntity> response) {
                mRecyclerView.loadMoreError(0, getString(R.string.data_load_error));
                mRotateHeaderGridViewFrame.refreshComplete();
            }
        };
        request(8, districtBeanJavaBeanRequest, searchByCustmor, false, false);

    }

    /**
     * 获取拍摄主题
     */
    public void getThemeData() {
        //请求实体
        JavaBeanRequest<ThemeEntity> districtBeanJavaBeanRequest = new JavaBeanRequest<ThemeEntity>(Url, RequestMethod.POST, ThemeEntity.class);
        HttpListener<ThemeEntity> searchByCustmor = new HttpListener<ThemeEntity>() {
            @Override
            public void onSucceed(int what, Response<ThemeEntity> response) {
                mRotateHeaderGridViewFrame.refreshComplete();
                boolean ok = response.get().isOK();
                if (ok) {
                    mAllData.clear();
                    themeData = response.get().getData();
                    if (themeData != null) {
                        for (ThemeEntity.DataBean da : themeData) {
                            mAllData.add(da.getTopic());
                        }
                        setAdapterCtrl();
                    } else {
                        mRecyclerView.loadMoreFinish(true, false);
                        toastShort(response.get().getMsg());
                    }
                } else {
                    toastShort(response.get().getMsg());
                    mRecyclerView.loadMoreError(0, getString(R.string.data_load_error));
                }
            }

            @Override
            public void onFailed(int what, Response<ThemeEntity> response) {
                mRecyclerView.loadMoreError(0, getString(R.string.data_load_error));
                mRotateHeaderGridViewFrame.refreshComplete();
            }
        };
        request(8, districtBeanJavaBeanRequest, searchByCustmor, false, false);

    }

    /**
     * 选片类型
     */
    public void getOptionPanelType() {
        //请求实体
        JavaBeanRequest<OptionPanelTypeEntity> districtBeanJavaBeanRequest = new JavaBeanRequest<OptionPanelTypeEntity>(Url, OptionPanelTypeEntity.class);
        HttpListener<OptionPanelTypeEntity> searchByCustmor = new HttpListener<OptionPanelTypeEntity>() {
            @Override
            public void onSucceed(int what, Response<OptionPanelTypeEntity> response) {
                mRotateHeaderGridViewFrame.refreshComplete();
                boolean ok = response.get().isOK();
                if (ok) {
                    mAllData.clear();
                    optionPanelTypeData = response.get().getData();
                    if (optionPanelTypeData != null) {
                        for (OptionPanelTypeEntity.DataBean da : optionPanelTypeData) {
                            mAllData.add(da.getSelectordername());
                        }
                        setAdapterCtrl();
                    } else {
                        mRecyclerView.loadMoreFinish(true, false);
                        toastShort(response.get().getMsg());
                    }
                } else {
                    toastShort(response.get().getMsg());
                    mRecyclerView.loadMoreError(0, getString(R.string.data_load_error));
                }
            }

            @Override
            public void onFailed(int what, Response<OptionPanelTypeEntity> response) {
                mRecyclerView.loadMoreError(0, getString(R.string.data_load_error));
                mRotateHeaderGridViewFrame.refreshComplete();
            }
        };
        request(8, districtBeanJavaBeanRequest, searchByCustmor, false, false);
    }

    /**
     * 拍照类型
     */
    public void getPhotoType() {
        //请求实体
        JavaBeanRequest<PhotoTypeEntity> districtBeanJavaBeanRequest = new JavaBeanRequest<PhotoTypeEntity>(Url, PhotoTypeEntity.class);
        HttpListener<PhotoTypeEntity> searchByCustmor = new HttpListener<PhotoTypeEntity>() {
            @Override
            public void onSucceed(int what, Response<PhotoTypeEntity> response) {
                mRotateHeaderGridViewFrame.refreshComplete();
                boolean ok = response.get().isOK();
                if (ok) {
                    mAllData.clear();
                    photoTypeData = response.get().getData();
                    if (photoTypeData != null) {
                        for (PhotoTypeEntity.DataBean da : photoTypeData) {
                            mAllData.add(da.getPhototype());
                        }
                        setAdapterCtrl();
                    } else {
                        mRecyclerView.loadMoreFinish(true, false);
                        toastShort(response.get().getMsg());
                    }
                } else {
                    toastShort(response.get().getMsg());
                    mRecyclerView.loadMoreError(0, getString(R.string.data_load_error));
                }
            }

            @Override
            public void onFailed(int what, Response<PhotoTypeEntity> response) {
                mRecyclerView.loadMoreError(0, getString(R.string.data_load_error));
                mRotateHeaderGridViewFrame.refreshComplete();
            }
        };
        request(8, districtBeanJavaBeanRequest, searchByCustmor, false, false);
    }

    //--------------------------------------适配器专区----------------------------------------

    /**
     * 设置多选适配器
     */
    private void multipleAdapter() {
        stringBaseRecyclerAdapter = new BaseRecyclerAdapter<String>(mRecyclerView, mAllData, R.layout.item_checktextview) {
            @Override
            public void convert(RecyclerHolder holder, String item, final int position, boolean isScrolling) {
                CheckedTextView view = holder.getView(R.id.tv_checked);
                view.setText(item);
                view.setChecked(false);
                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        CheckedTextView checkedTextView = (CheckedTextView) v;
                        checkedTextView.toggle();
                        if (checkedTextView.isChecked()) {
                            currentChecked.add(mAllData.get(position));
                        } else {
                            currentChecked.remove(mAllData.get(position));
                        }
                    }
                });
                if (currentChecked != null) {
                    if (currentChecked.contains(item)) {
                        view.setChecked(true);
                    } else {
                        view.setChecked(false);
                    }
                }
            }
        };
    }

    /**
     * 设置单选适配器
     */

    private void singleSetAdapter() {
        stringBaseRecyclerAdapter = new BaseRecyclerAdapter<String>(mRecyclerView, mAllData, R.layout.item_textview) {
            @Override
            public void convert(RecyclerHolder holder, String item, int position, boolean isScrolling) {
                TextView view = holder.getView(R.id.tv_item);
                view.setText(item);
                if (currentChecked != null && currentChecked.size() > 0) {
                    String s = currentChecked.get(0);
                    if (item.equals(s)) {
                        view.setBackgroundColor(getResources().getColor(R.color.myBackground_f7));
                    }
                }

            }
        };
    }

    /**
     * 包套的适配器
     */
    private void packageAdapter() {
        new BaseRecyclerAdapter<PackageEntity.DataBean>(mRecyclerView, packageData, R.layout.item_package) {
            @Override
            public void convert(RecyclerHolder holder, PackageEntity.DataBean item, int position, boolean isScrolling) {
                CheckedTextView packagename = holder.getView(R.id.tv_package_name);
                packagename.setText(item.getPackage_name());
                TextView tv_right = holder.getView(R.id.tv_right_data);
                tv_right.setText("¥" + item.getPackage_price());
                if (!mMultiple) {//单选
                    holder.getView(R.id.item_package_line).setVisibility(View.GONE);
                    packagename.setCompoundDrawables(null, null, null, null);
                    if (currentChecked != null && currentChecked.size() > 0) {
                        String s = currentChecked.get(0);
                        if (!StringUtils.empty(s)) {
                            if (item.getId() == Integer.parseInt(s)) {
                                holder.getView(R.id.item_package_root_layout).setBackgroundColor(getResources().getColor(R.color.myBackground_f7));
                            }
                        }
                    }
                } else {

                }
            }
        };
    }

    /**
     * 产品的适配器(多选勾选的)
     */
    private void productAdapter() {
        //单选
        productAdapter = new BaseRecyclerAdapter<ProductEntity.DataBean>(mRecyclerView, productData, R.layout.item_package) {
            @Override
            public void convert(RecyclerHolder holder, final ProductEntity.DataBean item, int position, boolean isScrolling) {
                CheckedTextView packagename = holder.getView(R.id.tv_package_name);
                packagename.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        CheckedTextView checkedTextView = (CheckedTextView) v;
                        checkedTextView.toggle();
                        if (checkedTextView.isChecked()) {
                            currentChecked.add(item.getId() + "");
                        } else {
                            currentChecked.remove(item.getId() + "");
                        }
                    }
                });
                if (currentChecked != null) {
                    if (currentChecked.contains(item.getId() + "")) {
                        packagename.setChecked(true);
                    } else {
                        packagename.setChecked(false);
                    }
                }
                packagename.setText(item.getItem_name());
                TextView tv_right = holder.getView(R.id.tv_right_data);
                tv_right.setText("¥" + item.getItem_price());
                if (!mMultiple) {//单选
                    holder.getView(R.id.item_package_line).setVisibility(View.GONE);
                    packagename.setCompoundDrawables(null, null, null, null);
                    if (currentChecked != null && currentChecked.size() > 0) {
                        String s = currentChecked.get(0);
                        if (!StringUtils.empty(s)) {
                            if (item.getId() == Integer.parseInt(s)) {
                                holder.getView(R.id.item_package_root_layout).setBackgroundColor(getResources().getColor(R.color.myBackground_f7));
                            }
                        }
                    }
                } else {
                    if (currentChecked != null && currentChecked.size() > 0) {
                        for (String s : currentChecked) {
                            if (s.equals(productData.get(position).getId() + "")) {
                                packagename.setChecked(true);
                                break;
                            }
                        }
                    }
                }
            }
        };
    }

    /**
     * 产品的适配器
     */
    private void productAdapterNew() {
        //单选
        productAdapter = new BaseRecyclerAdapter<ProductEntity.DataBean>(mRecyclerView, productData, R.layout.item_option_product) {
            @Override
            public void convert(RecyclerHolder holder, final ProductEntity.DataBean item, int position, boolean isScrolling) {
                if (!isScrolling) {
                    TextView tvProductInfo = (TextView) holder.getView(R.id.tv_product_info);
                    TextView tvNowPrice = (TextView) holder.getView(R.id.tv_now_price);
                    ImageButton btMinus = (ImageButton) holder.getView(R.id.bt_minus);
                    TextView tvNumber = (TextView) holder.getView(R.id.tv_number);
                    ImageButton btPlus = (ImageButton) holder.getView(R.id.bt_plus);
                    tvProductInfo.setText(item.getItem_name());
                    tvProductInfo.append("\n¥:" + item.getItem_price());
                    btPlus.setOnClickListener(l -> {
                                item.setNumber((item.getNumber() + 1));
                                ProductEntity.DataBean dataBean = new ProductEntity.DataBean();
                                dataBean.setId(item.getId());
                                dataBean.setItem_name(item.getItem_name());
                                dataBean.setItem_price(item.getItem_price());
                                dataBean.setNow_price(item.getItem_price());
                                dataBean.setNumber(1);
                                productDataAdded.add(dataBean);
                                productAdapter.notifyItemChanged(position);
                                getSum();
                            }
                    );
                    if (item.getNumber() > 0) {
                        tvNumber.setVisibility(View.VISIBLE);
                        btMinus.setVisibility(View.VISIBLE);
                        tvNumber.setText(item.getNumber() + "");
                    } else {
                        tvNumber.setVisibility(View.GONE);
                        btMinus.setVisibility(View.GONE);
                    }
                    if (item.getNumber() > 0) {
                        tvNumber.setVisibility(View.VISIBLE);
                        tvNumber.setText(item.getNumber() + "");
                        btMinus.setVisibility(View.VISIBLE);
                        btMinus.setOnClickListener(l -> {
                            item.setNumber((item.getNumber() - 1));
                            removeProduct(item.getId());
                            getSum();
                            productAdapter.notifyDataSetChanged();

                        });
                    }
                }
            }
        };
    }

    /**
     * 移除产品的id
     *
     * @param id
     */
    public void removeProduct(int id) {
        int ps = -1;
        for (ProductEntity.DataBean d : productDataAdded) {
            if (d.getId() == id) {
                ps = productDataAdded.lastIndexOf(d);
            }
        }
        if (ps >= 0) {
            int number = productDataAdded.get(ps).getNumber() - 1;
            if (number > 0) {
                productDataAdded.get(ps).setNumber(number);
            } else {
                productDataAdded.remove(ps);
            }
        }
    }

    /**
     * 购物车
     */
    private void productShoppingCart() {
        //单选
        shoppingCartAdapter = new BaseRecyclerAdapter<ProductEntity.DataBean>(addedRecy, productDataAdded, R.layout.item_option_product) {
            @Override
            public void convert(RecyclerHolder holder, final ProductEntity.DataBean item, int position, boolean isScrolling) {
                if (!isScrolling && recyclerView.isComputingLayout()) {
                    TextView tvProductInfo = (TextView) holder.getView(R.id.tv_product_info);
                    TextView tvNowPrice = (TextView) holder.getView(R.id.tv_now_price);
                    ImageButton btMinus = (ImageButton) holder.getView(R.id.bt_minus);
                    TextView tvNumber = (TextView) holder.getView(R.id.tv_number);
                    ImageButton btPlus = (ImageButton) holder.getView(R.id.bt_plus);
                    tvNowPrice.setText("¥:" + item.getNow_price());
                    tvNowPrice.setOnClickListener(l -> {
                    });
                    tvProductInfo.setText(item.getItem_name());
                    tvProductInfo.append(new MyString("\n¥:" + item.getItem_price()).setDeletLine());
                    btPlus.setOnClickListener(l -> {
                                item.setNumber((item.getNumber() + 1));
                                productDataAdded.get(position).setNumber(item.getNumber());
                                shoppingCartAdapter.notifyDataSetChanged();
                                getSum();
                            }
                    );
                    if (item.getNumber() > 0) {
                        tvNumber.setVisibility(View.VISIBLE);
                        tvNumber.setText(item.getNumber() + "");
                        tvNowPrice.setOnClickListener(l -> {
                            showChangePrice(position);
                        });
                        btMinus.setVisibility(View.VISIBLE);
                        btMinus.setOnClickListener(l -> {
                            item.setNumber((item.getNumber() - 1));
                            if (item.getNumber() == 0) {
                                productDataAdded.remove(position);
                                shoppingCartAdapter.refresh(productDataAdded);
                            } else {
                                productDataAdded.get(position).setNumber(item.getNumber());
                                shoppingCartAdapter.notifyDataSetChanged();
                            }
                            getSum();
                        });
                    } else {
                        productDataAdded.remove(item);
                        shoppingCartAdapter.remove(position);
                    }
                }
            }
        };
    }

    public void showChangePrice(int postion) {
        View inflate = getLayoutInflater().inflate(R.layout.pop_price,null);
        EditText editText = inflate.findViewById(R.id.et_price);
        editText.setText(productDataAdded.get(postion).getNow_price()+"");
        editText.setSelection(editText.getText().toString().length());
        AlertDialog.newBuilder(this).setTitle("产品价格修改:")
                .setView(inflate)
                .setMessage(productDataAdded.get(postion).getItem_name())
                .setPositiveButton("确定", (DialogInterface var1, int var2) -> {
                    if (editText.getText().toString()!=null){
                        String string = editText.getText().toString();
                        int i = Integer.parseInt(string);
                        productDataAdded.get(postion).setNow_price(i);
                        shoppingCartAdapter.notifyDataSetChanged();
                        getSum();
                    }else {
                        return;
                    }
                })
                .setNegativeButton("取消", (DialogInterface var1, int var2) -> {
                }).show();

    }

    /**
     * 计算总价并显示到只听
     */
    public void getSum() {
        double money = 0;
        int count = 0;
        for (ProductEntity.DataBean d : productDataAdded) {
            count += d.getNumber();
            money += (d.getNow_price() * d.getNumber());
        }
        productSumInfo.setText("¥:" + money);
        productSumInfo.append("\n共计" + count + "件商品");
        tvPopProductSumInfo.setText("¥:" + money);
        tvPopProductSumInfo.append("\n共计" + count + "件商品");
        tvPopAddProduct.setBackground(null);
        addProduct.setBackground(null);
        if (count == 0) {
            tvPopAddProduct.setBackgroundColor(getResources().getColor(R.color.noticeOrange));
            addProduct.setBackgroundColor(getResources().getColor(R.color.noticeOrange));
            btnPopDeleteAll.setVisibility(View.GONE);
        } else {
            btnPopDeleteAll.setVisibility(View.VISIBLE);
            tvPopAddProduct.setBackgroundColor(getResources().getColor(R.color.themeColor));
            addProduct.setBackgroundColor(getResources().getColor(R.color.themeColor));
        }
    }

    /**
     * 重置所有条目中的数量
     */
    public void resetNumber() {
        for (ProductEntity.DataBean da : productData) {
            da.setNumber(0);
        }
    }

    public void addProduct() {
        Intent intent = getIntent();
        if (intent.hasExtra("orderId") && intent.hasExtra("customerId")) {
            String orderId = intent.getExtras().getString("orderId");
            String customerId = intent.getExtras().getString("customerId");
            StringBuilder stringBuilder = new StringBuilder();
            for (ProductEntity.DataBean pd : productDataAdded) {
//                产品ID|数量|单价;
                stringBuilder.append(pd.getId());
                stringBuilder.append("|");
                stringBuilder.append(pd.getNumber());
                stringBuilder.append("|");
                stringBuilder.append(pd.getNow_price());
                stringBuilder.append(";");
            }
            stringBuilder.deleteCharAt(stringBuilder.lastIndexOf(";"));
            String fullUrl = Contact.getFullUrl(Contact.ADD_PRODUCT, Contact.TOKEN, orderId, customerId, stringBuilder.toString(), 0, 0, App.getApplication().getUserInfor().getShop_code(),
                    App.getApplication().getUserInfor().getShop_name(), App.getApplication().getUserInfor().getBrandclass_id());
            //请求实体
            JavaBeanRequest<SimpleEntity> districtBeanJavaBeanRequest = new JavaBeanRequest<SimpleEntity>(fullUrl, SimpleEntity.class);
            HttpListener<SimpleEntity> searchByCustmor = new HttpListener<SimpleEntity>() {
                @Override
                public void onSucceed(int what, Response<SimpleEntity> response) {
                    if (response.get().isOK()) {
                        EventBus.getDefault().post("freshProduct");
                        onBackPressed();
                    }
                }

                @Override
                public void onFailed(int what, Response<SimpleEntity> response) {
                }
            };
            request(0, districtBeanJavaBeanRequest, searchByCustmor, false, false);
        }
    }


}
