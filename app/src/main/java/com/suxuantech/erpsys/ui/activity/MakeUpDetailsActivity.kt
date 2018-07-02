package com.suxuantech.erpsys.ui.activity

import android.os.Bundle
import android.view.View
import android.widget.TextView
import com.scwang.smartrefresh.layout.SmartRefreshLayout
import com.suxuantech.erpsys.App
import com.suxuantech.erpsys.R
import com.suxuantech.erpsys.entity.MakeUpDetailsEntity
import com.suxuantech.erpsys.nohttp.Contact
import com.suxuantech.erpsys.nohttp.HttpListener
import com.suxuantech.erpsys.nohttp.JavaBeanRequest
import com.suxuantech.erpsys.ui.activity.base.TitleNavigationActivity
import com.suxuantech.erpsys.ui.widget.ErrorView
import com.suxuantech.erpsys.utils.MyString
import com.yanzhenjie.nohttp.RequestMethod
import com.yanzhenjie.nohttp.rest.Response

/**
 * 化妆资料详情
 */
class MakeUpDetailsActivity : TitleNavigationActivity() {
    var errorView :ErrorView?=null;
    var smart :SmartRefreshLayout?=null;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        errorView=ErrorView(this)
        setContentView(R.layout.activity_make_up_details)
        supportToolbar()
        setCenterTitle("化妆资料详情")
        getData()

        smart= findViewById<SmartRefreshLayout>(R.id.sm_refresh)
        smart?.setEnableLoadMore(false)
        smart?.autoRefresh()
        smart?.setOnRefreshListener {
            errorView?.loading()
            getData()  }
        errorView?.setOnClickListener(View.OnClickListener {
            smart?.autoRefresh()
        })
    }
    fun getData(){
        var orderId=""
        var makeUpId="";
        if(intent.hasExtra("orderId")){
            orderId= intent.getStringExtra("orderId")
        }
        if(intent.hasExtra("makeUpId")){
            makeUpId= intent.getStringExtra("makeUpId")
        }
        var url = Contact.getFullUrl(Contact.MAKE_UP_DATEILES,Contact.TOKEN,orderId,makeUpId, App.getApplication().userInfor.shop_code)
        //请求实体
        val districtBeanJavaBeanRequest = JavaBeanRequest(url, RequestMethod.POST, MakeUpDetailsEntity::class.java)
        val searchByCustmor = object : HttpListener<MakeUpDetailsEntity> {
            override fun onSucceed(what: Int, response: Response<MakeUpDetailsEntity>) {
                smart?.finishRefresh();
                if( response.get().isOK){
                    smart?.setRefreshContent( findViewById<View>(R.id.ll_content))
                    val data = response.get().data;
                    var vInfo= findViewById<TextView>(R.id.tv_make_up_info)
                    var vNumbeIinfo= findViewById<TextView>(R.id.tv_make_up_number_info)
                    vInfo.setText("化妆项目:"+ data?.get(0)?.makeupItems)
                    vInfo.append("\n化妆人员:"+ data?.get(0)?.makeupman)
                    vInfo.append("\n销售人员:"+ data?.get(0)?.sellman)
                    vNumbeIinfo.setText("¥:"+ data?.get(0)?.makeup_price)
                    vNumbeIinfo.append("\nx:"+ data?.get(0)?.amount)
                    vNumbeIinfo.append("\n合计:" )
                    vNumbeIinfo.append(MyString("¥"+data?.get(0)?.makeup_total))
                }else{
                    errorView?.reset()?.let { smart?.setRefreshContent(it) }
                }
            }

            override fun onFailed(what: Int, response: Response<MakeUpDetailsEntity>) {
                smart?.finishRefresh(false);
                errorView?.reset()?.let { smart?.setRefreshContent(it) }
            }
        }
        request(0, districtBeanJavaBeanRequest, searchByCustmor, false, false)
    }
}
