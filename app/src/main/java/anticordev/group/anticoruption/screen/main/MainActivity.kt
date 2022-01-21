package anticordev.group.anticoruption.screen.main

import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AlertDialog
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.blankj.utilcode.util.NetworkUtils
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.bottomsheet_language.view.*
import org.greenrobot.eventbus.EventBus
import anticordev.group.anticoruption.BuildConfig
import anticordev.group.anticoruption.base.BaseActivity
import anticordev.group.anticoruption.base.startActivity
import anticordev.group.anticoruption.base.startClearActivity
import anticordev.group.anticoruption.screen.main.aboutapp.AboutAppActivity
import anticordev.group.anticoruption.screen.main.statistic.StatisticFragment
import anticordev.group.anticoruption.screen.main.faq.FaqFragment
import anticordev.group.anticoruption.R
import anticordev.group.anticoruption.screen.main.category.CategoryFragment
import anticordev.group.anticoruption.screen.main.oneId.OneActivity
import anticordev.group.anticoruption.screen.main.savollar.SavollarActivity
import anticordev.group.anticoruption.screen.main.splash.SplashActivity
import anticordev.group.anticoruption.screen.main.statistic.WebActivity
import anticordev.group.anticoruption.util.utils.Constants
import anticordev.group.anticoruption.util.utils.LocaleManager.setNewLocale
import anticordev.group.anticoruption.util.utils.Prefs
import kotlinx.android.synthetic.main.fragment_category.*

@Suppress("DEPRECATION")
class MainActivity : BaseActivity(), NavigationView.OnNavigationItemSelectedListener {
    override fun getLayout(): Int = R.layout.activity_main
    val home = CategoryFragment()
    val staticFragment = StatisticFragment()
    val faqFragment = FaqFragment()

    override fun initViews() {
        nav_bottom.setOnNavigationItemSelectedListener { item: MenuItem ->
            return@setOnNavigationItemSelectedListener when (item.itemId) {
                R.id.category -> {
                    if (Prefs.getToken().isEmpty()) {
                        one_id.visibility = View.GONE
                        logout.visibility = View.VISIBLE

                    }
                    one_id.visibility = View.VISIBLE
                    logout.visibility = View.GONE

                    one_id.setOnClickListener {
                        startActivity<OneActivity>()
                    }
                    tvTitle.visibility = View.VISIBLE
                    hideFragments()
                    if (!home.isAdded) {
                        supportFragmentManager.beginTransaction()
                            .add(R.id.container, home)
                            .commitAllowingStateLoss()
                    } else {
                        supportFragmentManager.beginTransaction()
                            .show(home)
                            .commitAllowingStateLoss()
                    }
                    true
                }

                R.id.statistic -> {
                        if (Prefs.getToken().isEmpty()) {
                            one_id.visibility = View.VISIBLE
                            logout.visibility = View.GONE

                        }
                        one_id.visibility = View.GONE
                        logout.visibility = View.VISIBLE
                    toolbar.visibility = View.VISIBLE
                    tvTitle.visibility = View.VISIBLE
                    hideFragments()
                    if (!staticFragment.isAdded) {
                        supportFragmentManager.beginTransaction()
                            .add(R.id.container, staticFragment)
                            .commitAllowingStateLoss()
                    } else {
                        supportFragmentManager.beginTransaction()
                            .show(staticFragment)
                            .commitAllowingStateLoss()
                    }

                    true
                }
                R.id.faq -> {
                    if (Prefs.getToken().isEmpty()) {
                        one_id.visibility = View.VISIBLE
                        logout.visibility = View.GONE

                    }
                    one_id.visibility = View.GONE
                    logout.visibility = View.VISIBLE

                    one_id.setOnClickListener {
                        startActivity<OneActivity>()
                    }
                    tvTitle.visibility = View.VISIBLE
                    hideFragments()
                    if (!faqFragment.isAdded) {
                        supportFragmentManager.beginTransaction()
                            .add(R.id.container, faqFragment)
                            .commitAllowingStateLoss()
                    } else {
                        supportFragmentManager.beginTransaction()
                            .show(faqFragment)
                            .commitAllowingStateLoss()
                    }

                    true
                }
                else -> true
            }
        }

        logout.setOnClickListener {
            val builder = AlertDialog.Builder(this)
            builder.setTitle(getString(R.string.Chiqish))
            builder.setMessage(getString(R.string.chiqmoqchimisiz))
            builder.setPositiveButton(android.R.string.yes) { _, _ ->
                Prefs.clearAll()

                    startActivity<SplashActivity>()
            }
            builder.setNegativeButton(android.R.string.no) { _, _ ->

            }
            builder.show()
        }

        val drawer: DrawerLayout = findViewById(R.id.drawer)
        val toggle = ActionBarDrawerToggle(this, drawer, R.string.open, R.string.close)
        drawer.addDrawerListener(toggle)
        toggle.syncState()
        navigation.setNavigationItemSelectedListener(this)

        imgMore.setOnClickListener {
            drawer.openDrawer(GravityCompat.START)
        }
        one_id.setOnClickListener {
            startActivity<OneActivity>()
        }

        call.setOnClickListener {
            callPhone()
        }

        if (intent.hasExtra(Constants.EXTRA_DATA_START_FRAGMENT)) {
            nav_bottom.selectedItemId =
                intent.getIntExtra(Constants.EXTRA_DATA_START_FRAGMENT, R.id.category)
        }
        if (nav_bottom.selectedItemId == R.id.category) {
            pushFragment(R.id.container, home)
        }
        NetworkUtils.registerNetworkStatusChangedListener(object :
            NetworkUtils.OnNetworkStatusChangedListener {
            override fun onConnected(networkType: NetworkUtils.NetworkType?) {
                showConnection(notConnection = false)
            }

            override fun onDisconnected() {
                showConnection(notConnection = true)
                loadData()
            }
        })
    }

    override fun loadData() {
    }

    override fun onBackPressed() {
        finish()
    }

    override fun initData() {

    }

    override fun updateData() {

    }

    @SuppressLint("InflateParams")
    override fun onNavigationItemSelected(p0: MenuItem): Boolean {
        if (p0.itemId == R.id.actionShareApp) {
            val shareIntent = Intent()
            shareIntent.action = Intent.ACTION_SEND
            shareIntent.type = "text/plain"
            shareIntent.putExtra(
                Intent.EXTRA_TEXT, "Друзья, я предлагаю вам это приложение: "
                        + getString(R.string.app_name)
                        + "\n https://play.google.com/store/apps/details?id=${BuildConfig.APPLICATION_ID}"
            )

            startActivity(Intent.createChooser(shareIntent, "Отправить своим друзьям."))
        } else if (p0.itemId == R.id.murojaat) {
            startActivity<MainActivity>()
        } else if (p0.itemId == R.id.faq) {
            startActivity<SavollarActivity>()
//        } else if (p0.itemId == R.id.actionLogout) {
//            Prefs.clearAll()
//            startClearActivity<SplashActivity>()
//            finish()
        } else if (p0.itemId == R.id.actionLanguage) {
            val bottomSheetDialog = BottomSheetDialog(this)
            val viewLang = layoutInflater.inflate(R.layout.bottomsheet_language, null)
            bottomSheetDialog.setContentView(viewLang)
            viewLang.tvUzbCr.setOnClickListener {
                Prefs.setLang("uz")
                setNewLocale(this, "uz")
                bottomSheetDialog.dismiss()
                startClearActivity<SplashActivity>()
                finish()
            }
            viewLang.tvRu.setOnClickListener {
                Prefs.setLang("ru")
                setNewLocale(this, "ru")
                bottomSheetDialog.dismiss()
                startClearActivity<SplashActivity>()
                finish()
            }
            bottomSheetDialog.show()
        } else {
            startActivity<AboutAppActivity>()
        }
        return true
    }

    override fun onDestroy() {
        super.onDestroy()

        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this)
        }
    }
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>, grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 42) {
            if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                callPhone()
            }
            return
        }
    }
    @SuppressLint("MissingPermission")
    fun callPhone() {
        val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + "1253 "))
        startActivity(intent)
    }
}
