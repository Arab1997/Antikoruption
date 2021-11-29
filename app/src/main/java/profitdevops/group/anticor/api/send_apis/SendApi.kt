package profitdevops.group.anticor.api.send_apis

import profitdevops.group.anticor.model.getToken.GetTokenResponse
import profitdevops.group.anticor.model.send_models.*
import retrofit2.Response
import retrofit2.http.*

interface SendApi {
//https://eanticor.uz/ru/api/ref/areas/
    @GET("ref/areas/")
    suspend fun getAreas(): Response<List<Area>>
//https://eanticor.uz/ru/api/ref/organizations/
    @GET("ref/organizations/")
    suspend fun getOrganizations(): Response<List<Organization>>

    @GET("ref/regions/{id}")
    suspend fun getRegionsById(
        @Path("id") regionId: Int
    ): Response<Region>

    //https://eanticor.uz/ru/api/ref/regions/
    @GET("ref/regions/")
    suspend fun getRegions(): Response<List<Region>>

    //https://eanticor.uz/uz/api/login-one/
    @POST("appeal/")
    suspend fun postComplain(
        @Body complain: Complain,
//        @Query("token") token: String = Prefs.getToken()
    ): Response<Complain>

   //https://eanticor.uz/ru/api/login-one/
    @POST("login-one/")
    suspend fun getToken(
        @Query("code") code:String,
        @Query ("state") state:String,
    ):Response<GetTokenResponse>
}