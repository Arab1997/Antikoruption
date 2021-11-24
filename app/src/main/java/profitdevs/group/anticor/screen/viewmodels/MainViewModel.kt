package profitdevs.group.anticor.screen.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import profitdevs.group.anticor.util.utils.NetworkHelper
import profitdevs.group.anticor.util.utils.Resource
import profitdev.group.eantikor.api.ApiHelperForRegister
import profitdev.group.eantikor.model.OneIdResponce
import profitdevs.group.anticor.App
import profitdevs.group.anticor.api.send_apis.RetrofitInstance
import javax.inject.Inject

class MainViewModel : ViewModel() {
    private val networkHelper: NetworkHelper = NetworkHelper()
    private val apiHelperForRegister: ApiHelperForRegister by lazy{ ApiHelperForRegister(
        RetrofitInstance.apiRegister) }

    fun getCode(code: String, state: String): MutableLiveData<Resource<OneIdResponce>> {
        val codeData = MutableLiveData<Resource<OneIdResponce>>()
        viewModelScope.launch {
            codeData.postValue(Resource.loading(null))
            if (networkHelper.isNetworkConnected(App.app)) {
                apiHelperForRegister.getAuthData(code, state).let {
                    if (it.isSuccessful) {
                        codeData.postValue(Resource.success(it.body()))

                    } else {
                        codeData.postValue(Resource.error(it.errorBody().toString(), null))
                    }
                }
            } else codeData.postValue(Resource.error("No internet connection", null))
        }
        return codeData
    }
}