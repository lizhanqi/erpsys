package com.suxuantech.erpsys.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckedTextView;
import android.widget.TextView;

import com.suxuantech.erpsys.App;
import com.suxuantech.erpsys.R;
import com.suxuantech.erpsys.common.OptionHelp;
import com.suxuantech.erpsys.entity.ConsumptionTypeEntity;
import com.suxuantech.erpsys.entity.CustomerIntentionEntity;
import com.suxuantech.erpsys.entity.CustomerSourceEntity;
import com.suxuantech.erpsys.entity.CustomerZoneEntity;
import com.suxuantech.erpsys.entity.OrderReceivingSiteEntity;
import com.suxuantech.erpsys.entity.OutletsReceptionEntity;
import com.suxuantech.erpsys.entity.PackageEntity;
import com.suxuantech.erpsys.entity.ProductEntity;
import com.suxuantech.erpsys.eventmsg.BaseMsg;
import com.suxuantech.erpsys.eventmsg.SmpileEventMsg;
import com.suxuantech.erpsys.nohttp.Contact;
import com.suxuantech.erpsys.nohttp.HttpListener;
import com.suxuantech.erpsys.nohttp.JavaBeanRequest;
import com.suxuantech.erpsys.ui.activity.base.ImmersedBaseActivity;
import com.suxuantech.erpsys.ui.adapter.BaseRecyclerAdapter;
import com.suxuantech.erpsys.ui.adapter.RecyclerHolder;
import com.suxuantech.erpsys.ui.widget.DefaultItemDecoration;
import com.suxuantech.erpsys.ui.widget.DefineLoadMoreView;
import com.suxuantech.erpsys.utils.StringUtils;
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
public class OptionActivity extends ImmersedBaseActivity {
    @BindView(R.id.recycler_view)
    SwipeMenuRecyclerView mRecyclerView;
    @BindView(R.id.rotate_header_grid_view_frame)
    PtrClassicFrameLayout mRotateHeaderGridViewFrame;
    /**
     * checkedData当前选中的，单选的话只认下表为0的那个才是
     * mAllData全部选项
     */
    ArrayList<String > checkedData,mAllData, currentChecked ;
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
    private BaseRecyclerAdapter<ProductEntity.DataBean> productAdapter;
    @Override
    public void widgetClick(View v) {
        switch (v.getId()){
            default:
            case R.id.tv_nav_right://多选确定时候关闭页面的
                StringBuffer sb = new StringBuffer();
            if (urlTag!= OptionHelp.UrlTag.PRODUCT){
                for(String f:currentChecked){
                    sb.append(f+"\n");
                }
            }else {
                ArrayList<ProductEntity.DataBean> dataBeans = new ArrayList<>();
                for(String f:currentChecked){
                    int i = Integer.parseInt(f);
                    for (ProductEntity.DataBean pd:productData){
                        if (pd.getId()== i){
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
                break;
            case R.id.tv_nav_left:
                finish();
                break;
        }
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.refresh_and_recyclerview);
        useButterKnife();
        showUserDefinedNav();
        //初始化传过来的IntentData
        initIntentData( getIntent());
        //初始化recycleview
        initRecycleView();
        /**
         * 设置一下刷新控件属性
         */
        initRefreshView();

        //如果路径不为空直接调用自动刷新（这个一定要在initIntentData后执行，Url有没有数据是那里获取到的）
        if (urlTag!=null){
            getUrlAndDataCtrl(false);
            mRotateHeaderGridViewFrame.postDelayed(new Runnable() {
                @Override
                public void run() {
                    mRotateHeaderGridViewFrame.autoRefresh();
                }
            },0);
        }else {
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
                if (Url==null) {
                    mRotateHeaderGridViewFrame.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            mRotateHeaderGridViewFrame.refreshComplete();
                            stringBaseRecyclerAdapter.notifyDataSetChanged();
                        }
                    }, 2000);
                }else {
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
        if (urlTag!= OptionHelp.UrlTag.PRODUCT) {
            mRecyclerView.addItemDecoration(new DefaultItemDecoration(getResources().getColor(R.color.mainNavline_e7)));
        }
//       mRecyclerView.useDefaultLoadMore();
        DefineLoadMoreView defineLoadMoreView = new DefineLoadMoreView(this);
        mRecyclerView.addFooterView(defineLoadMoreView);
        mRecyclerView.setLoadMoreView(defineLoadMoreView);
        mRecyclerView.setLoadMoreListener(mLoadMoreListener);
        mRecyclerView.setSwipeItemClickListener(new SwipeItemClickListener() {
            @Override
            public void onItemClick(View itemView, int position) {
                singleResult(position);
            }

        });
    }

    /**
     * 获取传递过来的的信息
     * @param intent
     */
    private void initIntentData(Intent intent) {
        //判断是否为空
        if (intent != null) {
                //获取是否是多选
                mMultiple = intent.getBooleanExtra("Multiple",false);
                  if (mMultiple){
                        setUseDefinedNavRightText(getString(R.string.complete));
                 }
                 tag = intent.getStringExtra("Tag");
                 title = intent.getStringExtra("Title");
              //设置标题
                setTitle(title);
                checkedData = intent.getStringArrayListExtra("Checked");
                //已经选中的
                if (checkedData==null){
                   checkedData=       new ArrayList<>();
                    }
              //当前选中的
                  currentChecked= (ArrayList<String>) checkedData.clone();
                //给个地址
                  urlTag = (OptionHelp.UrlTag) intent.getSerializableExtra("UrlTag");
         }
    }

    /**
     *获取网络路径和是否获取数据的总控
     */
    public  void getUrlAndDataCtrl(boolean getData){
        if (mAllData==null){
            mAllData =new ArrayList<>();
        }
        switch (urlTag){
            default:
            case PRODUCT:
                //包套
                Url= Contact.getFullUrl(Contact.PRODUCT, Contact.TOKEN, 0);
                if (getData){
                    getProduct();
                }
                break;

            case PACKAGE:
                //包套
                  Url= Contact.getFullUrl(Contact.PACKAGE, Contact.TOKEN, 0,0);
                  if (getData){
                      getPackage();
                  }
                break;
            case CONSUMPTION_TYPE:
                //消费类型
                Url= Contact.getFullUrl(Contact.CONSUMPTION_TYPE, Contact.TOKEN, 0);
                if(getData){
                 getConsumptionType();
                }
                break;
            case OUTLETS_RECEPTION:
                //门市接待
                Url=Contact.getFullUrl(Contact.OUTLETS_RECEPTION, Contact.TOKEN, App.getApplication().getUserInfor().getShop_code());
                if(getData) {
                    getOutletsReception();
                }
              break;
            case ORDER_RECEIVING_SITE:
                //接单点
                Url=Contact.getFullUrl(Contact.ORDER_RECEIVING_SITE, Contact.TOKEN, 0);
                if(getData) {
                    getOrderReceivingSite();
                }
                break;
            case CUSTOMER_ZONE:
                //客户分区
                Url=Contact.getFullUrl(Contact.CUSTOMER_ZONE, Contact.TOKEN, 0);
                if(getData) {
                    getCustomerZone();
                }
                break;
            case CUSTOMER_SOURCE:
                //客户来源
                Url=Contact.getFullUrl(Contact.CUSTOMER_SOURCE, Contact.TOKEN, 0);
                if(getData) {
                    getCustomerSource();
                }
                break;

            case CUSTOMER_INTENTION:
                //客户意向
                Url=Contact.getFullUrl(Contact.CUSTOMER_INTENTION, Contact.TOKEN, 0);
                if(getData) {
                    getCustomerIntention();
                }
                break;


        }
    }

    /**
     * 单选结果发送
     * @param position
     */
    private void singleResult(int position) {
        if (!mMultiple){
            //activity result 也可以设置
            Intent intent1 = new Intent();
            intent1.putExtra("result",mAllData.get(position));
            toast(mAllData.get(position));
            setResult(RESULT_OK,intent1);
            //发送EventBus事件也可以接收
            EventBus.getDefault().post(mAllData.get(position));
            EventBus.getDefault().post(new SmpileEventMsg(urlTag,mAllData.get(position)));
            if(Url!=null){

                if (consumptionTypeData!=null){
                    //消费类型
                    ArrayList<ConsumptionTypeEntity.DataBean> dataBeans = new ArrayList<>();
                    dataBeans.add(consumptionTypeData.get(position));
                    BaseMsg<ConsumptionTypeEntity.DataBean> dataBeanBaseMsg = new BaseMsg<>(consumptionTypeData, dataBeans, mMultiple);
                    dataBeanBaseMsg.setmTitle(title);
                    dataBeanBaseMsg.setTag(tag);
                    dataBeanBaseMsg.setUrl(Url);
                    dataBeanBaseMsg.setUrlTag(urlTag);
                    EventBus.getDefault().post(dataBeanBaseMsg);
                }else if (orderReceivingSiteData!=null){
                    //接单点
                    ArrayList<OrderReceivingSiteEntity.DataBean> dataBeans = new ArrayList<>();
                    dataBeans.add(orderReceivingSiteData.get(position));
                    BaseMsg<OrderReceivingSiteEntity.DataBean> dataBeanBaseMsg = new BaseMsg<>(orderReceivingSiteData, dataBeans, mMultiple);
                    dataBeanBaseMsg.setmTitle(title);
                    dataBeanBaseMsg.setTag(tag);
                    dataBeanBaseMsg.setUrl(Url);
                    dataBeanBaseMsg.setUrlTag(urlTag);
                    EventBus.getDefault().post(dataBeanBaseMsg);
                }else if (outletsReceptionData!=null){
                    //门市接待
                    ArrayList<OutletsReceptionEntity.DataBean> dataBeans = new ArrayList<>();
                    dataBeans.add(outletsReceptionData.get(position));
                    BaseMsg<OutletsReceptionEntity.DataBean> dataBeanBaseMsg = new BaseMsg<>(outletsReceptionData, dataBeans, mMultiple);
                    dataBeanBaseMsg.setmTitle(title);
                    dataBeanBaseMsg.setTag(tag);
                    dataBeanBaseMsg.setUrl(Url);
                    dataBeanBaseMsg.setUrlTag(urlTag);
                    EventBus.getDefault().post(dataBeanBaseMsg);
                }     else if (customerZoneData!=null){
                    //客户分区
                    ArrayList<CustomerZoneEntity.DataBean> dataBeans = new ArrayList<>();
                    dataBeans.add(customerZoneData.get(position));
                    BaseMsg<CustomerZoneEntity.DataBean> dataBeanBaseMsg = new BaseMsg<>(customerZoneData, dataBeans, mMultiple);
                    dataBeanBaseMsg.setmTitle(title);
                    dataBeanBaseMsg.setTag(tag);
                    dataBeanBaseMsg.setUrl(Url);
                    dataBeanBaseMsg.setUrlTag(urlTag);
                    EventBus.getDefault().post(dataBeanBaseMsg);
                }else  if(packageData!=null){
                    //包套
                    ArrayList<PackageEntity.DataBean> dataBeans = new ArrayList<>();
                    dataBeans.add(packageData.get(position));
                    BaseMsg<PackageEntity.DataBean> dataBeanBaseMsg = new BaseMsg<>(packageData, dataBeans, mMultiple);
                    dataBeanBaseMsg.setmTitle(title);
                    dataBeanBaseMsg.setTag(tag);
                    dataBeanBaseMsg.setUrl(Url);
                    dataBeanBaseMsg.setUrlTag(urlTag);
                    EventBus.getDefault().post(dataBeanBaseMsg);
                }else  if(customerSourceData!=null){
                    //客户来源
                    ArrayList<CustomerSourceEntity.DataBean> dataBeans = new ArrayList<>();
                    dataBeans.add(customerSourceData.get(position));
                    BaseMsg<CustomerSourceEntity.DataBean> dataBeanBaseMsg = new BaseMsg<CustomerSourceEntity.DataBean>(customerSourceData, dataBeans, mMultiple);
                    dataBeanBaseMsg.setmTitle(title);
                    dataBeanBaseMsg.setTag(tag);
                    dataBeanBaseMsg.setUrl(Url);
                    dataBeanBaseMsg.setUrlTag(urlTag);
                    EventBus.getDefault().post(dataBeanBaseMsg);
                }else  if(customerIntentionData!=null){
                    //客户来源
                    ArrayList<CustomerIntentionEntity.DataBean> dataBeans = new ArrayList<>();
                    dataBeans.add(customerIntentionData.get(position));
                    BaseMsg<CustomerIntentionEntity.DataBean> dataBeanBaseMsg = new BaseMsg<CustomerIntentionEntity.DataBean>(customerIntentionData, dataBeans, mMultiple);
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
     *
     */
    private void setAdapterCtrl() {
        //适配器不为空刷新反复
        if (stringBaseRecyclerAdapter!=null)   {
            stringBaseRecyclerAdapter.notifyDataSetChanged();
            return;
        }
        if (productAdapter!=null)   {
            productAdapter.notifyDataSetChanged();
            return;
        }
         if(urlTag== OptionHelp.UrlTag.PRODUCT) {
            productAdapter();
        }else  if (urlTag== OptionHelp.UrlTag.PACKAGE){
            packageAdapter();
        }else {
         //多选的
         if (mMultiple){
             multipleAdapter();
         }else {
             singleSetAdapter();
          }
        }
        mRecyclerView.loadMoreFinish(false, false);
    }
   //------------------网络专区-----------------------------------------
    /**
     * 获取包套
     */
    private void getProduct() {
        //请求实体
        JavaBeanRequest<ProductEntity> districtBeanJavaBeanRequest = new JavaBeanRequest<ProductEntity>(Url, RequestMethod.POST,ProductEntity.class);
        HttpListener<ProductEntity> searchByCustmor = new HttpListener<ProductEntity>(){
            @Override
            public void onSucceed(int what, Response<ProductEntity> response) {
                mRotateHeaderGridViewFrame.refreshComplete();
                boolean ok = response.get().isOK();
                if (ok){
                    mAllData.clear();
                    productData = response.get().getData();
                    if (productData!=null){
                        setAdapterCtrl();
                    }else {
                        mRecyclerView.loadMoreFinish(true,false);
                    }
                }else {
                    mRecyclerView.loadMoreError(0,getString(R.string.data_load_error));
                }
            }
            @Override
            public void onFailed(int what, Response<ProductEntity> response) {
                mRotateHeaderGridViewFrame.refreshComplete();
                mRecyclerView.loadMoreError(0,getString(R.string.data_load_error));
            }
        };
       request(0,districtBeanJavaBeanRequest, searchByCustmor, false, false);
    }
    /**
     * 获取包套
     */
    private void getPackage() {
        //请求实体
        JavaBeanRequest<PackageEntity> districtBeanJavaBeanRequest = new JavaBeanRequest<PackageEntity>(Url, RequestMethod.POST,PackageEntity.class);
        HttpListener<PackageEntity> searchByCustmor = new HttpListener<PackageEntity>(){
            @Override
            public void onSucceed(int what, Response<PackageEntity> response) {
                mRotateHeaderGridViewFrame.refreshComplete();
                boolean ok = response.get().isOK();
                if (ok){
                    mAllData.clear();
                    packageData = response.get().getData();
                    if (packageData!=null){
                        for (PackageEntity.DataBean da:packageData) {
                            mAllData.add(da.getPackage_name()) ;
                        }
                        setAdapterCtrl();
                    }else {
                        mRecyclerView.loadMoreFinish(true,false);
                    }
                }else {
                    mRecyclerView.loadMoreError(0,getString(R.string.data_load_error));
                }
            }
            @Override
            public void onFailed(int what, Response<PackageEntity> response) {
                mRotateHeaderGridViewFrame.refreshComplete();
                mRecyclerView.loadMoreError(0,getString(R.string.data_load_error));
            }
        };
        request(2,districtBeanJavaBeanRequest, searchByCustmor,   false, false);
    }

    /**
     * 获取消费类型
     */
    private void getConsumptionType() {
        //请求实体
        JavaBeanRequest<ConsumptionTypeEntity> districtBeanJavaBeanRequest = new JavaBeanRequest<ConsumptionTypeEntity>(Url, RequestMethod.POST,ConsumptionTypeEntity.class);
        HttpListener<ConsumptionTypeEntity> searchByCustmor = new HttpListener<ConsumptionTypeEntity>(){
            @Override
            public void onSucceed(int what, Response<ConsumptionTypeEntity> response) {
                mRotateHeaderGridViewFrame.refreshComplete();
                boolean ok = response.get().isOK();
                if (ok){
                    mAllData.clear();
                    consumptionTypeData = response.get().getData();
                    if (consumptionTypeData!=null){
                        for (ConsumptionTypeEntity.DataBean da:consumptionTypeData) {
                            mAllData.add(da.getConsumption_name()) ;
                        }
                        setAdapterCtrl();
                    }else {
                        mRecyclerView.loadMoreFinish(true,false);
                    }
                }else {
                    mRecyclerView.loadMoreError(0, getString(R.string.data_load_error));
                }
            }
            @Override
            public void onFailed(int what, Response<ConsumptionTypeEntity> response) {
                mRotateHeaderGridViewFrame.refreshComplete();
                mRecyclerView.loadMoreError(0,getString(R.string.data_load_error));
            }
        };
        request(3,districtBeanJavaBeanRequest, searchByCustmor, false, false);
    }

    /**
     * 获取接单点
     */
    private void getOrderReceivingSite() {
        //请求实体
        JavaBeanRequest<OrderReceivingSiteEntity> districtBeanJavaBeanRequest = new JavaBeanRequest<OrderReceivingSiteEntity>(Url, RequestMethod.POST,OrderReceivingSiteEntity.class);
        HttpListener<OrderReceivingSiteEntity> searchByCustmor = new HttpListener<OrderReceivingSiteEntity>(){
            @Override
            public void onSucceed(int what, Response<OrderReceivingSiteEntity> response) {
                mRotateHeaderGridViewFrame.refreshComplete();
                boolean ok = response.get().isOK();
                if (ok){
                    mAllData.clear();
                   orderReceivingSiteData = response.get().getData();
                    if (orderReceivingSiteData!=null){
                        for (OrderReceivingSiteEntity.DataBean da:orderReceivingSiteData) {
                            mAllData.add(da.getAcceptoraddress_name()) ;
                        }
                        setAdapterCtrl();
                    }else {
                        mRecyclerView.loadMoreFinish(true,false);
                    }
                }else {
                    mRecyclerView.loadMoreError(0,getString(R.string.data_load_error));
                }
            }
            @Override
            public void onFailed(int what, Response<OrderReceivingSiteEntity> response) {
                mRotateHeaderGridViewFrame.refreshComplete();
                mRecyclerView.loadMoreError(0,getString(R.string.data_load_error));
            }
        };
        request(4,districtBeanJavaBeanRequest, searchByCustmor, false, false);
    }
    /**
     * 门市接待
     */
    public void getOutletsReception() {
        //请求实体
        JavaBeanRequest<OutletsReceptionEntity> districtBeanJavaBeanRequest = new JavaBeanRequest<OutletsReceptionEntity>(Url, RequestMethod.POST,OutletsReceptionEntity.class);
        HttpListener<OutletsReceptionEntity> searchByCustmor = new HttpListener<OutletsReceptionEntity>(){
            @Override
            public void onSucceed(int what, Response<OutletsReceptionEntity> response) {
                mRotateHeaderGridViewFrame.refreshComplete();
                boolean ok = response.get().isOK();
                if (ok){
                    mAllData.clear();
                    outletsReceptionData = response.get().getData();
                    if (outletsReceptionData!=null){
                        for (OutletsReceptionEntity.DataBean da:outletsReceptionData) {
                            mAllData.add(da.getStaffName()) ;
                        }
                        setAdapterCtrl();
                    }else {
                        mRecyclerView.loadMoreFinish(true,false);
                    }
                }else {
                    mRecyclerView.loadMoreError(0,getString(R.string.data_load_error));
                }
            }
            @Override
            public void onFailed(int what, Response<OutletsReceptionEntity> response) {
                mRotateHeaderGridViewFrame.refreshComplete();
                mRecyclerView.loadMoreError(0,getString(R.string.data_load_error));
            }
        };
        request(5,districtBeanJavaBeanRequest, searchByCustmor, false, false);

    }
    /**
     * 客户分区
     */
    public void getCustomerZone() {
        //请求实体
        JavaBeanRequest<CustomerZoneEntity> districtBeanJavaBeanRequest = new JavaBeanRequest<CustomerZoneEntity>(Url, RequestMethod.POST,CustomerZoneEntity.class);
        HttpListener<CustomerZoneEntity> searchByCustmor = new HttpListener<CustomerZoneEntity>(){
            @Override
            public void onSucceed(int what, Response<CustomerZoneEntity> response) {
                mRotateHeaderGridViewFrame.refreshComplete();
                boolean ok = response.get().isOK();
                if (ok){
                    mAllData.clear();
                    customerZoneData = response.get().getData();
                    if (customerZoneData!=null){
                        for (CustomerZoneEntity.DataBean da:customerZoneData) {
                            mAllData.add(da.getArea_name()) ;
                        }
                        setAdapterCtrl();
                    }else {
                        mRecyclerView.loadMoreFinish(true,false);
                    }
                }else {
                    mRecyclerView.loadMoreError(0,getString(R.string.data_load_error));
                }
            }
            @Override
            public void onFailed(int what, Response<CustomerZoneEntity> response) {
                mRecyclerView.loadMoreError(0,getString(R.string.data_load_error));
                mRotateHeaderGridViewFrame.refreshComplete();
            }
        };
        request(6,districtBeanJavaBeanRequest, searchByCustmor, false, false);

    }


    /**
     *客户来源
     */
    public void getCustomerSource() {
        //请求实体
        JavaBeanRequest<CustomerSourceEntity> districtBeanJavaBeanRequest = new JavaBeanRequest<CustomerSourceEntity>(Url, RequestMethod.POST,CustomerSourceEntity.class);
        HttpListener<CustomerSourceEntity> searchByCustmor = new HttpListener<CustomerSourceEntity>(){
            @Override
            public void onSucceed(int what, Response<CustomerSourceEntity> response) {
                mRotateHeaderGridViewFrame.refreshComplete();
                boolean ok = response.get().isOK();
                if (ok){
                    mAllData.clear();
                    customerSourceData = response.get().getData();
                    if (customerSourceData!=null){
                        for (CustomerSourceEntity.DataBean da:customerSourceData) {
                            mAllData.add(da.getCus_name()) ;
                        }
                        setAdapterCtrl();
                    }else {
                        mRecyclerView.loadMoreFinish(true,false);
                    }
                }else {
                    mRecyclerView.loadMoreError(0,getString(R.string.data_load_error));
                }
            }
            @Override
            public void onFailed(int what, Response<CustomerSourceEntity> response) {
                mRecyclerView.loadMoreError(0,getString(R.string.data_load_error));
                mRotateHeaderGridViewFrame.refreshComplete();
            }
        };
        request(7,districtBeanJavaBeanRequest, searchByCustmor, false, false);

    }


    /**
     *客户意向
     */
    public void getCustomerIntention() {
        //请求实体
        JavaBeanRequest<CustomerIntentionEntity> districtBeanJavaBeanRequest = new JavaBeanRequest<CustomerIntentionEntity>(Url, RequestMethod.POST,CustomerIntentionEntity.class);
        HttpListener<CustomerIntentionEntity> searchByCustmor = new HttpListener<CustomerIntentionEntity>(){
            @Override
            public void onSucceed(int what, Response<CustomerIntentionEntity> response) {
                mRotateHeaderGridViewFrame.refreshComplete();
                boolean ok = response.get().isOK();
                if (ok){
                    mAllData.clear();
                    customerIntentionData = response.get().getData();
                    if (customerIntentionData!=null){
                        for (CustomerIntentionEntity.DataBean da:customerIntentionData) {
                            mAllData.add(da.getIntention_name()) ;
                        }
                        setAdapterCtrl();
                    }else {
                        mRecyclerView.loadMoreFinish(true,false);
                    }
                }else {
                    mRecyclerView.loadMoreError(0,getString(R.string.data_load_error));
                }
            }
            @Override
            public void onFailed(int what, Response<CustomerIntentionEntity> response) {
                mRecyclerView.loadMoreError(0,getString(R.string.data_load_error));
                mRotateHeaderGridViewFrame.refreshComplete();
            }
        };
        request(8,districtBeanJavaBeanRequest, searchByCustmor, false, false);

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
                        CheckedTextView checkedTextView = (CheckedTextView)v;
                        checkedTextView.toggle();
                        if (checkedTextView.isChecked()){
                          currentChecked.add( mAllData.get(position)) ;
                        }else {
                            currentChecked.remove( mAllData.get(position)) ;
                        }
                    }
                });
                if (currentChecked!=null){
                    if (currentChecked.contains(item)){
                        view.setChecked(true);
                    }else {
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
                if (currentChecked!=null&&currentChecked.size()>0){
                    String s = currentChecked.get(0);
                    if(item.equals(s)){
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
                tv_right.setText("¥"+item.getPackage_price());
                if (!mMultiple){//单选
                    holder.getView(R.id.item_package_line).setVisibility(View.GONE);
                    packagename.setCompoundDrawables(null,null,null,null);
                    if (currentChecked!=null&&currentChecked.size()>0){
                        String s = currentChecked.get(0);
                        if (!StringUtils.empty(s)){
                            if(item.getId()==Integer.parseInt(s)){
                                holder.getView(R.id.item_package_root_layout).setBackgroundColor(getResources().getColor(R.color.myBackground_f7));
                            }
                        }

                    }
                }else {

                }
            }
        };
    }
    /**
     * 产品的适配器
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
                        CheckedTextView checkedTextView = (CheckedTextView)v;
                        checkedTextView.toggle();
                        if (checkedTextView.isChecked()){
                            currentChecked.add( item.getId()+"") ;
                        }else {
                            currentChecked.remove( item.getId()+"") ;
                        }
                    }
                });
                 if (currentChecked!=null){
                     if (currentChecked.contains(item.getId()+"")){
                         packagename.setChecked(true);
                     }else {
                         packagename.setChecked(false);
                     }
                 }
               packagename.setText(item.getItem_name());
               TextView tv_right = holder.getView(R.id.tv_right_data);
                tv_right.setText("¥"+item.getItem_price());
                if (!mMultiple){//单选
                    holder.getView(R.id.item_package_line).setVisibility(View.GONE);
                    packagename.setCompoundDrawables(null,null,null,null);
                    if (currentChecked!=null&&currentChecked.size()>0){
                        String s = currentChecked.get(0);
                        if (!StringUtils.empty(s)){
                            if(item.getId()==Integer.parseInt(s)){
                                holder.getView(R.id.item_package_root_layout).setBackgroundColor(getResources().getColor(R.color.myBackground_f7));
                            }
                        }
                    }
                }else {
                    if (currentChecked!=null&&currentChecked.size()>0){
                        for (String s:currentChecked){
                            if(s.equals(productData.get(position).getId()+"")){
                                packagename.setChecked(true);
                            break;
                            }
                        }
                    }
                }
            }
        };
    }



}
