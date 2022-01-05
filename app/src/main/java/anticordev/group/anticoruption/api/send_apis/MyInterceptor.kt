package anticordev.group.anticoruption.api.send_apis

import anticordev.group.anticoruption.util.utils.Prefs
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

/**
 * @author Zokirjon
 * @date 11/25/2021
 */
class MyInterceptor: Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request: Request = chain.request()
            .newBuilder()
            .addHeader("Authorization", "Bearer " + Prefs.getToken())
            .build()

        return chain.proceed(request)
    }
}