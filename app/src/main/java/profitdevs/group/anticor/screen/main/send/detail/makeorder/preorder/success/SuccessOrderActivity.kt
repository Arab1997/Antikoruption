package profitdevs.group.anticor.screen.main.send.detail.makeorder.preorder.success

import kotlinx.android.synthetic.main.activity_success_order.*
import profitdevs.group.anticor.base.BaseActivity
import profitdevs.group.anticor.base.startActivityToOpenUrlInBrowser
import profitdevs.group.anticor.base.startClearActivity
import profitdevs.group.anticor.base.startClearTopActivity
import profitdevs.group.anticor.screen.main.MainActivity
import profitdevs.group.anticor.R
import profitdevs.group.anticor.util.utils.Constants


class SuccessOrderActivity : BaseActivity() {
    override fun getLayout(): Int = R.layout.activity_success_order
    lateinit var url: String
    var summa = 0

    override fun initViews() {
        url = intent.getStringExtra(Constants.EXTRA_DATA).toString()
        summa = intent.getIntExtra(Constants.EXTRA_DATA_2, 0)

        if (summa > 0){
            send.setOnClickListener {
                startClearActivity<MainActivity>()
                startActivityToOpenUrlInBrowser(url)
            }
        }else{
            send.setOnClickListener {
                startClearTopActivity<MainActivity>()
            }
        }
    }

    override fun onBackPressed() {
        startClearActivity<MainActivity>()
    }

    override fun loadData() {

    }

    override fun initData() {

    }

    override fun updateData() {

    }

}
