package com.suxuantech.erpsys.ui.activity;

import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.TimePickerView;
import com.suxuantech.erpsys.R;
import com.suxuantech.erpsys.common.OptionHelp;
import com.suxuantech.erpsys.entity.ConsumptionTypeEntity;
import com.suxuantech.erpsys.entity.CustomerSourceEntity;
import com.suxuantech.erpsys.entity.CustomerZoneEntity;
import com.suxuantech.erpsys.entity.OrderReceivingSiteEntity;
import com.suxuantech.erpsys.entity.OutletsReceptionEntity;
import com.suxuantech.erpsys.entity.PhotoShopEntity;
import com.suxuantech.erpsys.eventmsg.BaseMsg;
import com.suxuantech.erpsys.presenter.OutletsOrderPresenter;
import com.suxuantech.erpsys.presenter.connector.IOutletsOrderPresenter;
import com.suxuantech.erpsys.ui.activity.base.ImmersedBaseActivity;
import com.suxuantech.erpsys.utils.DateUtil;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.Calendar;
import java.util.Date;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 门市开单
 */
public class OutletsOrderActivity extends ImmersedBaseActivity implements IOutletsOrderPresenter {
    @BindView(R.id.tv_order_id)
    TextView mTvOrderId;
    @BindView(R.id.ll_order_id)
    LinearLayout mLlOrderId;
    @BindView(R.id.tv_order_date)
    TextView mTvOrderDate;
    @BindView(R.id.ll_order_date)
    LinearLayout mLlOrderDate;
    @BindView(R.id.tv_consumption_type)
    TextView mTvConsumptionType;
    @BindView(R.id.ll_consumption_type)
    LinearLayout mLlConsumptionType;
    @BindView(R.id.tv_consumption_sub)
    TextView mTvConsumptionSub;
    @BindView(R.id.ll_consumption_sub)
    LinearLayout mLlConsumptionSub;
    @BindView(R.id.et_customer_name_m)
    TextInputEditText mEtCustomerNameM;
    @BindView(R.id.et_customer_phone_m)
    TextInputEditText mEtCustomerPhoneM;
    @BindView(R.id.et_customer_phone)
    TextInputEditText mEtCustomerPhone;
    @BindView(R.id.tv_customer_birthday_m)
    TextView mTvCustomerBirthdayM;
    @BindView(R.id.ll_customer_birthday_m)
    LinearLayout mLlCustomerBirthdayM;
    @BindView(R.id.et_customer_name_w)
    TextInputEditText mEtCustomerNameW;
    @BindView(R.id.et_customer_phone_w)
    TextInputEditText mEtCustomerPhoneW;
    @BindView(R.id.et_customer_wechat_w)
    TextInputEditText mEtCustomerWechatW;
    @BindView(R.id.tv_customer_birthday_w)
    TextView mTvCustomerBirthdayW;
    @BindView(R.id.ll_customer_birthday_w)
    LinearLayout mLlCustomerBirthdayW;
    @BindView(R.id.tv_reception)
    TextView mTvReception;
    @BindView(R.id.ll_reception)
    LinearLayout mLlReception;
    @BindView(R.id.tv_reception_market)
    TextView mTvReceptionMarket;
    @BindView(R.id.ll_reception_market)
    LinearLayout mLlReceptionMarket;
    @BindView(R.id.tv_reception_governor)
    TextView mTvReceptionGovernor;
    @BindView(R.id.ll_reception_governor)
    LinearLayout mLlReceptionGovernor;
    @BindView(R.id.tv_marriage_date)
    TextView mTvMarriageDate;
    @BindView(R.id.ll_marriage_date)
    LinearLayout mLlMarriageDate;
    @BindView(R.id.tv_photo_shop_one)
    TextView mTvPhotoShopOne;
    @BindView(R.id.ll_photo_shop_one)
    LinearLayout mLlPhotoShopOne;
    @BindView(R.id.tvintention_shop)
    TextView mTvintentionShop;
    @BindView(R.id.ll_intention_shop)
    LinearLayout mLlIntentionShop;
    @BindView(R.id.tv_photo_shop_two)
    TextView mTvPhotoShopTwo;
    @BindView(R.id.ll_photo_shop_two)
    LinearLayout mLlPhotoShopTwo;
    @BindView(R.id.tv_vip_zone)
    TextView mTvVipZone;
    @BindView(R.id.ll_customer_zone)
    LinearLayout mLlCustomerZone;
    @BindView(R.id.tv_customer_source)
    TextView mTvCustomerSource;
    @BindView(R.id.ll_customer_source)
    LinearLayout mLlCustomerSource;
    @BindView(R.id.et_indoor_clothes)
    TextInputEditText mEtIndoorClothes;
    @BindView(R.id.et_outdoor_clothes)
    TextInputEditText mEtOutdoorClothes;
    @BindView(R.id.et_seaview_clothes)
    TextInputEditText mEtSeaviewClothes;
    @BindView(R.id.et_overseas_shoot_clothes)
    TextInputEditText mEtOverseasShootClothes;
    @BindView(R.id.et_shoot_number)
    TextInputEditText mEtShootNumber;
    @BindView(R.id.et_number_sheets)
    TextInputEditText mEtNumberSheets;
    @BindView(R.id.et_truing_number)
    TextInputEditText mEtTruingNumber;
    @BindView(R.id.et_customer_email)
    TextInputEditText mEtCustomerEmail;
    @BindView(R.id.et_customer_address)
    TextInputEditText mEtCustomerAddress;
    @BindView(R.id.et_customer_remarks)
    TextInputEditText mEtCustomerRemarks;
   // @BindView(R.id.btn_submint_order)
    //Button mBtnSubmintOrder;
    private OutletsOrderPresenter outletsOrderPresenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_outlets_order_new);
        useEventBus();
        useButterKnife();
//        showUserDefinedNav();
        outletsOrderPresenter = new OutletsOrderPresenter(this, getRequestQueue());
        outletsOrderPresenter.getOrderNum();
    }

    /**
     * 时间选择
     */
    public void showDataSelect(TextView showIn) {
//年月日时分秒 的显示与否，不设置则默认全部显示
        TimePickerView timePickerView = new TimePickerView.Builder(this, new TimePickerView.OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {//选中事件回调
                showIn.setText(DateUtil.dateToString(date, DateUtil.DatePattern.ONLY_DAY));
            }
        }) //年月日时分秒 的显示与否，不设置则默认全部显示
                .setTitleText(getString(R.string.please_select_marriage_date)).setLineSpacingMultiplier(4).setType(new boolean[]{true, true, true, false, false, false})
                .setTitleColor(getResources().getColor(R.color.textHint_99)).setTitleBgColor(getResources().getColor(R.color.white)).build();
        timePickerView.setDate(Calendar.getInstance());//注：根据需求来决定是否使用该方法（一般是精确到秒的情况），此项可以在弹出选择器的时候重新设置当前时间，避免在初始化之后由于时间已经设定，导致选中时间与当前时间不匹配的问题。
        timePickerView.show();
    }

    @Override
    public void getNumberFailed(String msg) {

    }

    @Override
    public void getOrderNumberSucceed(int what, String data) {
       if (what==9){
           toastShort("开单成功："+data);
           finish();
       }else {
           if (mTvOrderId != null) {
             mTvOrderId.setText(data);
           }
       }
    }



    @Override
    @OnClick({R.id.ll_order_id, R.id.ll_order_date, R.id.ll_consumption_type, R.id.ll_consumption_sub, R.id.ll_customer_birthday_m,
            R.id.ll_customer_birthday_w, R.id.ll_reception, R.id.ll_reception_market, R.id.ll_reception_governor, R.id.ll_marriage_date,
            R.id.ll_photo_shop_one, R.id.ll_intention_shop, R.id.ll_photo_shop_two, R.id.ll_customer_zone, R.id.ll_customer_source, R.id.btn_submint_order})
    public void onClick(View v) {
        OptionHelp optionHelp = new OptionHelp(this);
        switch (v.getId()) {
            default:
                break;
            case R.id.ll_order_date:
                showDataSelect(mTvOrderDate);
                break;
            case R.id.ll_consumption_type:
                optionHelp.setTitle(getString(R.string.consumption_type));
                optionHelp.setCheckedData(mTvConsumptionType.getText().toString());
                optionHelp.setUrlTag(OptionHelp.UrlTag.CONSUMPTION_TYPE);
                startActivity(optionHelp.creat());
                break;
            case R.id.ll_consumption_sub:
                break;
            case R.id.ll_customer_birthday_m:
                showDataSelect(mTvCustomerBirthdayM);
                break;
            case R.id.ll_customer_birthday_w:
                showDataSelect(mTvCustomerBirthdayW);
                break;
            case R.id.ll_reception:
                optionHelp.setTitle("销售门市");
                optionHelp.setCheckedData(mTvConsumptionType.getText().toString());
                optionHelp.setUrlTag(OptionHelp.UrlTag.OUTLETS_RECEPTION);
                startActivity(optionHelp.creat());
                break;
            case R.id.ll_reception_market:
                optionHelp.setTitle("网销门市");
                optionHelp.setCheckedData(mTvConsumptionType.getText().toString());
                optionHelp.setUrlTag(OptionHelp.UrlTag.RECEPTION_MARKET);
                startActivity(optionHelp.creat());
                break;
            case R.id.ll_reception_governor:
                break;
            case R.id.ll_marriage_date:
                showDataSelect(mTvMarriageDate);
                break;
            case R.id.ll_photo_shop_one:
                optionHelp.setTitle("拍摄店一");
                optionHelp.setCheckedData(mTvPhotoShopOne.getText().toString());
                optionHelp.setUrlTag(OptionHelp.UrlTag.PHOTO_SHOP);
                optionHelp.setTag("拍摄店一");
                startActivity(optionHelp.creat());
                break;
            case R.id.ll_intention_shop:
                optionHelp.setTitle("意向店");
                optionHelp.setCheckedData(mTvintentionShop.getText().toString());
                optionHelp.setUrlTag(OptionHelp.UrlTag.PHOTO_SHOP);
                optionHelp.setTag("意向店");
                startActivity(optionHelp.creat());
                break;
            case R.id.ll_photo_shop_two:
                optionHelp.setTitle("拍摄店二");
                optionHelp.setTag("拍摄店二");
                optionHelp.setCheckedData(mTvPhotoShopTwo.getText().toString());
                optionHelp.setUrlTag(OptionHelp.UrlTag.PHOTO_SHOP);
                startActivity(optionHelp.creat());
                break;
            case R.id.ll_customer_zone:
                OptionHelp optionHelp3 = new OptionHelp(this);
                optionHelp3.setTitle(getString(R.string.customer_zone));
                optionHelp3.setCheckedData(mTvVipZone.getText().toString());
                optionHelp3.setUrlTag(OptionHelp.UrlTag.CUSTOMER_ZONE);
                startActivity(optionHelp3.creat());
                break;
            case R.id.ll_customer_source:
                OptionHelp    optionHelp1 =new  OptionHelp(this);
                optionHelp1.setTitle("客户来源");
                optionHelp1.setCheckedData(mTvCustomerSource.getText().toString());
                optionHelp1.setUrlTag(OptionHelp.UrlTag.CUSTOMER_SOURCE);
                startActivity(optionHelp1.creat());
                break;
            case R.id.btn_submint_order:
                break;
        }
    }

    /**
     * @param msg
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(BaseMsg msg) {
        OptionHelp.UrlTag urlTag = msg.getUrlTag();
        switch (urlTag) {
            default:
            case CUSTOMER_SOURCE:
                mTvCustomerSource.setText(((CustomerSourceEntity.DataBean) msg.getSingleChecked()).getCus_name());
                break;
            case CONSUMPTION_TYPE:
                mTvConsumptionType.setText(((ConsumptionTypeEntity.DataBean) msg.getSingleChecked()).getConsumption_name());
                break;
            case PRODUCT:
                //  prd = (List<ProductEntity.DataBean>) msg.getMultiSelected();
                //mTvAddition.setText(prd.size()+"");
                break;
            case PACKAGE:
                // mLlAddition.setVisibility(View.VISIBLE);
                //  mViewPackageLine.setVisibility(View.VISIBLE);
                //   packageChecked = (PackageEntity.DataBean) msg.getSingleChecked();
                //mTvPackageSet.setText(packageChecked.getPackage_name());
                break;
            case OUTLETS_RECEPTION:
                OutletsReceptionEntity.DataBean checked2 = (OutletsReceptionEntity.DataBean) msg.getSingleChecked();
                mTvReception.setText(checked2.getStaffName());
//                    if (msg.getTag()!=null&&msg.getTag().equals(getString(R.string.reception))){
//
//                    }else {
//                        OutletsReceptionEntity.DataBean checked2 = (OutletsReceptionEntity.DataBean) msg.getSingleChecked();
//                 mTvOutletsReception.setText(checked2.getStaffName());
//                    }
                break;
            case ORDER_RECEIVING_SITE:
                OrderReceivingSiteEntity.DataBean checked3 = (OrderReceivingSiteEntity.DataBean) msg.getSingleChecked();
                //  mTvOrderReceivingSite.setText(checked3.getShop_name());
                break;
            case CUSTOMER_ZONE:
                CustomerZoneEntity.DataBean checked4 = (CustomerZoneEntity.DataBean) msg.getSingleChecked();
                mTvVipZone.setText(checked4.getArea_name());
                break;
            case PHOTO_SHOP:
                if (msg.getTag().equals("拍摄店一")) {
                    mTvPhotoShopOne.setText(((PhotoShopEntity.DataBean) msg.getSingleChecked()).getShop_name());
                } else if (msg.getTag().equals("意向店")) {
                    mTvintentionShop.setText(((PhotoShopEntity.DataBean) msg.getSingleChecked()).getShop_name());
                } else {
                    mTvPhotoShopTwo.setText(((PhotoShopEntity.DataBean) msg.getSingleChecked()).getShop_name());
                }
                break;
        }
    }

}
//    @Override
//    @OnClick({R.id.tv_nav_left, R.id.tv_other_infromation, R.id.btn_submint_order,R.id.tv_nav_title, R.id.tv_nav_right, R.id.v_line, R.id.head_nav_use_defined_root, R.id.tv_order_id, R.id.et_customer_name, R.id.et_customer_phone, R.id.tv_sex, R.id.tv_consumption_type, R.id.tv_outlets_reception, R.id.tv_order_receiving_site, R.id.tv_package_set, R.id.tv_addition, R.id.tv_vip_zone, R.id.tv_reception, R.id.tv_marriage_date, R.id.et_customer_remarks, R.id.et_photography_remarks, R.id.ll_other_information})
//    public void onClick(View v) {
//        switch (v.getId()) {
//            default:
//                break;
//            case R.id.tv_nav_left:
//                finish();
//                break;
//            case R.id.tv_nav_title:
//                break;
//            case R.id.tv_nav_right:
//                break;
//            case R.id.v_line:
//                break;
//            case R.id.head_nav_use_defined_root:
//                break;
//            case R.id.tv_order_id:
//                break;
//            case R.id.et_customer_name:
//                break;
//            case R.id.et_customer_phone:
//                break;
//            case R.id.tv_sex:
//                new AlertView(getString(R.string.please_select_sex), null, getString(R.string.cancel), null,
//                        getResources().getStringArray(R.array.sex) ,
//                        this, AlertView.Style.ACTIONSHEET, new OnItemClickListener(){
//                    @Override
//                    public void onItemClick(Object o, int position){//position -1是取消按钮
//                    toast( "点击了第" + position + "个");
//                    }
//                }).show();
//                break;
//            case R.id.tv_consumption_type:
//                OptionHelp optionHelp = new OptionHelp(this);
//                optionHelp.setTitle(getString(R.string.consumption_type));
//                //optionHelp .setCheckedData(mTvConsumptionType.getText().toString());
//                optionHelp .setUrlTag(OptionHelp.UrlTag.CONSUMPTION_TYPE);
//                startActivity(optionHelp.creat());
//                break;
//            case R.id.tv_outlets_reception:
//                OptionHelp optionHelp1 = new OptionHelp(this);
//                optionHelp1.setTitle(getString(R.string.outlets_reception));
//              //  optionHelp1 .setCheckedData(mTvOutletsReception.getText().toString());
//                optionHelp1 .setUrlTag(OptionHelp.UrlTag.OUTLETS_RECEPTION);
//                startActivity(optionHelp1.creat());
//                break;
//            case R.id.tv_order_receiving_site:
//                OptionHelp optionHelp2 = new OptionHelp(this);
//                optionHelp2.setTitle(getString(R.string.order_receiving_site));
//               // optionHelp2 .setCheckedData(mTvOrderReceivingSite.getText().toString());
//                optionHelp2 .setUrlTag(OptionHelp.UrlTag.ORDER_RECEIVING_SITE);
//                startActivity(optionHelp2.creat());
//                break;
//            case R.id.tv_package_set:
//                OptionHelp optionHelp6 = new OptionHelp(this);
//                optionHelp6.setTitle(getString(R.string.package_select));
//                if (packageChecked!=null){
//                    optionHelp6 .setCheckedData(packageChecked.getId()+"");
//                }
//                optionHelp6 .setUrlTag(OptionHelp.UrlTag.PACKAGE);
//                startActivity(optionHelp6.creat());
//                break;
//            case R.id.tv_addition:
//                OptionHelp optionHelp7 = new OptionHelp(this);
//                optionHelp7.setTitle(getString(R.string.product_select));
//                optionHelp7.setMultiple(true);
//                if (prd!=null&&prd.size()>0){
//                    ArrayList<String> stringArrayList = new ArrayList<String>();
//                    for (ProductEntity.DataBean p:prd){
//                        stringArrayList.add(p.getId()+"");
//                    }
//                    optionHelp7.setCheckedData(stringArrayList);
//                }
////              optionHelp7 .setCheckedData(mtvp.getText().toString());
//                optionHelp7 .setUrlTag(OptionHelp.UrlTag.PRODUCT);
//                startActivity(optionHelp7.creat());
//                break;
//            case R.id.tv_vip_zone:
//                OptionHelp optionHelp3 = new OptionHelp(this);
//                optionHelp3.setTitle(getString(R.string.customer_zone));
//             //   optionHelp3 .setCheckedData(mTvVipZone.getText().toString());
//                optionHelp3 .setUrlTag(OptionHelp.UrlTag.CUSTOMER_ZONE);
//                startActivity(optionHelp3.creat());
//                break;
//            case R.id.tv_reception:
//                OptionHelp optionHelp4 = new OptionHelp(this);
//                optionHelp4.setTitle(getString(R.string.reception));
//                optionHelp4.setTag(getString(R.string.reception));
//               // optionHelp4 .setCheckedData(mTvReception.getText().toString());
//                optionHelp4 .setUrlTag(OptionHelp.UrlTag.OUTLETS_RECEPTION);
//                startActivity(optionHelp4.creat());
//                break;
//            case R.id.tv_marriage_date:
//                showDataSelect();
//                break;
//            case R.id.et_customer_remarks:
//                break;
//            case R.id.et_photography_remarks:
//                break;
//            case R.id.ll_other_information:
//                break;
//            case R.id.btn_submint_order:
//                commit();
//                break;
//            case R.id.tv_other_infromation:
////                if (mLlOtherInformation.getVisibility()!=View.VISIBLE){
////                    mLlOtherInformation.setVisibility(View.VISIBLE);
////                    mTvOtherInformation.setSelected(true);
////                }else {
////                    mLlOtherInformation.setVisibility(View.GONE);
////                    mTvOtherInformation.setSelected(false);
////                }
//
//                break;
//        }
//    }
//    private void commit() {
////        OpenOrderTempEntity openOrderTempBean = new OpenOrderTempEntity();
////        openOrderTempBean.setOrderId(mTvOrderId.getText().toString());
////        openOrderTempBean.setCustromerName(mEtCustomerName.getText().toString());
////        openOrderTempBean.setPhoneNumber(mEtCustomerPhone.getText().toString());
////        openOrderTempBean.setSex(mTvSex.getText().toString());
////        openOrderTempBean.setConsumptionType(mTvConsumptionType.getText().toString());
////        openOrderTempBean.setOutletsReception(mTvOutletsReception.getText().toString());
////        openOrderTempBean.setOrderReceivingSite(mTvOrderReceivingSite.getText().toString());
////        if (packageChecked!=null){
////            openOrderTempBean.setPackageSetID(packageChecked.getId()+"");
////            openOrderTempBean.setPackageSetName(packageChecked.getPackage_name());
////        }
////        openOrderTempBean.setCustomerZone(mTvVipZone.getText().toString());
////        openOrderTempBean.setReception(mTvReception.getText().toString());
////        openOrderTempBean.setMarriageDate(mTvMarriageDate.getText().toString());
////        openOrderTempBean.setCustomerRemarks(mEtCustomerRemarks.getText().toString());
////        openOrderTempBean.setPhotographyRemarks(mEtPhotographyRemarks.getText().toString());
////        outletsOrderPresenter.OpenOrder(openOrderTempBean);
////        if (StringUtils.empty(mTvOrderId.getText().toString())){
////        }
////        if (StringUtils.empty(mEtCustomerName.getText().toString())){
////        }
////        if (StringUtils.empty(mEtCustomerPhone.getText().toString())){
////        }
////        if (StringUtils.empty(mTvConsumptionType.getText().toString())){
////        }
////        if (StringUtils.empty(mTvOutletsReception.getText().toString())){
////        }
////        if (StringUtils.empty(mTvOrderReceivingSite.getText().toString())){
////        }
////        if (StringUtils.empty(mTvPackageSet.getText().toString())){
////        }
//
//
//    }
//    PackageEntity.DataBean packageChecked;
//    List<ProductEntity.DataBean> prd;
//
//    }

//
//    @Override
//    public void getNumberFailed(String msg) {
//        if (msg!=null){
//            toast(msg);
//        }else {
//            toast("获取订单号失败");
//        }
//
//    }
//    @Override
//    public void getOrderNumberSucceed(int what ,String data) {

//    }
//}
