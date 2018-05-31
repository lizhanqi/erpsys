package com.suxuantech.erpsys.ui.activity

import android.os.Bundle
import android.support.design.widget.AppBarLayout
import android.support.design.widget.FloatingActionButton
import android.support.v4.graphics.drawable.DrawableCompat
import android.support.v4.widget.NestedScrollView
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.chad.library.adapter.base.BaseViewHolder
import com.scwang.smartrefresh.layout.SmartRefreshLayout
import com.scwang.smartrefresh.layout.listener.OnRefreshListener
import com.suxuantech.erpsys.App
import com.suxuantech.erpsys.R
import com.suxuantech.erpsys.entity.CustomerIntoStoreCountEntity
import com.suxuantech.erpsys.entity.FormEntity
import com.suxuantech.erpsys.entity.UnremarkCustomerCountEntity
import com.suxuantech.erpsys.nohttp.Contact
import com.suxuantech.erpsys.nohttp.HttpListener
import com.suxuantech.erpsys.nohttp.JavaBeanRequest
import com.suxuantech.erpsys.ui.AppBarStateChangeListener
import com.suxuantech.erpsys.ui.activity.base.TitleNavigationActivity
import com.suxuantech.erpsys.ui.adapter.QuickAdapter
import com.suxuantech.erpsys.utils.DateUtil
import com.suxuantech.erpsys.utils.MyString
import com.suxuantech.erpsys.utils.StringUtils
import com.suxuantech.erpsys.utils.ToastUtils
import com.yanzhenjie.nohttp.RequestMethod
import com.yanzhenjie.nohttp.rest.Response
import kotlinx.android.synthetic.main.activity_register_into_shop.*

/**
 * 进店登记
Intent intent = new Intent(getActivity(), ScheduleActivity.class);
intent.putExtra("title","选片");
startActivity(intent);

Intent intent1 = new Intent(getActivity(), ScheduleActivity.class);
intent1.putExtra("title","拍照");
startActivity(intent1);
 */
class RegisterIntoShopActivity : TitleNavigationActivity() {
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
    var mToolbar: android.support.v7.widget.Toolbar? = null
    var scrollView: NestedScrollView? = null
    var recyclerView: RecyclerView? = null
    var list = ArrayList<FormEntity>();
    var addButton: FloatingActionButton? = null
    var adapter = object : QuickAdapter<FormEntity>(R.layout.item_type, list) {
        public override fun convert(helper: BaseViewHolder, item: FormEntity) {
            var root = helper.getView<LinearLayout>(R.id.ll_type)
            val layoutParams = root.layoutParams as RecyclerView.LayoutParams
            layoutParams.setMargins(0, item.marginTop, 0, 0)
            var name = helper.getView<TextView>(R.id.tv_type_name)
            var value = helper.getView<TextView>(R.id.tv_type_value)
            var icon = helper.getView<ImageView>(R.id.img_icon_type)
            name.setText(item.key)
            if(StringUtils.empty(item.value)){
                value.visibility=View.GONE
            }else{
                value.visibility=View.VISIBLE
                name.setText(item.value)
            }

            icon.setImageDrawable(resources.getDrawable(item.icon))
            var temp = "";
            if (item.flag != null) {
                temp = item.flag as String
            }
            if (!intent.hasExtra("title")) {
                value?.append(MyString(temp.toString()).setColor(resources.getColor(R.color.litte_red)))

            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_into_shop)
        addButton = idSetOnClick<FloatingActionButton>(R.id.fab_add);
        mToolbar = idGetView<android.support.v7.widget.Toolbar>(R.id.toolbar2)
        setSupportActionBar(mToolbar)
        supportToolbar()
        if (intent.hasExtra("title")) {
            val stringExtra = intent.getStringExtra("title");
            addButton?.visibility = View.GONE
            mToolbar?.setTitle(stringExtra)
        }
        scrollView = idGetView<NestedScrollView>(R.id.nsv_view)
        idGetView<AppBarLayout>(R.id.appbar).addOnOffsetChangedListener(object : AppBarStateChangeListener() {
            override fun onStateChanged(appBarLayout: AppBarLayout, state: State) {
                if (!intent.hasExtra("title")) {
                    if (state === State.EXPANDED) {
                        //展开状态
                        menus?.findItem(R.id.action_settings)?.setVisible(false)
                        menus?.findItem(R.id.action_today)?.setVisible(false)
                    } else if (state === State.COLLAPSED) {
                        //折叠状态
                        menus?.findItem(R.id.action_settings)?.setVisible(true)
                        menus?.findItem(R.id.action_today)?.setVisible(true)
                    } else {
                        //中间状态
                    }
                } else {
                    addButton?.visibility = View.GONE
                }
            }
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
        recyclerView = idGetView<RecyclerView>(R.id.rv_list);
        if (!intent.hasExtra("title")) {
            //（0：所有客资；1：已成交；2：流失客资；3：新进客客资；4：未进店；5：进店未成交；6：登记日期未标记预约进店日；）
            list.add(FormEntity(R.drawable.icon_all_customer, "所有客资", "今日所有客资", 5, 0))
            list.add(FormEntity(R.drawable.icon_new_customer, "新进客资", "今日新进客资", 5, 3))
            list.add(FormEntity(R.drawable.icon_not_in_shop, "未进店", "今日未进店客资", 5, 4))
            list.add(FormEntity(R.drawable.icon_into_shop_unbargain, "进店未成", "进店未成交客资", 5, 5))
            list.add(FormEntity(R.drawable.icon_already_make_bargain, "已成交", "今日成交客资", 5, 1))
            list.add(FormEntity(R.drawable.icon_run_away, "流失客资", "今日流失客资", 5, 2))
            list.add(FormEntity(R.drawable.icon_in_shop_unlabeled, "未标记进店日期客资", "未标记进店客资", 30, 6))
        } else {
            list.add(FormEntity(R.drawable.icon_camera_schedule, "拍照排程",   5))
            list.add(FormEntity(R.drawable.icon_schedule_select_pictrue, "选片排程",   5))
        }
        recyclerView?.layoutManager = LinearLayoutManager(this);
        adapter.setOnItemClickListener { adapter, view, position ->
            val bundle = Bundle();
            bundle.putString("title", list.get(position).key);
            if (intent.hasExtra("title")) {
                if (!App.getApplication().hasPermission("M2")) {
                    ToastUtils.snackbarShort("无排程查询权限,无法进入", "确定")
                } else {
                    startActivity(ScheduleActivity::class.java, bundle)
                }
            } else {
                val get = adapter.data.get(position) as FormEntity;
                bundle.putInt("jktype", get.flag2 as Int)
                bundle.putBoolean("hideSearch", true)
                startActivity(RegisterIntoShopSearchActivity::class.java, bundle)
            }
        }
        recyclerView?.adapter = adapter;
        smartRefreshLayout.setOnRefreshListener(OnRefreshListener {
            if (!intent.hasExtra("title")) {
                getData();
            } else {
                refreshLayout.finishRefresh();
            }
        })
    }
    fun getData() {
        if (App.getApplication().hasPermission("K2")) {
            ToastUtils.snackbarShort("无权限获取登记统计数据","确定")
            refreshLayout.finishRefresh();
            return
        }
        val nowDate = DateUtil.getNowDate(DateUtil.DatePattern.JUST_DAY_NUMBER);
        var name1 = App.getApplication().userInfor.staffname;
        var name2 = App.getApplication().userInfor.staffname;
        if (App.getApplication().hasPermission("K14")) {
            name1 = "";
        }
        if (App.getApplication().hasPermission("K15")) {
            name2 = "";
        }

        var url = Contact.getFullUrl(Contact.CUSTOMER_INTO_STORE_COUNT, Contact.TOKEN,
                nowDate, nowDate,
                "", 0, 0, App.getApplication().userInfor.shop_code, name1, name2)
        var url2 = Contact.getFullUrl(Contact.UNREMARK_CUSTOMER_INTO_STORE_DATE_COUNT, Contact.TOKEN,
                nowDate, nowDate,
                "", 0, 0, App.getApplication().userInfor.shop_code, name1, name2)
        //请求实体
        val requset = JavaBeanRequest(url, RequestMethod.POST, CustomerIntoStoreCountEntity::class.java)
        val customerIntoStoreCountEntity = object : HttpListener<CustomerIntoStoreCountEntity> {
            override fun onSucceed(what: Int, response: Response<CustomerIntoStoreCountEntity>) {
                if (response.get().isOK) {
                    list.forEach {
                        if (it.value.equals("今日新进客资")) {
                            it.flag = response.get().data?.get(0)?.newcount;
                        } else if (it.value.equals("今日进店未成交")) {
                            it.flag = response.get().data?.get(0)?.jdwccount;
                        } else if (it.value.equals("未进店客客资")) {
                            it.flag = response.get().data?.get(0)?.wjdcount;
                        } else if (it.value.equals("今日成交客资")) {
                            it.flag = response.get().data?.get(0)?.ycjcount;
                        } else if (it.value.equals("今日流失客资")) {
                            it.flag = response.get().data?.get(0)?.lscount;
                        } else if (it.value.equals("今日所有客资")) {
                            it.flag = response.get().data?.get(0)?.allcount;
                        }
                    }
                    adapter.notifyDataSetChanged()
                    refreshLayout.finishRefresh();
                    refreshLayout.setNoMoreData(false);
                } else {
                    refreshLayout.finishRefresh();
                    refreshLayout.setNoMoreData(true);
                }
            }

            override fun onFailed(what: Int, response: Response<CustomerIntoStoreCountEntity>) {
                refreshLayout.finishRefresh();
                refreshLayout.setNoMoreData(true);
            }
        }

        //请求实体
        val requset1 = JavaBeanRequest(url2, RequestMethod.POST, UnremarkCustomerCountEntity::class.java)
        val unremarkCustomerCountEntity = object : HttpListener<UnremarkCustomerCountEntity> {
            override fun onSucceed(what: Int, response: Response<UnremarkCustomerCountEntity>) {
                if (response.get().isOK) {
                    list.forEach {
                        if (it.key.equals("未标记进店日期客资")) {
                            it.flag = response.get().data?.get(0)?.wycount;
                        }
                    }
                    adapter.notifyItemChanged(list.size - 1)
                    refreshLayout.finishRefresh();
                    refreshLayout.setNoMoreData(false);
                } else {
                    refreshLayout.finishRefresh();
                    refreshLayout.setNoMoreData(true);
                }
            }

            override fun onFailed(what: Int, response: Response<UnremarkCustomerCountEntity>) {
                refreshLayout.finishRefresh();
                refreshLayout.setNoMoreData(true);
            }
        }
        request(2, requset, customerIntoStoreCountEntity, true, false)
        request(0, requset1, unremarkCustomerCountEntity, true, false)
    }

    var menus: Menu? = null;
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.reg_nmb_menu, menu)
        menus = menu;
        //展开状态
        var ad = resources?.getDrawable(R.drawable.icon_add)
        DrawableCompat.setTint(ad!!, resources.getColor(R.color.white))
        var sodrawable = resources?.getDrawable(R.drawable.icon_soso)
        DrawableCompat.setTint(sodrawable!!, resources.getColor(R.color.white))
        sodrawable.setBounds(0, 0, sodrawable.minimumWidth * 2, sodrawable.minimumWidth * 2)
        menus?.findItem(R.id.action_settings)?.setIcon(ad)?.setVisible(false)
        menus?.findItem(R.id.action_today)?.setIcon(sodrawable)?.setVisible(false)
        return true
    }

    /**
     * toolbar菜单的点击
     */
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (android.R.id.home == item.itemId) {
            this.onBackPressed()
        } else if (R.id.action_settings == item.itemId) {
            if (App.getApplication().hasPermission("K3")) {
                startActivity(IntoGuestRegistrationActivity::class.java)
            } else {
                toastShort("无权登记")
            }
        } else if (R.id.action_today == item.itemId) {
            var bundle = Bundle()
            bundle.putBoolean("hideSearch", false)
            bundle.putString("title", "客资搜索")
            startActivity(RegisterIntoShopSearchActivity::class.java, bundle)
        }
        return super.onOptionsItemSelected(item)
    }

    override fun initImmersionBar() {
        lineView.visibility = View.GONE
        statusBarFollow = appbar
        super.initImmersionBar()
    }

    override fun widgetClick(v: View?) {
        super.widgetClick(v)
        when (v?.id) {
            R.id.fab_add -> {
                if (App.getApplication().hasPermission("K3")) {
                    startActivity(IntoGuestRegistrationActivity::class.java)
                } else {
                    toastShort("无权登记")
                }
            }
        }
    }
}
