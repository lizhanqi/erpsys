package com.suxuantech.erpsys.ui.activity

import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.scwang.smartrefresh.layout.SmartRefreshLayout
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
import com.suxuantech.erpsys.utils.StringUtils
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
    var smartRefreshLayout: SmartRefreshLayout? = null;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_into_shop_search)
        // showUserDefinedNav()
        supportToolbar()
        setCenterTitle(intent.getStringExtra("title"))
        tietSearch = findViewById<OneKeyClearAutoCompleteText>(R.id.tiet_search)
        if (intent.getBooleanExtra("hideSearch", false)) {
            findViewById<LinearLayout>(R.id.ll_serach).visibility = View.GONE;
        }
        imgSearch = idSetOnClick<ImageView>(R.id.img_search)
        tvAdd = idSetOnClick<TextView>(R.id.tv_add)
        rvRegister = findViewById<RecyclerView>(R.id.rv_register)
        smartRefreshLayout = findViewById<SmartRefreshLayout>(R.id.smlayout)
        smartRefreshLayout?.setOnRefreshListener {
            pageIndex = 0;
            search(pageIndex)
        }
        smartRefreshLayout?.setOnLoadMoreListener {
            search(pageIndex)
        }
       // smartRefreshLayout?.autoRefresh()
        smartRefreshLayout?.isEnableLoadMore = true
        rvRegister!!.layoutManager = LinearLayoutManager(baseContext)
        adapter.onItemClickListener = BaseQuickAdapter.OnItemClickListener { adapters, view, position ->
            val data = adapter.data.get(position);
            var it = Intent(baseContext, CustomerDetailsActivity::class.java);
            var vb = Bundle();
            vb.putString("orderId", data.customer_number)
            vb.putParcelable("data", data)
            it.putExtra("bundle", vb)
            startActivity(it)
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menu?.add(DateUtil.getNowDate(DateUtil.DatePattern.ONLY_DAY))?.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS)
        return super.onCreateOptionsMenu(menu)
    }

    override fun widgetClick(v: View?) {
        super.widgetClick(v)
        when (v!!.id) {
            R.id.img_search -> {
                lastKey = tietSearch!!.text.toString();
                pageIndex = 0;
                search(pageIndex)
            }
            R.id.tv_add -> {
                if(App.getApplication().hasPermission("K3")){
                    startActivity(IntoGuestRegistrationActivity::class.java)
                }else{
                    toastShort("无权登记")
                }
            }
        }
    }

    var pageIndex = 0;
    var pageSize = 10;
    var lastKey = "";
    private fun search(index: Int) {
        if(!App.getApplication().hasPermission("K2")){
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
                , 20170101, nowDate, lastKey,
                index, pageSize,
                App.getApplication().userInfor.shop_code, "", name1, name2
        )
        val districtBeanJavaBeanRequest = JavaBeanRequest(url, RequestMethod.POST, RegisterEntity::class.java)
        val searchByCustmor = object : HttpListener<RegisterEntity> {
            override fun onSucceed(what: Int, response: Response<RegisterEntity>) {
                if (response.get().isOK) {
                    if (pageIndex == 0) {
                        smartRefreshLayout?.finishRefresh()
                    } else {
                        smartRefreshLayout?.finishLoadMore()
                    }
                    if (response.get().data != null && response.get().data.size >= pageSize) {
                        pageIndex++
                    } else {
                        smartRefreshLayout?.finishLoadMoreWithNoMoreData()
                    }
                    setAdaputer(response.get().data)
                } else {
                    if (pageIndex == 0) {
                        smartRefreshLayout?.finishRefresh(false)
                    } else {
                        smartRefreshLayout?.finishLoadMore(false)
                    }
                }
            }

            override fun onFailed(what: Int, response: Response<RegisterEntity>) {
                if (pageIndex == 0) {
                    smartRefreshLayout?.finishRefresh(false)
                } else {
                    smartRefreshLayout?.finishLoadMore(false)
                }
            }
        }
        request(3, districtBeanJavaBeanRequest, searchByCustmor, true, pageIndex == 0)
    }
    val adapter: QuickAdapter<RegisterEntity.DataBean> = object : QuickAdapter<RegisterEntity.DataBean>(R.layout.item_register_info, null) {
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
            cotype.text = "消费类型:" + StringUtils.safetyString(item.consultation_type)
            zone.text = "客户分区:" + StringUtils.safetyString(item.customer_area)
            var st = StringUtils.safetyString(item.yjd_day);

            if (st.length > 10) {
                st = st.substring(0, 10);
            }
            date.text = "进店日期:" + st
            staff.text = "接待人:" + StringUtils.safetyString(item.sales_staff)
            phone.text = "手机:" + StringUtils.safetyString(item.shouji)
            type.text = StringUtils.safetyString(item.is_loss)
            llLossing!!.visibility = View.GONE
        }
    }
    private fun setAdaputer(dataX: List<RegisterEntity.DataBean>) {
        if (pageIndex == 0 || pageIndex == 1) {
            adapter.updateAll(dataX)
            rvRegister!!.adapter = adapter
        } else {
            adapter.apped(dataX)
        }
    }
}
