package com.suxuantech.erpsys.activity;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bigkoo.alertview.AlertView;
import com.bigkoo.alertview.OnItemClickListener;
import com.bigkoo.pickerview.TimePickerView;
import com.suxuantech.erpsys.OptionHelp;
import com.suxuantech.erpsys.R;
import com.suxuantech.erpsys.activity.base.BaseActivity;
import com.suxuantech.erpsys.bean.ConsumptionTypeBean;
import com.suxuantech.erpsys.bean.CustomerZoneBean;
import com.suxuantech.erpsys.bean.OpenOrderTempBean;
import com.suxuantech.erpsys.bean.OrderReceivingSiteBean;
import com.suxuantech.erpsys.bean.OutletsReceptionBean;
import com.suxuantech.erpsys.bean.PackageBean;
import com.suxuantech.erpsys.bean.ProductBean;
import com.suxuantech.erpsys.eventmsg.BaseMsg;
import com.suxuantech.erpsys.presenter.OutletsOrderPresenter;
import com.suxuantech.erpsys.presenter.connector.IOutletsOrderPresenter;
import com.suxuantech.erpsys.utils.DateUtil;
import com.suxuantech.erpsys.utils.StringUtils;
import com.suxuantech.erpsys.utils.ToastUtils;
import com.suxuantech.erpsys.views.AdjustDrawableTextView;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 门市开单
 */
public class OutletsOrderActivity extends BaseActivity implements IOutletsOrderPresenter {
    @BindView(R.id.tv_nav_left)
    AdjustDrawableTextView mTvNavLeft;
    @BindView(R.id.tv_nav_title)
    AdjustDrawableTextView mTvNavTitle;
    @BindView(R.id.tv_nav_right)
    AdjustDrawableTextView mTvNavRight;
    @BindView(R.id.v_line)
    View mVLine;
    @BindView(R.id.head_nav_use_defined_root)
    RelativeLayout mHeadNavUseDefinedRoot;
    @BindView(R.id.tv_order_id)
    TextView mTvOrderId;
    @BindView(R.id.et_customer_name)
    TextInputEditText mEtCustomerName;
    @BindView(R.id.et_customer_phone)
    TextInputEditText mEtCustomerPhone;
    @BindView(R.id.tv_sex)
    TextView mTvSex;
    @BindView(R.id.tv_consumption_type)
    TextView mTvConsumptionType;
    @BindView(R.id.tv_outlets_reception)
    TextView mTvOutletsReception;
    @BindView(R.id.tv_order_receiving_site)
    TextView mTvOrderReceivingSite;
    @BindView(R.id.tv_package_set)
    TextView mTvPackageSet;
    @BindView(R.id.tv_addition)
    TextView mTvAddition;
    @BindView(R.id.tv_vip_zone)
    TextView mTvVipZone;
    @BindView(R.id.tv_reception)
    TextView mTvReception;
    @BindView(R.id.tv_marriage_date)
    TextView mTvMarriageDate;
    @BindView(R.id.et_customer_remarks)
    TextInputEditText mEtCustomerRemarks;
    @BindView(R.id.et_photography_remarks)
    TextInputEditText mEtPhotographyRemarks;
    @BindView(R.id.ll_other_information)
    LinearLayout mLlOtherInformation;
    @BindView(R.id.btn_submint_order)
    Button mBtnSubmintOrder;
    @BindView(R.id.tv_other_infromation)
    TextView mTvOtherInformation;
    @BindView(R.id.ll_addition)
    LinearLayout mLlAddition;
    @BindView(R.id.view_package_line)
    View mViewPackageLine;
    private OutletsOrderPresenter outletsOrderPresenter;

    @Override
    protected void permissionResult(boolean hasPermission, int requsetcode, List<String> permission) {
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        useEventBus();
        setContentView(R.layout.activity_outlets_order);
        mTvNavTitle =  idSetOnClick(R.id.tv_nav_title);
        idSetOnClick(R.id.tv_sex);
        idSetOnClick(R.id.tv_nav_left);
        mTvNavTitle.setText("门市开单");
        ButterKnife.bind(this);
        outletsOrderPresenter = new OutletsOrderPresenter(this,getRequestQueue());
        outletsOrderPresenter.getOrderNum();
    }
    @Override
    protected void widgetClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.tv_nav_left:
                toast("zuobian111");
                break;
            case R.id.tv_sex:
                toast("sex");
                break;
        }

    }
    @Override
    @OnClick({R.id.tv_nav_left, R.id.tv_other_infromation, R.id.btn_submint_order,R.id.tv_nav_title, R.id.tv_nav_right, R.id.v_line, R.id.head_nav_use_defined_root, R.id.tv_order_id, R.id.et_customer_name, R.id.et_customer_phone, R.id.tv_sex, R.id.tv_consumption_type, R.id.tv_outlets_reception, R.id.tv_order_receiving_site, R.id.tv_package_set, R.id.tv_addition, R.id.tv_vip_zone, R.id.tv_reception, R.id.tv_marriage_date, R.id.et_customer_remarks, R.id.et_photography_remarks, R.id.ll_other_information})
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.tv_nav_left:
                finish();
                break;
            case R.id.tv_nav_title:
                break;
            case R.id.tv_nav_right:
                break;
            case R.id.v_line:
                break;
            case R.id.head_nav_use_defined_root:
                break;
            case R.id.tv_order_id:
                break;
            case R.id.et_customer_name:
                break;
            case R.id.et_customer_phone:
                break;
            case R.id.tv_sex:
                new AlertView(getString(R.string.please_select_sex), null, getString(R.string.cancel), null,
                        getResources().getStringArray(R.array.sex) ,
                        this, AlertView.Style.ACTIONSHEET, new OnItemClickListener(){
                    @Override
                    public void onItemClick(Object o, int position){//position -1是取消按钮
                    toast( "点击了第" + position + "个");
                    }
                }).show();
                break;
            case R.id.tv_consumption_type:
                OptionHelp optionHelp = new OptionHelp(this);
                optionHelp.setTitle(getString(R.string.consumption_type));
                optionHelp .setCheckedData(mTvConsumptionType.getText().toString());
                optionHelp .setUrlTag(OptionHelp.UrlTag.CONSUMPTION_TYPE);
                startActivity(optionHelp.creat());
                break;
            case R.id.tv_outlets_reception:
                OptionHelp optionHelp1 = new OptionHelp(this);
                optionHelp1.setTitle(getString(R.string.outlets_reception));
                optionHelp1 .setCheckedData(mTvOutletsReception.getText().toString());
                optionHelp1 .setUrlTag(OptionHelp.UrlTag.OUTLETS_RECEPTION);
                startActivity(optionHelp1.creat());
                break;
            case R.id.tv_order_receiving_site:
                OptionHelp optionHelp2 = new OptionHelp(this);
                optionHelp2.setTitle(getString(R.string.order_receiving_site));
                optionHelp2 .setCheckedData(mTvOrderReceivingSite.getText().toString());
                optionHelp2 .setUrlTag(OptionHelp.UrlTag.ORDER_RECEIVING_SITE);
                startActivity(optionHelp2.creat());
                break;
            case R.id.tv_package_set:
                OptionHelp optionHelp6 = new OptionHelp(this);
                optionHelp6.setTitle(getString(R.string.package_select));
                if (packageChecked!=null){
                    optionHelp6 .setCheckedData(packageChecked.getId()+"");
                }
                optionHelp6 .setUrlTag(OptionHelp.UrlTag.PACKAGE);
                startActivity(optionHelp6.creat());
                break;
            case R.id.tv_addition:
                OptionHelp optionHelp7 = new OptionHelp(this);
                optionHelp7.setTitle(getString(R.string.product_select));
                optionHelp7.setMultiple(true);
                if (prd!=null&&prd.size()>0){
                    ArrayList<String> stringArrayList = new ArrayList<String>();
                    for (ProductBean.DataBean p:prd){
                        stringArrayList.add(p.getId()+"");
                    }
                    optionHelp7.setCheckedData(stringArrayList);
                }
//              optionHelp7 .setCheckedData(mtvp.getText().toString());
                optionHelp7 .setUrlTag(OptionHelp.UrlTag.PRODUCT);
                startActivity(optionHelp7.creat());
                break;
            case R.id.tv_vip_zone:
                OptionHelp optionHelp3 = new OptionHelp(this);
                optionHelp3.setTitle(getString(R.string.customer_zone));
                optionHelp3 .setCheckedData(mTvVipZone.getText().toString());
                optionHelp3 .setUrlTag(OptionHelp.UrlTag.CUSTOMER_ZONE);
                startActivity(optionHelp3.creat());
                break;
            case R.id.tv_reception:
                OptionHelp optionHelp4 = new OptionHelp(this);
                optionHelp4.setTitle(getString(R.string.reception));
                optionHelp4.setTag(getString(R.string.reception));
                optionHelp4 .setCheckedData(mTvReception.getText().toString());
                optionHelp4 .setUrlTag(OptionHelp.UrlTag.OUTLETS_RECEPTION);
                startActivity(optionHelp4.creat());
                break;
            case R.id.tv_marriage_date:
                showDataSelect();
                break;
            case R.id.et_customer_remarks:
                break;
            case R.id.et_photography_remarks:
                break;
            case R.id.ll_other_information:
                break;
            case R.id.btn_submint_order:
                commit();
                break;
            case R.id.tv_other_infromation:
                if (mLlOtherInformation.getVisibility()!=View.VISIBLE){
                    mLlOtherInformation.setVisibility(View.VISIBLE);
                    mTvOtherInformation.setSelected(true);
                }else {
                    mLlOtherInformation.setVisibility(View.GONE);
                    mTvOtherInformation.setSelected(false);
                }

                break;
        }
    }
    private void commit() {
        OpenOrderTempBean openOrderTempBean = new OpenOrderTempBean();
        openOrderTempBean.setOrderId(mTvOrderId.getText().toString());
        openOrderTempBean.setCustromerName(mEtCustomerName.getText().toString());
        openOrderTempBean.setPhoneNumber(mEtCustomerPhone.getText().toString());
        openOrderTempBean.setSex(mTvSex.getText().toString());
        openOrderTempBean.setConsumptionType(mTvConsumptionType.getText().toString());
        openOrderTempBean.setOutletsReception(mTvOutletsReception.getText().toString());
        openOrderTempBean.setOrderReceivingSite(mTvOrderReceivingSite.getText().toString());
        if (packageChecked!=null){
            openOrderTempBean.setPackageSetID(packageChecked.getId()+"");
            openOrderTempBean.setPackageSetName(packageChecked.getPackage_name());
        }
        openOrderTempBean.setCustomerZone(mTvVipZone.getText().toString());
        openOrderTempBean.setReception(mTvReception.getText().toString());
        openOrderTempBean.setMarriageDate(mTvMarriageDate.getText().toString());
        openOrderTempBean.setCustomerRemarks(mEtCustomerRemarks.getText().toString());
        openOrderTempBean.setPhotographyRemarks(mEtPhotographyRemarks.getText().toString());
        outletsOrderPresenter.OpenOrder(openOrderTempBean);
        if (StringUtils.empty(mTvOrderId.getText().toString())){
        }
        if (StringUtils.empty(mEtCustomerName.getText().toString())){
        }
        if (StringUtils.empty(mEtCustomerPhone.getText().toString())){
        }
        if (StringUtils.empty(mTvConsumptionType.getText().toString())){
        }
        if (StringUtils.empty(mTvOutletsReception.getText().toString())){
        }
        if (StringUtils.empty(mTvOrderReceivingSite.getText().toString())){
        }
        if (StringUtils.empty(mTvPackageSet.getText().toString())){
        }


    }
    PackageBean.DataBean packageChecked;
    List<ProductBean.DataBean> prd;
    /**
     * @param msg
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(BaseMsg msg){
        OptionHelp.UrlTag urlTag = msg.getUrlTag();
        switch (urlTag){
            default:
            case CONSUMPTION_TYPE:
                ConsumptionTypeBean.DataBean checked1 = (ConsumptionTypeBean.DataBean) msg.getSingleChecked();
                mTvConsumptionType.setText(checked1.getShop_name());
                break;
            case PRODUCT:
               prd = (List<ProductBean.DataBean>) msg.getMultiSelected();
                mTvAddition.setText(prd.size()+"");
                break;
            case PACKAGE:
                    mLlAddition.setVisibility(View.VISIBLE);
                    mViewPackageLine.setVisibility(View.VISIBLE);
                packageChecked = (PackageBean.DataBean) msg.getSingleChecked();
                mTvPackageSet.setText(packageChecked.getPackage_name());
                break;
            case OUTLETS_RECEPTION:
                    if (msg.getTag()!=null&&msg.getTag().equals(getString(R.string.reception))){
                        OutletsReceptionBean.DataBean checked2 = (OutletsReceptionBean.DataBean) msg.getSingleChecked();
                        mTvReception.setText(checked2.getStaffName());
                    }else {
                        OutletsReceptionBean.DataBean checked2 = (OutletsReceptionBean.DataBean) msg.getSingleChecked();
                        mTvOutletsReception.setText(checked2.getStaffName());
                    }
                break;
            case ORDER_RECEIVING_SITE:
                OrderReceivingSiteBean.DataBean checked3 = (OrderReceivingSiteBean.DataBean) msg.getSingleChecked();
                mTvOrderReceivingSite.setText(checked3.getShop_name());
                break;
            case CUSTOMER_ZONE:
                CustomerZoneBean.DataBean checked4 = (CustomerZoneBean.DataBean) msg.getSingleChecked();
                mTvVipZone.setText(checked4.getArea_name());
                break;
        }

    }
    /**
     * 时间选择
     */
    public void showDataSelect() {
//                DateUtil.dateToString()
//年月日时分秒 的显示与否，不设置则默认全部显示
        TimePickerView  timePickerView = new TimePickerView.Builder(this, new TimePickerView.OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {//选中事件回调
                mTvMarriageDate.setText(DateUtil.dateToString(date, DateUtil.DatePattern.ONLY_DAY));
//                DateUtil.dateToString()
//            toast(date.toString());
            }
        }) //年月日时分秒 的显示与否，不设置则默认全部显示
                .setTitleText(getString(R.string.please_select_marriage_date)).setLineSpacingMultiplier(4).setType(new boolean[]{true, true, true, false  , false, false})
                .setTitleColor(getResources().getColor(R.color.textHint_99)).setTitleBgColor(getResources().getColor(R.color.white)).build();
        timePickerView.setDate(Calendar.getInstance());//注：根据需求来决定是否使用该方法（一般是精确到秒的情况），此项可以在弹出选择器的时候重新设置当前时间，避免在初始化之后由于时间已经设定，导致选中时间与当前时间不匹配的问题。
        timePickerView.show();

    }

    @Override
    public void getNumberFailed(String msg) {
        if (msg!=null){
            toast(msg);
        }else {
            toast("获取订单号失败");
        }

    }
    @Override
    public void getOrderNumberSucceed(int what ,String data) {
        if (what==9){
            ToastUtils.show("开单成功："+data);
            finish();
        }else {
            if (mTvOrderId != null) {
                mTvOrderId.setText(data);
            }
        }
    }
}
