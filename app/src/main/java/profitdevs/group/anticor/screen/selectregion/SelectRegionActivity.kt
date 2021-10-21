package profitdevs.group.anticor.screen.selectregion

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import profitdevs.group.anticor.base.BaseActivity
import profitdev.group.eantikor.base.showError
import profitdevs.group.anticor.R


class SelectRegionActivity : BaseActivity() {
    override fun getLayout(): Int = R.layout.activity_select_region

    lateinit var viewModel: SelectRegionViewModel

    override fun initViews() {
        viewModel = ViewModelProviders.of(this).get(SelectRegionViewModel::class.java)

        viewModel.progress.observe(this, Observer {
            setProgress(it)
        })

        viewModel.error.observe(this, Observer {
            showError(it)
        })

        viewModel.regionsData.observe(this, Observer {
            updateData()
        })
    }

    override fun loadData() {
       // viewModel.getRegions()
    }

    override fun initData() {

    }

    override fun updateData() {
//        recycler.layoutManager = LinearLayoutManager(this)
//        recycler.adapter = RegionsAdapter(viewModel.regionsData.value ?: emptyList(), object: BaseAdapterListener{
//            override fun onClickItem(item: Any?) {
//                val item = item as DistrictModel
//                Prefs.setDistrictId(item.id)
//                startClearTopActivity<SignActivity>(Constants.EXTRA_DATA, item)
//                finish()
//            }
//        })
    }

}
