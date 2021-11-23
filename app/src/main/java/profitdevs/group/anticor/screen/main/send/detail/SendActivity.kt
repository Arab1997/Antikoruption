package profitdevs.group.anticor.screen.main.send.detail

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.blankj.utilcode.util.NetworkUtils
import es.dmoral.toasty.Toasty
import kotlinx.android.synthetic.main.activity_category_details.*
import org.greenrobot.eventbus.EventBus
import profitdevs.group.anticor.base.BaseActivity
import profitdevs.group.anticor.R
import profitdevs.group.anticor.model.send_models.Area
import profitdevs.group.anticor.model.send_models.Complain
import profitdevs.group.anticor.model.send_models.Organization
import profitdevs.group.anticor.model.send_models.Region
import profitdevs.group.anticor.repository.SendRepository
import profitdevs.group.anticor.screen.viewmodels.SendViewModel
import profitdevs.group.anticor.screen.viewmodels.SendViewModelProviderFactory

class SendActivity : BaseActivity() {
    override fun getLayout(): Int = R.layout.activity_category_details
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
                            complain.region = region.name
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
                                complain.area = area.name
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
                        complain.organization = org.name
                    }

                    override fun onNothingSelected(parent: AdapterView<*>) {}
                }
            } else {
                Log.d("SendActivity", response.message())
            }
        })

        send.setOnClickListener {
            if (validate()) {
                complain.amount = amount.text.toString().toInt()
                complain.text = edComment.text.toString()
                viewModel.postComplain(complain)
                Toasty.success(this, R.string.success, Toast.LENGTH_SHORT).show()
            } else {
                Toasty.error(this, "Error", Toast.LENGTH_SHORT).show()
            }
        }

    }

    private fun fetchData() {

    }

    private fun validate(): Boolean {
        if (
            amount.text.isNullOrEmpty() ||
            currency.text.isNullOrEmpty() ||
            edComment.text.isNullOrEmpty() ||
            is_individual.text.isNullOrEmpty()
        ) {
            return false
        }

        return true
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

