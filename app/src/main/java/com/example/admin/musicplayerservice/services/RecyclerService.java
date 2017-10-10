package com.example.admin.musicplayerservice.services;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class RecyclerService extends Service {

    private static final String TAG = "RecyclerServiceTag";

    IBinder iBinder = new MyBinder();
    List<String> planets;

    public RecyclerService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.d(TAG, "onBind: ");
        return iBinder;
    }

    //using the binder
    public class MyBinder extends Binder {

        public RecyclerService getService() {
            return RecyclerService.this;
        }
    }

    public List<String> getPlanets() {
        planets = new ArrayList<>();

        planets.add( "Mercury" );
        planets.add( "Venus" );
        planets.add( "Earth" );
        planets.add( "Mars" );
        planets.add( "Jupiter" );
        planets.add( "Saturn" );
        planets.add( "Uranus" );
        planets.add( "Neptune" );

        return planets;
    }
}
