package profitdev.group.eantikor.api

import retrofit2.Response
import profitdev.group.eantikor.model.OneIdResponce
import javax.inject.Inject

class ApiHelperForRegister constructor(private val apiServiceForRegister: ApiServiceForRegister) {

    suspend fun getAuthData(code: String, state: String): Response<OneIdResponce> =
        apiServiceForRegister.getAuthData(code = code, state = state)

}