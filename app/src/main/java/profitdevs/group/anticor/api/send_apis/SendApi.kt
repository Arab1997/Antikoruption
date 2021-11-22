package profitdevs.group.anticor.api.send_apis

import profitdevs.group.anticor.model.send_models.Area
import profitdevs.group.anticor.model.send_models.Complain
import profitdevs.group.anticor.model.send_models.Organization
import profitdevs.group.anticor.model.send_models.Region
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

/**
 * @author Zokirjon
 * @date 11/16/2021
 */
interface SendApi {

    @GET("areas/")
    suspend fun getAreas(): Response<List<Area>>

    @GET("organizations/")
    suspend fun getOrganizations(): Response<List<Organization>>

    @GET("regions/{id}")
    suspend fun getRegionsById(
        @Path("id") regionId: Int
    ): Response<Region>

    @GET("regions/")
    suspend fun getRegions(): Response<List<Region>>

    @POST("appeal/")
    suspend fun postComplain(
        @Body complain: Complain
    ): Response<Complain>
}