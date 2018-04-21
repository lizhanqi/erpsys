package com.suxuantech.erpsys.ui.activity

import android.os.Bundle
import android.widget.LinearLayout
import com.suxuantech.erpsys.R
import com.suxuantech.erpsys.ui.activity.base.ImmersedBaseActivity

/**
 * 进店登记
 */
class RegisterIntoShopActivity : ImmersedBaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_into_shop)
        idSetOnClick<LinearLayout>(R.id.ll_not_in_shop)
    }
}
