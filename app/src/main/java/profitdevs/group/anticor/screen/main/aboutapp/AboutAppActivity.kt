package profitdevs.group.anticor.screen.main.aboutapp
import android.annotation.SuppressLint
import kotlinx.android.synthetic.main.activity_about_app.*
import profitdevs.group.anticor.base.BaseActivity
import profitdev.group.eantikor.base.startActivityToOpenUrlInBrowser
import profitdevs.group.anticor.R

class AboutAppActivity : BaseActivity() {
    override fun getLayout(): Int = R.layout.activity_about_app

    @SuppressLint("SetTextI18n")
    override fun initViews() {
       // tvVersion.text = "version: ${BuildConfig.VERSION_NAME}"

        imgBack.setOnClickListener { finish() }

        lyDeveloper.setOnClickListener {
            startActivityToOpenUrlInBrowser("")
        }
    }

    override fun loadData() {

    }

    override fun initData() {

    }

    override fun updateData() {

    }

}
