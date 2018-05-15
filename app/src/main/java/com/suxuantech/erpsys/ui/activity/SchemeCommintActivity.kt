package com.suxuantech.erpsys.ui.activity

import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.Menu
import android.view.MenuItem
import android.view.View
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
import com.suxuantech.erpsys.ui.TypeFlag
import com.suxuantech.erpsys.ui.activity.base.TitleNavigationActivity
import com.suxuantech.erpsys.ui.adapter.QuickAdapter
import com.suxuantech.erpsys.ui.widget.ScrollEditText
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
class SchemeCommintActivity : TitleNavigationActivity() {
    var searchType: TypeFlag? = null;
    var schemeData: BaseScheme? = null;
    var dataBean: SearchOrderEntity.DataBean? = null;
    private var rvList: RecyclerView? = null
    private var tvHistory: TextView? = null
    private var tvHistoryName: TextView? = null
    private var tvHistoryValue: TextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scheme_commint)
        supportToolbar()
        useEventBus()
        searchType = intent.extras.getSerializable("type") as TypeFlag?;
        if (searchType == TypeFlag.OPTION_PANEL) {
            setTitle("选片排程")
        } else if (searchType == TypeFlag.PHOTOGRAPH) {
            setTitle("拍照排程")
        }
        schemeData = intent.extras.getSerializable("schemeData") as BaseScheme?;
        dataBean = intent.extras.getParcelable<SearchOrderEntity.DataBean>("data");
        initView()
    }

    var listData = ArrayList<FormEntity>();
    var ada = object : QuickAdapter<FormEntity>(R.layout.item_form, listData) {
        override fun convert(helper: BaseViewHolder?, item: FormEntity?) {
            var llForm = helper?.getView<View>(R.id.ll_form) as LinearLayout;
            var tvFormKey = helper?.getView<View>(R.id.tv_form_key) as TextView;
            var tvFormValue = helper?.getView<View>(R.id.tv_form_value) as ScrollEditText;
            if (item?.mustFill!!) {
                val drawable = resources.getDrawable(R.drawable.arrows_right_gray)
                drawable.setBounds(0, 0, drawable.minimumWidth, drawable.minimumWidth)
                tvFormValue.setCompoundDrawables(null, null, drawable, null);
            }
            tvFormValue.setEnabled(false)
            if (item?.edit!!) {
                tvFormValue.minLines = 3;
                tvFormValue.maxLines = 3;
                tvFormValue.setEnabled(true)
            }
            tvFormKey.setText(item.key)
            if (StringUtils.empty(item.value)) {
                tvFormValue.setHint(StringUtils.safetyString(item.hint))
            } else {
                tvFormValue.setText(StringUtils.safetyString(item.value))
            }
        }
    }

    private fun initView() {
        rvList = findViewById<View>(R.id.rv_list) as RecyclerView
        tvHistory = findViewById<View>(R.id.tv_history) as TextView
        tvHistoryName = findViewById<View>(R.id.tv_history_name) as TextView
        tvHistoryValue = findViewById<View>(R.id.tv_history_value) as TextView
        findViewById<View>(R.id.ll_history_info)  .setOnClickListener(View.OnClickListener {
            val intent = Intent(baseContext, OrderDetailActivity::class.java)
            intent.putExtra("orderId", dataBean?.getOrderId())
            if (searchType == TypeFlag.OPTION_PANEL) {
                intent.putExtra("showOnPosition", 5)
            }else{
                intent.putExtra("showOnPosition", 4)
            }

            intent.putExtra("canChange", false)
            val bundle = Bundle()
            bundle.putParcelable("data", dataBean)
            intent.putExtras(bundle)
            startActivity(intent)
        });

        if (searchType == TypeFlag.OPTION_PANEL) {
            val dataAll = intent.extras.getParcelable<TodayOptionPhotoSchemeEntity.DataBean>("allSchemeData");
            tvHistory?.setText("历史选片排程")
            tvHistoryName?.setText("选片类型:" + dataAll.sptype)
            tvHistoryValue?.setText(StringUtils.safetyString(dataAll.selectday))
            val formEntity = FormEntity("选片类型");
            formEntity.hint = "请选择选片类型"
            formEntity.mustFill = true
            formEntity.option = true;
            listData.add(formEntity)
            val formEntity2 = FormEntity("选片日期");
            formEntity2.hint = schemeData?.pcday
            listData.add(formEntity2)
            val formEntity3 = FormEntity("选片时间");
            formEntity3.hint = schemeData?.pctime
            listData.add(formEntity3)
            val formEntity4 = FormEntity("选片备注");
            formEntity4.hint = "请填写选片备注"
            formEntity4.edit = true
            listData.add(formEntity4)
        } else if (searchType == TypeFlag.PHOTOGRAPH) {
            val dataAll = intent.extras.getParcelable<TodayPhotoScheduleEntity.DataBean>("allSchemeData");
            tvHistory?.setText("历史拍照排程")
            tvHistory?.setText("历史选片排程")
            tvHistoryName?.setText("拍照类型:" + dataAll.phototype)
            tvHistoryValue?.setText(StringUtils.safetyString(dataAll.photodate))
            val formEntity = FormEntity("拍照类型");
            formEntity.hint = "请选择拍照类型"
            formEntity.mustFill = true
            formEntity.option = true;
            listData.add(formEntity)
            val formEntity2 = FormEntity("拍照日期");
            formEntity2.hint = schemeData?.pcday
            listData.add(formEntity2)
            val formEntity3 = FormEntity("拍照时间");
            formEntity3.hint = schemeData?.pctime
            listData.add(formEntity3)
            val formEntity4 = FormEntity("客户备注");
            formEntity4.hint = "请填写备注"
            formEntity4.edit = true
            listData.add(formEntity4)
        }
        rvList?.layoutManager = LinearLayoutManager(baseContext);
        rvList?.adapter = ada;
        ada.bindToRecyclerView(rvList)
        ada.setOnItemClickListener(BaseQuickAdapter.OnItemClickListener { adapter, view, position ->
            if (position == 0) {
                Option();
            }
        });

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
                        if (intent.getBooleanExtra("add", true)) {
                            if (searchType == TypeFlag.OPTION_PANEL) {
                                url = Contact.getFullUrl(Contact.ADD_OPTION_PANEL_SCHEME, Contact.TOKEN, schemeData?.getPcday(), App.getApplication().userInfor.shop_code)
                            } else if (searchType == TypeFlag.PHOTOGRAPH) {
                                url = Contact.getFullUrl(Contact.ADD_PHOTOGRAPH_SCHEME, Contact.TOKEN, schemeData?.getPcday(), App.getApplication().userInfor.shop_code)
                            }
                            scheme(url)
                        } else {
                            if (searchType == TypeFlag.OPTION_PANEL) {
                                //选片
                                url = Contact.getFullUrl(Contact.CHANGE_OPTION_PANEL_SCHEME, Contact.TOKEN, schemeData?.getPcday(), App.getApplication().userInfor.shop_code)
                            } else if (searchType == TypeFlag.PHOTOGRAPH) {
                                //拍照的
                                url = Contact.getFullUrl(Contact.CHANGE_PHOTOGRAPH_SCHEME, Contact.TOKEN, schemeData?.getPcday(), App.getApplication().userInfor.shop_code)
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
                ada.updateAll(listData)
            }
            OptionHelp.UrlTag.PHOTO_TYPE_SET -> {
                var pan = msg.singleChecked as PhotoTypeEntity.DataBean
                listData.get(0).value = pan.phototype;
                ada.updateAll(listData)
            }
        }
    }

    /**
     * 排程
     */
    fun scheme(url: String) {
        if (searchType == TypeFlag.OPTION_PANEL) {
            //选片
            val viewByPosition = ada.getViewByPosition(listData.size - 1, R.id.tv_form_value) as ScrollEditText;
            //请求实体
            var request = JavaBeanRequest(url, RequestMethod.POST, SimpleEntity::class.java)
            val dataAll = intent.extras.getParcelable<TodayOptionPhotoSchemeEntity.DataBean>("allSchemeData");
            request.add("Pcid", schemeData?.pcid!!);
            request.add("Orderid", dataBean?.orderId);
            request.add("Customerid", dataBean?.customerid);
            request.add("Spid", dataAll?.spid);
            request.add("Select_order_name", listData.get(0).value);
            request.add("Selectday", schemeData?.pcday);
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
            val viewByPosition = ada.getViewByPosition(listData.size - 1, R.id.tv_form_value) as ScrollEditText;
            //请求实体
            var request = JavaBeanRequest(url, RequestMethod.POST, SimpleEntity::class.java)
            request.add("Orderid", dataBean?.orderId);
            request.add("Customerid", dataBean?.customerid);
            request.add("Phototype", listData.get(0).value);
            request.add("Photodate", schemeData?.pcday);
            request.add("Phototime", schemeData?.pctime);
            request.add("Shop_code", App.getApplication().userInfor.shop_code);
            request.add("Shop_name", App.getApplication().userInfor.shop_name);
            request.add("Ordernote", viewByPosition.text.toString())
            val dataAll = intent.extras.getParcelable<TodayPhotoScheduleEntity.DataBean>("allSchemeData");
            request.add("Photoid", dataAll?.getPhotoid());
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
