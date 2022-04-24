package anticordev.group.anticoruption.screen.viewmodels

import android.util.Log
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import anticordev.group.anticoruption.base.startActivity
import anticordev.group.anticoruption.model.getToken.UploadFileResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import anticordev.group.anticoruption.model.send_models.*
import anticordev.group.anticoruption.repository.SendRepository
import anticordev.group.anticoruption.screen.main.MainActivity
import anticordev.group.anticoruption.screen.main.aboutapp.AboutAppActivity
import anticordev.group.anticoruption.util.utils.Prefs
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.toRequestBody
import retrofit2.Response
import java.lang.Exception


class SendViewModel(
    val sendRepository: SendRepository
): ViewModel() {

    val regions: MutableLiveData<Response<List<Region>>> = MutableLiveData()
    val areas: MutableLiveData<Response<List<Area>>> = MutableLiveData()
    val organizations: MutableLiveData<Response<List<Organization>>> = MutableLiveData()
    val regionsById: MutableLiveData<Response<Region>> = MutableLiveData()
    val complains: MutableLiveData<Response<Appeal>> = MutableLiveData()
    val uploadResponse = MutableLiveData<Response<UploadFileResponse>>()

    fun getRegions() = viewModelScope.launch {
        val response = sendRepository.getRegions()
        regions.value = response
    }

    fun getAreas() = viewModelScope.launch {
        val response = sendRepository.getAreas()
        areas.postValue(response)
    }

    fun getOrganizations() = viewModelScope.launch {
        val response = sendRepository.getOrganizations()
        organizations.postValue(response)
    }

    fun getRegionsById(regionId: Int) = viewModelScope.launch {
        val response = sendRepository.getRegionsById(regionId)
        regionsById.postValue(response)
    }

    fun postComplain(complain: Complain) = viewModelScope.launch {
        try{
            var map = HashMap<String, Any>()
            map["area"] = complain.area
            map["region"] = complain.region
            map["organization"] = complain.organization
            map["amount"] = complain.amount
            map["currency"] = complain.currency
            map["button_type"] = complain.button_type
            map["text"] = complain.text
            val request = sendRepository.postComplain(map)

            Log.d("SendViewModelTAG", request.toString())
            complains.postValue(request)
        } catch (e:Exception){
            e.printStackTrace()
        }
    }

    //getToken: bbb55f1973b3619d4843d21a41822f3588b6d58c
    fun getToken(code: String, state: String) {
        viewModelScope.launch(context = Dispatchers.IO) {
            val data = sendRepository.getToken(code, state)

            if (data.isSuccessful) {
                data.body()?.token?.let { Prefs.setToken(it) }
                Log.d("SendViewModelTAG", "getToken: ${data.body()?.token}")
                Prefs.getToken()
                Log.d("savetoken",  Prefs.getToken())

            }

        }
    }

    fun chatUploadFile(
        file: MultipartBody.Part
    ) {
        viewModelScope.launch {
           val data =  sendRepository.chatUploadFile(file)
            uploadResponse.postValue(data)
        }
    }
}