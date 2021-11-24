package profitdevs.group.anticor.util.utils

import com.orhanobut.hawk.Hawk
import profitdev.group.eantikor.model.MobileData
import profitdevs.group.anticor.screen.main.MainActivity
import profitdevs.group.anticor.App

const val PREF_TOKEN = "pref_token"
const val PREF_USER_SETTINGS = "pref_user_settings"
const val PREF_FAVOURITE = "pref_favourites"
const val PREF_CART = "pref_cart"
const val PREF_CART_DATA = "pref_cart_data"
const val PREF_DISTRICT = "pref_district"
const val PREF_STORE_INFO = "pref_store_info"
const val PREF_STORE = "pref_store"
const val PREF_LANG = "pref_lang"
const val PREF_FCM = "pref_fcm"
const val PREF_SHOW_SALE = "pref_show_sale"
const val PREF_SERVER_DATA = "pref_server_data"
lateinit var APP_ACTIVITY: MainActivity
class Prefs {


    companion object{
        fun init(app: App){
            Hawk.init(app).build()
        }

        //d1cb7fe42c63d70cd6ad17475cbc78deab72f066
        fun getToken(): String{
            return Hawk.get(PREF_TOKEN, "")
        }

        fun setToken(token: String){
            Hawk.put(PREF_TOKEN, token)
        }

        fun getLang(): String{
            return Hawk.get(PREF_LANG, "en")
        }

        fun setLang(lang: String){
            Hawk.put(PREF_LANG, lang)
        }


        fun setShowSale(value: Boolean){
            Hawk.put(PREF_SHOW_SALE, value)
        }

        fun getShowSale(): Boolean{
            return Hawk.get(PREF_SHOW_SALE)
        }

        fun setShowEdit(value: Boolean){
            Hawk.put("show_edit", value)
        }

        fun getShowEdit(): Boolean{
            return Hawk.get("show_edit", true)
        }

        fun setFCM(value: String){
            Hawk.put(PREF_FCM, value)
        }

        fun getFCM(): String?{
            return Hawk.get(PREF_FCM)
        }

        fun clearCart(){
            Hawk.put(PREF_CART, null)
        }


        fun getFavourites(): List<String>{
            return Hawk.get<List<String>>(PREF_FAVOURITE, emptyList())
        }

        fun setFavouriteItem(item: String, favourite: Boolean){
            val items = getFavourites().toMutableList()
            if (favourite){
                items.forEach {
                    if (item == it){
                        return
                    }
                }

                items.add(0, item)
            }else{
                items.remove(item)
            }

            Hawk.put(PREF_FAVOURITE, items.toList())
        }

        fun isFavourite(item: String): Boolean{
            getFavourites().forEach {
                if (item == it){
                    return true
                }
            }
            return false
        }


        fun setServerData(value: MobileData){
            Hawk.put(PREF_SERVER_DATA, value)
        }

        fun getServerData(): MobileData?{
            return Hawk.get(PREF_SERVER_DATA)
        }

        fun clearAll(){
            Hawk.deleteAll()
        }
    }
}

