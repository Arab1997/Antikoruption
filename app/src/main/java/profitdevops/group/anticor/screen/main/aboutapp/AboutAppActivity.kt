package profitdevops.group.anticor.screen.main.aboutapp
import android.annotation.SuppressLint
import kotlinx.android.synthetic.main.activity_about_app.*
import profitdevops.group.anticor.base.BaseActivity
import profitdevops.group.anticor.base.startActivityToOpenUrlInBrowser
import profitdevops.group.anticor.R

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
