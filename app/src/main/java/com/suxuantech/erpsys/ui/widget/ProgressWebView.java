package com.suxuantech.erpsys.ui.widget;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebStorage;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.suxuantech.erpsys.App;
import com.suxuantech.erpsys.R;

public class ProgressWebView extends WebView {
    private ProgressBar progressbar;

    public interface LoadingUrlMonitor {
        void loadNewUrl(String url);

        void loadTitle(String title);
    }

    LoadingUrlMonitor loadingUrl;

    public void setLoadingUrlMonitor(LoadingUrlMonitor loadingUrl) {
        this.loadingUrl = loadingUrl;
    }

    public void setProgressbar(ProgressBar progressbar) {
        removeViewInLayout(this.progressbar);
        progressbar.getLayoutParams().height = 10;
        Drawable drawable = getResources().getDrawable(R.drawable.progress_bar_states);
        progressbar.setProgressDrawable(drawable);
        this.progressbar = progressbar;
    }

    public ProgressWebView(Context context, AttributeSet attrs) {
        super(context, attrs);
        progressbar = new ProgressBar(context, null, android.R.attr.progressBarStyleHorizontal);
        progressbar.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT, 10, 0, 0));
        Drawable drawable = context.getResources().getDrawable(R.drawable.progress_bar_states);
        progressbar.setProgressDrawable(drawable);
        addView(progressbar);
        setWebViewClient(new MyWebViewClient());
        setWebChromeClient(new MyWebChromeClient());
        //是否可以缩放
        getSettings().setSupportZoom(true);
        getSettings().setJavaScriptEnabled(true);
        getSettings().setBuiltInZoomControls(true);
        getSettings().setCacheMode(WebSettings.LOAD_DEFAULT);
        getSettings().setSupportMultipleWindows(true);
        getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        getSettings().setDomStorageEnabled(true);
        //启用地理定位
        getSettings().setGeolocationEnabled(true);
        getSettings().setPluginState(WebSettings.PluginState.ON);
        getSettings().setBlockNetworkImage(false);
        getSettings().setBlockNetworkLoads(false);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getSettings().setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }




    }

    public class MyWebChromeClient extends WebChromeClient {
        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            if (newProgress == 100) {
                progressbar.setVisibility(GONE);
            } else {
                if (progressbar.getVisibility() == GONE) {
                    progressbar.setVisibility(VISIBLE);
                }
                progressbar.setProgress(newProgress);
            }
            super.onProgressChanged(view, newProgress);
        }

        @Override
        public void onExceededDatabaseQuota(String url, String databaseIdentifier, long quota, long estimatedDatabaseSize, long totalQuota, WebStorage.QuotaUpdater quotaUpdater) {
            super.onExceededDatabaseQuota(url, databaseIdentifier, quota, estimatedDatabaseSize, totalQuota, quotaUpdater);
        }

        @Override
        public void onReachedMaxAppCacheSize(long requiredStorage, long quota, WebStorage.QuotaUpdater quotaUpdater) {
            super.onReachedMaxAppCacheSize(requiredStorage, quota, quotaUpdater);
        }

        @Override
        public boolean onCreateWindow(WebView view, boolean isDialog, boolean isUserGesture, Message resultMsg) {
            WebView mWebView = new WebView(view.getContext());
            WebView.WebViewTransport transport = (WebView.WebViewTransport) resultMsg.obj;
            transport.setWebView(mWebView);
            resultMsg.sendToTarget();
            return true;
        }

        @Override
        public void onReceivedTitle(WebView view, String title) {
            super.onReceivedTitle(view, title);
            if (loadingUrl != null) {
                loadingUrl.loadTitle(title);
            }
        }
    }

    //https://blog.csdn.net/lhkzx007/article/details/41527177
    public class MyWebViewClient extends WebViewClient {
        @Nullable
        @Override
        public WebResourceResponse shouldInterceptRequest(WebView view, WebResourceRequest request) {
            return super.shouldInterceptRequest(view, request);
        }

        @Nullable
        @Override
        public WebResourceResponse shouldInterceptRequest(WebView view, String url) {

            return super.shouldInterceptRequest(view, url);
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            if (url == null) {
                return false;
            }
            if (url.startsWith("http://") || url.startsWith("https://")) {
                //处理http和https开头的url
                view.loadUrl(url);
                if (loadingUrl != null) {
                    loadingUrl.loadNewUrl(url);
                }
            } else {
                try {
//                    if (url.startsWith("weixin://") //微信
//                            || url.startsWith("alipays://") //支付宝
//                            || url.startsWith("mailto://") //邮件
//                            || url.startsWith("tel://")//电话
//                            || url.startsWith("dianping://")//大众点评
//                            || url.startsWith("baidumap://")//百度地图
//                            || url.startsWith("baiduhaokan://")//百度好看视频
//                        //其他自定义的scheme
//                            ) {
//                    }
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                    App.getApplication().getTopActivity().startActivity(intent);
                } catch (Exception e) { //防止crash (如果手机上没有安装处理某个scheme开头的url的APP, 会导致crash)
                    return true;//没有安装该app时，返回true，表示拦截自定义链接，但不跳转，避免弹出上面的错误页面
                }
            }
            return true;
        }

        @Override
        public void onReceivedSslError(WebView view, SslErrorHandler handler, android.net.http.SslError error) {
            handler.proceed();
        }

        @Override
        public boolean shouldOverrideKeyEvent(WebView view, KeyEvent event) {
            return super.shouldOverrideKeyEvent(view, event);
        }
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
//        LayoutParams lp = (LayoutParams) progressbar.getLayoutParams();
//        lp.x = l;
//        lp.y = t;
//        progressbar.setLayoutParams(lp);
        super.onScrollChanged(l, t, oldl, oldt);
    }
}
