package anticordev.group.anticoruption.api.send_apis

import android.os.Build
import anticordev.group.anticoruption.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import anticordev.group.anticoruption.util.utils.Constants.Companion.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.security.KeyStore
import java.util.*
import java.util.concurrent.TimeUnit
import javax.net.ssl.SSLContext
import javax.net.ssl.TrustManagerFactory
import javax.net.ssl.X509TrustManager


class RetrofitInstance {

    companion object {
        private val retrofit by lazy {
            val logging = HttpLoggingInterceptor()
            logging.setLevel(HttpLoggingInterceptor.Level.BODY)

            Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(getOkHttpClient())
                .build()
        }

        val api: SendApi by lazy {
            retrofit.create(SendApi::class.java)
        }

        private fun getOkHttpClient(): OkHttpClient {
            val logging = HttpLoggingInterceptor()
            logging.setLevel(HttpLoggingInterceptor.Level.BODY)

            var builder = OkHttpClient().newBuilder()
            builder.retryOnConnectionFailure(false)
            builder.connectTimeout(60, TimeUnit.SECONDS)
            builder.writeTimeout(60, TimeUnit.SECONDS)
            builder.readTimeout(60, TimeUnit.SECONDS)
           // builder.addInterceptor(AppInterceptor())
            builder.addInterceptor(MyInterceptor())
            builder.addInterceptor(logging)
            builder = enableTls12OnPreLollipop(builder)
            if (BuildConfig.DEBUG) {
                builder.addInterceptor(HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                })
            }
            return builder.build()
        }

        private fun enableTls12OnPreLollipop(client: OkHttpClient.Builder): OkHttpClient.Builder {
            if (Build.VERSION.SDK_INT in 16..21) {
                try {
                    val trustManagerFactory =
                        TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm())
                    trustManagerFactory.init(null as KeyStore?)
                    val trustManagers = trustManagerFactory.trustManagers
                    if (trustManagers.size != 1 || trustManagers[0] !is X509TrustManager) {
                        throw IllegalStateException(
                            "Unexpected default trust managers:" + Arrays.toString(
                                trustManagers
                            )
                        )
                    }
                    val trustManager = trustManagers[0] as X509TrustManager
                    val sslContext = SSLContext.getInstance("SSL")
                    sslContext.init(null, arrayOf(trustManager), null)
                    val sslSocketFactory = sslContext.socketFactory
                    return client.sslSocketFactory(sslSocketFactory, trustManager)
                } catch (e: Exception) {
                    return client
                }
            } else {
                return client
            }
        }
    }
}