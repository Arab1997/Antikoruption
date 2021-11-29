package profitdevops.group.anticor.screen.main.splash

import android.os.CountDownTimer
import profitdevops.group.anticor.base.BaseActivity
import profitdevops.group.anticor.base.startClearTopActivity
import profitdevops.group.anticor.screen.main.MainActivity
import profitdevops.group.anticor.R

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
