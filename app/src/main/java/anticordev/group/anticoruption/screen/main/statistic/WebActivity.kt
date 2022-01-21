package anticordev.group.anticoruption.screen.main.statistic

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import anticordev.group.anticoruption.R
import kotlinx.android.synthetic.main.activity_web.*

class WebActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web)
        webView.settings.javaScriptEnabled = true
        webView.loadUrl("file:///android_asset/index.html")

    }
}