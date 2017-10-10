package com.example.admin.musicplayerservice.services;

import android.app.Service;
import android.content.ContentUris;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Binder;
import android.os.IBinder;
import android.os.PowerManager;
import android.util.Log;

import com.example.admin.musicplayerservice.R;

public class MusicService extends Service {

    private MediaPlayer player;    //media player
    private final IBinder musicBinder = new MusicBinder();

    public MusicService() {
    }

    @Override
    public void onCreate() {
        //create the service
        super.onCreate();

        //create player
        //player = new MediaPlayer();
        player = MediaPlayer.create( getApplicationContext(), R.raw.ogg_mp3_48kbps );
    }

    public void playSong(){
        player.start();
    }

    public void pauseSong() {
        if( player.isPlaying() )
            player.pause();
    }

    public void stopSong() {
        if( player.isPlaying() ) {
            player.stop();
            player = MediaPlayer.create( getApplicationContext(), R.raw.ogg_mp3_48kbps );
        }

        //player.pause();
        //player.seekTo(0);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return musicBinder;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        player.stop();
        player.release();
        return false;
    }

    public class MusicBinder extends Binder {
        public MusicService getService() {
            return MusicService.this;
        }
    }
}
