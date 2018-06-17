package com.suxuantech.erpsys.ui.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.webkit.WebSettings
import com.suxuantech.erpsys.ui.activity.base.ImmersionActivity
import com.suxuantech.erpsys.R
import com.suxuantech.erpsys.ui.widget.ProgressWebView

class BaseWebActivity : ImmersionActivity() {
    protected var mWebView: ProgressWebView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base_web)
        mWebView = findViewById(R.id.baseweb_webview)
        var webSettings = mWebView?.getSettings();
        webSettings?.setJavaScriptEnabled(true)
        webSettings?.setSupportZoom(true)
        webSettings?.setBuiltInZoomControls(true)
        webSettings?.setCacheMode(WebSettings.LOAD_NO_CACHE)
        webSettings?.setSupportMultipleWindows(true)
        webSettings?.setJavaScriptCanOpenWindowsAutomatically(true)
        initData();
    }

    private fun initData() {
        val intent = intent

        val url = intent.getStringExtra("url")
        if (url != null) {
            mWebView?.loadUrl(url)
        }
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK && mWebView?.canGoBack()!!) {
            mWebView!!.goBack()
            return true
        }
        return super.onKeyDown(keyCode, event)
    }

}
