package com.suxuantech.erpsys.ui.fragment


import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.chad.library.adapter.base.BaseViewHolder
import com.suxuantech.erpsys.R
import com.suxuantech.erpsys.entity.PaymentDetailsEntity
import com.suxuantech.erpsys.ui.adapter.QuickAdapter
import com.suxuantech.erpsys.ui.widget.DefaultItemDecoration
import com.suxuantech.erpsys.utils.DateUtil
import com.suxuantech.erpsys.utils.StringUtils

/**
 * 付款明细
 */
class PaymentDetailFragment : BaseSupportFragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_payment_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var position = arguments?.getInt("position")
        var data = arguments?.getSerializable("data") as PaymentDetailsEntity.DataBean
        var paylist = view.findViewById<RecyclerView>(R.id.rv_payment);
        paylist.layoutManager= LinearLayoutManager(context)
        paylist.addItemDecoration(DefaultItemDecoration(resources.getColor(R.color.gray_f9),50,20))
        if (position == 0) {
            var ms = data.ms
            var  quickAdapter = object : QuickAdapter<PaymentDetailsEntity.DataBean.MsBean>(R.layout.item_payment, ms) {
                override fun convert(helper: BaseViewHolder?, item: PaymentDetailsEntity.DataBean.MsBean?) {
                    data2View(helper,item?.paymentdate,item?.fundname, item?.cashierman ,item?.paytype,item?.payment_money )
                }
            }
             paylist.adapter=quickAdapter
        } else if (position == 1) {
            var lf = data.lf
            var quickAdapter = object : QuickAdapter<PaymentDetailsEntity.DataBean.LfBean>(R.layout.item_payment, lf) {
                override fun convert(helper: BaseViewHolder?, item: PaymentDetailsEntity.DataBean.LfBean?) {
                    data2View(helper,item?.paymentdate,item?.fundname, item?.cashierman ,item?.paytype,item?.payment_money )
                }
            }
            paylist.adapter=quickAdapter
        } else if (position == 2) {
            var hz = data.hz
            var quickAdapter = object : QuickAdapter<PaymentDetailsEntity.DataBean.HzBean>(R.layout.item_payment, hz) {
                override fun convert(helper: BaseViewHolder?, item: PaymentDetailsEntity.DataBean.HzBean?) {
                    data2View(helper,item?.paymentdate,item?.fundname, item?.cashierman ,item?.paytype,item?.payment_money )
                }
            }
            paylist.adapter=quickAdapter
        }
    }

    private fun data2View(helper: BaseViewHolder?, paymentdate: String?, fundname: String?, cashierman: String?, paytype: String?, payment_money: String?) {
       var tvPaymentDateKey = helper?.getView<View>(R.id.tv_payment_date_key) as TextView
      var tvPaymentDateValue = helper?.getView<View>(R.id.tv_payment_date_value) as TextView
      var tvPayeeTypeKey = helper?.getView<View>(R.id.tv_payee_type_key) as TextView
      var tvPayeeTypeValue = helper?.getView<View>(R.id.tv_payee_type_value) as TextView
      var tvPaymentPayeeKey = helper?.getView<View>(R.id.tv_payment_payee_key) as TextView
      var tvPaymentPayeeValue = helper?.getView<View>(R.id.tv_payment_payee_value) as TextView
      var tvPaymentTypeKey = helper?.getView<View>(R.id.tv_payment_type_key) as TextView
      var tvPaymentTypeValue = helper?.getView<View>(R.id.tv_payment_type_value) as TextView
      var tvPaymentMoneyKey = helper?.getView<View>(R.id.tv_payment_money_key) as TextView
      var tvPaymentMoneyValue = helper?.getView<View>(R.id.tv_payment_money_value) as TextView
      tvPaymentMoneyValue.setText("¥:"+StringUtils.moneyFormat(payment_money))
      tvPaymentDateValue.setText(DateUtil.String2String(paymentdate,DateUtil.DatePattern.JUST_DAY_NUMBER,DateUtil.DatePattern.YEAR_MONTHE_DAY_TEXT))
      tvPayeeTypeValue.text = fundname
      tvPaymentPayeeValue.text = cashierman
        tvPaymentTypeValue.text = paytype
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
                PaymentDetailFragment().apply {
                    arguments = Bundle().apply {
                    }
                }
    }
}
