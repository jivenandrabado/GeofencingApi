package com.aix.gefencingapi;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.location.Geofence;
import com.google.android.gms.location.GeofencingEvent;

import java.util.Date;
import java.util.List;

public class GeofenceBroadcastReceiver extends BroadcastReceiver {

    private static final String TAG = "WABBLER";
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(TAG, "onReceive: GEOFENCE TRIGGERED");
//        Toast.makeText(context, "GEOFENCE TRIGGERED", Toast.LENGTH_SHORT).show();
        NotificationHelper notificationHelper = new NotificationHelper(context);
        GeofencingEvent geofencingEvent = GeofencingEvent.fromIntent(intent);

        if(geofencingEvent.hasError()){
            Log.d(TAG, "onReceive: ERROR RECEIVING GEOFENCE");
            return;
        }

        List<Geofence> geofenceList = geofencingEvent.getTriggeringGeofences();

        int transitionType =geofencingEvent.getGeofenceTransition();

        for(Geofence geofence: geofenceList){
            Log.d(TAG, "onReceive: "+geofence.getRequestId());
        }

        switch (transitionType){
            case Geofence.GEOFENCE_TRANSITION_ENTER:
                Toast.makeText(context, "Entered the geofence", Toast.LENGTH_LONG).show();
                Log.d(TAG, "onReceive: Entered the geofence");
                notificationHelper.sendHighPriorityNotification("Entered Geofence","You have just entered the geofence "+new Date(), MapsActivity.class);
                break;

            case Geofence.GEOFENCE_TRANSITION_DWELL:
                Toast.makeText(context, "Dwell in the geofence", Toast.LENGTH_LONG).show();
                Log.d(TAG, "onReceive: Dwell in the geofence");
                notificationHelper.sendHighPriorityNotification("Dwell Geofence","You are dwelling in the geofence "+ new Date(), MapsActivity.class);
                break;

            case Geofence.GEOFENCE_TRANSITION_EXIT:
                Toast.makeText(context, "Exit the geofence", Toast.LENGTH_LONG).show();
                Log.d(TAG, "onReceive: Exit the geofence");
                notificationHelper.sendHighPriorityNotification("Exit Geofence","You have just exit the geofence "+ new Date(), MapsActivity.class);

                break;


        }

    }
}