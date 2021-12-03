package anticordev.group.anticoruption.api.send_apis

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import anticordev.group.anticoruption.util.utils.Prefs

class AppInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()
        return chain.proceed(getRequest(original))
    }

    private fun getRequest(original: Request): Request {
        val builder = original.newBuilder()
        builder.header("X-MobileLang", Prefs.getLang())
        builder.header("X-Mobile-Type", "android")
        if (Prefs.getToken().isNotEmpty()) {
            builder.addHeader("Authorization","Bearer ${Prefs.getToken()}")
        }
        builder.method(original.method, original.body)
        return builder.build()
    }
}