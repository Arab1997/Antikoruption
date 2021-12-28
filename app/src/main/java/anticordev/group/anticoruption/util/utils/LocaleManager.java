package anticordev.group.anticoruption.util.utils;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.util.Log;

import com.orhanobut.hawk.Hawk;

import java.util.Locale;

public class LocaleManager {

    /**
     * set current pref locale
     */
    public static Context setLocale(Context mContext) {
        Log.d("prefs", getLanguagePref());
        return updateResources(mContext, getLanguagePref());
    }
    /**
     * Set new Locale with context
     */
    public static Context setNewLocale(Context mContext, String language) {
        setLanguagePref(language);
        return updateResources(mContext, language);
    }
    /**
     * Get saved Locale from SharedPreferences
     *
     * @return current locale key by default return english locale
     */
    public static String getLanguagePref() {
//        PrefsUtil prefsUtil = new PrefsUtil(mContext);
        return Hawk.get("pref_lang", "uz");
    }
    /**
     * set pref key
     */
    private static void setLanguagePref(String localeKey) {
        Hawk.put("pref_lang", localeKey);
    }
    /**
     * update resource
     */
    private static Context updateResources(Context context, String language) {
        Locale locale = new Locale(language);
        Locale.setDefault(locale);

        Resources res = context.getResources();

        //Log.d("prefs", Locale.getDefault().toString() + " >>> " +  language + " >>> " + res.getString(R.string.label_cards));
        Configuration config = new Configuration(res.getConfiguration());
        config.setLocale(locale);
        context = context.createConfigurationContext(config);
        res.updateConfiguration(config, res.getDisplayMetrics());
        return context;
    }
}