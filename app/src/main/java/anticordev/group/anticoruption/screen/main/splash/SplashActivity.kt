package anticordev.group.anticoruption.screen.main.splash

import android.annotation.SuppressLint
import android.os.CountDownTimer
import anticordev.group.anticoruption.BuildConfig
import anticordev.group.anticoruption.base.BaseActivity
import anticordev.group.anticoruption.base.startClearTopActivity
import anticordev.group.anticoruption.screen.main.MainActivity
import anticordev.group.anticoruption.R
import anticordev.group.anticoruption.base.startClearActivity
import anticordev.group.anticoruption.util.utils.LocaleManager
import kotlinx.android.synthetic.main.bottomsheet_language.view.*
import anticordev.group.anticoruption.util.utils.Prefs
import com.google.android.material.bottomsheet.BottomSheetDialog

@SuppressLint("CustomSplashScreen")
class SplashActivity : BaseActivity() {
    override fun getLayout(): Int = R.layout.activity_splash

    override fun initViews() {
        object : CountDownTimer(2000, 1000) {
            override fun onFinish() {
              if (Prefs.getToken().isNullOrEmpty()){
                    showLanguageDialog()
                }else{
                    startClearTopActivity<MainActivity>()
                    finish()
                }
               // startClearTopActivity<MainActivity>()
            }
            override fun onTick(millisUntilFinished: Long) {
            }

        }.start()
    }
    fun showLanguageDialog(){
        val bottomSheetDialog = BottomSheetDialog(this)
        val viewLang = layoutInflater.inflate(R.layout.bottomsheet_language, null)
        bottomSheetDialog.setContentView(viewLang)
        bottomSheetDialog.setCancelable(false)
        viewLang.tvUzbCr.setOnClickListener {
            Prefs.setLang("uz")
            LocaleManager.setNewLocale(this, "uz")
            bottomSheetDialog?.dismiss()

            startClearTopActivity<MainActivity>()
           // startClearActivity<SplashActivity>()
            finish()
        }
        viewLang.tvRu.setOnClickListener {
            Prefs.setLang("en")
            LocaleManager.setNewLocale(this, "en")
            bottomSheetDialog?.dismiss()

            startClearTopActivity<MainActivity>()
            finish()
        }

        bottomSheetDialog.show()
    }

//    fun showMustUpdate(){
//        val bottomSheetDialog = BottomSheetDialog(this)
//        val view = layoutInflater.inflate(R.layout.must_update_layout, null)
//        bottomSheetDialog.setContentView(view)
//        bottomSheetDialog.setCancelable(false)
//        view.cardViewDownload.setOnClickListener {
//            startActivityToOpenUrlInBrowser("https://play.google.com/store/apps/details?id=${BuildConfig.APPLICATION_ID}")
//        }
//
//        bottomSheetDialog.show()
//    }


    override fun loadData() {
    }

    override fun initData() {
    }
    override fun updateData() {
    }
}
