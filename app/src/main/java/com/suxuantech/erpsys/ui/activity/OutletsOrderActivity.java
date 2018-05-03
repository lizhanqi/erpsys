package com.suxuantech.erpsys.ui.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IntRange;
import android.support.design.widget.TextInputEditText;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.TimePickerView;
import com.blankj.utilcode.util.KeyboardUtils;
import com.suxuantech.erpsys.App;
import com.suxuantech.erpsys.R;
import com.suxuantech.erpsys.common.OptionHelp;
import com.suxuantech.erpsys.entity.ConsumptionTypeEntity;
import com.suxuantech.erpsys.entity.CustomerSourceEntity;
import com.suxuantech.erpsys.entity.CustomerZoneEntity;
import com.suxuantech.erpsys.entity.NewOrderTypeEntity;
import com.suxuantech.erpsys.entity.OpenWeddingOrderEntity;
import com.suxuantech.erpsys.entity.OrderReceivingSiteEntity;
import com.suxuantech.erpsys.entity.OutletsReceptionEntity;
import com.suxuantech.erpsys.entity.PackageEntity;
import com.suxuantech.erpsys.entity.PhotoShopEntity;
import com.suxuantech.erpsys.entity.RegisterEntity;
import com.suxuantech.erpsys.entity.ThemeEntity;
import com.suxuantech.erpsys.eventmsg.BaseMsg;
import com.suxuantech.erpsys.nohttp.Contact;
import com.suxuantech.erpsys.nohttp.HttpListener;
import com.suxuantech.erpsys.nohttp.JavaBeanRequest;
import com.suxuantech.erpsys.presenter.OutletsOrderPresenter;
import com.suxuantech.erpsys.presenter.connector.IOutletsOrderPresenter;
import com.suxuantech.erpsys.ui.activity.base.TitleNavigationActivity;
import com.suxuantech.erpsys.utils.DateUtil;
import com.yanzhenjie.alertdialog.AlertDialog;
import com.yanzhenjie.nohttp.RequestMethod;
import com.yanzhenjie.nohttp.rest.Response;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

import static com.suxuantech.erpsys.utils.DateUtil.DatePattern.JUST_DAY_NUMBER;

/**
 * 门市开单
 */
public class OutletsOrderActivity extends TitleNavigationActivity implements IOutletsOrderPresenter {
    @BindView(R.id.tv_order_id)
    TextView mTvOrderId;
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
    @BindView(R.id.et_customer_wechat_m)
    TextInputEditText mEtCustomerWechatM;

    @BindView(R.id.et_customer_qq_m)
    TextInputEditText mEtCustomerQQM;
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
    @BindView(R.id.et_customer_qq_w)
    TextInputEditText mEtCustomerQQW;
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
    @BindView(R.id.ll_shoot_theme)
    LinearLayout mLlShootTheme;
    @BindView(R.id.rl_shoot_theme)
    RelativeLayout mRlShootTheme;
    @BindView(R.id.ll_drees_theme)
    LinearLayout mLlDreesTheme;
    @BindView(R.id.rl_drees_theme)
    RelativeLayout mRlDreesTheme;
    @BindView(R.id.tv_intention_package)
    TextView mTvIntentionPackage;
    @BindView(R.id.ll_intention_package)
    LinearLayout mLlIntentionPackage;
//    @BindView(R.id.srl_fresh)
//    SmartRefreshLayout mSmartRefreshLayout;

    // @BindView(R.id.btn_submint_order)
    //Button mBtnSubmintOrder;
    private OutletsOrderPresenter outletsOrderPresenter;
    private List<ThemeEntity.DataBean> shootTheme;
    private List<ThemeEntity.DataBean> dressTheme;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_outlets_order_new);
        useEventBus();
        useButterKnife();
        initViewData();
        android.support.v7.widget.Toolbar toolbar = findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
   supportToolbar();
        //      mSmartRefreshLayout.autoRefresh();
        outletsOrderPresenter = new OutletsOrderPresenter(this, getRequestQueue());
        //  mSmartRefreshLayout.setOnRefreshListener(refreshLayout -> {
//            outletsOrderPresenter.getOrderNum();
//        });
        outletsOrderPresenter.getOrderNum();
        mTvOrderDate.setText(DateUtil.getNowDate(DateUtil.DatePattern.ONLY_DAY));
    }

    private void initViewData() {
        RegisterEntity.DataBean parcelable = getIntent().getParcelableExtra("data");
        if (parcelable == null) {
            return;
        }
        mTvConsumptionType.setText(parcelable.getConsultation_type());
        if (parcelable.getCustomer_sex().equals("男")) {
            mEtCustomerNameM.setText(parcelable.getCustomer_name());
            mEtCustomerNameW.setText(parcelable.getMate_name());

            mTvCustomerBirthdayM.setText(DateUtil.String2String(parcelable.getCustomer_birthday(), DateUtil.DatePattern.JUST_DAY_NUMBER,
                    DateUtil.DatePattern.ONLY_DAY));
            mTvCustomerBirthdayW.setText(DateUtil.String2String(parcelable.getMate_birthday(),
                    DateUtil.DatePattern.JUST_DAY_NUMBER,
                    DateUtil.DatePattern.ONLY_DAY));

            mEtCustomerPhoneM.setText(parcelable.getCustomer_tel());
            mEtCustomerPhoneW.setText(parcelable.getMate_tel());

            mEtCustomerWechatM.setText(parcelable.getCustomer_wechat());
            mEtCustomerWechatW.setText(parcelable.getMate_wechat());

        } else {

            mEtCustomerNameW.setText(parcelable.getCustomer_name());
            mEtCustomerNameM.setText(parcelable.getMate_name());

            mTvCustomerBirthdayW.setText(DateUtil.String2String(parcelable.getCustomer_birthday(), DateUtil.DatePattern.JUST_DAY_NUMBER,
                    DateUtil.DatePattern.ONLY_DAY));
            mTvCustomerBirthdayM.setText(DateUtil.String2String(parcelable.getMate_birthday(), DateUtil.DatePattern.JUST_DAY_NUMBER,
                    DateUtil.DatePattern.ONLY_DAY));

            mEtCustomerPhoneW.setText(parcelable.getCustomer_tel());
            mEtCustomerPhoneM.setText(parcelable.getMate_tel());

            mEtCustomerWechatW.setText(parcelable.getCustomer_wechat());
            mEtCustomerWechatM.setText(parcelable.getMate_wechat());
        }
        mTvReceptionMarket.setText(parcelable.getSales_staff());
        mTvReception.setText(parcelable.getDj_staff());
        mTvReceptionGovernor.setText(parcelable.getFp_staff());
        mTvCustomerSource.setText(parcelable.getCustomer_cource());
        // mTvCustomerSource.setText(parcelable.getShop_codeZD());
    }

    /**
     * 时间选择
     */
    public void showDataSelect(TextView showIn) {
        KeyboardUtils.hideSoftInput(showIn);
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
        if (what == 9) {
            toastShort("开单成功：" + data);
            finish();
        } else {
            if (mTvOrderId != null) {
                mTvOrderId.setText(data);
            }
        }
    }

    String consumptionType = "";

    @Override
    @OnClick({R.id.ll_order_date, R.id.ll_consumption_type, R.id.ll_consumption_sub, R.id.ll_customer_birthday_m,
            R.id.ll_customer_birthday_w, R.id.ll_reception, R.id.ll_reception_market, R.id.ll_reception_governor, R.id.ll_marriage_date,
            R.id.ll_photo_shop_one, R.id.ll_intention_shop, R.id.ll_photo_shop_two, R.id.ll_customer_zone, R.id.ll_customer_source, R.id.btn_submint_order, R.id.ll_shoot_theme, R.id.rl_shoot_theme, R.id.ll_drees_theme, R.id.rl_drees_theme, R.id.ll_intention_package})
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
                if (mTvConsumptionType.getText().toString().isEmpty()) {
                    toastShort("请先选择消费类型");
                    return;
                }
                optionHelp.setTitle("新单类型");
                optionHelp.setCheckedData(mTvConsumptionSub.getText().toString());
                optionHelp.setUrlTag(OptionHelp.UrlTag.NEW_ORDER_TYPE);
                Intent creat = optionHelp.creat();
                creat.putExtra("ConsumptionType", mTvConsumptionType.getText().toString());
                startActivity(creat);
                break;
            case R.id.ll_customer_birthday_m:
                showDataSelect(mTvCustomerBirthdayM);
                break;
            case R.id.ll_customer_birthday_w:
                showDataSelect(mTvCustomerBirthdayW);
                break;
            case R.id.ll_reception:
                optionHelp.setTitle("销售门市");
                optionHelp.setCheckedData(mTvReception.getText().toString());
                optionHelp.setUrlTag(OptionHelp.UrlTag.OUTLETS_RECEPTION);
                startActivity(optionHelp.creat());
                break;
            case R.id.ll_reception_market:
                optionHelp.setTitle("网销门市");
                optionHelp.setCheckedData(mTvReceptionMarket.getText().toString());
                optionHelp.setUrlTag(OptionHelp.UrlTag.RECEPTION_MARKET);
                startActivity(optionHelp.creat());
                break;
            case R.id.ll_reception_governor:
                optionHelp.setTitle("门市主管");
                optionHelp.setCheckedData(mTvReceptionGovernor.getText().toString());
                optionHelp.setUrlTag(OptionHelp.UrlTag.OUTLETS_RECEPTION);
                startActivity(optionHelp.creat());
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
                optionHelp.setTitle("客户来源");
                optionHelp.setCheckedData(mTvCustomerSource.getText().toString());
                optionHelp.setUrlTag(OptionHelp.UrlTag.CUSTOMER_SOURCE);
                startActivity(optionHelp.creat());
                break;
            case R.id.btn_submint_order:
                if (getIntent().getParcelableExtra("data") != null) {
                    commit(0);
                } else {
                    commit(1);
                }

                break;
            case R.id.rl_shoot_theme:
                optionHelp.setTitle("摄影主题");
                // optionHelp.setCheckedData(shootTheme);
                optionHelp.setUrlTag(OptionHelp.UrlTag.SHOOT_THEME);
                optionHelp.setMultiple(true);
                startActivity(optionHelp.creat());
                break;
            case R.id.rl_drees_theme:
                optionHelp.setTitle("礼服主题");
                optionHelp.setCheckedData(mTvCustomerSource.getText().toString());
                optionHelp.setUrlTag(OptionHelp.UrlTag.DRESS_THEME);
                optionHelp.setMultiple(true);
                startActivity(optionHelp.creat());
                break;
            case R.id.ll_intention_package:
                optionHelp.setTitle("意向套系");
                if (mTvIntentionPackage.getTag() != null) {
                    optionHelp.addCheckedData(mTvIntentionPackage.getTag().toString());
                }
                optionHelp.setUrlTag(OptionHelp.UrlTag.PACKAGE);
                startActivity(optionHelp.creat());
                break;
        }
    }

    /**
     * 婚纱
     * （0开单/1转单）
     *
     * @param zktype
     */
    private void commit(@IntRange(from = 0, to = 1) int zktype) {
        if (mTvOrderId.getText().toString().isEmpty()) {
            toastShort("请刷新获取订单号");
            return;
        }
        if (mTvOrderDate.getText().toString().isEmpty()) {
            toastShort("请选择订单日期");
            return;
        }
        if (mEtCustomerNameM.getText().toString().isEmpty() && mEtCustomerNameW.getText().toString().isEmpty()) {
            toastShort("请填写一个姓名");
            return;
        }
        if (mEtCustomerPhoneM.getText().toString().isEmpty() && mEtCustomerPhoneW.getText().toString().isEmpty()) {
            toastShort("至少填写一个手机");
            return;
        }
        if (mTvPhotoShopOne.getText().toString().isEmpty() && mTvPhotoShopTwo.getText().toString().isEmpty()) {
            toastShort("至少选择一个拍照店");
            return;
        }
        if (mTvConsumptionType.getText().toString().isEmpty()) {
            toastShort("请选择消费类型");
            return;
        }
        if (mTvReception.getText().toString().isEmpty()) {
            toastShort("请选择销售门市");
            return;
        }
        if (mTvVipZone.getText().toString().isEmpty()) {
            toastShort("请选择客户分区");
            return;
        }
        String url = Contact.getFullUrl(Contact.OPEN_WEDDING_ORDER, Contact.TOKEN, mTvIntentionPackage.getText().toString(), "  ",
                App.getApplication().getUserInfor().getShop_code(),
                App.getApplication().getUserInfor().getShop_name(),
                mTvPhotoShopOne.getTag() == null ? " " : mTvPhotoShopOne.getTag(), mTvPhotoShopOne.getText() == null ? " " : mTvPhotoShopOne.getText(),
                mTvPhotoShopTwo.getTag() == null ? " " : mTvPhotoShopTwo.getTag(), mTvPhotoShopTwo.getText() == null ? " " : mTvPhotoShopTwo.getText(),
                zktype, App.getApplication().getUserInfor().getStaff_id());
        JavaBeanRequest<OpenWeddingOrderEntity> districtBeanJavaBeanRequest = new JavaBeanRequest<OpenWeddingOrderEntity>(url, RequestMethod.POST, OpenWeddingOrderEntity.class);
        districtBeanJavaBeanRequest.addBodyJson("mname", mEtCustomerNameM.getText().toString());//男士姓名
        districtBeanJavaBeanRequest.addBodyJson("mphone", mEtCustomerPhoneM.getText().toString());//男手机
        districtBeanJavaBeanRequest.addBodyJson("mwechat", mEtCustomerWechatM.getText().toString());//男微信
        districtBeanJavaBeanRequest.addBodyJson("mqq", mEtCustomerQQM.getText().toString());//男QQ
        //男生日
        districtBeanJavaBeanRequest.addBodyJson("mbirthdate", DateUtil.String2String(mTvCustomerBirthdayM.getText().toString(), DateUtil.DatePattern.ONLY_DAY, JUST_DAY_NUMBER));
        districtBeanJavaBeanRequest.addBodyJson("wname", mEtCustomerNameM.getText().toString());//女姓名
        districtBeanJavaBeanRequest.addBodyJson("wphone", mEtCustomerPhoneM.getText().toString());//女手机
        districtBeanJavaBeanRequest.addBodyJson("wwechat", mEtCustomerWechatM.getText().toString());//女微信
        districtBeanJavaBeanRequest.addBodyJson("wqq", mEtCustomerQQM.getText().toString());//女QQ
        //女生日
        districtBeanJavaBeanRequest.addBodyJson("wbirthdate", DateUtil.String2String(mTvCustomerBirthdayW.getText().toString(), DateUtil.DatePattern.ONLY_DAY, JUST_DAY_NUMBER));
        districtBeanJavaBeanRequest.addBodyJson("address", mEtCustomerAddress.getText().toString());//地址
        districtBeanJavaBeanRequest.addBodyJson("cssname", mTvCustomerSource.getText().toString());//客户来源
        districtBeanJavaBeanRequest.addBodyJson("area", mTvVipZone.getText().toString());//客户分区
        districtBeanJavaBeanRequest.addBodyJson("midtype", " ");//男件类型
        districtBeanJavaBeanRequest.addBodyJson("mid", " ");//男证件号
        districtBeanJavaBeanRequest.addBodyJson("introducerid", " ");//介绍人编号
        districtBeanJavaBeanRequest.addBodyJson("introducer_name", " ");//介绍人姓名
        //结婚日期
        districtBeanJavaBeanRequest.addBodyJson("weddingdate", DateUtil.String2String(mTvMarriageDate.getText().toString(), DateUtil.DatePattern.ONLY_DAY, JUST_DAY_NUMBER));
        districtBeanJavaBeanRequest.addBodyJson("wIdtype", " ");//女证件类型
        districtBeanJavaBeanRequest.addBodyJson("wid", " ");//女证件号
        districtBeanJavaBeanRequest.addBodyJson("customernote", mEtCustomerRemarks.getText().toString());//客户备注
        districtBeanJavaBeanRequest.addBodyJson("bbname", " ");//宝宝姓名
        districtBeanJavaBeanRequest.addBodyJson("bnickname", " ");//宝宝昵称
        districtBeanJavaBeanRequest.addBodyJson("bbithdate", " ");//宝宝生日
        districtBeanJavaBeanRequest.addBodyJson("bbsex", " ");//宝宝性别
        districtBeanJavaBeanRequest.addBodyJson("liunardate", " ");//农历生日
        districtBeanJavaBeanRequest.addBodyJson("bzodiac", " ");//生肖
        districtBeanJavaBeanRequest.addBodyJson("email", mEtCustomerEmail.getText().toString());//邮箱
        districtBeanJavaBeanRequest.addBodyJson("shop_code", App.getApplication().getUserInfor().getShop_code());//客户所属店面码
        districtBeanJavaBeanRequest.addBodyJson("shop_name", App.getApplication().getUserInfor().getShop_name());//     客户所属店面
        districtBeanJavaBeanRequest.addBodyJson("OrderId", mTvOrderId.getText().toString());// 订单编号
        districtBeanJavaBeanRequest.addBodyJson("Consumption_type", mTvConsumptionType.getText().toString());//消费类型
        districtBeanJavaBeanRequest.addBodyJson("Package_name", mTvintentionShop.getText().toString());//包套
        //订单日期
        districtBeanJavaBeanRequest.addBodyJson("Targetdate", DateUtil.String2String(mTvOrderDate.getText().toString(), DateUtil.DatePattern.ONLY_DAY, JUST_DAY_NUMBER));
        districtBeanJavaBeanRequest.addBodyJson("Storeconsuitant1", mTvReception.getText().toString());//门市
        districtBeanJavaBeanRequest.addBodyJson("Storeconsuitant2", mTvReceptionMarket.getText().toString());//网销
        districtBeanJavaBeanRequest.addBodyJson("Storeconsuitant3", mTvReceptionGovernor.getText().toString());//门市主管
        districtBeanJavaBeanRequest.addBodyJson("Yc_day", " ");//预产日期
        districtBeanJavaBeanRequest.addBodyJson("Bb_hospital", " ");//医院
        districtBeanJavaBeanRequest.addBodyJson("Customer_from", " ");//客资系统来源
        districtBeanJavaBeanRequest.addBodyJson("Brandid", App.getApplication().getUserInfor().getBrandclass_id());//品牌id
        districtBeanJavaBeanRequest.addBodyJson("Opershop", App.getApplication().getUserInfor().getShop_name());//操作店
        districtBeanJavaBeanRequest.addBodyJson("Oeder_newclass", mTvConsumptionSub.getText().toString());//新单类型
        districtBeanJavaBeanRequest.addBodyJson("Shareshoplist", "");//共享店
        districtBeanJavaBeanRequest.addBodyJson("Ordernote", " ");//订单备注

        districtBeanJavaBeanRequest.addBodyJson("Indoordresscount", mEtIndoorClothes.getText().toString());//室内服装套数
        districtBeanJavaBeanRequest.addBodyJson("Outerdresscount", mEtOutdoorClothes.getText().toString());//外景服装套数
        districtBeanJavaBeanRequest.addBodyJson("Seadresscount", mEtSeaviewClothes.getText().toString());//海景服装套数
        districtBeanJavaBeanRequest.addBodyJson("Travelresscount", mEtOverseasShootClothes.getText().toString());//旅拍服装套数
        districtBeanJavaBeanRequest.addBodyJson("Paizhaocount", mEtShootNumber.getText().toString());//拍照张数
        districtBeanJavaBeanRequest.addBodyJson("Rucecount", mEtNumberSheets.getText().toString());//入册张数
        districtBeanJavaBeanRequest.addBodyJson("Jingxiucount", mEtTruingNumber.getText().toString());//精修张数
        districtBeanJavaBeanRequest.addBodyJson("Customer_from_index", " ");//客户系统来源编号(转单用)
        districtBeanJavaBeanRequest.addBodyJson("Jinkeid", " ");//进客ID(转单用)
        districtBeanJavaBeanRequest.addBodyJson("Consumption_type_id", mTvConsumptionType.getTag() == null ? "" : mTvConsumptionType.getTag().toString());//消费类型ID
        districtBeanJavaBeanRequest.addBodyJson("Cssname_id", mTvCustomerSource.getTag() == null ? "" : mTvCustomerSource.getTag().toString());//客户来源ID
        districtBeanJavaBeanRequest.addBodyJson("Area_id", mTvVipZone.getTag() == null ? "" : mTvVipZone.getTag().toString());//客户分区ID
        districtBeanJavaBeanRequest.addBodyJson("Acceptor_address", "");//接单点
        districtBeanJavaBeanRequest.param2Json();
        StringBuffer shootThemeString = new StringBuffer();
        if (shootTheme != null) {
            for (ThemeEntity.DataBean th : shootTheme) {
                shootThemeString.append(th.getTopic());
                if (!((shootTheme.size() - 1) == shootTheme.lastIndexOf(th))) {
                    shootThemeString.append("|");
                }
            }
        }
        StringBuffer dressThemeString = new StringBuffer();
        if (dressTheme != null) {
            for (ThemeEntity.DataBean th : dressTheme) {
                dressThemeString.append(th.getTopic());
                if (!((dressTheme.size() - 1) == dressTheme.lastIndexOf(th))) {
                    dressThemeString.append("|");
                }
            }
        }
        districtBeanJavaBeanRequest.add("Ordertopic", shootThemeString.toString());//拍摄主题
        districtBeanJavaBeanRequest.add("Dresstheme", dressThemeString.toString());//礼服主题
        HttpListener<OpenWeddingOrderEntity> searchByCustmor = new HttpListener<OpenWeddingOrderEntity>() {
            @Override
            public void onSucceed(int what, Response<OpenWeddingOrderEntity> response) {
                if (response.get().isOK()) {
                    toastShort("开单成功");
                } else if (response.get().getCode().equals("700")) {
                    AlertDialog.Builder builder = AlertDialog.newBuilder(OutletsOrderActivity.this);
                    builder.setMessage(response.get().getMsg());
                    builder.setCancelable(false);
                    builder.setPositiveButton("刷新单号", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            outletsOrderPresenter.getOrderNum();
                        }
                    });
                    builder.show();
                }
            }

            @Override
            public void onFailed(int what, Response<OpenWeddingOrderEntity> response) {
            }
        };
        request(11, districtBeanJavaBeanRequest, searchByCustmor, false, true);
    }


    /**
     * @param msg
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(BaseMsg msg) {
        OptionHelp.UrlTag urlTag = msg.getUrlTag();
        switch (urlTag) {
            default:
            case RECEPTION_MARKET:
                mTvReceptionMarket.setText(((OutletsReceptionEntity.DataBean) msg.getSingleChecked()).getStaffName());
                break;
            case SHOOT_THEME:
                shootTheme = msg.getMultiSelected();
                mLlShootTheme.removeAllViews();
                for (ThemeEntity.DataBean f : shootTheme) {
                    TextView textView = new TextView(getBaseContext());
                    //   textView.setGravity(Gravity.RIGHT);
                    textView.setPadding((int) getResources().getDimension(R.dimen.px30), 0, 0, 0);
                    textView.setText(f.getTopic());
                    mLlShootTheme.addView(textView);
                }
                break;
            case DRESS_THEME:
                dressTheme = msg.getMultiSelected();
                mLlDreesTheme.removeAllViews();
                for (ThemeEntity.DataBean f : dressTheme) {
                    TextView textView = new TextView(getBaseContext());
                    // textView.setGravity(Gravity.RIGHT);
                    textView.setText(f.getTopic());
                    mLlDreesTheme.addView(textView);
                }
                break;
            case CUSTOMER_SOURCE:
                mTvCustomerSource.setTag(((CustomerSourceEntity.DataBean) msg.getSingleChecked()).getId());
                mTvCustomerSource.setText(((CustomerSourceEntity.DataBean) msg.getSingleChecked()).getCus_name());
                break;
            case NEW_ORDER_TYPE:
                mTvConsumptionSub.setText(((NewOrderTypeEntity.DataBean) msg.getSingleChecked()).getOrdertype());
                break;
            case CONSUMPTION_TYPE:
                mTvConsumptionType.setText(((ConsumptionTypeEntity.DataBean) msg.getSingleChecked()).getConsumption_name());
                consumptionType = ((ConsumptionTypeEntity.DataBean) msg.getSingleChecked()).getId();
                mTvConsumptionType.setTag(consumptionType);
                mTvConsumptionSub.setText(null);
                break;
            //http://192.168.0.15:8033/SXWebErpAppStaff/SX_Open_WeddingOrder?Token=^******^&package_name=  &cid=  &Code=ZX002&shopname=沈阳时尚经典婚纱店&PZCode=VVC009&PZshopname=时尚经典抚顺店&PZCode1=VVC009&PZshopname1=时尚经典旗舰店&zktype=0&staffid=404
            //    &shopname=%E6%B2%88%E9%98%B3%E6%97%B6%E5%B0%9A%E7%BB%8F%E5%85%B8%E5%A9%9A%E7%BA%B1%E5%BA%97&Code=ZX002&PZCode=ZX002&PZCode1=daxin123&zktype=0&PZshopname=%E6%B2%88%E9%98%B3%E6%97%B6%E5%B0%9A%E7%BB%8F%E5%85%B8%E5%A9%9A%E7%BA%B1%E5%BA%97&PZshopname1=%E5%A4%A7%E5%9E%8B%E6%97%97%E8%88%B0%E5%BA%97&staffid=404&package_name=7999%E4%B8%BD%E8%8E%8E%E6%B0%B4%E6%99%B6%E5%8C%85%E5%A5%97&cid=
            case PACKAGE:
                PackageEntity.DataBean packageChecked = (PackageEntity.DataBean) msg.getSingleChecked();
                mTvIntentionPackage.setText(packageChecked.getPackage_name());
                mTvIntentionPackage.setTag(packageChecked.getId());
                mEtIndoorClothes.setText(packageChecked.getShineifz() + "");//室内
                mEtOutdoorClothes.setText(packageChecked.getWaijingfz() + "");//室外
                mEtSeaviewClothes.setText(packageChecked.getNeijingfz() + "");//海景
                mEtOverseasShootClothes.setText(packageChecked.getLvpaifz() + "");//旅拍
                mEtShootNumber.setText(packageChecked.getPaizhaoshu() + "");//拍摄张数
                mEtNumberSheets.setText(packageChecked.getRuceshu() + "");//入册
                mEtTruingNumber.setText(packageChecked.getJingxiushu() + "");//精修
                break;
            case OUTLETS_RECEPTION:
                OutletsReceptionEntity.DataBean checked2 = (OutletsReceptionEntity.DataBean) msg.getSingleChecked();
                if (msg.getmTitle().equals("门市主管")) {
                    mTvReceptionGovernor.setText(checked2.getStaffName());
                } else {
                    mTvReception.setText(checked2.getStaffName());
                }
                break;
            case ORDER_RECEIVING_SITE:
                OrderReceivingSiteEntity.DataBean checked3 = (OrderReceivingSiteEntity.DataBean) msg.getSingleChecked();
                //  mTvOrderReceivingSite.setText(checked3.getShop_name());
                break;
            case CUSTOMER_ZONE:
                CustomerZoneEntity.DataBean checked4 = (CustomerZoneEntity.DataBean) msg.getSingleChecked();
                mTvVipZone.setText(checked4.getArea_name());
                mTvVipZone.setTag(checked4.getId());
                break;
            case PHOTO_SHOP:
                if (msg.getTag().equals("拍摄店一")) {
                    mTvPhotoShopOne.setText(((PhotoShopEntity.DataBean) msg.getSingleChecked()).getShop_name());
                    mTvPhotoShopOne.setTag(((PhotoShopEntity.DataBean) msg.getSingleChecked()).getBelong_shop_code());
                } else if (msg.getTag().equals("意向店")) {
                    mTvintentionShop.setText(((PhotoShopEntity.DataBean) msg.getSingleChecked()).getShop_name());
                } else {
                    mTvPhotoShopTwo.setTag(((PhotoShopEntity.DataBean) msg.getSingleChecked()).getBelong_shop_code());
                    mTvPhotoShopTwo.setText(((PhotoShopEntity.DataBean) msg.getSingleChecked()).getShop_name());
                }
                break;
        }
    }

}
