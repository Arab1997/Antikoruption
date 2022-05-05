package anticordev.group.anticoruption.screen.main.send.detail

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.provider.OpenableColumns
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import anticordev.group.anticoruption.R
import anticordev.group.anticoruption.base.BaseActivity
import anticordev.group.anticoruption.base.getName
import anticordev.group.anticoruption.base.startActivity
import anticordev.group.anticoruption.model.send_models.Area
import anticordev.group.anticoruption.model.send_models.Complain
import anticordev.group.anticoruption.model.send_models.Organization
import anticordev.group.anticoruption.model.send_models.Region
import anticordev.group.anticoruption.repository.SendRepository
import anticordev.group.anticoruption.screen.main.MainActivity
import anticordev.group.anticoruption.screen.viewmodels.SendViewModel
import anticordev.group.anticoruption.screen.viewmodels.SendViewModelProviderFactory
import com.blankj.utilcode.util.NetworkUtils
import com.blankj.utilcode.util.Utils
import es.dmoral.toasty.Toasty
import kotlinx.android.synthetic.main.send_activity.*
import okhttp3.MultipartBody
import org.greenrobot.eventbus.EventBus
import java.io.FileNotFoundException
import java.io.IOException


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
    private var file: Uri? = null

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
                Toasty.success(this, getString(R.string.successs) + "\n" + response.body().toString(), Toast.LENGTH_SHORT).show()

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

     @Deprecated("Deprecated in Java")
     override fun onActivityResult(requestCode: Int, resultCode: Int, result: Intent?) {
        super.onActivityResult(requestCode, resultCode, result)
        val file = result?.data
        if (requestCode == PICK_FILE_FOR_UPLOAD && resultCode == RESULT_OK && file != null) {
            this.file = file
            tv_name_file.text = file.getName(this, file)
        }
    }

//    @Deprecated("Deprecated in Java")
//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        super.onActivityResult(requestCode, resultCode, data)
//        if (requestCode == PICK_FILE_FOR_UPLOAD && resultCode == RESULT_OK && null != data) {
//            val selectedImage = data.data
//            val filePathColumn = arrayOf(MediaStore.Images.Media.DATA)
//            val cursor = contentResolver.query(
//                selectedImage!!,
//                filePathColumn, null, null, null
//            )
//            cursor!!.moveToFirst()
//            val columnIndex = cursor.getColumnIndex(filePathColumn[0])
//            val picturePath = cursor.getString(columnIndex)
//            cursor.close()
//
//           // tv_name_file.text = file!!.getName(selectedImage)
//            tv_name_file.text = file!!.getPath(selectedImage)
//        }
//    }

//
//    @Deprecated("Deprecated in Java")
//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        super.onActivityResult(requestCode, resultCode, data)
//        if (requestCode == PICK_FILE_FOR_UPLOAD && resultCode == RESULT_OK) {
//            val uri = data!!.data
//            val path = getRealPathFromURI(applicationContext, uri)
//
//            Log.e("Check", "URI Path : " + uri!!.path)
//            Log.e("Check", "Real Path : $path")
//        }
//    }




//    // Method to get the absolute path of the selected image from its URI
//    @Deprecated("Deprecated in Java")
//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        super.onActivityResult(requestCode, resultCode, data)
//        if (requestCode == PICK_FILE_FOR_UPLOAD) {
//            if (resultCode == RESULT_OK) {
//                file = data!!.data // Get the image file URI
//
//            }
//        }
//    }


    @SuppressLint("Range")
    fun getRealPathFromURI(context: Context?, contentUri: Uri?): String? {
        var cursor = contentResolver.query(contentUri!!, null, null, null, null)
        cursor!!.moveToFirst()
        var document_id = cursor.getString(0)
        document_id = document_id.substring(document_id.lastIndexOf(":") + 1)
        cursor.close()
        cursor = contentResolver.query(
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
            null,
            MediaStore.Images.Media._ID + " = ? ",
            arrayOf(document_id),
            null
        )
        cursor!!.moveToFirst()
        val path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA))
        cursor.close()
        return path
    }



//    fun getRealPathFromURI(context: Context, contentUri: Uri?): String? {
//        var cursor: Cursor? = null
//        return try {
//            val proj = arrayOf(MediaStore.Images.Media.DATA)
//            cursor = context.getContentResolver().query(contentUri!!, proj, null, null, null)
//            val column_index: Int = cursor!!.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
//            cursor.moveToFirst()
//            cursor.getString(column_index)
//        } finally {
//            if (cursor != null) {
//                cursor.close()
//            }
//        }
//    }

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