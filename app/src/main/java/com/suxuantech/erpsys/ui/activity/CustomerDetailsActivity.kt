package com.suxuantech.erpsys.ui.activity

import android.os.Bundle
import com.suxuantech.erpsys.R
import com.suxuantech.erpsys.ui.activity.base.ImmersedBaseActivity

/**
 * 客资详情
 */
class CustomerDetailsActivity : ImmersedBaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_customer_details)
        showUserDefinedNav()
        setTitle("客资详情")
    }
}
