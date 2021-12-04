package anticordev.group.anticoruption.screen.main.send.detail

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.blankj.utilcode.util.NetworkUtils
import es.dmoral.toasty.Toasty
import kotlinx.android.synthetic.main.send_activity.*
import org.greenrobot.eventbus.EventBus
import anticordev.group.anticoruption.base.BaseActivity
import anticordev.group.anticoruption.R
import anticordev.group.anticoruption.model.send_models.*
import anticordev.group.anticoruption.repository.SendRepository
import anticordev.group.anticoruption.screen.viewmodels.SendViewModel
import anticordev.group.anticoruption.screen.viewmodels.SendViewModelProviderFactory


class SendActivity : BaseActivity() {

    override fun getLayout(): Int = R.layout.send_activity
    lateinit var viewModel: SendViewModel
    private var areas: MutableList<Area> = mutableListOf()
    private var regions: MutableList<Region> = mutableListOf()
    private lateinit var region: Region
    private lateinit var area: Area
    private var orgs: MutableList<Organization> = mutableListOf()
    private lateinit var org: Organization

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val complain = Complain()
        val sendRepository = SendRepository()
        val viewModelProviderFactory = SendViewModelProviderFactory(sendRepository)
        viewModel = ViewModelProvider(this, viewModelProviderFactory).get(SendViewModel::class.java)

        viewModel.getRegions()
        viewModel.regions.observe(this, { response ->
            if (response.isSuccessful) {
                regions.addAll(response.body()!!)

                val data: MutableList<String> = ArrayList()
                data.addAll(regions.map {
                    it.name
                })

                spinner_region.adapter = ArrayAdapter(
                    this,
                    R.layout.simple_spinner_item,
                    data
                )
                spinner_region.onItemSelectedListener =
                    object : AdapterView.OnItemSelectedListener {
                        override fun onItemSelected(
                            parent: AdapterView<*>?,
                            view: View?,
                            position: Int,
                            id: Long
                        ) {
                            region = regions[position]
                            viewModel.getRegionsById(region.id)
                            complain.region = region.id
                        }

                        override fun onNothingSelected(parent: AdapterView<*>?) {
                        }
                    }
            } else {
                Log.d("SendActivity", response.message())
            }
        })

        viewModel.regionsById.observe(this, { response ->
            if (response.isSuccessful) {
                response.body()!!.areas.forEach {
                    areas.clear()
                    areas.addAll(response.body()!!.areas)
                    val data: MutableList<String> = ArrayList()
                    data.addAll(
                        areas.map {
                            it.name
                        }
                    )
                    spinner_area.adapter = ArrayAdapter(
                        this,
                        R.layout.simple_spinner_item,
                        data
                    )

                    spinner_area.onItemSelectedListener =
                        object : AdapterView.OnItemSelectedListener {
                            override fun onItemSelected(
                                parent: AdapterView<*>,
                                view: View,
                                position: Int,
                                id: Long
                            ) {
                                area = areas[position]
                                complain.area = area.id
                            }

                            override fun onNothingSelected(p0: AdapterView<*>?) {
                            }

                        }
                }
            } else {
                Log.d("SendActivity", response.message())
            }
        })

        viewModel.getOrganizations()
        viewModel.organizations.observe(this, { response ->
            if (response.isSuccessful) {
                orgs.addAll(response.body()!!)

                val data: MutableList<String> = ArrayList()
                data.addAll(orgs.map { it.name })
                spinner_org.adapter = ArrayAdapter(
                    this@SendActivity,
                    R.layout.simple_spinner_item,
                    data
                )
                spinner_org.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                    override fun onItemSelected(
                        parent: AdapterView<*>,
                        view: View,
                        position: Int,
                        id: Long
                    ) {
                        org = orgs[position]
                        complain.organization = org.id
                    }

                    override fun onNothingSelected(parent: AdapterView<*>) {}
                }
            }
        })

        viewModel.complains.observe(this, { response ->
            if (response.isSuccessful && (response.code() == 200 || response.code() == 201)) {
                Toasty.success(this, R.string.success, Toast.LENGTH_SHORT).show()
            } else {
                Toasty.warning(this, R.string.error, Toast.LENGTH_SHORT).show()
           //     Toasty.warning(this, response.code().toString(), Toast.LENGTH_LONG).show()
            }
        })

        button_type.setOnCheckedChangeListener { radioGroup, i ->
            complain.button_type = i
        }

        currency.setOnCheckedChangeListener { radioGroup, i ->
            complain.currency = i
        }

        send.setOnClickListener {
            if (validate()) {
                complain.amount = amount.text.toString().toInt()
                complain.button_type = button_type.checkedRadioButtonId
                complain.text = edComment.text.toString()
                complain.currency = 1

                viewModel.postComplain(complain)
            } else {
                Toasty.error(this, "Error", Toast.LENGTH_SHORT).show()
            }
        }
    }
    private fun validate(): Boolean {
        if (
            amount.text.isNullOrEmpty() ||
            edComment.text.isNullOrEmpty()
        ) {
            return false
        }

        return true
    }

    override fun initViews() {
        imgBackk.setOnClickListener {
            finish()
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