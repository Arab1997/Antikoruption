package profitdevs.group.anticor.api.send_apis

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
            .addHeader("token", "aab55f1973b3619d4843d21a41822f3588b6d58c")
            .build()

        return chain.proceed(request)
    }
}