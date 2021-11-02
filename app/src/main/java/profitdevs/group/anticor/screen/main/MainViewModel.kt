package profitdevs.group.anticor.screen.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import profitdev.group.eantikor.model.NewsModel
import profitdev.group.eantikor.model.ProductModel
import profitdev.group.eantikor.model.RegionsModel
import profitdev.group.eantikor.repository.UserRepository

class MainViewModel : ViewModel() {
    val userRepository = UserRepository()
    val error = MutableLiveData<String>()
    val progressRegions = MutableLiveData<Boolean>()
    val progressAds = MutableLiveData<Boolean>()
    val progress = MutableLiveData<Boolean>()
    val progressClientInfo = MutableLiveData<Boolean>()
    val progressCategories = MutableLiveData<Boolean>()
    val progressSections = MutableLiveData<Boolean>()
    val progressManufacturers = MutableLiveData<Boolean>()
    val progressProducts = MutableLiveData<Boolean>()
    val progressFavouriteProducts = MutableLiveData<Boolean>()
    val progressCartProducts = MutableLiveData<Boolean>()
    val progressProductDetail = MutableLiveData<Boolean>()
    val progressStoreInfo = MutableLiveData<Boolean>()
    val productsData = MutableLiveData<List<ProductModel>>()
    val favouriteProductsData = MutableLiveData<List<ProductModel>>()
    val cartProductsData = MutableLiveData<List<ProductModel>?>()
    val productDetailData = MutableLiveData<ProductModel>()
    val successRatingData = MutableLiveData<Boolean>()
    val newsData = MutableLiveData<List<NewsModel>>()
    val searchProductsData = MutableLiveData<List<ProductModel>>()
    val regions = MutableLiveData<RegionsModel>()

 /*   fun getRegions(id: String){
        userRepository.getRegions(id, progressRegions, error, regions)
    }*/

   /* fun getStores(){
        userRepository.getStores(progressRegions, error, storesData)
    }*/

   /* fun getSections(){
        userRepository.getSections(progressSections, error, sectionsData)
    }

    fun getBrandList(id: String){
        userRepository.getBrands(id, progressManufacturers, error, brandData)
    }

    fun getProductsByBrandId(id: String, categoryId: String){
        userRepository.getProducts(id, categoryId, progressProducts, error, productsData)
    }

    fun getFavouriteProducts(){
        userRepository.getFavouriteProducts(progressFavouriteProducts, error, favouriteProductsData)
    }

    fun getCartProducts(){
        userRepository.getCartProducts(progressCartProducts, error, cartProductsData)
    }

    fun getProductDetail(id: String){
        userRepository.getProductDetail(id, progressProductDetail, error, productDetailData)
    }

    fun getStoreInfo(){
        userRepository.getStoreInfo(progressStoreInfo, error, storeInfoData)
    }*/

  /*  fun createOrder(order: MakeOrderModel){
        userRepository.createOrder(order, progress, error, createOrderData)
    }*/

    /*fun clientInfo(request: ClientInfoRequest){
        userRepository.clientInfo(request, progressClientInfo, error, clientInfoData)
    }

    fun getOrders(){
        userRepository.getOrders(progress, error, ordersData)
    }

    fun postRating(request: RatingRequest){
        userRepository.postRating(request, progress, error, successRatingData)
    }

    fun getAds(){
        userRepository.getAds(progressAds, error, successAdsData)
    }

    fun getNews(){
        userRepository.getNews(progress, error, newsData)
    }*/

 /*   fun getProductsByName(keyword: String){
        userRepository.searchProductsByName(keyword, progress, error, searchProductsData)
    }*/

   /* fun getDeliveryLocation(id: String){
        userRepository.getDeliveryLocation(id, progress, error, deliveryLocation)
    }

    fun getActReport(startDate: String, endDate: String, storeId: String, dollar: Int){
        userRepository.getActReport(startDate, endDate, storeId, dollar, progress, error, actReport)
    }*/
}