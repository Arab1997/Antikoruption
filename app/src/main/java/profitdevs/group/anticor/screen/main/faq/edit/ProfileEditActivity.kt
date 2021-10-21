package profitdevs.group.anticor.screen.main.faq.edit

import com.redmadrobot.inputmask.MaskedTextChangedListener
import kotlinx.android.synthetic.main.activity_profile_edit.*
import profitdevs.group.anticor.base.BaseActivity
import profitdevs.group.anticor.screen.main.MainViewModel
import profitdevs.group.anticor.R

class ProfileEditActivity : BaseActivity() {
    override fun getLayout(): Int = R.layout.activity_profile_edit
    lateinit var viewModel: MainViewModel

    var dateListener: MaskedTextChangedListener? = null

    override fun initViews() {
        imgBackk.setOnClickListener {
            finish()
        }

       /* viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)

        viewModel.progressClientInfo.observe(this, Observer {
            setProgress(it)
        })

        viewModel.progressRegions.observe(this, Observer {
            setProgress(it)
        })

        viewModel.error.observe(this, Observer {
            showError(it)
        })

        viewModel.clientInfoData.observe(this, Observer {
            Prefs.setClientInfo(it)
            showSuccess(getString(R.string.refreshed))
            startClearActivity<SplashActivity>()
            finish()
        })

        viewModel.storesData.observe(this, Observer {
            initData()
        })

        dateListener = MaskedTextChangedListener(
            "[00].[00].[0000]",
            true,
            edBirthDay,
            null,
            null
        )
        edBirthDay.addTextChangedListener(dateListener)
        edBirthDay.onFocusChangeListener = dateListener

        btnEdit.setOnClickListener {
            viewModel.clientInfo(ClientInfoRequest(
                Prefs.getToken(),
                fio = edFullName.text.toString(),
                date = edBirthDay.text.toString().replace(".", ""),
                marketName = edStore.text.toString()
            ))
        }
*/

    }

    override fun loadData() {
        initData()
    }

    override fun initData() {
       // val user = Prefs.getClientInfo()

       /* edPhone.setText(user?.phone)
        edFullName.setText(user?.name)
        edStore.setText(user?.marketName)*/
       // edBirthDay.setText(DateUtils.getDateFromServerTime(user?.date))

    }

    override fun updateData() {

    }

}
