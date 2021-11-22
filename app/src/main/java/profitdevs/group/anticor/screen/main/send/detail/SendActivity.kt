package profitdevs.group.anticor.screen.main.send.detail

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.blankj.utilcode.util.NetworkUtils
import es.dmoral.toasty.Toasty
import kotlinx.android.synthetic.main.activity_category_details.*
import kotlinx.android.synthetic.main.activity_category_details.shaxs
import kotlinx.android.synthetic.main.activity_category_details.spinner_hudud
import kotlinx.android.synthetic.main.activity_category_details.spinner_shahar
import kotlinx.android.synthetic.main.activity_category_details.spinner_tashkilot
import kotlinx.android.synthetic.main.activity_category_details.spinner_tuman
import kotlinx.android.synthetic.main.fragment_send.*
import org.greenrobot.eventbus.EventBus
import profitdevs.group.anticor.base.BaseActivity
import profitdev.group.eantikor.model.AddressModel
import profitdevs.group.anticor.R
import profitdevs.group.anticor.model.send_models.Complain
import profitdevs.group.anticor.repository.SendRepository
import profitdevs.group.anticor.screen.viewmodels.SendViewModel
import profitdevs.group.anticor.screen.viewmodels.SendViewModelProviderFactory

class SendActivity : BaseActivity() {
    override fun getLayout(): Int = R.layout.fragment_send
    lateinit var viewModel: SendViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val complain = Complain()

        val sendRepository = SendRepository()
        val viewModelProviderFactory = SendViewModelProviderFactory(sendRepository)
        viewModel = ViewModelProvider(this, viewModelProviderFactory).get(SendViewModel::class.java)
//
        val stringArr: MutableList<String> = mutableListOf()
        val orgranizations: MutableList<String> = mutableListOf()
        val regionsById: MutableList<String> = mutableListOf()
        val regions: MutableList<String> = mutableListOf()

        viewModel.getAreas()
        viewModel.areas.observe(this, { response ->
            if (response.isSuccessful) {
                response.body()!!.forEach {
                    var index = 0
                    stringArr.add(index, it.name)
                    index++
                }
            } else {
                Log.d("SendActivity", response.message())
            }
        })

        viewModel.getOrganizations()
        viewModel.organizations.observe(this, { response ->
            if (response.isSuccessful) {
                response.body()!!.forEach {
                    var index = 0
                    orgranizations.add(index, it.name)
                    index++
                }
            } else {
                Log.d("SendActivity", response.message())
            }
        })

        viewModel.getRegions()
        viewModel.regios.observe(this, { response ->
            if (response.isSuccessful) {
                response.body()!!.forEach {
                    var index = 0
                    regions.add(index, it.name)
                    index++
                }
            } else {
                Log.d("SendActivity", response.message())
            }
        })

        viewModel.getRegionsById(14)
        viewModel.regionsById.observe(this, { response ->
            if (response.isSuccessful) {
                response.body()!!.areas.forEach {
                    var index = 0
                    regionsById.add(index, it.name)
                    index++
                }
            } else {
                Log.d("SendActivity", response.message())
            }
        })

        //Spinners adapters
        val regionsAdapter = ArrayAdapter(this, R.layout.simple_spinner_item, regions)
        val organizationsAdapter = ArrayAdapter(this, R.layout.simple_spinner_item, orgranizations)
        val dataAdapter = ArrayAdapter(this, R.layout.simple_spinner_item, stringArr)
        val regionsByIdAdapter = ArrayAdapter(this, R.layout.simple_spinner_item, regionsById)

        //setting layout for custom Spinners
        regionsAdapter.setDropDownViewResource(R.layout.simple_spinner_drop_down)
        organizationsAdapter.setDropDownViewResource(R.layout.simple_spinner_drop_down)
        dataAdapter.setDropDownViewResource(R.layout.simple_spinner_drop_down)
        regionsByIdAdapter.setDropDownViewResource(R.layout.simple_spinner_drop_down)

        regionsAdapter.notifyDataSetChanged()
        organizationsAdapter.notifyDataSetChanged()
        dataAdapter.notifyDataSetChanged()
        regionsByIdAdapter.notifyDataSetChanged()

        spinner_shahar.adapter = regionsAdapter
        spinner_tashkilot.adapter = organizationsAdapter
        shaxs.adapter = dataAdapter
        spinner_hudud.adapter = regionsByIdAdapter
        spinner_tuman.adapter = dataAdapter

        btnSend.setOnClickListener {
            Toasty.success(this, spinner_shahar.getItemAtPosition(12).toString(), Toast.LENGTH_LONG).show()
        }
//
        spinner_shahar.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
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
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }

    }

    override fun initViews() {

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
