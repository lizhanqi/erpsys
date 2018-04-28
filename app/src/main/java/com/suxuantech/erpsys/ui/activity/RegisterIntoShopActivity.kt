package com.suxuantech.erpsys.ui.activity

import android.os.Bundle
import android.support.design.widget.AppBarLayout
import android.support.v4.widget.NestedScrollView
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import com.gyf.barlibrary.ImmersionBar
import com.scwang.smartrefresh.layout.SmartRefreshLayout
import com.scwang.smartrefresh.layout.listener.OnRefreshListener
import com.suxuantech.erpsys.App
import com.suxuantech.erpsys.R
import com.suxuantech.erpsys.entity.CustomerIntoStoreCountEntity
import com.suxuantech.erpsys.entity.UnremarkCustomerCountEntity
import com.suxuantech.erpsys.nohttp.Contact
import com.suxuantech.erpsys.nohttp.HttpListener
import com.suxuantech.erpsys.nohttp.JavaBeanRequest
import com.suxuantech.erpsys.ui.AppBarStateChangeListener
import com.suxuantech.erpsys.ui.activity.base.ImmersionActivity
import com.suxuantech.erpsys.utils.DateUtil
import com.suxuantech.erpsys.utils.MyString
import com.yanzhenjie.nohttp.RequestMethod
import com.yanzhenjie.nohttp.rest.Response
import kotlinx.android.synthetic.main.activity_register_into_shop.*




/**
 * 进店登记
 */

class RegisterIntoShopActivity : ImmersionActivity() {
    var llNewCustomer: LinearLayout? = null
    var llNotIntoShop: LinearLayout? = null
    var llNotInShop: LinearLayout? = null
    var llMakeBargain: LinearLayout? = null
    var llRunAway: LinearLayout? = null
    var tvNewCustomer: TextView? = null
    var tvNotIntoShop: TextView? = null
    var tvNotInShop: TextView? = null
    var tvMakeBargain: TextView? = null
    var tvRunAway: TextView? = null
    var tvUnmarkedDate: TextView? = null
    var tvCustomerAll: TextView? = null
    var llCustomerAll: LinearLayout? = null
    var smartRefreshLayout: SmartRefreshLayout? = null;
    var mToolbar :android.support.v7.widget.Toolbar?=null
    var scrollView : NestedScrollView?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_into_shop)
        mToolbar = idGetView<android.support.v7.widget.Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        toolbar.setNavigationOnClickListener {
            onBackPressed()
        }
        scrollView=  idGetView<NestedScrollView>(R.id.nsv_view)

        idGetView<AppBarLayout>(R.id.appbar).addOnOffsetChangedListener(object : AppBarStateChangeListener() {
            override fun onStateChanged(appBarLayout: AppBarLayout, state: State) {
                if (state === State.EXPANDED) {
                    //展开状态
                    menus?.findItem(R.id.action_settings)?.setIcon(R.drawable.icon_add_white)?.setVisible(false)
                } else if (state === State.COLLAPSED) {
                    //折叠状态
                        menus?.findItem(R.id.action_settings)?.setVisible(true)
                } else {
                    //中间状态
                }
            }
        })
        scrollView?.setOnScrollChangeListener(NestedScrollView.OnScrollChangeListener { v, scrollX, scrollY, oldScrollX, oldScrollY ->

        })
        llNewCustomer = idSetOnClick<LinearLayout>(R.id.ll_new_customer)
        llNotIntoShop = idSetOnClick<LinearLayout>(R.id.ll_not_into_shop) as LinearLayout
        llNotInShop = idSetOnClick<LinearLayout>(R.id.ll_not_in_shop) as LinearLayout
        llMakeBargain = idSetOnClick<LinearLayout>(R.id.ll_make_bargain) as LinearLayout
        llRunAway = idSetOnClick<LinearLayout>(R.id.ll_run_away) as LinearLayout
        llCustomerAll = idSetOnClick<LinearLayout>(R.id.ll_customer_all) as LinearLayout
        var smartRefreshLayout = idSetOnClick<SmartRefreshLayout>(R.id.refreshLayout) as SmartRefreshLayout
        smartRefreshLayout.autoRefresh();//第一次进入触发自动刷新，演示效果
        tvNewCustomer = idGetView<TextView>(R.id.tv_new_customer);
        tvNotIntoShop = idGetView<TextView>(R.id.tv_not_into_shop);
        tvNotInShop = idGetView<TextView>(R.id.tv_not_in_shop);
        tvMakeBargain = idGetView<TextView>(R.id.tv_make_bargain);
        tvRunAway = idGetView<TextView>(R.id.tv_run_away);
        tvUnmarkedDate = idGetView<TextView>(R.id.tv_unmarked_date);
        tvCustomerAll = idGetView<TextView>(R.id.tv_customer_all);
        smartRefreshLayout.setOnRefreshListener(OnRefreshListener {
            getData();
        })
    }


    fun getData() {
        var url = Contact.getFullUrl(Contact.CUSTOMER_INTO_STORE_COUNT, Contact.TOKEN,
                "20160808", DateUtil.getNowDate(DateUtil.DatePattern.JUST_DAY_NUMBER),
                "", 0, 0, App.getApplication().userInfor.shop_code)
        var url2 = Contact.getFullUrl(Contact.UNREMARK_CUSTOMER_INTO_STORE_DATE_COUNT, Contact.TOKEN,
                DateUtil.getNowDate(DateUtil.DatePattern.JUST_DAY_NUMBER), DateUtil.getNowDate(DateUtil.DatePattern.JUST_DAY_NUMBER),
                "", 0, 0, App.getApplication().userInfor.shop_code)
        //请求实体
        val requset = JavaBeanRequest(url, RequestMethod.POST, CustomerIntoStoreCountEntity::class.java)
        val customerIntoStoreCountEntity = object : HttpListener<CustomerIntoStoreCountEntity> {
            override fun onSucceed(what: Int, response: Response<CustomerIntoStoreCountEntity>) {
                if (response.get().isOK) {
                    //  [{"allcount":"54","newcount":"38","wjdcount":"49","jdwccount":"4","ycjcount":"8","lscount":"3"}]}
//                    @{@"newcount":@[@"新进客资",@"今日新进客资",@"kc_new_z"],
//                        @"jdwccount":@[@"进店未成",@"进店未成交客资",@"kc_jindianweicheng_z"],
//                        @"ycjcount":@[@"已成交",@"进店成交客资",@"kc_chengjiao_z"],
//                        @"lscount":@[@"流失客资",@"今日流失客资",@"kc_lose_z"],
//                        @"wjdcount":@[@"未进店",@"今日未进店客资",@"kdz_weijindian_z"],
//                        @"allcount":@[@"所有客资",@"今日所有客资",@"kc_sy_z"]
                    tvNewCustomer?.setText("今日新进客资")
                    tvNewCustomer?.append(   MyString(  response.get().data?.get(0)?.newcount).setColor(resources.getColor(R.color.litte_red)))
                    //进店未成交
                    tvNotInShop?.setText("今日进店未成交")
                      tvNotInShop?.append(MyString( response.get().data?.get(0)?.jdwccount).setColor(resources.getColor(R.color.litte_red)))
                    tvNotIntoShop?.setText("未进店客客资")
                    tvNotIntoShop?.append(MyString( response.get().data?.get(0)?.wjdcount).setColor(resources.getColor(R.color.litte_red)))
                    tvMakeBargain?.setText("今日成交客资")
                    tvMakeBargain?.append(MyString(  response.get().data?.get(0)?.ycjcount).setColor(resources.getColor(R.color.litte_red)))
                    tvRunAway?.setText("今日流失客资")
                        tvRunAway?.append(MyString(  response.get().data?.get(0)?.lscount).setColor(resources.getColor(R.color.litte_red)))
                    tvCustomerAll?.setText("今日所有客资")
                    tvCustomerAll?.append(MyString(  response.get().data?.get(0)?.allcount).setColor(resources.getColor(R.color.litte_red)))
                    refreshLayout.finishRefresh();
                    refreshLayout.setNoMoreData(false);
                }else{
                    refreshLayout.finishRefresh();
                    refreshLayout.setNoMoreData(true);
                }
            }

            override fun onFailed(what: Int, response: Response<CustomerIntoStoreCountEntity>) {
                refreshLayout.finishRefresh();
                refreshLayout.setNoMoreData(true);
            }}

        //请求实体
        val requset1 = JavaBeanRequest(url2 , RequestMethod.POST, UnremarkCustomerCountEntity::class.java)
        val unremarkCustomerCountEntity = object : HttpListener<UnremarkCustomerCountEntity> {
            override fun onSucceed(what: Int, response: Response<UnremarkCustomerCountEntity>) {
                if (response.get().isOK) {
                    tvUnmarkedDate?.setText("未标记日期")
                    tvUnmarkedDate ?.append(MyString("" + response.get().data?.get(0)?.wycount).setColor(resources.getColor(R.color.litte_red)))
                    refreshLayout.finishRefresh();
                    refreshLayout.setNoMoreData(false);
                }else {
                    refreshLayout.finishRefresh();
                    refreshLayout.setNoMoreData(true);
                }
            }

            override fun onFailed(what: Int, response: Response<UnremarkCustomerCountEntity>) {
                refreshLayout.finishRefresh();
                refreshLayout.setNoMoreData(true);}
        }
        request(2, requset, customerIntoStoreCountEntity, true, false)
        request(0, requset1, unremarkCustomerCountEntity, true, false)
    }
    var menus:Menu?=null;
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_right, menu)
        menus=menu;
        //展开状态
        menus?.findItem(R.id.action_settings)?.setIcon(R.drawable.icon_add_white)?.setVisible(false)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (android.R.id.home == item.itemId) {
            this.onBackPressed()
        }
        if (R.id.action_settings== item.itemId) {
            startActivity(IntoGuestRegistrationActivity::class.java)
        }
        return super.onOptionsItemSelected(item)
    }

     override fun initImmersionBar() {
         super.initImmersionBar()
         mImmersionBar = ImmersionBar.with(this)
         //同时自定义状态栏和导航栏颜色，不写默认状态栏为透明色，导航栏为黑色
         mImmersionBar?.fitsSystemWindows(true)
         mImmersionBar?.statusBarDarkFont(true, 0.15f)
         mImmersionBar?.titleBar(mToolbar)
         mImmersionBar?. statusBarColorTransform(R.color.colorPrimaryDark)  //状态栏变色后的颜色
         mImmersionBar?. navigationBarColorTransform(R.color.colorPrimaryDark) //导航栏变色后的颜色
         mImmersionBar?.statusBarColor(R.color.themeColor)
         mImmersionBar?.keyboardEnable(false)
         mImmersionBar?.navigationBarColor(R.color.themeColor)
         mImmersionBar?.init()
     }

   override fun widgetClick(v: View?) {
        super.widgetClick(v)
        var bd = Bundle();
        when (v!!.id) {
            R.id.fab_add -> {
                startActivity(IntoGuestRegistrationActivity::class.java)
            }
            R.id.ll_new_customer -> {
                bd.putString("title", "新进客资");
                startActivity(RegisterIntoShopSearchActivity::class.java, bd)
            }
            R.id.ll_customer_all -> {
                bd.putString("title", "全部客资");
                startActivity(RegisterIntoShopSearchActivity::class.java, bd)
            }
            R.id.ll_not_into_shop -> {
                bd.putString("title", "未进店");
                startActivity(RegisterIntoShopSearchActivity::class.java, bd)
            }
            R.id.ll_not_in_shop -> {
                bd.putString("title", "进店未成");
                startActivity(RegisterIntoShopSearchActivity::class.java, bd)
            }

            R.id.ll_make_bargain -> {
                bd.putString("title", "已成交");
                startActivity(RegisterIntoShopSearchActivity::class.java, bd)
            }
            R.id.ll_run_away -> {
                bd.putString("title", "流失客资");
                startActivity(RegisterIntoShopSearchActivity::class.java, bd)
            }
        }

    }
}
