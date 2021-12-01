package anticordev.group.anticoruption.screen.main.splash

import android.os.CountDownTimer
import anticordev.group.anticoruption.base.BaseActivity
import anticordev.group.anticoruption.base.startClearTopActivity
import anticordev.group.anticoruption.screen.main.MainActivity
import anticordev.group.anticoruption.R

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
