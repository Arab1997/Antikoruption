package uz.group.eanticor.api

import io.reactivex.Observable
import profitdev.group.eantikor.api.BaseResponse
import profitdev.group.eantikor.model.LoginConfirmResponse
import profitdev.group.eantikor.model.NewsModel
import profitdev.group.eantikor.model.RegionsModel
import retrofit2.http.*
import profitdev.group.eantikor.model.request.CodeConfirmRequest

interface Api {

    @GET("GetStore")
    fun getRegions(): Observable<BaseResponse<List<RegionsModel>?>>
   /* @GET("GetStore")
    fun getRegions(): Observable<BaseResponse<List<StoreSimpleModel>?>>*/
  /*  @GET("SmsCheck")
    fun login(@Query("telephone") phone: String): Observable<BaseResponse<PhoneCheckResponse?>>*/

    @POST("RegistryClient")
    fun loginConfirm(@Body request: CodeConfirmRequest): Observable<BaseResponse<LoginConfirmResponse?>>

    @GET("GetNews")
    fun getNews(): Observable<BaseResponse<List<NewsModel>?>>

   }

