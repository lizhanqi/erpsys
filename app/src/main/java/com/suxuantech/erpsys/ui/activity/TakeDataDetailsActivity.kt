package com.suxuantech.erpsys.ui.activity

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.Gravity
import android.view.View
import android.widget.TextView
import com.chad.library.adapter.base.BaseViewHolder
import com.suxuantech.erpsys.R
import com.suxuantech.erpsys.entity.TakeDataEntity
import com.suxuantech.erpsys.ui.activity.base.TitleNavigationActivity
import com.suxuantech.erpsys.ui.adapter.MapQuickAdapter
import com.suxuantech.erpsys.ui.widget.DefaultItemDecoration
import java.util.*

class TakeDataDetailsActivity : TitleNavigationActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_take_data_details)
        supportToolbar()
        setCenterTitle("取件详情")
        if (intent.hasExtra("data")) {
            var data = intent.extras.getParcelable<TakeDataEntity.DataBean>("data")
            val stringStringHashMap = LinkedHashMap<String, String>()
            stringStringHashMap["项目名称"] = data.consumption_name+""
            stringStringHashMap["取件数量"] =  ""+data.amount+""
            stringStringHashMap["是否加急"] = data.urgent+""
            stringStringHashMap["整件完成"] = data.whethercantake+""
            stringStringHashMap["整件日期"] = data.take_can_date+""
            stringStringHashMap["取件完成"] = data.whether_already_take
            stringStringHashMap["取件日期"] = data.take_away_date+""
            stringStringHashMap["产品备注"] = data.remarks+""
            stringStringHashMap["取件类型"] = data.sj_sendtype+""
            stringStringHashMap["送件日期"] = data.sj_senddate+""
            stringStringHashMap["送件人员"] = data.sj_sendname+""
            stringStringHashMap["车牌号"] = data.sj_sendnumberplate+""
            stringStringHashMap["联系电话"] = data.sj_sendphone+""
            stringStringHashMap["送件区域"] = data.sj_area+""
            stringStringHashMap["送件地址"] = data.sj_sendaddress+""
            stringStringHashMap["送件备注"] = data.sj_sendremarks+""
            val recyclerView = findViewById<RecyclerView>(R.id.rv_data)
            val mapQuickAdapter = object : MapQuickAdapter<String>(R.layout.item_form, stringStringHashMap) {
                override fun convert(helper: BaseViewHolder, posstion: Int, key: String, value: String) {
                    super.convert(helper, posstion, key, value)
                    val view1 = helper.getView<View>(R.id.ll_form)
                    if(key.equals("整件完成")||key.equals("产品备注") ){
                        val layoutParams = view1.layoutParams as RecyclerView.LayoutParams
                        layoutParams.setMargins(0, 30, 0, 0)
                    }
                    if(key.equals("产品备注")||key.equals("送件地址")||key.equals("送件备注")){
                        val tvk = helper.getView<TextView>(R.id.tv_form_key)
                        tvk.gravity=Gravity.TOP
                        val tvv = helper.getView<TextView>(R.id.tv_form_value)
                        tvv.maxLines=3;
                        tvv.minLines=3
                    }
                    helper.setText(R.id.tv_form_key, key)
                    helper.setText(R.id.tv_form_value, value)
                }
            }
            recyclerView.addItemDecoration(DefaultItemDecoration(getResources().getColor(R.color.mainNavline_e7)))
            recyclerView.setLayoutManager(LinearLayoutManager(this))
            recyclerView.setAdapter(mapQuickAdapter)
            toastShort(data.consumption_name)
        }
    }
}
