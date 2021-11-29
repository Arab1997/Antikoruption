package profitdevops.group.anticor

import androidx.multidex.MultiDex
import androidx.multidex.MultiDexApplication
import com.google.android.libraries.places.api.Places
import profitdevops.group.anticor.api.ISTClient
import profitdevops.group.anticor.util.utils.Prefs
import java.util.*

class App : MultiDexApplication(){
    companion object{
        lateinit var app: App
    }
    override fun onCreate() {
        super.onCreate()
        app = this
        ISTClient.initClient(app)
        MultiDex.install(this)
        Prefs.init(this)
        // Log.d("JW", AppSignatureHelper(this).appSignatures[0])

        if (!Places.isInitialized()) {
            Places.initialize(app, "AIzaSyBUBf8yv4W9m35D_xAmsTFRQxTCcWv1SmI", Locale.ENGLISH)
            Places.createClient(this)
        }
    }
}