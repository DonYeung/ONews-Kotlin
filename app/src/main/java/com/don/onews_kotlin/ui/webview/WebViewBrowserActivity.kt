package com.don.onews_kotlin.ui.webview

import android.content.Context
import android.content.Intent
import android.os.Build
import android.support.v7.widget.Toolbar
import android.webkit.WebChromeClient
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.LinearLayout

import com.xiaochao.lcrapiddeveloplibrary.viewtype.ProgressActivity
import com.xiaochao.lcrapiddeveloplibrary.widget.SpringView

import com.don.onews_kotlin.R
import com.don.onews_kotlin.app.AppConstant
import com.don.onews_kotlin.base.BaseActivity
import com.don.onews_kotlin.base.BaseModel
import com.don.onews_kotlin.base.BasePresenter

/**
 * Created by drcom on 2017/3/22.
 */

class WebViewBrowserActivity<T : BasePresenter<*, *>, E : BaseModel> : BaseActivity<T, E>() {

    private var webView: WebView? = null
    private var springview: SpringView? = null
    private var progress: ProgressActivity? = null
    private var toolbar: Toolbar? = null
    private var webViewLayout: LinearLayout? = null

    override fun getLayoutId(): Int {
        return R.layout.act_web_browser
    }

    override fun initPresenter() {

    }

    override fun initView() {
        webView = findViewById(R.id.web_view) as WebView
        springview = findViewById(R.id.springview) as SpringView
        progress = findViewById(R.id.progress) as ProgressActivity
        toolbar = findViewById(R.id.toolbar) as Toolbar
        webViewLayout = findViewById(R.id.webViewLayout) as LinearLayout

        webView = WebView(mContext)
        webViewLayout?.addView(webView)
        setWebViewSettings()
        setWebView()
        toolbar?.setNavigationOnClickListener {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                finishAfterTransition()
            } else {
                finish()
            }
        }
    }

    private fun setWebViewSettings() {
        val webSettings = webView?.settings

        webSettings?.useWideViewPort = true //设置此属性，可任意比例缩放
        webSettings?.loadWithOverviewMode = true // 适配缩放至屏幕的大小
        webSettings?.javaScriptEnabled = true //支持js
        webSettings?.setSupportZoom(true) //支持缩放
        webSettings?.setAppCacheEnabled(true)//开启 Application Caches 功能
        webSettings?.cacheMode = WebSettings.LOAD_CACHE_ELSE_NETWORK//设置 缓存模式
        webSettings?.blockNetworkImage = true//关闭加载网络图片，在一开始加载的时候可以设置为true，当加载完网页的时候再设置为false
        //        webSettings.setBuiltInZoomControls(true); // 放大和缩小的按钮，容易引发异常 http://blog.csdn.net/dreamer0924/article/details/34082687
    }

    private fun setWebView() {
        webView?.loadUrl(getIntent().getStringExtra(AppConstant.NEWS_LINK))
        webView?.setWebViewClient(object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView, url: String?): Boolean {
                if (url != null) view.loadUrl(url)
                //需要设置在当前WebView中显示网页，才不会跳到默认的浏览器进行显示
                return true
            }
        })
        webView?.setWebChromeClient(object : WebChromeClient() {
            override fun onProgressChanged(view: WebView, newProgress: Int) {
                super.onProgressChanged(view, newProgress)
                if (newProgress == 100) {
                    //设置页面加载完 progress隐藏
                    try {
                        progress?.showContent()
                    } catch (e: Exception) {

                    }

                } else {
                    try {
                        //设置页面为加载中..
                        progress?.showLoading()
                    } catch (e: Exception) {

                    }

                }
            }
        })
    }

    override fun onPause() {
        super.onPause()
        if (webView != null) {
            webView?.onPause()
        }
    }

    override fun onResume() {
        super.onResume()
        if (webView != null) {
            webView?.onResume()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        if (webView != null) {
            webView?.clearCache(true) //清空缓存
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                if (webViewLayout != null) {
                    webViewLayout?.removeView(webView)
                }
                webView?.removeAllViews()
                webView?.destroy()
            } else {
                webView?.removeAllViews()
                webView?.destroy()
                if (webViewLayout != null) {
                    webViewLayout?.removeView(webView)
                }
            }
            webView = null
        }

    }

    companion object {

        fun startAction(context: Context, link: String, title: String) {
            val intent = Intent(context, WebViewBrowserActivity::class.java)
            intent.putExtra(AppConstant.NEWS_LINK, link)
            intent.putExtra(AppConstant.NEWS_TITLE, title)
            context.startActivity(intent)
        }
    }


}
