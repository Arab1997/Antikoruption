package profitdevs.group.anticor.screen.main.asosiy

import kotlinx.android.synthetic.main.activity_asosiy.*
import profitdevs.group.anticor.base.BaseActivity
import profitdevs.group.anticor.R

class AsosiyActivity : BaseActivity() {
    override fun getLayout(): Int = R.layout.activity_asosiy

    override fun initViews() {
        imgBackk.setOnClickListener { finish() }

    }

    override fun loadData() {
    }

    override fun initData() {
    }

    override fun updateData() {
    }
}