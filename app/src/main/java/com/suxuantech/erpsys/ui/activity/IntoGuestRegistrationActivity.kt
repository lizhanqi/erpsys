package com.suxuantech.erpsys.ui.activity

import android.content.Intent
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import com.bigkoo.alertview.AlertView
import com.bigkoo.alertview.OnItemClickListener
import com.bigkoo.pickerview.TimePickerView
import com.blankj.utilcode.util.KeyboardUtils
import com.suxuantech.erpsys.App
import com.suxuantech.erpsys.R
import com.suxuantech.erpsys.common.OptionHelp
import com.suxuantech.erpsys.entity.*
import com.suxuantech.erpsys.eventmsg.BaseMsg
import com.suxuantech.erpsys.nohttp.Contact
import com.suxuantech.erpsys.nohttp.HttpListener
import com.suxuantech.erpsys.nohttp.JavaBeanRequest
import com.suxuantech.erpsys.ui.activity.base.TitleNavigationActivity
import com.suxuantech.erpsys.ui.widget.ScrollEditText
import com.suxuantech.erpsys.utils.DateUtil
import com.suxuantech.erpsys.utils.ToastUtils
import com.yanzhenjie.nohttp.RequestMethod
import com.yanzhenjie.nohttp.rest.Response
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import java.util.*

/**
 * 进客登记
 */
class IntoGuestRegistrationActivity : TitleNavigationActivity() {
    private var submit: Button? = null
    /**
     * 请填写姓名
     */
    private var mEtCustomerName: EditText? = null
    /**
     * 请填写客户手机号
     */
    private var mEtCustomerPhone: EditText? = null
    /**
     * 请选择门市接待
     */
    private var mTvOutletsReception: TextView? = null
    private var mLlOutletsReception: LinearLayout? = null
    /**
     * 请填写客户微信
     */
    private var mEtCustomerWechat: EditText? = null
    private var mLlCustomerWechat: LinearLayout? = null
    /**
     * 请填写客户QQ
     */
    private var mETCustomerQQ: TextView? = null
    private var mLlCustomerQQ: LinearLayout? = null
    /**
     * 请选择性别
     */
    private var mTvCustomerSex: TextView? = null
    private var mLlCustomerSex: LinearLayout? = null
    /**
     * 请选择消费类型
     */
    private var mTvConsumptionType: TextView? = null
    private var mLlConsumptionType: LinearLayout? = null
    /**
     * 请选择客户分区
     */
    private var mTvCustomerZone: TextView? = null
    private var mLlCustomerZone: LinearLayout? = null
    /**
     * 请选择客户生日
     */
    private var mTvCustomerBirthday: TextView? = null
    private var mLlCustomerBirthday: LinearLayout? = null
    /**
     * 请填写姓名
     */
    private var mTvCustmoerSource: TextView? = null
    private var mLlCustmoerSource: LinearLayout? = null
    /**
     * 请选择客户意向
     */
    private var mTvCustomerIntention: TextView? = null
    private var mLlCustomerIntention: LinearLayout? = null
    /**
     * 请选择接单点
     */
    private var mTvOrderReceivingPoint: TextView? = null
    private var mLlOrderReceivingPoint: LinearLayout? = null
    /**
     * 请选择客户地址
     */
    private var mTvCustmoerAddress: EditText? = null
    private var mLlCustmoerAddress: LinearLayout? = null
    /**
     * 请选择预约进店时间
     */
    private var mTvReservationDate: TextView? = null
    private var mLlReservationDate: LinearLayout? = null
    /**
     * 请选择预拍日期
     */
    private var mTvReservationShootDate: TextView? = null
    private var mLlReservationShootDate: LinearLayout? = null
    /**
     * 请选择结婚日期
     */
    private var mTvMarryDate: TextView? = null
    private var mLlMarryDate: LinearLayout? = null
    /**
     * 请填写配偶姓名
     */
    private var mEtMateName: EditText? = null
    /**
     * 请填写配偶手机
     */
    private var mEtMatePhone: EditText? = null
    /**
     * 请填写配偶微信
     */
    private var mEtMateWechat: EditText? = null
    /**
     * 请填写配偶QQ
     */
    private var mEtMateQQ: EditText? = null
    /**
     * 请选择配偶性别
     */
    private var mTvMateSex: TextView? = null
    private var mLlMateSex: LinearLayout? = null
    /**
     * 请填选择配偶生日
     */
    private var mTvMateBirthday: TextView? = null
    private var mLlMateBirthday: LinearLayout? = null
    /**
     * 请填写客户备注
     */
    private var mEtCustmoerMark: ScrollEditText? = null
    /**
     * 消费类型id
     */
    var consultationTypeId = "";
    /**
     * 客户分区id
     */
    var customerZoneId = "";
    /**
     * 客户来源id
     */
    var customerSourceId = "";
    /**
     * 客户意向id
     */
    var customerIntentionId = "";
    /**
     * 门市接待(销售人员)id
     */
    var salesStaffNumber = "";

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_into_guest_registration)
        initView()
        setTitle("进客登记")
        supportToolbar()
        getSupportActionBar()?.setBackgroundDrawable(ColorDrawable(resources.getColor(R.color.white)))
        getSupportActionBar()!!.setDisplayHomeAsUpEnabled(true);
        useEventBus()
    }

    override fun widgetClick(v: View?) {
        super.widgetClick(v)
        when (v?.id) {
            R.id.ll_order_receiving_point -> {
                val optionHelp2 = OptionHelp(this)
                optionHelp2.setTitle(getString(R.string.order_receiving_site))
                optionHelp2.setCheckedData(mTvOrderReceivingPoint?.getText().toString())
                optionHelp2.setUrlTag(OptionHelp.UrlTag.ORDER_RECEIVING_SITE)
                startActivity(optionHelp2.creat())
            }
            R.id.ll_custmoer_source -> {
                val optionHelp1 = OptionHelp(this)
                optionHelp1.setTitle("客户来源")
                optionHelp1.setCheckedData(mTvCustmoerSource?.getText().toString())
                optionHelp1.setUrlTag(OptionHelp.UrlTag.CUSTOMER_SOURCE)
                startActivity(optionHelp1.creat())
            }
            R.id.ll_outlets_reception -> {
                val optionHelp1 = OptionHelp(this)
                optionHelp1.setTitle(getString(R.string.outlets_reception))
                optionHelp1.setCheckedData(mTvOutletsReception?.getText().toString())
                optionHelp1.setUrlTag(OptionHelp.UrlTag.OUTLETS_RECEPTION)
                startActivity(optionHelp1.creat())
            }
            R.id.ll_consumption_type -> {
                val optionHelp = OptionHelp(this)
                optionHelp.setTitle(getString(R.string.consumption_type))
                optionHelp.setCheckedData(mTvConsumptionType?.getText().toString())
                optionHelp.setUrlTag(OptionHelp.UrlTag.CONSUMPTION_TYPE)
                startActivity(optionHelp.creat())
            }
            R.id.ll_customer_zone -> {
                val optionHelp3 = OptionHelp(this)
                optionHelp3.setTitle(getString(R.string.customer_zone))
                optionHelp3.setCheckedData(mTvCustomerZone?.getText().toString())
                optionHelp3.setUrlTag(OptionHelp.UrlTag.CUSTOMER_ZONE)
                startActivity(optionHelp3.creat())
            }
            R.id.ll_customer_intention -> {
                val optionHelp3 = OptionHelp(this)
                optionHelp3.setTitle("客户意向")
                optionHelp3.setCheckedData(mTvCustomerIntention?.getText().toString())
                optionHelp3.setUrlTag(OptionHelp.UrlTag.CUSTOMER_INTENTION)
                startActivity(optionHelp3.creat())
            }


            R.id.ll_customer_sex -> {
                AlertView(getString(R.string.please_select_sex), null, getString(R.string.cancel), null,
                        resources.getStringArray(R.array.sex),
                        this, AlertView.Style.ACTIONSHEET, OnItemClickListener { o, position ->
                    //position -1是取消按钮
                    if (position == 1) {
                        mTvCustomerSex?.setText("女")
                    } else if (position == 0) {
                        mTvCustomerSex?.setText("男")
                    }
                }).show()
            }
            R.id.ll_mate_sex -> {
                AlertView(getString(R.string.please_select_sex), null, getString(R.string.cancel), null,
                        resources.getStringArray(R.array.sex),
                        this, AlertView.Style.ACTIONSHEET, OnItemClickListener { o, position ->
                    //position -1是取消按钮
                    if (position == 1) {
                        mTvMateSex?.setText("女")
                    } else if (position == 0) {
                        mTvMateSex?.setText("男")
                    }
                }).show()
            }

            R.id.ll_customer_birthday -> {
                showDateSelect(mTvCustomerBirthday)
            }
            R.id.ll_mate_birthday -> {
                showDateSelect(mTvMateBirthday)
            }
            R.id.ll_reservation_shoot_date -> {
                showDateSelect(mTvReservationShootDate)
            }

            R.id.ll_reservation_date -> {
                showDateSelect(mTvReservationDate)
            }

            R.id.ll_marry_date -> {
                showDateSelect(mTvMarryDate)
            }
            R.id.btn_submint -> {
                commint();
            }
        }
    }

    /**
     * @param msg
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onMessageEvent(msg: BaseMsg<*>) {
        val urlTag = msg.urlTag
        when (urlTag) {
            OptionHelp.UrlTag.CUSTOMER_ZONE -> {
                val checked4 = msg.singleChecked as CustomerZoneEntity.DataBean
                customerZoneId = checked4.id
                mTvCustomerZone?.setText(checked4.getArea_name())
            }
            OptionHelp.UrlTag.OUTLETS_RECEPTION -> {
                val checked2 = msg.singleChecked as OutletsReceptionEntity.DataBean
                mTvOutletsReception?.setText(checked2.staffName)
                salesStaffNumber = checked2.staffID
            }
            OptionHelp.UrlTag.CONSUMPTION_TYPE -> {
                val checked1 = msg.singleChecked as ConsumptionTypeEntity.DataBean
                mTvConsumptionType?.setText(checked1.consumption_name)
                consultationTypeId = checked1.id
            }
            OptionHelp.UrlTag.CUSTOMER_SOURCE -> {
                val checked1 = msg.singleChecked as CustomerSourceEntity.DataBean
                mTvCustmoerSource?.setText(checked1.cus_name)
                customerSourceId = checked1.id
            }
            OptionHelp.UrlTag.CUSTOMER_INTENTION -> {
                val checked1 = msg.singleChecked as CustomerIntentionEntity.DataBean
                mTvCustomerIntention?.setText(checked1.intention_name)
                customerIntentionId = checked1.id
            }
            OptionHelp.UrlTag.ORDER_RECEIVING_SITE -> {
                val checked3 = msg.singleChecked as OrderReceivingSiteEntity.DataBean
                mTvOrderReceivingPoint?.setText(checked3.acceptoraddress_name)
            }
        }
    }

    private fun commint() {
        //请求实体
        var url = Contact.getFullUrl(Contact.GUEST_REGISTRATION, Contact.TOKEN)
        val stringRequest = JavaBeanRequest(url, RequestMethod.POST, GustRegistrationEntity::class.java)
        stringRequest.addBodyJson("Customer_name", mEtCustomerName?.text.toString())
        stringRequest.addBodyJson("Customer_tel", mEtCustomerPhone?.text.toString())
        stringRequest.addBodyJson("Customer_wechat", mEtCustomerWechat?.text.toString())
        stringRequest.addBodyJson("Customer_qq", mETCustomerQQ?.text.toString())
        stringRequest.addBodyJson("Customer_sex", mTvCustomerSex?.text.toString())
        stringRequest.addBodyJson("Consultation_type", mTvConsumptionType?.text.toString())
        stringRequest.addBodyJson("Customer_area", mTvCustomerZone?.text.toString())
        stringRequest.addBodyJson("Customer_birthday", if (TextUtils.isEmpty(mTvCustomerBirthday?.text.toString())) {
            ""
        } else {
            DateUtil.String2String(mTvCustomerBirthday?.text.toString(), DateUtil.DatePattern.ONLY_DAY, DateUtil.DatePattern.JUST_DAY_NUMBER)
        })
        stringRequest.addBodyJson("Customer_cource", mTvCustmoerSource?.text.toString())
        stringRequest.addBodyJson("Customer_intention", mTvCustomerIntention?.text.toString())
        stringRequest.addBodyJson("Customer_orderaddress", mTvOrderReceivingPoint?.text.toString())
        stringRequest.addBodyJson("Customer_address", mTvCustmoerAddress?.text.toString())
        stringRequest.addBodyJson("Yjd_day", if (TextUtils.isEmpty(mTvReservationDate?.text.toString())) {
            ""
        } else {
            DateUtil.String2String(mTvReservationDate?.text.toString(), DateUtil.DatePattern.ONLY_DAY, DateUtil.DatePattern.JUST_DAY_NUMBER)
        })
        stringRequest.addBodyJson("Wedding_date", if (TextUtils.isEmpty(mTvMarryDate?.text.toString())) {
            ""
        } else {
            DateUtil.String2String(mTvMarryDate?.text.toString(), DateUtil.DatePattern.ONLY_DAY, DateUtil.DatePattern.JUST_DAY_NUMBER)
        })
        stringRequest.addBodyJson("Yp_day", if (TextUtils.isEmpty(mTvReservationShootDate?.text.toString())) {
            ""
        } else {
            DateUtil.String2String(mTvReservationShootDate?.text.toString(), DateUtil.DatePattern.ONLY_DAY, DateUtil.DatePattern.JUST_DAY_NUMBER)
        })
        stringRequest.addBodyJson("Mate_name", mEtMateName?.text.toString())
        stringRequest.addBodyJson("Mate_sex", mTvMateSex?.text.toString())
        stringRequest.addBodyJson("Mate_tel", mEtMatePhone?.text.toString())
        stringRequest.addBodyJson("Mate_wechat", mEtMateWechat?.text.toString())
        stringRequest.addBodyJson("Mate_qq", mEtMateQQ?.text.toString())
        stringRequest.addBodyJson("Mate_birthday", if (TextUtils.isEmpty(mTvMateBirthday?.text.toString())) {
            ""
        } else {
            DateUtil.String2String(mTvMateBirthday?.text.toString(), DateUtil.DatePattern.ONLY_DAY, DateUtil.DatePattern.JUST_DAY_NUMBER)
        })
        stringRequest.addBodyJson("Dj_staff", App.getApplication().userInfor.staffname)
        stringRequest.addBodyJson("Sales_staff", mTvOutletsReception?.text.toString())
        stringRequest.addBodyJson("Customer_remark", mEtCustmoerMark?.text.toString())
        stringRequest.addBodyJson("Shop_name", App.getApplication().userInfor.shop_name)
        stringRequest.addBodyJson("Shop_code", App.getApplication().userInfor.shop_code)
        stringRequest.addBodyJson("Brandid", App.getApplication().userInfor.brandclass_id)
        stringRequest.addBodyJson("Customer_from", "APP")
        stringRequest.addBodyJson("From_index", "")
        stringRequest.addBodyJson("Sales_staff_number", salesStaffNumber)
        stringRequest.addBodyJson("Consultation_type_id", consultationTypeId)
        stringRequest.addBodyJson("Customer_area_id", customerZoneId)
        stringRequest.addBodyJson("Customer_cource_id", customerSourceId)
        stringRequest.addBodyJson("Customer_intention_id", customerIntentionId)
        stringRequest.addBodyJson("Staffid", App.getApplication().userInfor.staff_id)
        stringRequest.param2Json()
        val httpListener = object : HttpListener<GustRegistrationEntity> {
            override fun onSucceed(what: Int, response: Response<GustRegistrationEntity>?) {
                if (response!!.get().isOK) {
                    ToastUtils.snackbarShort("登记成功","确定")
                    search(response.get().data)
                } else {
                    ToastUtils.showShort(response.get().getMsg())
                }
            }

            override fun onFailed(what: Int, response: Response<GustRegistrationEntity>?) {
            }

        }
        request(0, stringRequest, httpListener, false, true)
    }


    fun showDateSelect(showint: TextView?) {
        KeyboardUtils.hideSoftInput(showint)
        //                DateUtil.dateToString()
        //年月日时分秒 的显示与否，不设置则默认全部显示
        val timePickerView = TimePickerView.Builder(this, TimePickerView.OnTimeSelectListener { date, v ->
            //选中事件回调
            showint?.setText(DateUtil.dateToString(date, DateUtil.DatePattern.ONLY_DAY))
            //                DateUtil.dateToString()
            //            toast(date.toString());
        }) //年月日时分秒 的显示与否，不设置则默认全部显示
                .setTitleText("选择日期").setLineSpacingMultiplier(4f).setType(booleanArrayOf(true, true, true, false, false, false))
                .setTitleColor(resources.getColor(R.color.textHint_99)).setTitleBgColor(resources.getColor(R.color.white)).build()
        timePickerView.setDate(Calendar.getInstance())//注：根据需求来决定是否使用该方法（一般是精确到秒的情况），此项可以在弹出选择器的时候重新设置当前时间，避免在初始化之后由于时间已经设定，导致选中时间与当前时间不匹配的问题。
        timePickerView.show()
    }

    private fun initView() {
        submit = findViewById<Button>(R.id.btn_submint) as Button
        submit?.setOnClickListener(this)
        mEtCustomerName = findViewById<EditText>(R.id.et_customer_name) as EditText
        mEtCustomerPhone = findViewById<EditText>(R.id.et_customer_phone) as EditText
        mTvOutletsReception = findViewById<TextView>(R.id.tv_outlets_reception) as TextView
        mLlOutletsReception = findViewById<LinearLayout>(R.id.ll_outlets_reception) as LinearLayout
        mLlOutletsReception?.setOnClickListener(this)
        mEtCustomerWechat = findViewById<EditText>(R.id.et_customer_wechat) as EditText
        mLlCustomerWechat = findViewById<LinearLayout>(R.id.ll_customer_wechat) as LinearLayout
        mLlCustomerWechat?.setOnClickListener(this)
        mETCustomerQQ = findViewById<TextView>(R.id.et_customer_QQ) as TextView
        mLlCustomerQQ = findViewById<LinearLayout>(R.id.ll_customer_QQ) as LinearLayout
        mTvCustomerSex = findViewById<TextView>(R.id.tv_customer_sex) as TextView
        mLlCustomerSex = findViewById<LinearLayout>(R.id.ll_customer_sex) as LinearLayout
        mLlCustomerSex?.setOnClickListener(this)
        mTvConsumptionType = findViewById<TextView>(R.id.tv_consumption_type) as TextView
        mLlConsumptionType = findViewById<LinearLayout>(R.id.ll_consumption_type) as LinearLayout
        mLlConsumptionType?.setOnClickListener(this)
        mTvCustomerZone = findViewById<TextView>(R.id.tv_customer_zone) as TextView
        mLlCustomerZone = findViewById<LinearLayout>(R.id.ll_customer_zone) as LinearLayout
        mLlCustomerZone?.setOnClickListener(this)
        mTvCustomerBirthday = findViewById<TextView>(R.id.tv_customer_birthday) as TextView
        mLlCustomerBirthday = findViewById<LinearLayout>(R.id.ll_customer_birthday) as LinearLayout
        mLlCustomerBirthday?.setOnClickListener(this)
        mTvCustmoerSource = findViewById<TextView>(R.id.tv_custmoer_source) as TextView
        mLlCustmoerSource = findViewById<LinearLayout>(R.id.ll_custmoer_source) as LinearLayout
        mLlCustmoerSource?.setOnClickListener(this)
        mTvCustomerIntention = findViewById<TextView>(R.id.tv_customer_intention) as TextView
        mLlCustomerIntention = findViewById<LinearLayout>(R.id.ll_customer_intention) as LinearLayout
        mLlCustomerIntention?.setOnClickListener(this)
        mTvOrderReceivingPoint = findViewById<TextView>(R.id.tv_order_receiving_point) as TextView
        mLlOrderReceivingPoint = findViewById<LinearLayout>(R.id.ll_order_receiving_point) as LinearLayout
        mLlOrderReceivingPoint?.setOnClickListener(this)
        mTvCustmoerAddress = findViewById<EditText>(R.id.et_custmoer_address) as EditText
        mLlCustmoerAddress = findViewById<LinearLayout>(R.id.ll_custmoer_address) as LinearLayout
        mLlCustmoerAddress?.setOnClickListener(this)
        mTvReservationDate = findViewById<TextView>(R.id.tv_reservation_date) as TextView
        mLlReservationDate = findViewById<LinearLayout>(R.id.ll_reservation_date) as LinearLayout
        mLlReservationDate?.setOnClickListener(this)
        mTvReservationShootDate = findViewById<TextView>(R.id.tv_reservation_shoot_date) as TextView
        mLlReservationShootDate = findViewById<LinearLayout>(R.id.ll_reservation_shoot_date) as LinearLayout
        mLlReservationShootDate?.setOnClickListener(this)
        mTvMarryDate = findViewById<TextView>(R.id.tv_marry_date) as TextView
        mLlMarryDate = findViewById<LinearLayout>(R.id.ll_marry_date) as LinearLayout
        mLlMarryDate?.setOnClickListener(this)
        mEtMateName = findViewById<EditText>(R.id.et_mate_name) as EditText
        mEtMatePhone = findViewById<EditText>(R.id.et_mate_phone) as EditText
        mEtMateWechat = findViewById<EditText>(R.id.et_mate_wechat) as EditText
        mEtMateQQ = findViewById<EditText>(R.id.et_mate_QQ) as EditText
        mTvMateSex = findViewById<TextView>(R.id.tv_mate_sex) as TextView
        mLlMateSex = findViewById<LinearLayout>(R.id.ll_mate_sex) as LinearLayout
        mLlMateSex?.setOnClickListener(this)
        mTvMateBirthday = findViewById<TextView>(R.id.tv_mate_birthday) as TextView
        mLlMateBirthday = findViewById<LinearLayout>(R.id.ll_mate_birthday) as LinearLayout
        mLlMateBirthday?.setOnClickListener(this)
        mEtCustmoerMark = findViewById<ScrollEditText>(R.id.et_custmoer_mark) as ScrollEditText
    }

    private fun search(jkID: String) {
        if (!App.getApplication().hasPermission("K2")) {
            toastShort("无权限查询")
            return
        }
        var name1 = App.getApplication().userInfor.staffname;
        var name2 = App.getApplication().userInfor.staffname;
        if (App.getApplication().hasPermission("K14")) {
            name1 = ""
        }
        if (App.getApplication().hasPermission("K15")) {
            name2 = ""
        }
        val nowDate = DateUtil.getNowDate(DateUtil.DatePattern.JUST_DAY_NUMBER);
        var url = Contact.getFullUrl(Contact.INQUIRE_GUEST_INFO, Contact.TOKEN
                , nowDate, nowDate,"" ,
                0, 20,
                App.getApplication().userInfor.shop_code,jkID , name1, name2
        )
        val districtBeanJavaBeanRequest = JavaBeanRequest(url,  RegisterEntity::class.java)
        val searchByCustmor = object : HttpListener<RegisterEntity> {
            override fun onSucceed(what: Int, response: Response<RegisterEntity>) {
                if (response.get().isOK) {
                    if (response.get().data != null && response.get().data.size == 1) {
                        val data = response.get().data.get(0);
                        var it = Intent(baseContext, CustomerDetailsActivity::class.java);
                        var vb = Bundle();
                        vb.putString("orderId", data.customer_number)
                        vb.putParcelable("data", data)
                        it.putExtra("bundle", vb)
                        startActivity(it)
                    } else {
                        toastShort("多条记录无法转到进客详情")
                    }
                }
            }

            override fun onFailed(what: Int, response: Response<RegisterEntity>) {
            }
        }
        request(3, districtBeanJavaBeanRequest, searchByCustmor, true, false)
    }

}
