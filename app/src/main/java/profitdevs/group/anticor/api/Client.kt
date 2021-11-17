package profitdev.group.eantikor.api

import android.os.Build
import com.google.gson.GsonBuilder
import okhttp3.*
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import profitdevs.group.anticor.util.utils.Prefs
import java.util.concurrent.TimeUnit
import javax.net.ssl.SSLContext
import okhttp3.OkHttpClient
import java.security.KeyStore
import java.util.*
import javax.net.ssl.TrustManagerFactory
import javax.net.ssl.X509TrustManager


object Client {

    internal var retrofit: Retrofit? = null
    var username: String = ""
    var password: String = ""

    fun initClient(host: String, username: String, password: String) {
        Client.username = username
        Client.password = password
        val gson = GsonBuilder()
            .setLenient()
            .create()

        retrofit = Retrofit.Builder()
            .baseUrl(host)
            .client(getOkHttpClient())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    fun getInstance(): Retrofit{
        if (retrofit != null){
            return retrofit!!
        }else{
            Prefs.getServerData()
//            initClient(context, data!!.serverHost, data!!.username, data!!.password)
            return retrofit!!
        }
    }

    fun getOkHttpClient(): OkHttpClient {
        var builder = OkHttpClient().newBuilder()
        builder.retryOnConnectionFailure(false)
        builder.connectTimeout(60, TimeUnit.SECONDS)
        builder.writeTimeout(60, TimeUnit.SECONDS)
        builder.readTimeout(60, TimeUnit.SECONDS)
       /* if (BuildConfig.DEBUG) {
            builder.addInterceptor(ChuckInterceptor(context))
        }*/
        builder.addInterceptor(AppInterceptor())
        builder = enableTls12OnPreLollipop(builder)

        return builder.build()
    }

    private fun enableTls12OnPreLollipop(client: OkHttpClient.Builder): OkHttpClient.Builder {
        if (Build.VERSION.SDK_INT in 16..21) {
            try {
                val trustManagerFactory =
                    TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm())
                trustManagerFactory.init(null as KeyStore?)
                val trustManagers = trustManagerFactory.getTrustManagers()
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

    class AppInterceptor : Interceptor {
        override fun intercept(chain: Interceptor.Chain): Response {
            val original = chain.request()
            return chain.proceed(getRequest(original))
        }

        fun getRequest(original: Request): Request {
            var builder = original.newBuilder()
            builder.addHeader("Content-Type", "application/json")
            builder.header("Connection", "close")
            builder.header("X-MobileLang", Prefs.getLang())
            builder.header("X-Mobile-Type", "android")
            builder.addHeader("Authorization", Credentials.basic(username, password))
            if (!Prefs.getToken().isEmpty()) {
                builder.addHeader("token", Prefs.getToken())
            }
            builder.method(original.method, original.body)
            return builder.build()
        }
    }
}