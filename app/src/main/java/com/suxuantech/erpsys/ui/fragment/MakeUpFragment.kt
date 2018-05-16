package com.suxuantech.erpsys.ui.fragment


import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.chad.library.adapter.base.BaseViewHolder
import com.scwang.smartrefresh.layout.SmartRefreshLayout
import com.scwang.smartrefresh.layout.listener.OnRefreshListener
import com.suxuantech.erpsys.App
import com.suxuantech.erpsys.R
import com.suxuantech.erpsys.entity.MakeUpEntity
import com.suxuantech.erpsys.entity.SearchOrderEntity
import com.suxuantech.erpsys.nohttp.Contact
import com.suxuantech.erpsys.nohttp.HttpListener
import com.suxuantech.erpsys.nohttp.JavaBeanRequest
import com.suxuantech.erpsys.ui.activity.MakeUpDetailsActivity
import com.suxuantech.erpsys.ui.adapter.QuickAdapter
import com.suxuantech.erpsys.utils.MyString
import com.suxuantech.erpsys.utils.StringUtils
import com.yanzhenjie.nohttp.RequestMethod
import com.yanzhenjie.nohttp.rest.Response

/**
 * 化妆
 */
class MakeUpFragment : BaseSupportFragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    var tvSumMoney: TextView? = null
    var tvPaidMoney: TextView? = null
    var tvDebtMoney: TextView? = null
    var rvMakeUp: RecyclerView? = null
    var smart_refresh: SmartRefreshLayout? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_make_up, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val string = arguments?.getString("orderId");
        tvSumMoney = view.findViewById<View>(R.id.tv_sum_money) as TextView
        tvPaidMoney = view.findViewById<View>(R.id.tv_paid_money) as TextView
        tvDebtMoney = view.findViewById<View>(R.id.tv_debt_money) as TextView
        rvMakeUp = view.findViewById<View>(R.id.rv_make_up) as RecyclerView
        smart_refresh = view.findViewById<View>(R.id.smrl_make_up) as SmartRefreshLayout
        smart_refresh?.autoRefresh()
        smart_refresh?.setOnRefreshListener(OnRefreshListener {
            if (!TextUtils.isEmpty(string)) {
                getData(string)
            }
        })
        var data = arguments?.getParcelable<SearchOrderEntity.DataBean>("data")
        if (data != null) {
            data.makeupnonpay;
            tvSumMoney?.setText("总计金额");
            tvSumMoney?.append(MyString("\n¥" + data.makeuptotal).setColor(resources.getColor(R.color.textColor)))
            tvPaidMoney?.setText("已付金额")
            tvPaidMoney?.append(MyString("\n¥" + data.makeuppay).setColor(resources.getColor(R.color.textColor)))
            tvDebtMoney?.setText("欠款金额\n¥" + data.makeupnonpay)

        }
//        if(!TextUtils.isEmpty(string )){
//            getData(string)
//        }

    }

    fun getData(string: String?) {
        var url = Contact.getFullUrl(Contact.MAKE_UP, Contact.TOKEN, string, App.getApplication().userInfor.shop_code)
        //请求实体
        val districtBeanJavaBeanRequest = JavaBeanRequest(url, RequestMethod.POST, MakeUpEntity::class.java)
        val searchByCustmor = object : HttpListener<MakeUpEntity> {
            override fun onSucceed(what: Int, response: Response<MakeUpEntity>) {
                smart_refresh?.finishRefresh()
                if (response.get().isOK) {
                    setAdapter(response.get().data)
                }
            }

            override fun onFailed(what: Int, response: Response<MakeUpEntity>) {
                smart_refresh?.finishRefresh()
            }
        }
        request(0, districtBeanJavaBeanRequest, searchByCustmor, false, false)
    }

    private fun setAdapter(data: List<MakeUpEntity.DataBean>) {
        var adaputer = object : QuickAdapter<MakeUpEntity.DataBean>(R.layout.item_makeup, data) {
            override fun convert(helper: BaseViewHolder?, item: MakeUpEntity.DataBean?) {
                var name = helper?.getView<View>(R.id.tv_store_name) as TextView
                var info1 = helper?.getView<View>(R.id.tv_make_up_info1) as TextView
                var info2 = helper?.getView<View>(R.id.tv_make_up_info2) as TextView
                var remark = helper?.getView<View>(R.id.tv_make_up_remarks) as TextView
                name.setText(item?.shop_name)
                var s1 = MyString("化妆单据:\u3000")
                var s2 = MyString("化妆日期:\u3000")
                var s3 = MyString("完成日期:\u3000")

                var s4 = MyString(StringUtils.safetyString(item?.voucher_name) + "\n").setColor(resources.getColor(R.color.myValue_33))
                var s5 = MyString(StringUtils.safetyString(item?.makeupdate) + "\n").setColor(resources.getColor(R.color.myValue_33))
                var s6 = MyString(StringUtils.safetyString(item?.completion_date)).setColor(resources.getColor(R.color.myValue_33))
                info1.setText(s1)
                info1.append(s4)
                info1.append(s2)
                info1.append(s5)
                info1.append(s3)
                info1.append(s6)
                info2.setText(MyString("¥" + item?.makeup_money + "\n" + StringUtils.safetyString(item?.sellman)).setColor(resources.getColor(R.color.myValue_33)))
                remark.setText("备注:")
                remark.append(MyString(StringUtils.safetyString(item?.makeup_remarks)).setColor(resources.getColor(R.color.myValue_33)))
            }
        }
        rvMakeUp?.layoutManager = LinearLayoutManager(context)
        rvMakeUp?.adapter = adaputer;
        adaputer.setOnItemClickListener { adapter, view, position ->
            var intent = Intent(context, MakeUpDetailsActivity::class.java);
            intent.putExtra("orderId", data.get(position).orderId)
            intent.putExtra("makeUpId", "" + data.get(position).id)
            startActivity(intent);
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment MakeUpFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
                MakeUpFragment().apply {
                    arguments = Bundle().apply {
                    }
                }
    }
}
