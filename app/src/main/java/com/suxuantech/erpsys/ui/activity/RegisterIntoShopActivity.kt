package com.suxuantech.erpsys.ui.activity

import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import com.suxuantech.erpsys.R
import com.suxuantech.erpsys.ui.activity.base.ImmersedBaseActivity

/**
 * 进店登记
 */

class RegisterIntoShopActivity : ImmersedBaseActivity() {
    var llNewCustomer: LinearLayout? = null
    var llNotIntoShop: LinearLayout? = null
    var llNotInShop: LinearLayout? = null
    var llMakeBargain: LinearLayout? = null
    var llRunAway: LinearLayout? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_into_shop)
        // useButterKnife()
        setSupportToolbar()
        toolbar.setBackgroundColor(resources.getColor(R.color.gray_f9))
        getSupportActionBar()!!.setDisplayHomeAsUpEnabled(true);
        setTitle("进店登记")

        val idSetOnClick = idSetOnClick<LinearLayout>(R.id.ll_not_in_shop)

        llNewCustomer = idSetOnClick<LinearLayout>(R.id.ll_new_customer)
        llNotIntoShop = idSetOnClick<LinearLayout>(R.id.ll_not_into_shop) as LinearLayout
        llNotInShop = idSetOnClick<LinearLayout>(R.id.ll_not_in_shop) as LinearLayout
        llMakeBargain = idSetOnClick<LinearLayout>(R.id.ll_make_bargain) as LinearLayout
        llRunAway = idSetOnClick<LinearLayout>(R.id.ll_run_away) as LinearLayout
    }

    override fun widgetClick(v: View?) {
        super.widgetClick(v)
        var bd = Bundle();
        when (v!!.id) {
            R.id.ll_new_customer -> {
                bd.putString("title", "新进客资");
            }
            R.id.ll_not_into_shop -> {
                bd.putString("title", "未进店");
            }
            R.id.ll_not_in_shop -> {
                bd.putString("title", "进店未成"); }

            R.id.ll_make_bargain -> {
                bd.putString("title", "已成交");

            }
            R.id.ll_run_away -> {
                bd.putString("title", "流失客资"); }
        }
        startActivity(RegisterIntoShopSearchActivity::class.java, bd)
    }
}
