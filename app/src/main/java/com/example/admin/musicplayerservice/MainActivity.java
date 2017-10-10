package com.example.admin.musicplayerservice;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Switch;
import android.widget.Toast;

import com.example.admin.musicplayerservice.services.MusicService;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private MusicService musicSrv;
    private boolean isBound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent playIntent = new Intent(this, MusicService.class);
        bindService(playIntent, musicConnection, Context.BIND_AUTO_CREATE);
        //startService(playIntent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.status_bar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle status bar button clicks
        if( isBound ) {
            switch (item.getItemId()) {
                case R.id.play:
                    Log.d(TAG, "onOptionsItemSelected: Playing Song");
                    musicSrv.playSong();
                    return true;
                case R.id.pause:
                    Log.d(TAG, "onOptionsItemSelected: Pausing Song");
                    musicSrv.pauseSong();
                    return true;
                case R.id.stop:
                    Log.d(TAG, "onOptionsItemSelected: Stopping Song");
                    musicSrv.stopSong();
                    return true;
            }
        }

        return false;
    }

    //connect to the service
    private ServiceConnection musicConnection = new ServiceConnection(){

        @Override
        public void onServiceConnected( ComponentName name, IBinder service) {
            Log.d(TAG, "onServiceConnected: ");

            MusicService.MusicBinder binder = (MusicService.MusicBinder)service;
            musicSrv = binder.getService();
            isBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            isBound = false;
        }
    };

    public void buttonClicked(View view) {
        switch( view.getId() ) {
            case R.id.btnGoToRecycler:
                Intent intent = new Intent( this, RecyclerActivity.class );
                startActivity( intent );
        }
    }
}

/*
X 1. Create a music player app that will play the music in the foreground service. You can play,
     pause and stop the music from the status bar.
X 2. Use a bind service to get data and populate a recyclerView
3. Use the AlarmManager to send a notification after 10 secs on clicking each list item. The
   notification should have the object that was clicked on.

-Use any kind of object for the recyclerview
 */