package profitdevs.group.anticor.screen.main.category

import android.view.View
import kotlinx.android.synthetic.main.fragment_category.*
import profitdev.group.eantikor.base.BaseFragment
import profitdev.group.eantikor.base.startActivity
import profitdevs.group.anticor.screen.main.MainViewModel
import profitdevs.group.anticor.screen.main.oneId.OneActivity
import profitdevs.group.anticor.util.utils.Constants
import profitdevs.group.anticor.screen.main.send.detail.SendActivity
import profitdevs.group.anticor.R

class CategoryFragment : BaseFragment(), View.OnClickListener {
    override fun getLayout(): Int = R.layout.fragment_category
    lateinit var viewModel: MainViewModel

    override fun setupViews() {
       // tv_no_courses.isVisible = true

        btn_register.setOnClickListener {
            getBaseActivity {
                it.startActivity<OneActivity>(Constants.EXTRA_DATA,  Constants.EXTRA_DATA_2)
            }

        }

        category_card1.setOnClickListener {
            getBaseActivity {
                it.startActivity<SendActivity>(Constants.EXTRA_DATA,  Constants.EXTRA_DATA_2)
            }

        }

        category_card2.setOnClickListener {
            getBaseActivity {
                it.startActivity<SendActivity>(Constants.EXTRA_DATA,  Constants.EXTRA_DATA_2)
            }

        }

        category_card3.setOnClickListener {
            getBaseActivity {
                it.startActivity<SendActivity>(Constants.EXTRA_DATA,  Constants.EXTRA_DATA_2)
            }

        }

        category_card4.setOnClickListener {
            getBaseActivity {
                it.startActivity<SendActivity>(Constants.EXTRA_DATA,  Constants.EXTRA_DATA_2)
            }

        }
        }

    override fun loadData() {

    }

    override fun setData() {

    }

    override fun onClick(v: View?) {

    }

    /*private fun alert() {
        Handler().postDelayed({
            alert("Are you sure you want to log out?") {
                title = "Log out"
                positiveButton("LOG OUT") {
                    val firebaseAuth = FirebaseAuth.getInstance()
                    firebaseAuth.signOut()
                    FirebaseMessaging.getInstance().unsubscribeFromTopic(K.TOPIC_GLOBAL)

                    startActivity(Intent(this@MainActivity, AuthActivity::class.java))
                    AppUtils.animateEnterLeft(this@MainActivity)
                    finish()
                }
                negativeButton("CANCEL") {}
            }.show()
        }, 300)
    }*/

    /* fun setAds(){
         if (viewModel.successAdsData.value != null){
             val data = viewModel.successAdsData.value

             carouselView.setImageListener { position, imageView ->
                 imageView.scaleType = ImageView.ScaleType.FIT_XY
                 if (position == 0){
                     imageView.loadImage(App.imageBaseUrl + data?.reklama1)
                 }
                 if (position == 1){
                     imageView.loadImage(App.imageBaseUrl + data?.reklama2)
                 }
                 if (position == 2){
                     imageView.loadImage(App.imageBaseUrl + data?.reklama3)
                 }
             }

             carouselView.pageCount = 3
             carouselView.visibility = View.VISIBLE
         }
     }*/

    /* override fun setData() {
         if (viewModel.storesData.value != null){
             viewModel.storesData.value!!.forEach { store ->
                 if (Prefs.getStore()?.id == store.id){
                     store.checked = true
                 }
             }
             recycler.layoutManager = LinearLayoutManager(activity)
             recycler.adapter = StoresAdapter(viewModel.storesData.value ?: emptyList(), object : BaseAdapterListener{
                 override fun onClickItem(item: Any?) {
                     getBaseActivity {
                         if (Prefs.getCartList().count() > 0){
                             activity?.showWarning(getString(R.string.please_clear_cart))
                             return@getBaseActivity
                         }

                         Prefs.setStore(item as StoreSimpleModel)

                         it.updateStore()
                         it.showSuccess(getString(R.string.store_selected))
                     }
                 }
             })
         }
     }*/

    }









