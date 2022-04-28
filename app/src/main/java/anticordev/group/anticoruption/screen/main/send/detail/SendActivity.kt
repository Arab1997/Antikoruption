package anticordev.group.anticoruption.screen.main.send.detail

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.OpenableColumns
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
import anticordev.group.anticoruption.R
import anticordev.group.anticoruption.model.send_models.*
import anticordev.group.anticoruption.repository.SendRepository
import anticordev.group.anticoruption.screen.viewmodels.SendViewModel
import anticordev.group.anticoruption.screen.viewmodels.SendViewModelProviderFactory
import android.widget.RadioButton

import android.widget.RadioGroup
import androidx.lifecycle.lifecycleScope
import anticordev.group.anticoruption.base.*
import anticordev.group.anticoruption.screen.main.MainActivity
import anticordev.group.anticoruption.screen.main.oneId.OneActivity
import anticordev.group.anticoruption.util.utils.Prefs
import com.blankj.utilcode.util.ToastUtils
import com.blankj.utilcode.util.Utils
import kotlinx.coroutines.launch
import okhttp3.MultipartBody
import java.io.FileNotFoundException

private const val PICK_FILE_FOR_UPLOAD = 1001

class SendActivity : BaseActivity() {

    override fun getLayout(): Int = R.layout.send_activity
    lateinit var viewModel: SendViewModel
    private var areas: MutableList<Area> = mutableListOf()
    private var regions: MutableList<Region> = mutableListOf()
    private lateinit var region: Region
    private lateinit var area: Area
    private var orgs: MutableList<Organization> = mutableListOf()
    private lateinit var org: Organization
    private val complain = Complain()
    private var file: Uri?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val sendRepository = SendRepository()
        val viewModelProviderFactory = SendViewModelProviderFactory(sendRepository)
        viewModel = ViewModelProvider(this, viewModelProviderFactory).get(SendViewModel::class.java)

        viewModel.getRegions()
        viewModel.regions.observe(this) { response ->
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
        }

        viewModel.regionsById.observe(this) { response ->
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
        }

        viewModel.getOrganizations()
        viewModel.organizations.observe(this) { response ->
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
        }

        viewModel.complains.observe(this) { response ->
            Log.d("RESPONSETAG", "onCreate: $response")
            if (response.isSuccessful && (response.code() in 200..299)) {
                // Toasty.success(this, R.string.success, Toast.LENGTH_SHORT).show()
                Toasty.success(this, getString(R.string.successs) + response.body().toString(), Toast.LENGTH_SHORT).show()

                startActivity<MainActivity>()
            } else {
                Toasty.warning(this, R.string.error, Toast.LENGTH_SHORT).show()
                //  Toasty.warning(this, response.code().toString(), Toast.LENGTH_LONG).show()
            }
        }

        currency.setOnCheckedChangeListener { radioGroup, i ->
            when (i) {
                2131362020 -> complain.currency = 0
                2131362448 -> complain.currency = 1
                // 2131362448 -> complain.currency = 2
                //  2131362448 -> complain.currency = 3
                else -> complain.currency = 0
            }
        }

        select_file.setOnClickListener {
            selectFile()
        }

        send.setOnClickListener {
            if (validate()) {
                complain.amount = amount.text.toString().toInt()
                complain.text = edComment.text.toString()

                viewModel.postComplain(complain, file?.toMultiPartData())
                file?.toMultiPartData()?.let { it1 -> viewModel.chatUploadFile(it1) }


            } else {
                Toasty.error(this, getString(R.string.error), Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun Uri.toMultiPartData(partName: String = "file"): MultipartBody.Part {
        val contentResolver = Utils.getApp().contentResolver
        val mime = contentResolver.getType(this).orEmpty()
        var size = 0L
        var name = "file"
        val inputStream = contentResolver.openInputStream(this)
            ?: throw FileNotFoundException("Failed to open input stream")

        contentResolver.query(this, null, null, null, null)?.use { cursor ->
            val sizeIndex = cursor.getColumnIndex(OpenableColumns.SIZE)
            val nameIndex = cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME)
            cursor.moveToFirst()

            size = cursor.getLong(sizeIndex)
            name = cursor.getString(nameIndex)
        }

//    val requestFile: RequestBody = object : RequestBody() {
//        override fun contentType() = mime.toMediaTypeOrNull()
//
//        override fun contentLength() = size
//
//        override fun writeTo(sink: BufferedSink) {
//            inputStream.source().use { source -> sink.writeAll(source) }
//        }
//    }

        return MultipartBody.Part.createFormData(partName, name)
    }

     fun selectFile() {
        var intent = Intent(Intent.ACTION_GET_CONTENT)
        intent.type = "*/*"
        intent = Intent.createChooser(intent, "Select a file")
        startActivityForResult(intent, PICK_FILE_FOR_UPLOAD)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, result: Intent?) {
        super.onActivityResult(requestCode, resultCode, result)
        val file = result?.data
        if (requestCode == PICK_FILE_FOR_UPLOAD && resultCode == RESULT_OK && file != null) {
            this.file = file
            tv_name_file.text = file.getName(this)
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