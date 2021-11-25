package profitdevs.group.anticor.screen.main.splash

import android.os.CountDownTimer
import profitdevs.group.anticor.base.BaseActivity
import profitdevs.group.anticor.base.startClearTopActivity
import profitdevs.group.anticor.screen.main.MainActivity
import profitdevs.group.anticor.R

class SplashActivity : BaseActivity() {
    override fun getLayout(): Int = R.layout.activity_splash

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
