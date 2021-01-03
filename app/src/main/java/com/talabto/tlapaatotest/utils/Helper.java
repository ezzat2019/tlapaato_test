package com.talabto.tlapaatotest.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Configuration;
import android.location.LocationManager;
import android.os.Build;

import java.util.Locale;

public class Helper {
    @SuppressLint("NewApi")
    public static void setLocal(Context context, String lang)
    {
        Locale locale = new Locale(lang);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        if (Build.VERSION.SDK_INT >= 24) {
            config.setLocale(locale);
            context.getResources().updateConfiguration(config, context.getResources().getDisplayMetrics());
        } else {
            config.locale = locale;
            context.getApplicationContext().createConfigurationContext(config);
        }


    }

    public static Boolean checkGPSONOROFF(Context context)
    {
        LocationManager manager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE );
        boolean statusOfGPS = manager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        return statusOfGPS;
    }
}
