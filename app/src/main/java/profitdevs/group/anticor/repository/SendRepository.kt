package profitdevs.group.anticor.repository

import profitdevs.group.anticor.api.send_apis.RetrofitInstance
import profitdevs.group.anticor.model.send_models.Complain
import retrofit2.Response

/**
 * @author Zokirjon
 * @date 11/17/2021
 */
class SendRepository {

    fun getAreas() =
        RetrofitInstance.api.getAreas()

    fun getOrganizations() =
        RetrofitInstance.api.getOrganizations()

    fun getRegionsById(regionId: Int) =
        RetrofitInstance.api.getRegionsById(regionId)

    fun postComplain(complain: Response<Complain>) =
        RetrofitInstance.api.postComplain(complain)
}