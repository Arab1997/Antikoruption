package anticordev.group.anticoruption.screen.main.aboutapp
import android.annotation.SuppressLint
import kotlinx.android.synthetic.main.activity_about_app.*
import anticordev.group.anticoruption.base.BaseActivity
import anticordev.group.anticoruption.base.startActivityToOpenUrlInBrowser
import anticordev.group.anticoruption.R

class AboutAppActivity : BaseActivity() {
    override fun getLayout(): Int = R.layout.activity_about_app

    @SuppressLint("SetTextI18n")
    override fun initViews() {
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
