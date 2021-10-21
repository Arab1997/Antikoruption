package profitdevs.group.anticor.screen.main.splash

import android.os.CountDownTimer
import profitdevs.group.anticor.base.BaseActivity
import profitdev.group.eantikor.base.startClearTopActivity
import profitdev.group.eantikor.screen.main.MainActivity
import profitdevs.group.anticor.R

class SplashActivity : BaseActivity() {
    override fun getLayout(): Int = R.layout.activity_splash
    private lateinit var listener: () -> Unit

    override fun initViews() {
        object : CountDownTimer(2000, 1000) {
            override fun onFinish() {
                startClearTopActivity<MainActivity>()
            }
            override fun onTick(millisUntilFinished: Long) {
            }

        }.start()
    }

    override fun loadData() {
    }

    override fun initData() {
    }
    override fun updateData() {
    }
}
