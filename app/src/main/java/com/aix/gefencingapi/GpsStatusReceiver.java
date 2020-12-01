package com.aix.gefencingapi;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.location.LocationManager;
import android.widget.Toast;

public class GpsStatusReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().matches("android.location.PROVIDERS_CHANGED")) {

            LocationManager locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
            boolean isGpsEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
//            boolean isNetworkEnabled = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);

            if (isGpsEnabled) {

                Toast.makeText(context, "GPS ENABLED",
                        Toast.LENGTH_SHORT).show();
            } else {

                Toast.makeText(context, "GPS DISABLED",
                        Toast.LENGTH_SHORT).show();

            }


        }
    }
}
