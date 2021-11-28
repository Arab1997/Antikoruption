package profitdevs.group.anticor.screen.main.faq

import android.annotation.SuppressLint
import android.app.AlertDialog
import androidx.fragment.app.Fragment
import com.google.android.material.bottomsheet.BottomSheetDialog
import kotlinx.android.synthetic.main.bottomsheet_language.view.*
import kotlinx.android.synthetic.main.fragment_profile.*
import profitdevs.group.anticor.base.BaseFragment
import profitdevs.group.anticor.base.startActivity
import profitdevs.group.anticor.base.startClearTopActivity
import profitdevs.group.anticor.screen.main.savollar.SavollarActivity
import profitdevs.group.anticor.screen.main.splash.SplashActivity
import profitdevs.group.anticor.R
import profitdevs.group.anticor.base.startClearActivity
import profitdevs.group.anticor.util.utils.LocaleManager.setNewLocale
import profitdevs.group.anticor.util.utils.Prefs

@Suppress("DEPRECATION")
class FaqFragment : BaseFragment() {
    override fun getLayout(): Int = R.layout.fragment_profile

    @SuppressLint("InflateParams")
    override fun setupViews() {
        lyProfile.setOnClickListener {
            getBaseActivity {
                it.startActivity<SavollarActivity>()
            }
        }

        lyLanguage.setOnClickListener {
            getBaseActivity {base ->
                val bottomSheetDialog = BottomSheetDialog(base)
                val viewLang = layoutInflater.inflate(R.layout.bottomsheet_language, null)
                bottomSheetDialog.setContentView(viewLang)
                viewLang.tvUzbCr.setOnClickListener {
                    Prefs.setLang("uz")
                    setNewLocale(base,"uz")
                    bottomSheetDialog.dismiss()
                }
                viewLang.tvRu.setOnClickListener {
                    Prefs.setLang("en")
                    setNewLocale(base,"en")
                    bottomSheetDialog.dismiss()
                }
                bottomSheetDialog.show()
            }
        }

        lyLogout.setOnClickListener {
            val builder = AlertDialog.Builder(activity)
            builder.setTitle("Выйти")
            builder.setMessage("Вы действительно хотите выйти?")
            builder.setPositiveButton(android.R.string.yes) { _, _ ->
                Prefs.clearAll()
                getBaseActivity {
                    it.startClearTopActivity<SplashActivity>()
                }
            }
            builder.setNegativeButton(android.R.string.no) { _, _ ->

            }
            builder.show()
        }
    }
    override fun loadData() {
    }
    override fun setData() {
    }
}
