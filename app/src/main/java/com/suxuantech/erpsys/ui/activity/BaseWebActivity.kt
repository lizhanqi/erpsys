package com.suxuantech.erpsys.ui.activity

import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.view.KeyEvent
import android.view.Menu
import android.view.View
import android.webkit.WebSettings
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import com.scwang.smartrefresh.header.MaterialHeader
import com.scwang.smartrefresh.layout.SmartRefreshLayout
import com.suxuantech.erpsys.R
import com.suxuantech.erpsys.ui.activity.base.TitleNavigationActivity
import com.suxuantech.erpsys.ui.widget.ProgressWebView
import com.suxuantech.erpsys.utils.ReflexUtils

class BaseWebActivity : TitleNavigationActivity() {
    protected var mWebView: ProgressWebView? = null
    protected var mSmartRefresh: SmartRefreshLayout? = null
    /**
     * 是否展示toolbar进行导航
     */
    protected var showToolbar = true;
    /**
     * 是否显示toolbar的一键关闭
     */
    protected var showOneClose = true;
    /**
     *导航是否有菜单的刷新
     */
    protected var showMoreMenu = false;

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        super.onCreateOptionsMenu(menu);
        if (showMoreMenu) {
            menu?.add(Menu.NONE, 1, 0, "刷新")
                    ?.setOnMenuItemClickListener { menuItem ->
                        mWebView?.reload()
                        true
                    }
        }
        return true
    }

    fun showTitleNavigation() {
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
        if (showOneClose) {
            toolbar.setLogo(R.drawable.icon_close_web)
            var logo = ReflexUtils.getValue(toolbar, "mLogoView") as ImageView
            logo.setOnClickListener(View.OnClickListener {
                onBackPressed()
            })
        }
        mWebView?.setLoadingUrlMonitor(object : ProgressWebView.LoadingUrlMonitor {
            override fun loadTitle(title: String?) {
                setTitle(title)
            }

            override fun loadNewUrl(url: String?) {
                setDomain(url!!)
            }
        });
        var progressBar = findViewById<ProgressBar>(R.id.progressBar)
        progressBar.visibility = View.VISIBLE
        mWebView?.setProgressbar(progressBar)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        showToolbar = intent.getBooleanExtra("showToolbar", true)
        showOneClose = intent.getBooleanExtra("showOneClose", true)
        showMoreMenu = intent.getBooleanExtra("showMoreMenu", true)
        setContentView(R.layout.activity_base_web)
        lineView.visibility = View.GONE
        mWebView = findViewById(R.id.baseweb_webview)
        mSmartRefresh = findViewById(R.id.refreshLayout)
        setSwipeBackEnable(false)
        if (showToolbar) {
            showTitleNavigation()
        } else {
            var domain = findViewById<TextView>(R.id.header)
            var swipeRefreshLayout = findViewById<SwipeRefreshLayout>(R.id.swipe_web)
            domain.visibility = View.GONE
            var mWebFreshView = MaterialHeader(this);
            mSmartRefresh?.isEnableRefresh = false
            mSmartRefresh?.isEnabled = false
            swipeRefreshLayout.setColorSchemeColors(resources.getColor(R.color.themeColor),
                    resources.getColor(R.color.gradual_change1),
                    resources.getColor(R.color.gradual_change2),
                    resources.getColor(R.color.gradual_change3))

            swipeRefreshLayout.setOnRefreshListener {
                mWebView?.reload();
                swipeRefreshLayout?.isRefreshing = false
            }

            //mWebFreshView.layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
            //   mWebFreshView?.setColorSchemeColors(R.color.themeColor, R.color.gradual_change1, R.color.gradual_change2, R.color.gradual_change3)
//            mSmartRefresh?.setRefreshHeader(mWebFreshView)
//            mSmartRefresh?.setOnRefreshListener(OnRefreshListener {
//                mWebView?.reload();
//                mSmartRefresh?.finishRefresh(5000)
//            })
        }

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
        domain?.setText(v)
    }

    private fun initData() {
        val intent = intent
        val url = intent.getStringExtra("url")

        if (url != null) {
            mWebView?.loadUrl(url)
            setDomain(url)
        }
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
