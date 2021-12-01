package anticordev.group.anticoruption.screen.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import anticordev.group.anticoruption.model.send_models.*
import anticordev.group.anticoruption.repository.SendRepository
import anticordev.group.anticoruption.util.utils.Prefs
import retrofit2.Response
class SendViewModel(
    val sendRepository: SendRepository
): ViewModel() {

    val regions: MutableLiveData<Response<List<Region>>> = MutableLiveData()
    val areas: MutableLiveData<Response<List<Area>>> = MutableLiveData()
    val organizations: MutableLiveData<Response<List<Organization>>> = MutableLiveData()
    val regionsById: MutableLiveData<Response<Region>> = MutableLiveData()
    val complains: MutableLiveData<Response<Complain>> = MutableLiveData()

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
        val response = sendRepository.postComplain(complain)
        complains.postValue(response)
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
}