package anticordev.group.anticoruption.api

import retrofit2.Response
import anticordev.group.anticoruption.model.OneIdResponce

class ApiHelperForRegister constructor(private val apiServiceForRegister: ApiServiceForRegister) {

    suspend fun getAuthData(code: String, state: String): Response<OneIdResponce> =
        apiServiceForRegister.getAuthData(code = code, state = state)

}