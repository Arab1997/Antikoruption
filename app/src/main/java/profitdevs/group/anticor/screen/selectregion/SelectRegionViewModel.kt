package profitdevs.group.anticor.screen.selectregion

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import profitdev.group.eantikor.model.RegionsModel
import profitdev.group.eantikor.repository.UserRepository

class SelectRegionViewModel : ViewModel(){
    val repository = UserRepository()

    val progress = MutableLiveData<Boolean>()
    val error = MutableLiveData<String>()
    val regionsData = MutableLiveData<List<RegionsModel>>()

  /*  fun getRegions(){
        repository.getStores(progress, error, regionsData)
    }

    fun getSend(){
        repository.getStores(progress, error, regionsData)
    }*/

}