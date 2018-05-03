package com.suxuantech.erpsys.ui.activity

import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import com.suxuantech.erpsys.R
import com.suxuantech.erpsys.ui.activity.base.TitleNavigationActivity
import com.suxuantech.erpsys.ui.fragment.HomeDataFragement

class TodayCustomerActivity : TitleNavigationActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_today_customer)
        supportToolbar()
        setTitle(intent.getStringExtra("title"))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setBackgroundDrawable(ColorDrawable(resources.getColor(R.color.gray_f9)))
        var fagr=  supportFragmentManager .findFragmentById(R.id.fdata) as HomeDataFragement
        val bundle = Bundle()
        bundle.putString("title",intent.getStringExtra("title"))
        fagr.onNewBundle(bundle)
    }

}
