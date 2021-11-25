package profitdevs.group.anticor.screen.selectregion

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import profitdevs.group.anticor.model.RegionsModel

class SelectRegionViewModel : ViewModel(){
    val progress = MutableLiveData<Boolean>()
    val error = MutableLiveData<String>()
    val regionsData = MutableLiveData<List<RegionsModel>>()


}