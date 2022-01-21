package anticordev.group.anticoruption.screen.main.oneId

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
import anticordev.group.anticoruption.R
import anticordev.group.anticoruption.base.startActivity
import anticordev.group.anticoruption.databinding.ActivityMainBinding
import anticordev.group.anticoruption.repository.SendRepository
import anticordev.group.anticoruption.screen.main.MainActivity
import anticordev.group.anticoruption.screen.viewmodels.SendViewModel
import anticordev.group.anticoruption.screen.viewmodels.SendViewModelProviderFactory
import anticordev.group.anticoruption.util.utils.Prefs
import es.dmoral.toasty.Toasty

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
                //Toast.makeText(applicationContext,getString(R.string.show_massage),Toast.LENGTH_SHORT).show()
                Toasty.success(applicationContext, getString(R.string.show_massage), Toast.LENGTH_SHORT).show()

                startActivity<MainActivity>()
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