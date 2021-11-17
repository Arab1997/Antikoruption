package profitdevs.group.anticor.screen.main.send.detail

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.blankj.utilcode.util.NetworkUtils
import kotlinx.android.synthetic.main.activity_category_details.*
import org.greenrobot.eventbus.EventBus
import profitdevs.group.anticor.base.BaseActivity
import profitdev.group.eantikor.model.AddressModel
import profitdevs.group.anticor.screen.main.MainViewModel
import profitdevs.group.anticor.R
import profitdevs.group.anticor.repository.SendRepository
import profitdevs.group.anticor.screen.viewmodels.SendViewModel
import profitdevs.group.anticor.screen.viewmodels.SendViewModelProviderFactory

class SendActivity : BaseActivity() {
    override fun getLayout(): Int = R.layout.activity_category_details
    lateinit var viewModel: SendViewModel
    var address: AddressModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val sendRepository = SendRepository()
        val viewModelProviderFactory = SendViewModelProviderFactory(sendRepository)
        viewModel = ViewModelProvider(this, viewModelProviderFactory).get(SendViewModel::class.java)

        //custom spinner
        var stringArr: Array<String> = resources.getStringArray(R.array.mode);
        val dataAdapter = ArrayAdapter<String>(this, R.layout.simple_spinner_item, stringArr)
        dataAdapter.setDropDownViewResource(R.layout.simple_spinner_drop_down)
        spinner_shahar.adapter = dataAdapter
        spinner_tashkilot.adapter = dataAdapter
        shaxs.adapter = dataAdapter
        spinner_hudud.adapter = dataAdapter
        spinner_tuman.adapter = dataAdapter

        spinner_shahar.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                Toast.makeText(this@SendActivity, stringArr[position], Toast.LENGTH_SHORT)
                    .show()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }

        spinner_tashkilot.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                Toast.makeText(this@SendActivity, stringArr[position], Toast.LENGTH_SHORT)
                    .show()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }


        shaxs.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                Toast.makeText(this@SendActivity, stringArr[position], Toast.LENGTH_SHORT)
                    .show()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }

        spinner_hudud.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                Toast.makeText(this@SendActivity, stringArr[position], Toast.LENGTH_SHORT)
                    .show()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }

        spinner_tuman.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
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

        }

        NetworkUtils.registerNetworkStatusChangedListener(object :
            NetworkUtils.OnNetworkStatusChangedListener {
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

    }

    override fun initData() {
    }

    override fun updateData() {

    }

    override fun onDestroy() {
        super.onDestroy()
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this)
        }
    }
}
