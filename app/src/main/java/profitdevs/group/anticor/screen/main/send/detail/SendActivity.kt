package profitdevs.group.anticor.screen.main.send.detail

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import com.blankj.utilcode.util.NetworkUtils
import kotlinx.android.synthetic.main.activity_category_details.*
import org.greenrobot.eventbus.EventBus
import profitdevs.group.anticor.base.BaseActivity
import profitdev.group.eantikor.model.AddressModel
import profitdevs.group.anticor.screen.main.MainViewModel
import profitdevs.group.anticor.R

class SendActivity : BaseActivity() {
    override fun getLayout(): Int = R.layout.activity_category_details
    lateinit var viewModel: MainViewModel
    var address: AddressModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //custom spinner
        val stringArr: Array<String> = resources.getStringArray(R.array.mode);
        val dataAdapter = ArrayAdapter<String>(this, R.layout.simple_spinner_item, stringArr)
        dataAdapter.setDropDownViewResource(R.layout.simple_spinner_drop_down)
        spinner_shahar.adapter = dataAdapter
        spinner_tashkilot.adapter = dataAdapter
        shaxs.adapter = dataAdapter
        spinner_hudud.adapter = dataAdapter
        spinner_tuman.adapter = dataAdapter


        spinner_shahar.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                Toast.makeText(this@SendActivity, stringArr[position], Toast.LENGTH_SHORT)
                    .show()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }

        spinner_tashkilot.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                Toast.makeText(this@SendActivity, stringArr[position], Toast.LENGTH_SHORT)
                    .show()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }


        shaxs.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                Toast.makeText(this@SendActivity, stringArr[position], Toast.LENGTH_SHORT)
                    .show()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }

        spinner_hudud.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                Toast.makeText(this@SendActivity, stringArr[position], Toast.LENGTH_SHORT)
                    .show()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }

        spinner_tuman.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                Toast.makeText(this@SendActivity, stringArr[position], Toast.LENGTH_SHORT)
                    .show()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }

    }


    override fun initViews() {

        imgBackk.setOnClickListener {
            finish()
        }


        send.setOnClickListener {

         /*   var items = products.map {
                MakeOrderProductModel(
                    it.name,
                    it.id,
                    if(Prefs.getCurrency() == CurrencyEnum.USD) it.price else it.price * Prefs.getClientInfo()!!.currency,
                    it.cartCount.toDouble(),
                    edComment.text.toString(),
                    (if(Prefs.getCurrency() == CurrencyEnum.USD) it.price else it.price * Prefs.getClientInfo()!!.currency) * it.cartCount
                )
            }*/

          /*  items.forEach {
                productAmount += it.psumma
            }*/



          /*  val order = MakeOrderModel(
                Prefs.getStore()?.id?.toIntOrNull() ?: 0,
                address?.lon.toString(),
                address?.lat.toString(),
                edAddress.text.toString(),
                address?.address ?: "",
                totalAmount,
                items
            ) */
        }

        NetworkUtils.registerNetworkStatusChangedListener(object: NetworkUtils.OnNetworkStatusChangedListener{
            override fun onConnected(networkType: NetworkUtils.NetworkType?) {
                showConnection(notConnection = false)
                loadData()
            }

            override fun onDisconnected() {
                showConnection(notConnection = true)
            }
        })

    }

    override fun loadData() {
      //  viewModel.getStoreInfo()
    }

    override fun initData() {
      /*  val userInfo = Prefs.getClientInfo()
        edFullName.setText( userInfo?.name )
        edPhone.setText( userInfo?.phone )*/
    }

    override fun updateData() {

    }

    override fun onDestroy() {
        super.onDestroy()
        if (EventBus.getDefault().isRegistered(this)){
            EventBus.getDefault().unregister(this)
        }
    }

  /*  fun setAddressData(){
        if (address != null && Prefs.getStoreInfo() != null){
            val store = Prefs.getStoreInfo()
            var results = FloatArray(10)
            Location.distanceBetween(store?.latitude?.toDoubleOrNull() ?: 0.0, store?.longitude?.toDoubleOrNull() ?: 0.0,
                address?.lat ?: 0.0, address?.lon ?: 0.0, results)
            var km = results[0] / 1000.0

            if (km.toInt() > (store?.radius ?: 0) && (store?.radius ?: 0) != 0){
                showWarning(getString(R.string.max_radius_delivery, (store?.radius ?: 0).toString()))
                address = null
                return
            }


            edAddress.setText(address?.address)
        }
    }*/

}
