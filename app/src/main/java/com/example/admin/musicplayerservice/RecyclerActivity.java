package com.example.admin.musicplayerservice;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.example.admin.musicplayerservice.services.RecyclerService;

import java.util.ArrayList;
import java.util.List;

public class RecyclerActivity extends AppCompatActivity {

    private static final String TAG = "MainActivityTag";
    private RecyclerService recyclerService;
    private boolean isBound;
    private List<String> planets;
    private RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager( this );
    RecyclerView.ItemAnimator itemAnimator;

    //create the service connection to bind the service
    ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            //iBinder is the one we return in our Bound Service
            Log.d(TAG, "onServiceConnected: ");

            RecyclerService.MyBinder myBinder = (RecyclerService.MyBinder) iBinder;
            recyclerService = myBinder.getService();

            isBound = true;

            planets = recyclerService.getPlanets();

            //layoutManager = new LinearLayoutManager( this );
            itemAnimator = new DefaultItemAnimator();
            recyclerView.setLayoutManager( layoutManager );
            recyclerView.setItemAnimator( itemAnimator );
            recyclerView.setAdapter( new PlanetListAdapter( planets ) );
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            Log.d(TAG, "onServiceDisconnected: ");
            isBound = false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler);

        Intent intent = new Intent( this, RecyclerService.class );
        bindService( intent, serviceConnection, Context.BIND_AUTO_CREATE);

        recyclerView = findViewById( R.id.rvPlanetList );
    }
}
