package com.talabto.tlapaatotest;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;

import com.talabto.tlapaatotest.ui.MainActivity3;
import com.talabto.tlapaatotest.ui.MapsActivity;
import com.talabto.tlapaatotest.utils.Helper;
import com.tbruyelle.rxpermissions3.RxPermissions;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {
static boolean  is=true;
     RxPermissions rxPermissions;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        rxPermissions= new RxPermissions(this);


    }

    public void change(View view) {
        Log.d("vvvvvvvvvvv", "change: "+is);
        if (is)
        {
            is=false;
           // Helper.setLocal(getBaseContext(),"en");
            setLocal("en");
            recreate();

        }
        else
        {
            is=true;
           // Helper.setLocal(getBaseContext(),"ar");
            setLocal("ar");
            recreate();


        }



    }
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    private void setLocal(String lang)
    {
        Locale locale = new Locale(lang);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        if (Build.VERSION.SDK_INT >= 24) {
            config.setLocale(locale);
            getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
        } else {
            config.locale = locale;
            getBaseContext().getApplicationContext().createConfigurationContext(config);
        }



    }

    public void startMap(View view) {

        rxPermissions
                .request(Manifest.permission.ACCESS_FINE_LOCATION)
                .subscribe(granted -> {
                    if (granted) { // Always true pre-M
                        if (Helper.checkGPSONOROFF(getApplicationContext()))
                        {
                            startActivity(new Intent(getApplicationContext(), MapsActivity.class));
                        }
                        else
                        {
                            Intent intent=new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                            startActivity(intent);
                        }

                    } else {
                        // Oups permission denied
                    }
                });

    }

    public void slide(View view) {
        startActivity(new Intent(getApplicationContext(), MainActivity3.class));
    }
}