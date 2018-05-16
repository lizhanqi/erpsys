package com.suxuantech.erpsys.ui.activity

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.Gravity
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.chad.library.adapter.base.BaseViewHolder
import com.suxuantech.erpsys.R
import com.suxuantech.erpsys.entity.FormEntity
import com.suxuantech.erpsys.entity.TakeDataEntity
import com.suxuantech.erpsys.ui.activity.base.TitleNavigationActivity
import com.suxuantech.erpsys.ui.adapter.MapQuickAdapter
import com.suxuantech.erpsys.ui.adapter.QuickAdapter
import com.suxuantech.erpsys.ui.widget.DefaultItemDecoration
import com.suxuantech.erpsys.ui.widget.ScrollEditText
import com.suxuantech.erpsys.utils.StringUtils
import java.util.*
import kotlin.collections.ArrayList

class TakeDataDetailsActivity : TitleNavigationActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_take_data_details)
        supportToolbar()
        setCenterTitle("取件详情")
        if (intent.hasExtra("data")) {
            var data = intent.extras.getParcelable<TakeDataEntity.DataBean>("data")
            val form = ArrayList<FormEntity>()
            form.add(FormEntity(R.drawable.icon_project_name, "项目名称", StringUtils.safetyString(data.consumption_name)))
            form.add(FormEntity(R.drawable.icon_take_number, "取件数量", "" + data.amount))
            form.add(FormEntity(R.drawable.icon_worry_date, "是否加急", StringUtils.safetyString(data.urgent)))
            form.add(FormEntity(R.drawable.icon_packaging_done, "整件完成", StringUtils.safetyString(data.whethercantake)))
            form.add(FormEntity(R.drawable.icon_packaging_done_date, "整件日期", StringUtils.safetyString(data.take_can_date)))
            form.add(FormEntity(R.drawable.icon_take_done, "取件完成", StringUtils.safetyString(data.whether_already_take)))
            form.add(FormEntity(R.drawable.icon_take_date, "取件日期", StringUtils.safetyString(data.take_away_date)))
            form.add(FormEntity(R.drawable.icon_product_remarks, "产品备注", StringUtils.safetyString(data.remarks)))
            form.add(FormEntity(R.drawable.icon_take_type, "取件类型", StringUtils.safetyString(data.sj_sendtype)))
            form.add(FormEntity(R.drawable.icon_send_date, "送件日期", StringUtils.safetyString(data.sj_senddate)))
            form.add(FormEntity(R.drawable.icon_send_person, "送件人员", StringUtils.safetyString(data.sj_sendname)))
            form.add(FormEntity(R.drawable.icon_send_car_number, "车牌号", StringUtils.safetyString(data.sj_sendnumberplate)))
            form.add(FormEntity(R.drawable.icon_tel, "联系电话", StringUtils.safetyString(data.sj_sendphone)))
            form.add(FormEntity(R.drawable.icon_send_area, "送件区域", StringUtils.safetyString(data.sj_area)))
            form.add(FormEntity(R.drawable.icon_send_address, "送件地址", StringUtils.safetyString(data.sj_sendaddress)))
            form.add(FormEntity(R.drawable.icon_send_remarks, "送件备注", StringUtils.safetyString(data.sj_sendremarks)))
            val recyclerView = findViewById<RecyclerView>(R.id.rv_data)
            val quickAdapter = object : QuickAdapter<FormEntity>(R.layout.item_form, form) {
                override fun convert(helper: BaseViewHolder?, item: FormEntity?) {
                    val view1 = helper?.getView<View>(R.id.ll_form)
                    val drawable = resources.getDrawable(item?.icon!!)
                    val imageView = helper?.getView<ImageView>(R.id.img_icon)
                    imageView?.setImageDrawable(drawable)
                    //
                    //    drawable.setBounds(0,0,drawable.minimumWidth,drawable.minimumHeight)
                    if (item?.key.equals("整件完成") || item?.key.equals("产品备注")) {
                        val layoutParams = view1?.layoutParams as RecyclerView.LayoutParams
                        layoutParams.setMargins(0, 30, 0, 0)
                    }
                    if (item?.key.equals("产品备注") || item?.key.equals("送件地址") || item?.key.equals("送件备注")) {
                        val tvk = helper?.getView<TextView>(R.id.tv_form_key)
                        tvk?.gravity = Gravity.TOP
                        // tvk?.setCompoundDrawables(drawable,null,null,null);
                        val tvv = helper?.getView<ScrollEditText>(R.id.tv_form_value)
                        tvv?.maxLines = 3;
                        tvv?.minLines = 3
                        tvv?.isEnabled = false
                    } else {
                        val tvv = helper?.getView<ScrollEditText>(R.id.tv_form_value)
                        tvv?.maxLines = 1;
                        tvv?.minLines = 1
                        tvv?.isEnabled = false
                    }
                    helper?.setText(R.id.tv_form_key, item?.key)
                    helper?.setText(R.id.tv_form_value, item?.value)
                }
            };
            recyclerView.addItemDecoration(DefaultItemDecoration(getResources().getColor(R.color.mainNavline_e7)))
            recyclerView.setLayoutManager(LinearLayoutManager(this))
            recyclerView.setAdapter(quickAdapter)
            toastShort(data.consumption_name)
        }
    }

    fun mapAdaputer() {
        var data = intent.extras.getParcelable<TakeDataEntity.DataBean>("data")
        val stringStringHashMap = LinkedHashMap<String, String>()
        stringStringHashMap["项目名称"] = data.consumption_name + ""
        stringStringHashMap["取件数量"] = "" + data.amount + ""
        stringStringHashMap["是否加急"] = data.urgent + ""
        stringStringHashMap["整件完成"] = data.whethercantake + ""
        stringStringHashMap["整件日期"] = data.take_can_date + ""
        stringStringHashMap["取件完成"] = data.whether_already_take
        stringStringHashMap["取件日期"] = data.take_away_date + ""
        stringStringHashMap["产品备注"] = data.remarks + ""
        stringStringHashMap["取件类型"] = data.sj_sendtype + ""
        stringStringHashMap["送件日期"] = data.sj_senddate + ""
        stringStringHashMap["送件人员"] = data.sj_sendname + ""
        stringStringHashMap["车牌号"] = data.sj_sendnumberplate + ""
        stringStringHashMap["联系电话"] = data.sj_sendphone + ""
        stringStringHashMap["送件区域"] = data.sj_area + ""
        stringStringHashMap["送件地址"] = data.sj_sendaddress + ""
        stringStringHashMap["送件备注"] = data.sj_sendremarks + ""
        val mapQuickAdapter = object : MapQuickAdapter<String>(R.layout.item_form, stringStringHashMap) {
            override fun convert(helper: BaseViewHolder, posstion: Int, key: String, value: String) {
                super.convert(helper, posstion, key, value)
                val view1 = helper.getView<View>(R.id.ll_form)
                if (key.equals("整件完成") || key.equals("产品备注")) {
                    val layoutParams = view1.layoutParams as RecyclerView.LayoutParams
                    layoutParams.setMargins(0, 30, 0, 0)
                }
                if (key.equals("产品备注") || key.equals("送件地址") || key.equals("送件备注")) {
                    val tvk = helper.getView<TextView>(R.id.tv_form_key)
                    var lap = tvk?.getLayoutParams() as LinearLayout.LayoutParams
                    lap.gravity = Gravity.TOP;
                    tvk.layoutParams=lap
                    val tvv = helper.getView<TextView>(R.id.tv_form_value)
                    tvv.maxLines = 3;
                    tvv.minLines = 3
                }
                helper.setText(R.id.tv_form_key, key)
                helper.setText(R.id.tv_form_value, value)
            }
        }
    }
}
