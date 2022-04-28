package anticordev.group.anticoruption.repository


import anticordev.group.anticoruption.api.send_apis.RetrofitInstance
import anticordev.group.anticoruption.base.toMultipartData
import anticordev.group.anticoruption.model.getToken.GetTokenResponse
import anticordev.group.anticoruption.model.getToken.UploadFileResponse
import anticordev.group.anticoruption.model.send_models.*
import okhttp3.MultipartBody
import retrofit2.Response
import retrofit2.Retrofit

class SendRepository {

    suspend fun getAreas(): Response<List<Area>> {
        return RetrofitInstance.api.getAreas()
    }

    suspend fun getOrganizations(): Response<List<Organization>> {
        return RetrofitInstance.api.getOrganizations()
    }

    suspend fun getRegionsById(regionId: Int): Response<Region> {
        return RetrofitInstance.api.getRegionsById(regionId)
    }

//    suspend fun postComplain(map: Map<String, MultipartBody.Part>): Response<Appeal>{
//        return RetrofitInstance.api.postComplain(map)
//    }

    suspend fun postComplain(
        regionId: Int,
        area: Int,
        organization: Int,
        amount: Int,
        currency: Int,
        file: MultipartBody.Part? = null,
        text: String,
        button_type: Int
    ): Response<Appeal> {
        return RetrofitInstance.api.postComplain(
            regionId.toMultipartData("region"),
            area.toMultipartData("area"),
            organization.toMultipartData("organization"),
            amount.toMultipartData("amount"),
            currency.toMultipartData("currency"),
            file,
            text.toMultipartData("text"),
            button_type.toMultipartData("button_type"),
        )
    }


    suspend fun getRegions(): Response<List<Region>> {
        return RetrofitInstance.api.getRegions()
    }
    suspend fun getToken(code:String,state:String): Response<GetTokenResponse> {
        return RetrofitInstance.api.getToken(code, state)
    }

    suspend fun chatUploadFile( file: MultipartBody.Part): Response<UploadFileResponse> {
        return RetrofitInstance.api.sendFile(file)
    }
}