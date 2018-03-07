package com.suxuantech.erpsys.ui.activity

import android.os.Bundle
import android.widget.TextView
import com.suxuantech.erpsys.R
import com.suxuantech.erpsys.ui.activity.base.ImmersedBaseActivity
import com.suxuantech.erpsys.ui.adapter.BaseRecyclerAdapter
import com.suxuantech.erpsys.ui.adapter.RecyclerHolder
import com.suxuantech.erpsys.ui.widget.DefaultItemDecoration
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView
import java.util.*

/**
 * 打卡
 */
class PunchCardActivity : ImmersedBaseActivity() {
    private var rvPunchTyep: SwipeMenuRecyclerView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_punch_card)
        showUserDefinedNav()
        title = "打卡"
        setUseDefinedNavRightText("考勤记录")
        rvPunchTyep= findViewById(R.id.rv_punch_type)
        val strings = ArrayList<String>()
        strings.add("正常出勤")
        strings.add("加班")
        strings.add("录用申请")
        strings.add("异常拍照签到/签退")
        var itemDecoration=DefaultItemDecoration(resources.getColor(R.color.line))
        itemDecoration.setJustLeftOffsetX(30)
        ( rvPunchTyep as  SwipeMenuRecyclerView ).addItemDecoration(itemDecoration)
        val value: BaseRecyclerAdapter<String> = object : BaseRecyclerAdapter<String>(rvPunchTyep, strings, R.layout.item_textview) {
            override fun convert(holder: RecyclerHolder, item: String, position: Int, isScrolling: Boolean) {
                val view = holder.getView<TextView>(R.id.tv_item)
                view.text = item
                var tick=resources.getDrawable(R.drawable.icon_tick)
                tick.setBounds(0, 0, 50, 50)
                view.setCompoundDrawables(null,null,tick,null)
                view.postInvalidate()
            }
        }
    }

}
