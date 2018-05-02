package com.suxuantech.erpsys.ui.activity

import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.suxuantech.erpsys.App
import com.suxuantech.erpsys.R
import com.suxuantech.erpsys.entity.RegisterEntity
import com.suxuantech.erpsys.nohttp.Contact
import com.suxuantech.erpsys.nohttp.HttpListener
import com.suxuantech.erpsys.nohttp.JavaBeanRequest
import com.suxuantech.erpsys.ui.activity.base.TitleNavigationActivity
import com.suxuantech.erpsys.ui.adapter.QuickAdapter
import com.suxuantech.erpsys.ui.widget.OneKeyClearAutoCompleteText
import com.suxuantech.erpsys.utils.DateUtil
import com.yanzhenjie.nohttp.RequestMethod
import com.yanzhenjie.nohttp.rest.Response

/**
 * 进店登记搜索
 */
class RegisterIntoShopSearchActivity : TitleNavigationActivity() {
    var tietSearch: OneKeyClearAutoCompleteText? = null
    var imgSearch: ImageView? = null
    var tvAdd: TextView? = null
    var rvRegister: RecyclerView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_into_shop_search)
        showUserDefinedNav()
        setTitle(   intent.getStringExtra("title"))
        setUseDefinedNavRightText(DateUtil.getNowDate(DateUtil.DatePattern.ONLY_DAY))
        tietSearch = findViewById<OneKeyClearAutoCompleteText>(R.id.tiet_search)
        imgSearch = idSetOnClick<ImageView>(R.id.img_search)
        tvAdd = idSetOnClick<TextView>(R.id.tv_add)
        rvRegister = findViewById<RecyclerView>(R.id.rv_register)
        rvRegister!!.layoutManager = LinearLayoutManager(baseContext)
        search()
    }

    override fun widgetClick(v: View?) {
        super.widgetClick(v)
        when (v!!.id) {
            R.id.img_search -> {
                search()
            }
            R.id.tv_add->{startActivity(IntoGuestRegistrationActivity::class.java)}
        }
    }

    var pageIndex = 0;
    var pageSize = 10;
    private fun search() {
        val nowDate = DateUtil.getNowDate(DateUtil.DatePattern.JUST_DAY_NUMBER);
        var url = Contact.getFullUrl(Contact.INQUIRE_GUEST_INFO, Contact.TOKEN
                , 20170101, nowDate, tietSearch!!.text.toString(),
                pageIndex, pageSize,
                App.getApplication().userInfor.shop_code, ""
        )
        val districtBeanJavaBeanRequest = JavaBeanRequest(url, RequestMethod.POST, RegisterEntity::class.java)
        val searchByCustmor = object : HttpListener<RegisterEntity> {
            override fun onSucceed(what: Int, response: Response<RegisterEntity>) {
                if (response.get().isOK) {
                    setAdaputer(response.get().data)
                }
            }

            override fun onFailed(what: Int, response: Response<RegisterEntity>) {
            }
        }
        request(3, districtBeanJavaBeanRequest, searchByCustmor, false, false)
    }

    private fun setAdaputer(dataX: List<RegisterEntity.DataBean>) {

        val value: QuickAdapter<RegisterEntity.DataBean> = object : QuickAdapter<RegisterEntity.DataBean>(R.layout.item_register_info, dataX) {

            override fun convert(helper: BaseViewHolder, item: RegisterEntity.DataBean) {

                var name = helper.getView<TextView>(R.id.tv_register_name)
                var type = helper.getView<TextView>(R.id.tv_register_type)
                var phone = helper.getView<TextView>(R.id.tv_register_phone)
                var cotype = helper.getView<TextView>(R.id.tv_register_consumption_type)
                var zone = helper.getView<TextView>(R.id.tv_register_zone)
                var date = helper.getView<TextView>(R.id.tv_register_date)
                var staff = helper.getView<TextView>(R.id.tv_register_staff)
                var reason = helper.getView<TextView>(R.id.tv_run_reason)
                var llLossing = helper.getView<LinearLayout>(R.id.ll_lossing)
                name.text = item.xingming;
                cotype.text = "消费类型:" + item.consultation_type
                zone.text = "客户分区:" + item.customer_area
                date.text = "进店日期:" + item.yjd_day
                staff.text = "接待人:" + item.sales_staff
                phone.text = "手机:"+item.shouji
                type.text = item.is_loss
                llLossing!!.visibility= View.GONE
//                reason.text="流失原因:"
            }
        }
        value.onItemClickListener = BaseQuickAdapter.OnItemClickListener { adapter, view, position ->
            val data = dataX.get(position);
            var it = Intent( baseContext , CustomerDetailsActivity::class.java);
            var vb = Bundle();
            vb .putString("orderId",data  .customer_number)
            vb .putParcelable("data",data)
            it.putExtra("bundle",vb)
            startActivity(it)
        }
        rvRegister!!.adapter = value
    }
}
