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
import androidx.lifecycle.ViewModelProvider
import com.orhanobut.hawk.Hawk
import kotlinx.android.synthetic.main.activity_one.*
import profitdevs.group.anticor.screen.main.MainActivity
import profitdevs.group.anticor.R
import profitdevs.group.anticor.databinding.ActivityMainBinding
import profitdevs.group.anticor.repository.SendRepository
import profitdevs.group.anticor.screen.viewmodels.MainViewModelProviderFactory
import profitdevs.group.anticor.screen.viewmodels.SendViewModel
import profitdevs.group.anticor.screen.viewmodels.SendViewModelProviderFactory
import profitdevs.group.anticor.util.utils.Prefs
import profitdevs.group.anticor.util.utils.Status

class OneActivity : AppCompatActivity() {
    private lateinit var mainViewModel: MainViewModel
    private lateinit var binding: ActivityMainBinding
    var redirect_url = "https://eanticor.uz/uz/api/login-one/"
    var responceType = "one_code"
    var clientId = "anticorruption.uz"
    var client_secret = "0wOQhto6c0bDKLrl28i96w=="
    var scope = "anticorruption.uz"
    var state = "0wOQhto6c0bDKLrl28i96w=="

    private val TAG = "Onectivity"
    private lateinit var prefs: SharedPreferences
    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
//        setContentView(binding.root)



        val sendRepository = SendRepository()

        val viewModelProviderFactory = MainViewModelProviderFactory(sendRepository)
        mainViewModel = ViewModelProvider(this, viewModelProviderFactory)[MainViewModel::class.java]

        setContentView(R.layout.activity_one)
        Hawk.init(this).build()
        val url = "http://sso.egov.uz:8443/sso/oauth/Authorization.do?response_type=$responceType&client_id=$clientId&redirect_uri=$redirect_url&scope=$scope&state=$state"

        Prefs.setToken(url)
        Prefs.getToken()
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
                val state = url.substring(index2 + 7)

                mainViewModel.getToken(code, state)

//                mainViewModel.getCode(code, state).observe(this@OneActivity, {
//                    if (it.status == Status.SUCCESS) {
//                        Log.d(TAG, "shouldOverrideUrlLoading: ${it.data}")
//                        val intent = Intent(this@OneActivity, MainActivity::class.java)
//                        startActivity(intent)
//                    }
//                })
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
    override fun onDestroy() {
        super.onDestroy()
        Hawk.destroy()
    }
}