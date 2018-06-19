package com.suxuantech.erpsys.ui.activity

import android.os.Bundle
import android.view.KeyEvent
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.webkit.WebSettings
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import com.suxuantech.erpsys.R
import com.suxuantech.erpsys.ui.activity.base.TitleNavigationActivity
import com.suxuantech.erpsys.ui.widget.ProgressWebView
import com.suxuantech.erpsys.utils.ReflexUtils

class BaseWebActivity : TitleNavigationActivity() {
    protected var mWebView: ProgressWebView? = null
    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return super.onOptionsItemSelected(item)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        super.onCreateOptionsMenu(menu);
        menu?.add(Menu.NONE, 1, 0, "刷新")
                ?.setOnMenuItemClickListener { menuItem ->
                    mWebView?.reload()
                    true
                }
        return true
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base_web)
        setSwipeBackEnable(false)
        supportToolbar()
        toolbar.contentInsetStartWithNavigation = 0;
        toolbar.setNavigationOnClickListener {
            if (mWebView?.canGoBack()!!) {
                mWebView?.getSettings()?.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
                mWebView!!.goBack()
            } else {
                onBackPressed()
            }
        }

        toolbar.setLogo(R.drawable.icon_close_web)
        var logo = ReflexUtils.getValue(toolbar, "mLogoView") as ImageView
        logo.setOnClickListener(View.OnClickListener {
            onBackPressed()
        })
        lineView.visibility = View.GONE
        mWebView = findViewById(R.id.baseweb_webview)
        var progressBar = findViewById<ProgressBar>(R.id.progressBar)
        mWebView?.setLoadingUrlMonitor(object : ProgressWebView.LoadingUrlMonitor {
            override fun loadTitle(title: String?) {
                setTitle(title)
            }

            override fun loadNewUrl(url: String?) {
                setDomain(url!!)
            }
        });
        mWebView?.setProgressbar(progressBar)
        var webSettings = mWebView?.getSettings();
        webSettings?.setJavaScriptEnabled(true)
        webSettings?.setSupportZoom(true)
        webSettings?.setBuiltInZoomControls(true)
        webSettings?.setCacheMode(WebSettings.LOAD_NO_CACHE)
        webSettings?.setSupportMultipleWindows(true)
        webSettings?.setJavaScriptCanOpenWindowsAutomatically(true)
        initData();
    }

    /**
     * 设置域名
     */
    private fun setDomain(url: String) {
        setTitle(title)
        var domain = findViewById<TextView>(R.id.header)
        val url = java.net.URL(url)
        val host = url.host// 获取主机名
        var v = "网页来自" + host + "提供\n由素玄科技提供展示"
        domain.setText(v)
    }

    private fun initData() {
        val intent = intent
        val url = intent.getStringExtra("url")
        if (url != null) {
            mWebView?.loadUrl(url)
            setDomain(url)
        }
    }

    override fun onBackPressedSupport() {
        super.onBackPressedSupport()
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK && mWebView?.canGoBack()!!) {
            mWebView?.getSettings()?.setCacheMode(WebSettings.LOAD_NO_CACHE);
            mWebView!!.goBack()
            return true
        }
        return super.onKeyDown(keyCode, event)
    }


}
