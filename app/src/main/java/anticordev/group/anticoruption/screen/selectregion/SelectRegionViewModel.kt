package anticordev.group.anticoruption.screen.selectregion

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import anticordev.group.anticoruption.model.RegionsModel

class SelectRegionViewModel : ViewModel(){
    val progress = MutableLiveData<Boolean>()
    val error = MutableLiveData<String>()
    val regionsData = MutableLiveData<List<RegionsModel>>()


}