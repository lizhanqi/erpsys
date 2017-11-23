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
import com.suxuantech.erpsys.R;
import com.suxuantech.erpsys.activity.base.BaseActivity;
import com.suxuantech.erpsys.utils.DateUtil;
import com.suxuantech.erpsys.views.AdjustDrawableTextView;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 门市开单
 */
public class OutletsOrderActivity extends BaseActivity {

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


    @Override
    protected void permissionResult(boolean hasPermission, int requsetcode, List<String> permission) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_outlets_order);
        mTvNavTitle =  idSetOnClick(R.id.tv_nav_title);
        idSetOnClick(R.id.tv_sex);
        idSetOnClick(R.id.tv_nav_left);
        mTvNavTitle.setText("门市开单");
        ButterKnife.bind(this);
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
                        this, AlertView.Style.ActionSheet, new OnItemClickListener(){
                    @Override
                    public void onItemClick(Object o, int position){//position -1是取消按钮
                    toast( "点击了第" + position + "个");
                    }
                }).show();
                break;
            case R.id.tv_consumption_type:
                break;
            case R.id.tv_outlets_reception:
                break;
            case R.id.tv_order_receiving_site:
                break;
            case R.id.tv_package_set:
                break;
            case R.id.tv_addition:
                break;
            case R.id.tv_vip_zone:
                break;
            case R.id.tv_reception:
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

    public void showDataSelect() {
        Calendar instance = Calendar.getInstance();

        //时间选择器
        //选中事件回调
//                DateUtil.dateToString()
//年月日时分秒 的显示与否，不设置则默认全部显示
        TimePickerView  timePickerView = new TimePickerView.Builder(this, new TimePickerView.OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {//选中事件回调
                mTvMarriageDate.setText(  DateUtil.dateToString(date, DateUtil.DatePattern.ONLY_DAY));
//                DateUtil.dateToString()
//            toast(date.toString());
            }
        }) //年月日时分秒 的显示与否，不设置则默认全部显示
                .setTitleText(getString(R.string.please_select_marriage_date)).setLineSpacingMultiplier(4).setType(new boolean[]{true, true, true, false  , false, false})
                .setTitleColor(getResources().getColor(R.color.textHint_99)).setTitleBgColor(getResources().getColor(R.color.white)).build();
        timePickerView.setDate(Calendar.getInstance());//注：根据需求来决定是否使用该方法（一般是精确到秒的情况），此项可以在弹出选择器的时候重新设置当前时间，避免在初始化之后由于时间已经设定，导致选中时间与当前时间不匹配的问题。
        timePickerView.show();
    }
}
