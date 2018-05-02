package com.suxuantech.erpsys.ui.activity

import android.os.Bundle
import android.support.annotation.IntRange
import android.view.View
import android.view.WindowManager
import android.widget.LinearLayout
import android.widget.PopupWindow
import android.widget.TextView
import com.suxuantech.erpsys.App
import com.suxuantech.erpsys.R
import com.suxuantech.erpsys.entity.ProductEntity
import com.suxuantech.erpsys.entity.RegisterEntity
import com.suxuantech.erpsys.nohttp.Contact
import com.suxuantech.erpsys.nohttp.HttpListener
import com.suxuantech.erpsys.nohttp.JavaBeanRequest
import com.suxuantech.erpsys.ui.activity.base.TitleNavigationActivity
import com.yanzhenjie.nohttp.RequestMethod
import com.yanzhenjie.nohttp.rest.Response

/**
 * 客资详情
 */
class CustomerDetailsActivity : TitleNavigationActivity() {
    var tvRegisterName: TextView? = null
    var tvRegisterType: TextView? = null
    var tvRegisterPhone: TextView? = null
    var tvRegisterConsumptionType: TextView? = null
    var tvRegisterZone: TextView? = null
    var tvRegisterDate: TextView? = null
    var tvRegisterStaff: TextView? = null
    var llLossing: LinearLayout? = null
    var tvRunReason: TextView? = null
    var tvCustomerWechat: TextView? = null
    var tvCustomerQQ: TextView? = null
    var tvCustomerSex: TextView? = null
    var tvCustomerBirthday: TextView? = null
    var tvCustomerSource: TextView? = null
    var tvCustomerIntention: TextView? = null
    var tvOrderAddress: TextView? = null
    var tvCustomerAddress: TextView? = null
    var tvMakeDate: TextView? = null
    var tvPreShootingDate: TextView? = null
    var tvMarryDate: TextView? = null
    var tvMateName: TextView? = null
    var tvMatePhone: TextView? = null
    var tvMateWechat: TextView? = null
    var tvMateQQ: TextView? = null
    var tvMateSex: TextView? = null
    var tvMateBirthday: TextView? = null
    var tvCustomerRemarks: TextView? = null
    override fun widgetClick(v: View?) {
        super.widgetClick(v)
        if (v!!.getId() == R.id.tv_nav_right) {
            showPopWindow()
        }
    }

    private var mMenuPopWindow: PopupWindow? = null
    /**
     * 发起会话弹窗
     */
    fun showPopWindow() {
        mMenuPopWindow?.setTouchable(true)
        mMenuPopWindow?.setOutsideTouchable(true)
        if (mMenuPopWindow!!.isShowing()) {
            mMenuPopWindow?.dismiss()
            recoverImmersionBar()
        } else {
            mMenuPopWindow?.showAsDropDown(navRightView, 0, 0)
            immersionBarDark()
        }
    }

    /**
     * flag 进店类型（数字类型1进店进客，2进店非进客）
     */
    public fun change(@IntRange(from = 1, to = 2) flag: Int) {
        //请求实体
        val districtBeanJavaBeanRequest = JavaBeanRequest(Contact.getFullUrl(Contact.INTO_STORE_STATUS, Contact.TOKEN, parcelable?.id, flag), RequestMethod.POST, ProductEntity::class.java)
        districtBeanJavaBeanRequest.addBodyJson("Customer_tel",parcelable?.customer_tel)
        districtBeanJavaBeanRequest.addBodyJson("Customer_name",parcelable?.customer_name)
        districtBeanJavaBeanRequest.addBodyJson("Mate_tel",parcelable?.mate_tel)
        districtBeanJavaBeanRequest.addBodyJson("Brandid",  App.getApplication().userInfor.brandclass_id)
        districtBeanJavaBeanRequest.addBodyJson("Shop_code",  App.getApplication().userInfor.shop_code)
         districtBeanJavaBeanRequest.addBodyJson("From_index", parcelable?.from_index)
        districtBeanJavaBeanRequest.addBodyJson("Staffid", App.getApplication().userInfor.staff_id)
        districtBeanJavaBeanRequest.param2Json()
//        districtBeanJavaBeanRequest.addBodyJson("Customer_wechat", parcelable?.customer_wechat)
//        districtBeanJavaBeanRequest.addBodyJson("Customer_qq", parcelable?.customer_qq)
//        districtBeanJavaBeanRequest.addBodyJson("Customer_sex", parcelable?.customer_sex)
//        districtBeanJavaBeanRequest.addBodyJson("Consultation_type", parcelable?.consultation_type)
//        districtBeanJavaBeanRequest.addBodyJson("Customer_area", parcelable?.customer_area)
//        districtBeanJavaBeanRequest.addBodyJson("Customer_birthday", parcelable?.customer_birthday)
//        districtBeanJavaBeanRequest.addBodyJson("Customer_cource", parcelable?.customer_cource)
//        districtBeanJavaBeanRequest.addBodyJson("Customer_intention", parcelable?.customer_intention)
//        districtBeanJavaBeanRequest.addBodyJson("Customer_orderaddress", parcelable?.customer_orderaddress)
//        districtBeanJavaBeanRequest.addBodyJson("Customer_address", parcelable?.customer_address)
//        districtBeanJavaBeanRequest.addBodyJson("Yjd_day", parcelable?.yjd_day)
//        districtBeanJavaBeanRequest.addBodyJson("Yp_day", parcelable?.yp_day)
//        districtBeanJavaBeanRequest.addBodyJson("Wedding_date", parcelable?.wedding_date)
//        districtBeanJavaBeanRequest.addBodyJson("Mate_name", parcelable?.mate_name)
//        districtBeanJavaBeanRequest.addBodyJson("Mate_sex", parcelable?.mate_sex)
//        districtBeanJavaBeanRequest.addBodyJson("Mate_tel", parcelable?.mate_tel)
//        districtBeanJavaBeanRequest.addBodyJson("Mate_wechat", parcelable?.mate_wechat)
//        districtBeanJavaBeanRequest.addBodyJson("Mate_qq", parcelable?.mate_qq)
//        districtBeanJavaBeanRequest.addBodyJson("Mate_birthday", parcelable?.mate_birthday)
//        districtBeanJavaBeanRequest.addBodyJson("Dj_staff", parcelable?.dj_staff)
//        districtBeanJavaBeanRequest.addBodyJson("Sales_staff", parcelable?.sales_staff)
//
//        districtBeanJavaBeanRequest.addBodyJson("customer_remark", parcelable?.customer_remark)
//        districtBeanJavaBeanRequest.addBodyJson("Shop_name", parcelable?.shop_name)
//        districtBeanJavaBeanRequest.addBodyJson("Shop_code", parcelable?.shop_code)
//        districtBeanJavaBeanRequest.addBodyJson("Customer_from", parcelable?.customer_from)
//        districtBeanJavaBeanRequest.addBodyJson("Brandid", parcelable?.brandid!!)
//        districtBeanJavaBeanRequest.addBodyJson("From_index", parcelable?.from_index!!)
//        districtBeanJavaBeanRequest.addBodyJson("Consultation_type_id", parcelable?.con)
//        districtBeanJavaBeanRequest.addBodyJson("Customer_area_id", parcelable?.customer_area_id!!)
        ///districtBeanJavaBeanRequest.addBodyJson("Sales_staff_number", parcelable?.sales_staff_number)
        districtBeanJavaBeanRequest.param2Json()
        val searchByCustmor = object : HttpListener<ProductEntity> {
            override fun onSucceed(what: Int, response: Response<ProductEntity>) {
                mMenuPopWindow?.dismiss()
                recoverImmersionBar()
                if(response.get().isOK){
                //更新....
                }
            }
            override fun onFailed(what: Int, response: Response<ProductEntity>) {
                mMenuPopWindow?.dismiss()
                recoverImmersionBar()
            }
        }
        request(0, districtBeanJavaBeanRequest, searchByCustmor, false, false)
    }


    /**
     * 初始化弹窗
     */
    private fun initPop() {
        val mMenuView = layoutInflater.inflate(R.layout.pop_customer_states, null)
        mMenuView.findViewById<TextView>(R.id.tv_new_customer).setOnClickListener(View.OnClickListener {
            change(1)
            mMenuPopWindow?.dismiss()
        })
        mMenuView.findViewById<TextView>(R.id.tv_not_into_shop).setOnClickListener(View.OnClickListener {
            change(2)
            mMenuPopWindow?.dismiss()
        })
        mMenuView.findViewById<TextView>(R.id.tv_not_in_shop).setOnClickListener(View.OnClickListener {
            mMenuPopWindow?.dismiss()
            recoverImmersionBar()
        })
        mMenuView.findViewById<TextView>(R.id.tv_make_bargain).setOnClickListener(View.OnClickListener {
            recoverImmersionBar()
            mMenuPopWindow?.dismiss()
            startActivity(OutletsOrderActivity::class.java,intent.getBundleExtra("bundle"))
        })
        mMenuView.findViewById<TextView>(R.id.tv_run_away).setOnClickListener(View.OnClickListener {
            recoverImmersionBar()
            mMenuPopWindow?.dismiss()
        })
        mMenuPopWindow = PopupWindow(mMenuView, WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT, true)
        //进出场动画
        mMenuPopWindow?.setAnimationStyle(R.style.AnimationPreview);
    }

    var parcelable: RegisterEntity.DataBean? = null;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_customer_details)
        showUserDefinedNav()
        setTitle("客资详情")
        initPop()
        initView()
        val drawable = resources.getDrawable(R.drawable.icon_ding)
        drawable.setBounds(0,0,drawable.minimumHeight,drawable.minimumHeight)
        setUseDefinedNavRightDrawable(drawable)
        var parcelable = intent.getBundleExtra("bundle").getParcelable<RegisterEntity.DataBean>("data")
        this.parcelable = parcelable;
        tvRegisterName!!.setText(parcelable.customer_name)
        tvRegisterPhone!!.setText("手机号" + parcelable.customer_tel)
        tvRegisterConsumptionType!!.setText("消费类型" + parcelable.consultation_type)
        tvRegisterZone!!.setText("客户分区" + parcelable.customer_area)
        tvRegisterDate!!.setText("进店日期" + parcelable.yjd_day)
        tvRegisterStaff!!.setText("接待人" + parcelable.sales_staff)
        tvRunReason!!.setText(parcelable.is_loss)
        tvCustomerWechat!!.setText(parcelable.customer_wechat)
        tvCustomerQQ!!.setText(parcelable.customer_qq)
        tvCustomerSex!!.setText(parcelable.customer_sex)
        tvCustomerBirthday!!.setText(parcelable.customer_birthday)
        tvCustomerSource!!.setText(parcelable.customer_cource)
        tvCustomerIntention!!.setText(parcelable.customer_intention)
        tvOrderAddress!!.setText(parcelable.customer_orderaddress)
        tvCustomerAddress!!.setText(parcelable.customer_address)
        tvMakeDate!!.setText(parcelable.yjd_day)
        tvMakeDate!!.setText(parcelable.yjd_day)
        tvMarryDate!!.setText(parcelable.wedding_date)
        tvMateName!!.setText(parcelable.mate_name)
        tvMatePhone!!.setText(parcelable.mate_tel)
        tvMateWechat!!.setText(parcelable.mate_wechat)
        tvMateQQ!!.setText(parcelable.mate_qq)
        tvMateSex!!.setText(parcelable.mate_sex)
        tvMateBirthday!!.setText(parcelable.mate_birthday)
        tvCustomerRemarks!!.setText(parcelable.customer_remark)
    }

    fun initView() {
        tvRegisterName = findViewById<TextView>(R.id.tv_register_name) as TextView
        tvRegisterType = findViewById<TextView>(R.id.tv_register_type) as TextView
        tvRegisterPhone = findViewById<TextView>(R.id.tv_register_phone) as TextView
        tvRegisterConsumptionType = findViewById<TextView>(R.id.tv_register_consumption_type) as TextView
        tvRegisterZone = findViewById<TextView>(R.id.tv_register_zone) as TextView
        tvRegisterDate = findViewById<TextView>(R.id.tv_register_date) as TextView
        tvRegisterStaff = findViewById<TextView>(R.id.tv_register_staff) as TextView
        llLossing = findViewById<LinearLayout>(R.id.ll_lossing) as LinearLayout
        tvRunReason = findViewById<TextView>(R.id.tv_run_reason) as TextView
        tvCustomerWechat = findViewById<TextView>(R.id.tv_customer_wechat) as TextView
        tvCustomerQQ = findViewById<TextView>(R.id.tv_customer_QQ) as TextView
        tvCustomerSex = findViewById<TextView>(R.id.tv_customer_sex) as TextView
        tvCustomerBirthday = findViewById<TextView>(R.id.tv_customer_birthday) as TextView
        tvCustomerSource = findViewById<TextView>(R.id.tv_customer_source) as TextView
        tvCustomerIntention = findViewById<TextView>(R.id.tv_customer_intention) as TextView
        tvOrderAddress = findViewById<TextView>(R.id.tv_order_address) as TextView
        tvCustomerAddress = findViewById<TextView>(R.id.tv_customer_address) as TextView
        tvMakeDate = findViewById<TextView>(R.id.tv_make_date) as TextView
        tvPreShootingDate = findViewById<TextView>(R.id.tv_pre_shooting_date) as TextView
        tvMarryDate = findViewById<TextView>(R.id.tv_marry_date) as TextView
        tvMateName = findViewById<TextView>(R.id.tv_mate_name) as TextView
        tvMatePhone = findViewById<TextView>(R.id.tv_mate_phone) as TextView
        tvMateWechat = findViewById<TextView>(R.id.tv_mate_wechat) as TextView
        tvMateQQ = findViewById<TextView>(R.id.tv_mate_QQ) as TextView
        tvMateSex = findViewById<TextView>(R.id.tv_mate_sex) as TextView
        tvMateBirthday = findViewById<TextView>(R.id.tv_mate_birthday) as TextView
        tvCustomerRemarks = findViewById<TextView>(R.id.tv_customer_remarks) as TextView
    }
}
