package profitdev.group.eantikor.screen.main.faq

import android.app.AlertDialog
import androidx.fragment.app.Fragment
import com.google.android.material.bottomsheet.BottomSheetDialog
import kotlinx.android.synthetic.main.bottomsheet_language.view.*
import kotlinx.android.synthetic.main.fragment_profile.*
import profitdev.group.eantikor.base.BaseFragment
import profitdev.group.eantikor.base.startActivity
import profitdev.group.eantikor.base.startClearTopActivity
import profitdevs.group.anticor.screen.main.MainViewModel
import profitdevs.group.anticor.screen.main.savollar.SavollarActivity
import profitdevs.group.anticor.screen.main.splash.SplashActivity
import profitdevs.group.anticor.R
import profitdevs.group.anticor.util.utils.LocaleManager.setNewLocale
import profitdevs.group.anticor.util.utils.Prefs

/**
 * A simple [Fragment] subclass.
 */
@Suppress("DEPRECATION")
class FaqFragment : BaseFragment() {
    override fun getLayout(): Int = R.layout.fragment_profile
    lateinit var viewModel: MainViewModel

    override fun setupViews() {
       /* viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        val user = Prefs.getClientInfo()*/

        /*tvPersonName.text = user?.name
        tvPhone.text = user?.phone*/

        lyProfile.setOnClickListener {
            getBaseActivity {
               // it.startActivity<ProfileEditActivity>()
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
                //
            }
            builder.show()
        }
    }

    override fun loadData() {

    }

    override fun setData() {

    }

}
