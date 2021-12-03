package anticordev.group.anticoruption.api.send_apis

import anticordev.group.anticoruption.model.getToken.GetTokenResponse
import anticordev.group.anticoruption.model.send_models.*
import retrofit2.Response
import retrofit2.http.*

@JvmSuppressWildcards
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
    @Multipart
    @POST("appeal/")
    suspend fun postComplain(
        @PartMap complain: Map<String, Any>,
    ): Response<Appeal>

    //https://eanticor.uz/ru/api/login-one/
    @POST("login-one/")
    suspend fun getToken(
        @Query("code") code:String,
        @Query ("state") state:String,
    ):Response<GetTokenResponse>
}