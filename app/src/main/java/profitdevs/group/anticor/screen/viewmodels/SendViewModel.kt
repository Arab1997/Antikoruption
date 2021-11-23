package profitdevs.group.anticor.screen.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import profitdevs.group.anticor.model.send_models.Area
import profitdevs.group.anticor.model.send_models.Complain
import profitdevs.group.anticor.model.send_models.Organization
import profitdevs.group.anticor.model.send_models.Region
import profitdevs.group.anticor.repository.SendRepository
import retrofit2.Response

/**
 * @author Zokirjon
 * @date 11/16/2021
 */
class SendViewModel(
    val sendRepository: SendRepository
): ViewModel() {

    val regions: MutableLiveData<Response<List<Region>>> = MutableLiveData()
    val areas: MutableLiveData<Response<List<Area>>> = MutableLiveData()
    val organizations: MutableLiveData<Response<List<Organization>>> = MutableLiveData()
    val regionsById: MutableLiveData<Response<Region>> = MutableLiveData()

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
        sendRepository.postComplain(complain)
    }
}