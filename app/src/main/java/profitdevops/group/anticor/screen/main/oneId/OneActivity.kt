package profitdevops.group.anticor.screen.main.oneId

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.net.http.SslError
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.webkit.*
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.orhanobut.hawk.Hawk
import kotlinx.android.synthetic.main.activity_one.*
import profitdevops.group.anticor.R
import profitdevops.group.anticor.databinding.ActivityMainBinding
import profitdevops.group.anticor.repository.SendRepository
import profitdevops.group.anticor.screen.viewmodels.SendViewModel
import profitdevops.group.anticor.screen.viewmodels.SendViewModelProviderFactory
import profitdevops.group.anticor.util.utils.Prefs

class OneActivity : AppCompatActivity() {
    private lateinit var sendViewModel: SendViewModel
   // private lateinit var mainViewModel: MainViewModel
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

        val sendRepository = SendRepository()
        val viewModelProviderFactory = SendViewModelProviderFactory(sendRepository)
        sendViewModel = ViewModelProvider(this, viewModelProviderFactory)[SendViewModel::class.java]

        setContentView(R.layout.activity_one)
        Hawk.init(this).build()
        val url = "http://sso.egov.uz:8443/sso/oauth/Authorization.do?response_type=$responceType&client_id=$clientId&redirect_uri=$redirect_url&scope=$scope&state=$state"

        Prefs.setToken(state)
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

                sendViewModel.getToken(code, state)
                Prefs.getToken()
                Toast.makeText(applicationContext,"Ваши данные загружены, вы можете вернуться",Toast.LENGTH_SHORT).show()

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