package profitdevs.group.anticor.api.send_apis

import okhttp3.Credentials
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import profitdev.group.eantikor.api.Client
import profitdevs.group.anticor.util.utils.Prefs

/**
 * @author Zokirjon
 * @date 11/23/2021
 */
class AppInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()
        return chain.proceed(getRequest(original))
    }

    fun getRequest(original: Request): Request {
        val builder = original.newBuilder()
        builder.addHeader("Content-Type", "application/json")
        builder.header("Connection", "close")
        builder.header("X-MobileLang", Prefs.getLang())
        builder.header("X-Mobile-Type", "android")
//        builder.addHeader("Authorization", Credentials.basic(Client.username, Client.password))
        if (!Prefs.getToken().isEmpty()) {
            builder.addHeader("token", Prefs.getToken())
        }
        builder.method(original.method, original.body)
        return builder.build()
    }
}