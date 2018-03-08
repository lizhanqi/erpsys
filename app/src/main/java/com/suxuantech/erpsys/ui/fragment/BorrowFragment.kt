package com.suxuantech.erpsys.ui.fragment


import `in`.srain.cube.views.ptr.PtrClassicFrameLayout
import `in`.srain.cube.views.ptr.PtrDefaultHandler
import `in`.srain.cube.views.ptr.PtrFrameLayout
import `in`.srain.cube.views.ptr.PtrHandler
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.suxuantech.erpsys.R
import com.suxuantech.erpsys.ui.adapter.BaseRecyclerAdapter
import com.suxuantech.erpsys.ui.adapter.RecyclerHolder
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView
import me.yokeyword.fragmentation.SupportFragment


/**
 * A simple [Fragment] subclass.
 */
class BorrowFragment : SupportFragment() {
    private var tv: TextView? = null
    var v :View ?=null
    var re :SwipeMenuRecyclerView?=null
    var mRotateHeaderGridViewFrame: PtrClassicFrameLayout? = null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
//          tv = TextView(activity)
//        tv!!.setText(R.string.hello_blank_fragment)
//        return tv
         v = View.inflate(activity,R.layout.refresh_and_recyclerview,null)
        re=  (  v as View) .findViewById(R.id.recycler_view)
        mRotateHeaderGridViewFrame=  (  v as View) .findViewById(R.id.rotate_header_grid_view_frame)
        initFreshHead()
        return v
    }

    private fun initFreshHead() {
        mRotateHeaderGridViewFrame?.setRatioOfHeaderHeightToRefresh(1.2f)
        mRotateHeaderGridViewFrame?.setDurationToClose(200)
        mRotateHeaderGridViewFrame?.setDurationToCloseHeader(1000)
        // default is false
        mRotateHeaderGridViewFrame?.isPullToRefresh = false
        mRotateHeaderGridViewFrame?.header?.setBackgroundColor(resources.getColor(R.color.themeColor))
        val childCount = mRotateHeaderGridViewFrame!!.header.childCount
        // default is true
        mRotateHeaderGridViewFrame?.isKeepHeaderWhenRefresh = true
        //刷新完成    这么用才有效(不设置这个会吃掉子view)
        mRotateHeaderGridViewFrame?.setPtrHandler(object : PtrHandler {
            override fun onRefreshBegin(frame: PtrFrameLayout) {
                mRotateHeaderGridViewFrame?.postDelayed(Runnable { mRotateHeaderGridViewFrame?.refreshComplete() }, 3000)
            }

            override fun checkCanDoRefresh(frame: PtrFrameLayout, content: View, header: View): Boolean {
                return PtrDefaultHandler.checkContentCanBePulledDown(frame, content, header)
            }
        })
    }

    fun setText(  text: String){
      var vs = ArrayList<String>()
        for (x in 1..10 step 1) {
            vs.add(text+x)
        }
       object : BaseRecyclerAdapter<String>(re, vs, R.layout.item_borrow) {
            override fun convert(holder: RecyclerHolder, item: String, position: Int, isScrolling: Boolean) {
               // val imageView = holder.getView<ImageView>(R.id.tv_item) as  TextView
                //imageView.setText(item)
            }
        }
    }

}
