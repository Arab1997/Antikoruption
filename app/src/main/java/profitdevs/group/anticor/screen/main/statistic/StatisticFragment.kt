package profitdevs.group.anticor.screen.main.statistic

import android.annotation.SuppressLint
import android.net.http.SslError
import android.os.Bundle
import android.os.StrictMode
import android.view.View
import android.webkit.SslErrorHandler
import android.webkit.WebView
import android.webkit.WebViewClient
import kotlinx.android.synthetic.main.fragment_statistic.*
import profitdev.group.eantikor.base.BaseFragment
import profitdevs.group.anticor.R

class StatisticFragment : BaseFragment() {
    override fun getLayout(): Int = R.layout.fragment_statistic

    @SuppressLint("SetJavaScriptEnabled")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)
        webview.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(
                view: WebView,
                url: String
            ): Boolean {
                view.loadUrl(url)
                return true
            }
        }
        initWebView()
    }

    override fun setupViews() {
        initWebView()
    }

    override fun loadData() {
        initWebView()
    }

    override fun setData() {
        initWebView()
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun initWebView() {
        webview.loadUrl("https://eanticor.uz/ru/")
        // webview.loadUrl("https://www.google.ru/")
        webview.settings.javaScriptEnabled = true
        webview.settings.loadWithOverviewMode = true
        webview.settings.useWideViewPort = true
        webview.settings.domStorageEnabled = true
        webview.settings.allowContentAccess = true
        webview.webViewClient = object : WebViewClient() {
            override
            fun onReceivedSslError(view: WebView?, handler: SslErrorHandler?, error: SslError?) {
                handler?.proceed()
            }
        }
    }
}
