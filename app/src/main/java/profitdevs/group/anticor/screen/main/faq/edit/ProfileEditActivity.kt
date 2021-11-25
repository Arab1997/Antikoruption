package profitdevs.group.anticor.screen.main.faq.edit

import kotlinx.android.synthetic.main.activity_profile_edit.*
import profitdevs.group.anticor.base.BaseActivity
import profitdevs.group.anticor.R

class ProfileEditActivity : BaseActivity() {
    override fun getLayout(): Int = R.layout.activity_profile_edit
    override fun initViews() {
        imgBackk.setOnClickListener {
            finish()
        }
    }
    override fun loadData() {
        initData()
    }
    override fun initData() {
    }
    override fun updateData() {
    }
}
