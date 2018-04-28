package com.suxuantech.erpsys.ui.activity

import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.LinearLayout
import android.widget.PopupWindow
import android.widget.TextView
import com.suxuantech.erpsys.R
import com.suxuantech.erpsys.entity.RegisterEntity
import com.suxuantech.erpsys.ui.activity.base.TitleNavigationActivity

/**
 * 客资详情
 */
class CustomerDetailsActivity : TitleNavigationActivity() {
    var tvRegisterName: TextView?=null
    var tvRegisterType: TextView?=null
    var tvRegisterPhone: TextView?=null
    var tvRegisterConsumptionType: TextView?=null
    var tvRegisterZone: TextView?=null
    var tvRegisterDate: TextView?=null
    var tvRegisterStaff: TextView?=null
    var llLossing: LinearLayout?=null
    var tvRunReason: TextView?=null
    var tvCustomerWechat: TextView?=null
    var tvCustomerQQ: TextView?=null
    var tvCustomerSex: TextView?=null
    var tvCustomerBirthday: TextView?=null
    var tvCustomerSource: TextView?=null
    var tvCustomerIntention: TextView?=null
    var tvOrderAddress: TextView?=null
    var tvCustomerAddress: TextView?=null
    var tvMakeDate: TextView?=null
    var tvPreShootingDate: TextView?=null
    var tvMarryDate: TextView?=null
    var tvMateName: TextView?=null
    var tvMatePhone: TextView?=null
    var tvMateWechat: TextView?=null
    var tvMateQQ: TextView?=null
    var tvMateSex: TextView?=null
    var tvMateBirthday: TextView?=null
    var tvCustomerRemarks: TextView?=null
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
        } else {
            mMenuPopWindow?.showAsDropDown(navRightView, -120, 0 )
        }
    }
    /**
     * 初始化弹窗
     */
    private fun initPop() {
        val mMenuView = layoutInflater.inflate(R.layout.pop_customer_states, null)
        mMenuView.findViewById<TextView>(R.id.tv_new_customer).setOnClickListener(View.OnClickListener {
            mMenuPopWindow?.dismiss()
        })
        mMenuView.findViewById<TextView>(R.id.tv_not_into_shop).setOnClickListener(View.OnClickListener {
            mMenuPopWindow?.dismiss()
        })
        mMenuView.findViewById<TextView>(R.id.tv_not_in_shop).setOnClickListener(View.OnClickListener {        mMenuPopWindow?.dismiss()})
        mMenuView.findViewById<TextView>(R.id.tv_make_bargain).setOnClickListener(View.OnClickListener {       mMenuPopWindow?.dismiss() })
        mMenuView.findViewById<TextView>(R.id.tv_run_away).setOnClickListener(View.OnClickListener {       mMenuPopWindow?.dismiss()})
        mMenuPopWindow = PopupWindow(mMenuView, WindowManager.LayoutParams.WRAP_CONTENT,   WindowManager.LayoutParams.WRAP_CONTENT, true)
            //进出场动画
        mMenuPopWindow?.setAnimationStyle(R.style.AnimationPreview);
   }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_customer_details)
        showUserDefinedNav()
        setTitle("客资详情")
        initPop()
        initView()
        val parcelable = intent.getBundleExtra("bundle").getParcelable<RegisterEntity.DataBean>("data")
        setUseDefinedNavRightText(parcelable.is_loss)
        tvRegisterName!!.setText(parcelable.customer_name)
        tvRegisterPhone!!.setText("手机号"+parcelable.customer_tel)
        tvRegisterConsumptionType!!.setText("消费类型"+parcelable.consultation_type)
        tvRegisterZone!!.setText("客户分区"+parcelable.customer_area)
        tvRegisterDate!!.setText("进店日期"+parcelable.yjd_day)
        tvRegisterStaff!!.setText("接待人"+parcelable.sales_staff)
        llLossing!!.visibility= View.GONE
        tvCustomerWechat!!.setText( parcelable.customer_wechat)
        tvCustomerQQ!!.setText( parcelable.customer_qq)
        tvCustomerSex!!.setText( parcelable.customer_sex)
        tvCustomerBirthday!!.setText( parcelable.customer_birthday)
       tvCustomerSource!!.setText( parcelable.customer_cource)
        tvCustomerIntention!!.setText( parcelable.customer_intention)
        tvOrderAddress!!.setText( parcelable.customer_orderaddress)
        tvCustomerAddress!!.setText( parcelable.customer_address)
        tvMakeDate!!.setText( parcelable.yjd_day)
        tvMakeDate!!.setText( parcelable.yjd_day)
        tvMarryDate!!.setText( parcelable.wedding_date)
        tvMateName!!.setText( parcelable.mate_name)
        tvMatePhone!!.setText( parcelable.mate_tel)
        tvMateWechat!!.setText( parcelable.mate_wechat)
        tvMateQQ!!.setText( parcelable.mate_qq)
        tvMateSex!!.setText( parcelable.mate_sex)
        tvMateBirthday!!.setText( parcelable.mate_birthday)
        tvCustomerRemarks!!.setText( parcelable.customer_remark)
    }

    fun initView(){
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
