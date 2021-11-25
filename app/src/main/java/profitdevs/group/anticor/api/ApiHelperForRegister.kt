package profitdevs.group.anticor.api

import retrofit2.Response
import profitdevs.group.anticor.model.OneIdResponce

class ApiHelperForRegister constructor(private val apiServiceForRegister: ApiServiceForRegister) {

    suspend fun getAuthData(code: String, state: String): Response<OneIdResponce> =
        apiServiceForRegister.getAuthData(code = code, state = state)

}