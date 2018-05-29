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
import com.suxuantech.erpsys.App
import com.suxuantech.erpsys.R
import com.suxuantech.erpsys.common.OptionHelp
import com.suxuantech.erpsys.entity.*
import com.suxuantech.erpsys.eventmsg.BaseMsg
import com.suxuantech.erpsys.nohttp.Contact
import com.suxuantech.erpsys.nohttp.HttpListener
import com.suxuantech.erpsys.nohttp.JavaBeanRequest
import com.suxuantech.erpsys.presenter.connector.ISearchOrderPresenter
import com.suxuantech.erpsys.ui.TypeFlag
import com.suxuantech.erpsys.ui.activity.base.TitleNavigationActivity
import com.suxuantech.erpsys.ui.adapter.QuickAdapter
import com.suxuantech.erpsys.ui.widget.DefaultItemDecoration
import com.suxuantech.erpsys.ui.widget.ScrollEditText
import com.suxuantech.erpsys.utils.DateUtil
import com.suxuantech.erpsys.utils.StringUtils
import com.suxuantech.erpsys.utils.ToastUtils
import com.yanzhenjie.nohttp.RequestMethod
import com.yanzhenjie.nohttp.rest.Response
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

/**
 * (排程确认)提交排程
 */
class SchemeCommintActivity : TitleNavigationActivity(), ISearchOrderPresenter {
    override fun searchFailed(response: Response<SearchOrderEntity>?, pageIndex: Int) {

    }

    override fun searchSucceed(data: MutableList<SearchOrderEntity.DataBean>?, isRefesh: Boolean, hasMore: Boolean) {
        if (data == null || data.size == 0) {
            ToastUtils.snackbarShort("未找到客户资料")
        } else if (data.size > 1) {
            ToastUtils.snackbarShort("找到多条资料,无法进行跳转")
        } else {
            val intent = Intent(this, OrderDetailActivity::class.java)
            val dataBean = data.get(0)
            intent.putExtra("orderId", dataBean.getOrderId())
            if (searchType == TypeFlag.OPTION_PANEL) {
                intent.putExtra("showOnPosition", OrderDetailActivity.SHOW_OPTION_PANEL_MATERIAL)
            } else {
                intent.putExtra("showOnPosition", OrderDetailActivity.SHOW_PHOTO_MATERIAL)
            }
            intent.putExtra("canChange", false)
            val bundle = Bundle()
            bundle.putParcelable("data", dataBean)
            intent.putExtras(bundle)
            startActivity(intent)
        }
    }

    /**
     * 是否是添加排程
     */
    var isAdd = true;
    /**
     *  排程类型
     */
    var searchType: TypeFlag? = null;
    /**
     * 排程显示在列表的简单排程数据信息
     */
    var schemeData: BaseScheme? = null;
    /**
     * 某一单的拍照排程数据
     */
    var photoSchemeHisory: ArrayList<PhotoSchemeSearchEntity.DataBean>? = null;
    /**
     * 数据显示
     */
    private var rvList: RecyclerView? = null
    /**
     * 历史
     */
    private var tvHistory: TextView? = null
    /**
     * 排程历史
     */
    var rvScheduledHistory: RecyclerView? = null
    /**
     * 某一单的历史选片排程
     */
    var optionPanelSchemeHisory: ArrayList<SearchOptionPanelEntity.DataBean>? = null;
    /**
     * 显示数据
     */
    var listData = ArrayList<FormEntity>();
    /**
     * 修改那一条排程
     */
    var changeIndex = -1;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scheme_commint)
        supportToolbar()
        useEventBus()
        searchType = intent.extras.getSerializable("type") as TypeFlag?;
        schemeData = intent.extras.getSerializable("schemeData") as BaseScheme?;
        isAdd = intent.extras.getBoolean("isAdd", true);
        changeIndex = intent.extras.getInt("changeIndex");
        photoSchemeHisory = intent.extras.getParcelableArrayList<PhotoSchemeSearchEntity.DataBean>("photoSchemeHisory");
        optionPanelSchemeHisory = intent.extras.getParcelableArrayList<SearchOptionPanelEntity.DataBean>("optionPanelSchemeHisory");
        initView()
        initData()
        initTitle()
    }
    fun initTitle(){
        if (searchType == TypeFlag.OPTION_PANEL) {
            if(isAdd){
                setTitle("选片排程(新增)")
                //  actionBar.setSubtitle("新增");//子标题
            }else{
                setTitle("选片排程(修改)")
                // actionBar.setSubtitle("修改");//子标题
            }
        } else if (searchType == TypeFlag.PHOTOGRAPH) {
            if(isAdd){
                setTitle("拍照排程(新增)")
                // supportActionBar?.setSubtitle("");//子标题
            }else{
                setTitle("拍照排程(修改)")
                // supportActionBar?.setSubtitle("修改");//子标题
            }
        }
    }
    /**
     * 表单的适配器
     */
    var formAdapter = object : QuickAdapter<FormEntity>(R.layout.item_form, listData) {
        override fun convert(helper: BaseViewHolder?, item: FormEntity?) {
            var llForm = helper?.getView<View>(R.id.ll_form) as LinearLayout;
            var tvFormKey = helper?.getView<View>(R.id.tv_form_key) as TextView;
            var tvFormValue = helper?.getView<View>(R.id.tv_form_value) as ScrollEditText;
            var iamge = helper?.getView<ImageView>(R.id.img_icon);
            iamge.setImageDrawable(resources.getDrawable(item?.icon!!))
            if (item?.mustFill!!) {
                val drawable = resources.getDrawable(R.drawable.arrows_right_gray)
                drawable.setBounds(0, 0, drawable.minimumWidth, drawable.minimumWidth)
                tvFormValue.setCompoundDrawables(null, null, drawable, null);
            }
            if (item?.edit!!) {
                tvFormValue.isEnabled=true
                tvFormValue.minLines = 3;
                tvFormValue.maxLines = 3;
                tvFormValue.setFocusableInTouchMode(true);
                tvFormValue.setFocusable(true);
                //tvFormValue.requestFocus();
            } else {
                tvFormValue.isEnabled=false
                tvFormValue.isClickable=false
                tvFormValue.setOnTouchListener(View.OnTouchListener { view, motionEvent ->
                    false
                })
            }
            tvFormKey.setText(item.key)
            if (StringUtils.empty(item.value)) {
                tvFormValue.setHint(StringUtils.safetyString(item.hint))
            } else {
                tvFormValue.setText(StringUtils.safetyString(item.value))
            }
        }
    }
    var optionPanelHistoryAdapter = object : QuickAdapter<SearchOptionPanelEntity.DataBean>(R.layout.item_scheme_history, null) {
        override fun convert(helper: BaseViewHolder?, item: SearchOptionPanelEntity.DataBean?) {
            var llHistoryInfo = helper?.getView<LinearLayout>(R.id.ll_history_info);
            var tvHistoryName = helper?.getView<TextView>(R.id.tv_history_name);
            var tvHistoryValue = helper?.getView<TextView>(R.id.tv_history_value);
            tvHistoryName?.setText(item?.consumption_type);
            tvHistoryValue?.setText(item?.selectday)
        }
    }
    var photoHistoryAdapter = object : QuickAdapter<PhotoSchemeSearchEntity.DataBean>(R.layout.item_scheme_history, null) {
        override fun convert(helper: BaseViewHolder?, item: PhotoSchemeSearchEntity.DataBean?) {
            var llHistoryInfo = helper?.getView<LinearLayout>(R.id.ll_history_info);
            var tvHistoryName = helper?.getView<TextView>(R.id.tv_history_name);
            var tvHistoryValue = helper?.getView<TextView>(R.id.tv_history_value);
            tvHistoryName?.setText(item?.consumption_type);
            tvHistoryValue?.setText(item?.photodate)
        }
    }

    fun initView() {
        rvList = findViewById<View>(R.id.rv_list) as RecyclerView
        tvHistory = findViewById<View>(R.id.tv_history) as TextView
        rvScheduledHistory = findViewById<View>(R.id.rv_scheduled_history) as RecyclerView
        rvScheduledHistory?.addItemDecoration(DefaultItemDecoration(resources.getColor(R.color.mainNavline_e7)))
        rvScheduledHistory?.layoutManager = LinearLayoutManager(baseContext);
        photoHistoryAdapter.setOnItemClickListener { adapter, view, position ->
            //todo 跳转专门的页面
//            var soso = SearchOrderPresenter();
//            soso.setiSearchOrderPresenter(this);
//            soso.sosoNetOrder(photoHistoryAdapter.data.get(position).orderid,
//                    App.getContext().resources.getString(R.string.start_time),
//                    App.getContext().resources.getString(R.string.end_time), true, false)
        }
        optionPanelHistoryAdapter.setOnItemClickListener { adapter, view, position ->

            //todo 跳转专门的页面
//            var soso = SearchOrderPresenter();
//            soso.setiSearchOrderPresenter(this);
//            soso.sosoNetOrder(optionPanelHistoryAdapter.data.get(position).orderid,
//                    App.getContext().resources.getString(R.string.start_time),
//                    App.getContext().resources.getString(R.string.end_time), true, false)

        }
        rvList?.layoutManager = LinearLayoutManager(baseContext);
        rvList?.adapter = formAdapter;
        rvList?.addItemDecoration(DefaultItemDecoration(resources.getColor(R.color.mainNavline_e7)))
        formAdapter.bindToRecyclerView(rvList)
        formAdapter.setOnItemClickListener(BaseQuickAdapter.OnItemClickListener { adapter, view, position ->
            if (position == 0) {
                Option();
            }
        });

    }

    /**
     * 初始化表单数据,以及历史信息
     */
        fun initData() {
        if (searchType == TypeFlag.OPTION_PANEL) {
            tvHistory?.setText("历史选片排程")
            rvScheduledHistory?.adapter = optionPanelHistoryAdapter;
            optionPanelHistoryAdapter.updateAll(optionPanelSchemeHisory)
            //显示在排程ScheduleActivity的网络真实所有数据
            //-----------表单数据-----------
            val formEntity = FormEntity("选片类型");
            formEntity.hint = "请选择选片类型"
            formEntity.icon = R.drawable.icon_photo_type
            formEntity.mustFill = true
            formEntity.option = true;
            listData.add(formEntity)
            val formEntity2 = FormEntity("选片日期");
            formEntity2.icon = R.drawable.icon_date
            formEntity2.hint = schemeData?.pcday
            listData.add(formEntity2)
            val formEntity3 = FormEntity("选片时间");
            formEntity3.hint = schemeData?.pctime
            formEntity3.icon = R.drawable.icon_time
            listData.add(formEntity3)
            val formEntity4 = FormEntity("选片备注");
            formEntity4.hint = "请填写选片备注"
            formEntity4.edit = true
            formEntity4.icon = R.drawable.icon_send_remarks
            listData.add(formEntity4)
        } else if (searchType == TypeFlag.PHOTOGRAPH) {


            rvScheduledHistory?.adapter = photoHistoryAdapter;
            photoHistoryAdapter.updateAll(photoSchemeHisory)
            tvHistory?.setText("历史拍照排程")
            //-----------表单数据-----------
            val formEntity = FormEntity("拍照类型");
            formEntity.hint = "请选择拍照类型"
            formEntity.icon = R.drawable.icon_photo_type
            formEntity.mustFill = true
            formEntity.option = true;
            listData.add(formEntity)
            val formEntity2 = FormEntity("拍照日期");
            formEntity2.hint = schemeData?.pcday
            formEntity2.icon = R.drawable.icon_date
            listData.add(formEntity2)
            val formEntity3 = FormEntity("拍照时间");
            formEntity3.icon = R.drawable.icon_time
            formEntity3.hint = schemeData?.pctime
            listData.add(formEntity3)
            val formEntity4 = FormEntity("客户备注");
            formEntity4.hint = "请填写备注"
            formEntity4.icon = R.drawable.icon_send_remarks
            formEntity4.edit = true
            listData.add(formEntity4)
        }
    }

    fun Option() {
        if (searchType == TypeFlag.OPTION_PANEL) {
            val optionHelp = OptionHelp(this)
            optionHelp.setUrlTag(OptionHelp.UrlTag.OPTION_PANEL_TYPE_SET);
            optionHelp.setTitle("选片类型")
            startActivity(optionHelp.creat())
        } else if (searchType == TypeFlag.PHOTOGRAPH) {
            val optionHelp = OptionHelp(this)
            optionHelp.setUrlTag(OptionHelp.UrlTag.PHOTO_TYPE_SET);
            optionHelp.setTitle("拍照类型")
            startActivity(optionHelp.creat())
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menu?.add(Menu.NONE, 1, 1, "提交")
                ?.setEnabled(true)
                ?.setOnMenuItemClickListener { menuItem ->
                    if (StringUtils.empty(listData.get(0).value)) {
                        ToastUtils.snackbarShort("请选择类型")
                        true
                    } else {
                        var url = ""
                        var pcday = schemeData?.getPcday();
                        pcday = DateUtil.String2String(pcday, DateUtil.DatePattern.JUST_DAY_NUMBER, DateUtil.DatePattern.ONLY_DAY);
                        if (isAdd) {
                            if (searchType == TypeFlag.OPTION_PANEL) {
                                url = Contact.getFullUrl(Contact.ADD_OPTION_PANEL_SCHEME, Contact.TOKEN, pcday, App.getApplication().userInfor.shop_code)
                            } else if (searchType == TypeFlag.PHOTOGRAPH) {
                                url = Contact.getFullUrl(Contact.ADD_PHOTOGRAPH_SCHEME, Contact.TOKEN, pcday, App.getApplication().userInfor.shop_code)
                            }
                        } else {
                            if (searchType == TypeFlag.OPTION_PANEL) {
                                //选片
                                url = Contact.getFullUrl(Contact.CHANGE_OPTION_PANEL_SCHEME, Contact.TOKEN, pcday, App.getApplication().userInfor.shop_code, optionPanelSchemeHisory?.get(changeIndex)?.id)
                            } else if (searchType == TypeFlag.PHOTOGRAPH) {
                                //拍照的
                                url = Contact.getFullUrl(Contact.CHANGE_PHOTOGRAPH_SCHEME, Contact.TOKEN, pcday, App.getApplication().userInfor.shop_code, photoSchemeHisory?.get(changeIndex)?.id)
                            }
                        }
                        scheme(url)
                    }
                    true
                }
                ?.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS)

        return super.onCreateOptionsMenu(menu)
    }

    /**
     * @param msg
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onMessageEvent(msg: BaseMsg<*>) {
        val urlTag = msg.urlTag
        when (urlTag) {
            OptionHelp.UrlTag.OPTION_PANEL_TYPE_SET -> {
                var pan = msg.singleChecked as OptionPanelTypeEntity.DataBean
                listData.get(0).value = pan.selectordername;
                formAdapter.updateAll(listData)
            }
            OptionHelp.UrlTag.PHOTO_TYPE_SET -> {
                var pan = msg.singleChecked as PhotoTypeEntity.DataBean
                listData.get(0).value = pan.phototype;
                formAdapter.updateAll(listData)
            }
        }
    }

    /**
     * 排程(修改和新增通吃)
     */
    fun scheme(url: String) {
        if (searchType == TypeFlag.OPTION_PANEL) {
            //选片
            val viewByPosition = formAdapter.getViewByPosition(listData.size - 1, R.id.tv_form_value) as ScrollEditText;
            var request = JavaBeanRequest(url, RequestMethod.POST, SimpleEntity::class.java)
            var pcday = schemeData?.pcday;
            //   pcday = DateUtil.String2String(pcday, DateUtil.DatePattern.JUST_DAY_NUMBER, DateUtil.DatePattern.ONLY_DAY);
            request.add("Pcid", schemeData?.pcid!!);
            request.add("Orderid", optionPanelSchemeHisory?.get(0)?.orderid);
            request.add("Customerid", optionPanelSchemeHisory?.get(0)?.customerid);
            if (!isAdd) {
                var Spid = "" + optionPanelSchemeHisory?.get(changeIndex)?.id;
                request.add("Spid", Spid);
            } else {
                request.add("Spid", "");
            }
            request.add("Select_order_name", listData.get(0).value);
            request.add("Selectday", pcday);
            request.add("SelectTime", schemeData?.pctime);
            request.add("Shop_code", App.getApplication().userInfor.shop_code);
            request.add("Shop_name", App.getApplication().userInfor.shop_name);
            request.add("Spremarks", viewByPosition.text.toString());
            val searchByCustmor = object : HttpListener<SimpleEntity> {
                override fun onSucceed(what: Int, response: Response<SimpleEntity>) {
                    if (response.get().isOK) {
                        App.findActivity(SearchOrderActivity::class.java).finish()
                        EventBus.getDefault().post("refresh")
                        finish()
                    }
                }

                override fun onFailed(what: Int, response: Response<SimpleEntity>) {}
            }
            request<SimpleEntity>(123, request, searchByCustmor, true, true)
        } else if (searchType == TypeFlag.PHOTOGRAPH) {
            //拍照的
            val viewByPosition = formAdapter.getViewByPosition(listData.size - 1, R.id.tv_form_value) as ScrollEditText;
            //请求实体
            var request = JavaBeanRequest(url, RequestMethod.POST, SimpleEntity::class.java)
            var pcday = schemeData?.pcday;
            request.add("Orderid", photoSchemeHisory?.get(0)?.orderid);
            request.add("Customerid", photoSchemeHisory?.get(0)?.customerid);
            request.add("Phototype", listData.get(0).value);
            request.add("Photodate", pcday);
            request.add("Phototime", schemeData?.pctime);
            request.add("Shop_code", App.getApplication().userInfor.shop_code);
            request.add("Shop_name", App.getApplication().userInfor.shop_name);
            request.add("Ordernote", viewByPosition.text.toString())
            if (!isAdd) {
                var photoid = "" + photoSchemeHisory?.get(changeIndex)?.id;
                request.add("Photoid", photoid);
            } else {
                request.add("Photoid", "");
            }
            request.add("Pcid", schemeData?.pcid!!);
            val searchByCustmor = object : HttpListener<SimpleEntity> {
                override fun onSucceed(what: Int, response: Response<SimpleEntity>) {
                    if (response.get().isOK) {
                        App.findActivity(SearchOrderActivity::class.java).finish()
                        EventBus.getDefault().post("refresh")
                        finish()
                    }
                }
                override fun onFailed(what: Int, response: Response<SimpleEntity>) {}
            }
            request<SimpleEntity>(123, request, searchByCustmor, true, true)
        }
    }
}
