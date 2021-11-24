package profitdevs.group.anticor.repository

import profitdevs.group.anticor.api.send_apis.RetrofitInstance
import profitdevs.group.anticor.model.getToken.GetTokenResponse
import profitdevs.group.anticor.model.send_models.Area
import profitdevs.group.anticor.model.send_models.Complain
import profitdevs.group.anticor.model.send_models.Organization
import profitdevs.group.anticor.model.send_models.Region
import retrofit2.Response

/**
 * @author Zokirjon
 * @date 11/17/2021
 */
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

    suspend fun postComplain(complain: Complain): Response<Complain>{
        return RetrofitInstance.api.postComplain(complain)
    }

    suspend fun getRegions(): Response<List<Region>> {
        return RetrofitInstance.api.getRegions()
    }
    suspend fun getToken(code:String,state:String): Response<GetTokenResponse> {
        return RetrofitInstance.api.getToken(code, state)
    }
}