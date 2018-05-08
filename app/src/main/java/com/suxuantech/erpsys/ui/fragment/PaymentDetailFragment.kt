package com.suxuantech.erpsys.ui.fragment


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.suxuantech.erpsys.R

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
        var v =view?.findViewById<TextView>(R.id.tvss)
        v ?.text=   arguments?.getString("mmp")
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
