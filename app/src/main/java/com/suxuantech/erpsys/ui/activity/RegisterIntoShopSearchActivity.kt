package com.suxuantech.erpsys.ui.activity

import android.content.Intent
import android.os.Bundle
import android.support.annotation.IntRange
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.bigkoo.pickerview.TimePickerView
import com.blankj.utilcode.util.KeyboardUtils
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.scwang.smartrefresh.layout.SmartRefreshLayout
import com.suxuantech.erpsys.App
import com.suxuantech.erpsys.R
import com.suxuantech.erpsys.entity.RegisterEntity
import com.suxuantech.erpsys.nohttp.Contact
import com.suxuantech.erpsys.nohttp.HttpListener
import com.suxuantech.erpsys.nohttp.JavaBeanRequest
import com.suxuantech.erpsys.presenter.RegsiterPresenter
import com.suxuantech.erpsys.ui.activity.base.TitleNavigationActivity
import com.suxuantech.erpsys.ui.adapter.QuickAdapter
import com.suxuantech.erpsys.ui.widget.OneKeyClearAutoCompleteText
import com.suxuantech.erpsys.utils.DateUtil
import com.suxuantech.erpsys.utils.StringUtils
import com.yanzhenjie.nohttp.RequestMethod
import com.yanzhenjie.nohttp.rest.Response
import java.util.*

/**
 * 进店登记搜索
 */
class RegisterIntoShopSearchActivity : TitleNavigationActivity() {
    var tietSearch: OneKeyClearAutoCompleteText? = null
    var imgSearch: ImageView? = null
    var tvAdd: TextView? = null
    var rvRegister: RecyclerView? = null
    var smartRefreshLayout: SmartRefreshLayout? = null;
    var hideSearch = false;
    var title = "";
    var jkType = 0;
    var soso = RegsiterPresenter();
    var pageIndex = 0;
    var pageSize = 20;
    var lastKey = "";
    var nowDate = DateUtil.getNowDate(DateUtil.DatePattern.JUST_DAY_NUMBER);
    /**
     * 适配器
     */
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
            var cont = helper.getView<LinearLayout>(R.id.ll_content)
            if (data.lastIndexOf(item) == 0) {
                val layoutParams = cont.getLayoutParams() as LinearLayout.LayoutParams
                layoutParams.setMargins(0, 0, 0, 0)
            }
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
            type.text = StringUtils.safetyString(item.is_intostore)
            llLossing!!.visibility = View.GONE
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_into_shop_search)
        supportToolbar()
        title = intent.getStringExtra("title");
        hideSearch = intent.getBooleanExtra("hideSearch", false)
        jkType = intent.getIntExtra("jktype", 0);
        initView()
        //搜索的调用结果
        soso.setiSearchOrderPresenter(object : RegsiterPresenter.RegisterSearchResult {
            override fun registerSearchFailed(response: Response<RegisterEntity>?, pageIndex: Int) {
                smartRefreshLayout?.finishRefresh()
                smartRefreshLayout?.finishLoadMore()
            }

            override fun registerSearchOk(data: List<RegisterEntity.DataBean>?, isFresh: Boolean, hasMore: Boolean) {
                setSoSoAdaputer(data!!, isFresh, hasMore)
            }
        })
    }

    /***
     * 初始化view或者页面
     */
    fun initView() {
        setCenterTitle(title)
        tietSearch = findViewById<OneKeyClearAutoCompleteText>(R.id.tiet_search)
        imgSearch = idSetOnClick<ImageView>(R.id.img_search)
        tvAdd = idSetOnClick<TextView>(R.id.tv_add)
        rvRegister = findViewById<RecyclerView>(R.id.rv_register)
        smartRefreshLayout = findViewById<SmartRefreshLayout>(R.id.smlayout)
        smartRefreshLayout?.setOnRefreshListener {
            if (adapter != null) {
                adapter.data.clear()
                adapter.notifyDataSetChanged()
            }
            lastKey = tietSearch!!.text.toString();
            pageIndex = 0;
            if (hideSearch) {
                getTodayAll(jkType, pageIndex)
            } else {
                search()
            }
        }
        smartRefreshLayout?.setOnLoadMoreListener {
            if (hideSearch) {
                val intExtra = intent.getIntExtra("jktype", 0);
                getTodayAll(intExtra, pageIndex)
            } else {
                soso.loadMore()
            }
        }
        if (hideSearch) {
            findViewById<LinearLayout>(R.id.ll_serach).visibility = View.GONE;
            smartRefreshLayout?.autoRefresh()
        }
        smartRefreshLayout?.isEnableLoadMore = true
        rvRegister!!.layoutManager = LinearLayoutManager(baseContext)
        /**
         * 点击条目的
         */
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

    /**
     * 时间选择
     */
    fun showDataSelect() {
        KeyboardUtils.hideSoftInput(tvAdd)
        //年月日时分秒 的显示与否，不设置则默认全部显示
        val timePickerView = TimePickerView.Builder(this, TimePickerView.OnTimeSelectListener { date, v ->
            //选中事件回调
            var v = menu.findItem(0)
            nowDate = DateUtil.dateToString(date, DateUtil.DatePattern.JUST_DAY_NUMBER);
            v.setTitle(DateUtil.dateToString(date, DateUtil.DatePattern.ONLY_DAY))
            smartRefreshLayout?.autoRefresh();
        }) //年月日时分秒 的显示与否，不设置则默认全部显示
                .setTitleText(getString(R.string.please_select_marriage_date)).setLineSpacingMultiplier(4f).setType(booleanArrayOf(true, true, true, false, false, false))
                .setTitleColor(resources.getColor(R.color.textHint_99)).setTitleBgColor(resources.getColor(R.color.white)).build()
        timePickerView.setDate(Calendar.getInstance())//注：根据需求来决定是否使用该方法（一般是精确到秒的情况），此项可以在弹出选择器的时候重新设置当前时间，避免在初始化之后由于时间已经设定，导致选中时间与当前时间不匹配的问题。
        timePickerView.show()
    }

    /**
     * 创建菜单
     */
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menu?.add(DateUtil.getNowDate(DateUtil.DatePattern.ONLY_DAY))?.setOnMenuItemClickListener {
            showDataSelect()
            true;
        }?.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS)
        return super.onCreateOptionsMenu(menu)
    }

    /**
     * view 点击
     */
    override fun widgetClick(v: View?) {
        super.widgetClick(v)
        when (v!!.id) {
            R.id.img_search -> {
                smartRefreshLayout?.autoRefresh();
            }
            R.id.tv_add -> {
                if (App.getApplication().hasPermission("K3")) {
                    startActivity(IntoGuestRegistrationActivity::class.java)
                } else {
                    toastShort("无权登记")
                }
            }
        }
    }

    /**
     * 搜索接口
     * 条件类型（0：所有客资；1：已成交；2：流失客资；3：新进客客资；4：未进店；5：进店未成交；6：登记日期未标记预约进店日；）
     */
    private fun getTodayAll(@IntRange(from = 0, to = 6) jktype: Int, index: Int) {
        if (adapter != null && index == 0) {
            adapter.data.clear()
            adapter.notifyDataSetChanged()
        }
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
        var url = Contact.getFullUrl(Contact.RegisterType, Contact.TOKEN
                , nowDate, nowDate, jktype,
                index, pageSize,
                App.getApplication().userInfor.shop_code, name1, name2
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

    /**
     * 搜索接口
     */
    private fun search() {
        if (!App.getApplication().hasPermission("K2")) {
            toastShort("无权限查询")
            return
        }
        soso.search(true, nowDate, lastKey)
    }


    /**
     * 设置类型的适配器
     */
    private fun setAdaputer(dataX: List<RegisterEntity.DataBean>) {
        if (pageIndex == 0 || pageIndex == 1) {
            adapter.updateAll(dataX)
            rvRegister!!.adapter = adapter
        } else {
            adapter.apped(dataX)
        }
    }

    /**
     * 设置搜索的设配器
     */
    private fun setSoSoAdaputer(dataX: List<RegisterEntity.DataBean>, isFresh: Boolean, hasMore: Boolean) {
        if (isFresh) {
            smartRefreshLayout?.finishRefresh()
        } else {
            smartRefreshLayout?.finishLoadMore()
        }
        smartRefreshLayout?.setEnableAutoLoadMore(hasMore)
        if (pageIndex == 0 || pageIndex == 1) {
            adapter.updateAll(dataX)
            rvRegister!!.adapter = adapter
        } else {
            adapter.apped(dataX)
        }
    }
}
