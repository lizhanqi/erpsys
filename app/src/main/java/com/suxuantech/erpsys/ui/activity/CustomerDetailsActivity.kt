package com.suxuantech.erpsys.ui.activity

import android.os.Bundle
import android.support.annotation.IntRange
import android.support.v4.content.ContextCompat
import android.view.*
import android.widget.EditText
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
import com.suxuantech.erpsys.utils.StringUtils
import com.yanzhenjie.alertdialog.AlertDialog
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
    val RUN_REASON = "流失原因"
    val UNSETTLED = "未成交"
    val NOT_INTO_SHOP = "未进店原因"
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
        if (!App.getApplication().hasPermission("K8")) {
            toastShort("您无权修改进店")
            return
        }
        //请求实体
        val districtBeanJavaBeanRequest = JavaBeanRequest(Contact.getFullUrl(Contact.INTO_STORE_STATUS, Contact.TOKEN, parcelable?.id, flag), RequestMethod.POST, ProductEntity::class.java)
        districtBeanJavaBeanRequest.addBodyJson("Customer_tel", parcelable?.customer_tel)
        districtBeanJavaBeanRequest.addBodyJson("Customer_name", parcelable?.customer_name)
        districtBeanJavaBeanRequest.addBodyJson("Mate_tel", parcelable?.mate_tel)
        districtBeanJavaBeanRequest.addBodyJson("Brandid", App.getApplication().userInfor.brandclass_id)
        districtBeanJavaBeanRequest.addBodyJson("Shop_code", App.getApplication().userInfor.shop_code)
        districtBeanJavaBeanRequest.addBodyJson("From_index", parcelable?.from_index)
        districtBeanJavaBeanRequest.addBodyJson("Staffid", App.getApplication().userInfor.staff_id)
        districtBeanJavaBeanRequest.param2Json()
        val searchByCustmor = object : HttpListener<ProductEntity> {
            override fun onSucceed(what: Int, response: Response<ProductEntity>) {
                mMenuPopWindow?.dismiss()
                recoverImmersionBar()
                if (response.get().isOK) {
                    parcelable?.is_intostore = "已进店"
                    tvRegisterType!!.setText(parcelable?.is_intostore)
                }
            }

            override fun onFailed(what: Int, response: Response<ProductEntity>) {
                mMenuPopWindow?.dismiss()
                recoverImmersionBar()
            }
        }
        request(0, districtBeanJavaBeanRequest, searchByCustmor, false, false)
    }

    fun showR(title: String) {
        if (title.equals(RUN_REASON)) {
            if (!App.getApplication().hasPermission("K11")) {
                toastShort("抱歉,无权流失")
                return
            }
        }
        if (title.equals(NOT_INTO_SHOP)) {
            if (!App.getApplication().hasPermission("K9")) {
                toastShort("抱歉,修改未进店")
                return
            }
        }
        val builder = AlertDialog.newBuilder(this@CustomerDetailsActivity)
        builder.setTitle(title)
        builder.setCancelable(true)
        val editText = EditText(this)
        editText.setPadding(50, 0, 50, 30)
        editText.minLines = 3
        editText.maxLines = 5
        editText.gravity = Gravity.TOP or Gravity.LEFT
        builder.setView(editText)
        builder.setOnDismissListener() {
            mMenuPopWindow?.dismiss()
            recoverImmersionBar()
        }
        builder.setPositiveButton(R.string.submit) { dialogInterface, i ->
            aw(editText.text.toString(), title)
        }
        builder.show()
    }

    /**
     *
     */

    fun aw(reason: String, title: String) {
        if (title.equals(UNSETTLED)) {
            var districtBeanJavaBeanRequest = JavaBeanRequest(Contact.getFullUrl(Contact.RUN_AWAY, Contact.TOKEN, parcelable?.id, reason), RequestMethod.POST, ProductEntity::class.java)
            districtBeanJavaBeanRequest.addBodyJson("customer_tel", parcelable?.customer_tel)
            districtBeanJavaBeanRequest.addBodyJson("customer_name", parcelable?.customer_name)
            districtBeanJavaBeanRequest.addBodyJson("mate_tel", parcelable?.mate_tel)
            districtBeanJavaBeanRequest.addBodyJson("brandid", App.getApplication().userInfor.brandclass_id)
            districtBeanJavaBeanRequest.addBodyJson("shop_code", App.getApplication().userInfor.shop_code)
            districtBeanJavaBeanRequest.addBodyJson("brom_index", parcelable?.from_index)
            districtBeanJavaBeanRequest.addBodyJson("staffid", App.getApplication().userInfor.staff_id)
            districtBeanJavaBeanRequest.param2Json()
            val searchByCustmor = object : HttpListener<ProductEntity> {
                override fun onSucceed(what: Int, response: Response<ProductEntity>) {
                    if (response.get().isOK) {
                        toastShort("修改成功")
                        parcelable?.is_intostore = " "
                        tvRegisterType!!.setText(parcelable?.is_intostore)
                    } else {
                        toastShort("修改失败")
                    }
                }

                override fun onFailed(what: Int, response: Response<ProductEntity>) {}
            }
            request(0, districtBeanJavaBeanRequest, searchByCustmor, false, false)
        } else {
            var districtBeanJavaBeanRequest = JavaBeanRequest(Contact.getFullUrl(Contact.SB, Contact.TOKEN, parcelable?.id, reason), RequestMethod.POST, ProductEntity::class.java)
            districtBeanJavaBeanRequest.addBodyJson("Customer_tel", parcelable?.customer_tel)
            districtBeanJavaBeanRequest.addBodyJson("Customer_name", parcelable?.customer_name)
            districtBeanJavaBeanRequest.addBodyJson("Mate_tel", parcelable?.mate_tel)
            districtBeanJavaBeanRequest.addBodyJson("Brandid", App.getApplication().userInfor.brandclass_id)
            districtBeanJavaBeanRequest.addBodyJson("Shop_code", App.getApplication().userInfor.shop_code)
            districtBeanJavaBeanRequest.addBodyJson("From_index", parcelable?.from_index)
            districtBeanJavaBeanRequest.addBodyJson("Staffid", App.getApplication().userInfor.staff_id)
            districtBeanJavaBeanRequest.param2Json()
            val searchByCustmor = object : HttpListener<ProductEntity> {
                override fun onSucceed(what: Int, response: Response<ProductEntity>) {
                    if (response.get().isOK) {
                        toastShort("修改成功")
                        parcelable?.is_intostore = " "
                        tvRegisterType!!.setText(parcelable?.is_intostore)
                        toastShort("修改成功")
                    } else {
                        toastShort("修改失败")
                    }
                }

                override fun onFailed(what: Int, response: Response<ProductEntity>) {}
            }
            request(0, districtBeanJavaBeanRequest, searchByCustmor, false, false)
        }

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
            showR(UNSETTLED)

        })
        mMenuView.findViewById<TextView>(R.id.tv_make_bargain).setOnClickListener(View.OnClickListener {
            recoverImmersionBar()
            mMenuPopWindow?.dismiss()
            if (App.getApplication().hasPermission("K10")) {
                startActivity(OutletsOrderActivity::class.java, intent.getBundleExtra("bundle"))
            } else {
                toastShort("无权转单")
            }

        })
        mMenuView.findViewById<TextView>(R.id.tv_run_away).setOnClickListener(View.OnClickListener {
            showR(RUN_REASON)
        })
        mMenuPopWindow = PopupWindow(mMenuView, WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT, true)
        //进出场动画
        mMenuPopWindow?.setAnimationStyle(R.style.AnimationPreview);
    }

    var parcelable: RegisterEntity.DataBean? = null;
    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.action_settings -> {
                change(1)
            }
            R.id.action_not_order -> {
                change(2)
            }
            R.id.action_make_bargain -> {
                startActivity(OutletsOrderActivity::class.java, intent.getBundleExtra("bundle"))
            }
            R.id.action_not_into_shop -> {
                showR(NOT_INTO_SHOP)
            }
            R.id.action_srun_away -> {
                showR(RUN_REASON)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    //折叠的选项中的 菜单图标可以用反射显示
    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {
//        //menu创建之前，反射设置显示图标
//        if (menu != null) {
//            if (menu.javaClass.simpleName.equals("MenuBuilder", ignoreCase = true)) {
//                try {
//                    val method = menu.javaClass.getDeclaredMethod("setOptionalIconsVisible", java.lang.Boolean.TYPE)
//                    method.isAccessible = true
//                    method.invoke(menu, true)
//                } catch (e: Exception) {
//                    e.printStackTrace()
//                }
//
//            }
//        }
        return super.onPrepareOptionsMenu(menu)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_flag, menu);
        //修改折叠的三个点
        val drawable = ContextCompat.getDrawable(this, R.drawable.icon_ding)
        toolbar.overflowIcon = drawable
        return super.onCreateOptionsMenu(menu)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_customer_details)
        supportToolbar(true)
        setTitle("客资详情")
        initPop()
        initView()
        var parcelable = intent.getBundleExtra("bundle").getParcelable<RegisterEntity.DataBean>("data")
        this.parcelable = parcelable;
        tvRegisterName!!.setText(parcelable.customer_name)
        tvRegisterPhone!!.setText("手机号" + parcelable.customer_tel)
        tvRegisterConsumptionType!!.setText("消费类型" + parcelable.consultation_type)
        tvRegisterZone!!.setText("客户分区" + parcelable.customer_area)
        tvRegisterDate!!.setText("进店日期" + StringUtils.subDate(parcelable.yjd_day))
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
        tvMakeDate!!.setText(StringUtils.subDate(parcelable.yjd_day))
        tvMakeDate!!.setText(StringUtils.subDate(parcelable.yjd_day))
        tvMarryDate!!.setText(StringUtils.subDate(parcelable.wedding_date))
        tvMateName!!.setText(parcelable.mate_name)
        tvMatePhone!!.setText(parcelable.mate_tel)
        tvMateWechat!!.setText(parcelable.mate_wechat)
        tvMateQQ!!.setText(parcelable.mate_qq)
        tvMateSex!!.setText(parcelable.mate_sex)
        tvMateBirthday!!.setText(parcelable.mate_birthday)
        tvCustomerRemarks!!.setText(parcelable.customer_remark)
        tvRegisterType!!.setText(parcelable.is_intostore)
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
        var cont = findViewById<LinearLayout>(R.id.ll_content)
        val layoutParams = cont.getLayoutParams() as LinearLayout.LayoutParams
        layoutParams.setMargins(0, 0, 0, 0)
    }
}
