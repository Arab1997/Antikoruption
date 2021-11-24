package profitdevs.group.anticor.screen.main.oneId

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.net.http.SslError
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.webkit.*
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.activity_one.*
import profitdevs.group.anticor.screen.main.MainActivity
import profitdevs.group.anticor.R
import profitdevs.group.anticor.databinding.ActivityMainBinding
import profitdevs.group.anticor.util.utils.Prefs
import profitdevs.group.anticor.util.utils.Status

class OneActivity : AppCompatActivity() {
    private val mainViewModel: MainViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding
   // var redirect_url = "http://sso.egov.uz"
    var redirect_url = "https://anticorruption.uz/uz"
    var responceType = "one_code"
    var clientId = "anticorruption.uz"
    var client_secret = "0wOQhto6c0bDKLrl28i96w=="
    var scope = "anticorruption.uz"
    var state = "0wOQhto6c0bDKLrl28i96w=="
    private val TAG = "Onectivity"
// url https://id.egov.uz/
    private lateinit var prefs: SharedPreferences
    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       // setContentView(R.layout.activity_one)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setContentView(R.layout.activity_one)
        //prefs = PreferenceHelper.defaultPrefs(this)
        // val url = "http://sso.egov.uz?response_type=$responceType&client_id=$clientId&redirect_uri=$redirect_url&scope=$scope&state=$state"
          //  val url = "http://sso.egov.uz:8443/sso/oauth/Authorization.do?response_type=$responceType&client_id=$clientId&redirect_uri=$redirect_url&scope=$scope&state=$state"
             val url = "http://sso.egov.uz:8443/sso/oauth/Authorization.do?response_type=$responceType&client_id=$clientId&redirect_uri=$redirect_url&scope=$scope&state=$state"
        //prefs.toString()
       //prefs.all.getValue(url)
        Prefs.setToken(url)
        webView.webViewClient = MyWebViewClient(binding.root.context)
        webView.loadUrl(url)
        webView.settings.javaScriptEnabled = true
        webView.settings.domStorageEnabled = true

        imgBack_btn.setOnClickListener {
            finish()
        }
    }
    override fun onBackPressed() {
        finish()
    }

    inner class MyWebViewClient internal constructor(private val context: Context) :
        WebViewClient() {
        @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
        override fun shouldOverrideUrlLoading(
            view: WebView?,
            request: WebResourceRequest?
        ): Boolean {
            val url: String = request?.url.toString()
            Log.d(TAG, "shouldOverrideUrlLoading: ${url}")
            if (url.contains("${redirect_url}?code=")) {
                val index1 = url.indexOf("code=")
                val index2 = url.indexOf("&state")
                val code = url.substring(index1 + 5, index2)

                mainViewModel.getCode(code).observe(this@OneActivity, {
                    if (it.status == Status.SUCCESS) {
                        Log.d(TAG, "shouldOverrideUrlLoading: ${it.data}")
                        val intent = Intent(this@OneActivity, MainActivity::class.java)
                        startActivity(intent)
                    }
                })
            } else {
                view?.loadUrl(url)
            }
            return true
        }

        override fun onReceivedSslError(
                view: WebView?,
                handler: SslErrorHandler,
                error: SslError?
        ) {
            handler.proceed() // Ignore SSL certificate errors
        }
    }
}