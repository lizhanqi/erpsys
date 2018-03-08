package com.suxuantech.erpsys.ui.activity

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.view.ViewPager
import android.support.v4.view.ViewPager.OnPageChangeListener
import android.view.View
import com.suxuantech.erpsys.R
import com.suxuantech.erpsys.ui.activity.base.ImmersedBaseActivity
import com.suxuantech.erpsys.ui.adapter.DefaultFragmentAdapter
import com.suxuantech.erpsys.ui.fragment.BorrowFragment
import java.util.*
import kotlin.collections.ArrayList

/**
 * 我的借阅
 */
class MyBorrowActivity : ImmersedBaseActivity() ,  OnPageChangeListener{
    override fun onPageScrollStateChanged(state: Int) {
    }
    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
        fragments!!.get(position).setText("我是第"+position+"的")
    }
    override fun onPageSelected(position: Int) {
    }
    private var thBorrow: TabLayout? = null
    private var viewPager: ViewPager? = null
    private var fragments: ArrayList<BorrowFragment>? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_tab_control)
        showUserDefinedNav()
        title = "我的借阅"

        var view :View ?= findViewById(R.id.view_tl)
        view?.visibility=View.INVISIBLE
        lineView.visibility=View.INVISIBLE
        fragments = ArrayList()
        fragments!!.add(BorrowFragment())
        fragments!!.add(BorrowFragment())
        val stringArray = resources.getStringArray(R.array.book_status)
        val title=   java.util.ArrayList(Arrays.asList(*stringArray))
        thBorrow= findViewById(R.id.tablayout)
        thBorrow?.minimumWidth= resources.getDimension(R.dimen.px360).toInt()
        viewPager= findViewById(R.id.vp)
         var adapter=   DefaultFragmentAdapter(supportFragmentManager, title,  DefaultFragmentAdapter.FragmentShow {
             positon -> fragments!!.get(positon)
        })
        viewPager?.adapter = adapter
        viewPager?.offscreenPageLimit = 1
            val setupWithViewPager = thBorrow?.setupWithViewPager(viewPager)
            viewPager!!.addOnPageChangeListener(this)
    }
}
