package profitdevs.group.anticor.screen.main

import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.view.MenuItem
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.blankj.utilcode.util.NetworkUtils
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.bottomsheet_language.view.*
import org.greenrobot.eventbus.EventBus
import profitdevs.group.anticor.base.BaseActivity
import profitdev.group.eantikor.base.startActivity
import profitdev.group.eantikor.base.startClearActivity
import profitdevs.group.anticor.screen.main.asosiy.AsosiyActivity
import profitdevs.group.anticor.screen.main.aboutapp.AboutAppActivity
import profitdevs.group.anticor.screen.main.statistic.StatisticFragment
import profitdev.group.eantikor.screen.main.faq.FaqFragment
import profitdevs.group.anticor.R
import profitdevs.group.anticor.screen.main.category.CategoryFragment
import profitdevs.group.anticor.screen.main.oneId.OneActivity
import profitdevs.group.anticor.screen.main.savollar.SavollarActivity
import profitdevs.group.anticor.screen.main.splash.SplashActivity
import profitdevs.group.anticor.util.utils.Constants
import profitdevs.group.anticor.util.utils.LocaleManager.setNewLocale
import profitdevs.group.anticor.util.utils.Prefs

@Suppress("DEPRECATION")
class MainActivity : BaseActivity(), NavigationView.OnNavigationItemSelectedListener {
    override fun getLayout(): Int = R.layout.activity_main
   // lateinit var viewModel: MainViewModel
    private val mainViewModel: MainViewModel by viewModels()
    val home = CategoryFragment()
    val staticFragment = StatisticFragment()
    val faqFragment = FaqFragment()

    override fun initViews() {
        nav_bottom.setOnNavigationItemSelectedListener { item: MenuItem ->
             /* if (Prefs.getStore() == null){
                  showWarning(getString(R.string.agentlik))
                  return@setOnNavigationItemSelectedListener false
              }*/
            return@setOnNavigationItemSelectedListener when (item.itemId) {
                R.id.category -> {
                    one_id.visibility = View.VISIBLE

                    one_id.setOnClickListener {
                        startActivity<OneActivity>()
                    }
                    tvTitle.visibility = View.VISIBLE
                    if (home.isAdded && home.isVisible) {

                    } else {
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
                    }
                    true
                }

                R.id.statistic -> {
                    one_id.visibility = View.GONE
                    toolbar.visibility = View.VISIBLE
                    tvTitle.visibility = View.VISIBLE
                    if (staticFragment.isAdded && staticFragment.isVisible) {

                    } else {
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
                    }
                    true
                }
                R.id.faq -> {
                    one_id.visibility = View.VISIBLE

                    one_id.setOnClickListener {
                        startActivity<OneActivity>()
                    }
                    tvTitle.visibility = View.VISIBLE
                    if (faqFragment.isAdded && faqFragment.isVisible) {

                    } else {
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
                    }
                    true
                }
                else -> true
            }
        }
        val drawer: DrawerLayout = findViewById(R.id.drawer)
        val toggle = ActionBarDrawerToggle(this, drawer, R.string.open, R.string.close)
        drawer.addDrawerListener(toggle)
        toggle.syncState()
        navigation.setNavigationItemSelectedListener(this)

        imgMore.setOnClickListener {
            drawer.openDrawer(GravityCompat.START)
        }

        //tvTitle.text = getString(R.string.title)
        one_id.setOnClickListener {
            startActivity<OneActivity>()
        }

        call.setOnClickListener {
          /*  startActivity<OneActivity>()*/
            callPhone()
        }
        /*imgSearch.setOnClickListener {
            startActivity<SearchProductActivity>()
        }*/

        if (intent.hasExtra(Constants.EXTRA_DATA_START_FRAGMENT)) {
            nav_bottom.selectedItemId =
                intent.getIntExtra(Constants.EXTRA_DATA_START_FRAGMENT, R.id.category)
        }
        if (nav_bottom.selectedItemId == R.id.category) {
            pushFragment(R.id.container, home)
        }

        // setClientDataNav()

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

    override fun onNavigationItemSelected(p0: MenuItem): Boolean {
       /* if (p0.itemId == R.id.actionNews) {
          //  startActivity<NewsActivity>()
        } else*/
            if (p0.itemId == R.id.actionShareApp) {
            val shareIntent = Intent()
            shareIntent.action = Intent.ACTION_SEND
            shareIntent.type = "text/plain"
           /* shareIntent.putExtra(
                Intent.EXTRA_TEXT, "Друзья, я предлагаю вам это приложение: "
                        + getString(R.string.app_name)
                        + "\n https://play.google.com/store/apps/details?id=${BuildConfig.APPLICATION_ID}"
            )*/

            startActivity(Intent.createChooser(shareIntent, "Отправить своим друзьям."))
        } else if (p0.itemId == R.id.murojaat) {
            startActivity<MainActivity>()
        } else if (p0.itemId == R.id.faq) {
            startActivity<SavollarActivity>()
        } else if (p0.itemId == R.id.main) {
            startActivity<AsosiyActivity>()
        } else if (p0.itemId == R.id.actionLogout) {
            Prefs.clearAll()
            startClearActivity<SplashActivity>()
            finish()
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
                Prefs.setLang("en")
                setNewLocale(this, "en")
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

    fun checkPermission() {
        if (ContextCompat.checkSelfPermission(
                this,
                android.Manifest.permission.CALL_PHONE
            )
            != PackageManager.PERMISSION_GRANTED
        ) {

            // Permission is not granted
            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(
                    this,
                    android.Manifest.permission.CALL_PHONE
                )
            ) {
                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.
            } else {
                // No explanation needed, we can request the permission.
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(android.Manifest.permission.CALL_PHONE),
                    42
                )
            }
        } else {
            // Permission has already been granted
            callPhone()
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>, grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 42) {
            // If request is cancelled, the result arrays are empty.
            if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                // permission was granted, yay!
                callPhone()
            } else {
                // permission denied, boo! Disable the
                // functionality
            }
            return
        }
    }

    @SuppressLint("MissingPermission")
    fun callPhone() {
        val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + "1007 "))
        startActivity(intent)
    }




}
