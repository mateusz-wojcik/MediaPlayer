package com.example.rudy.mediaplayer;

import android.annotation.TargetApi;
import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.widget.Toast;

import java.util.Random;

/**
 * Created by Rudy on 21.05.2018.
 */
public class BackgroundService extends IntentService {

    private static final int SEEK_RATE = 10000;
    private int currentPlayingIndex;
    private Context context;
    private Songs songs;
    private MediaPlayer mediaPlayer;
    private Random r;
    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param // name Used to name the worker thread, important only for debugging.
     */
    public BackgroundService(){
        super("Background running service");
        this.songs = new Songs();
        mediaPlayer = new MediaPlayer();
    }

    public BackgroundService(String name) {
        super(name);
    }


    @Override
    protected void onHandleIntent(Intent workIntent) {
        // Gets data from the incoming Intent
        String dataString = workIntent.getDataString();
        // Do work here, based on the contents of dataString
        //Toast.makeText(getApplicationContext(), "BackgroundServiceRunning", Toast.LENGTH_SHORT).show();
        //context = getApplicationContext();
        //mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.crystallize);
        //mediaPlayer.start();
        //setOnCompletionListener();
    }


    public void setOnCompletionListener(){
        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {

            @Override
            public void onCompletion(MediaPlayer mp) {
                SharedPreferences prefs = getSharedPreferences();
                if(prefs.getBoolean("isLooping", false)){
                    restartCurrentSong();
                }
                else if(prefs.getBoolean("isPlayingNext", true)){
                    playNextSong();
                }
                else if(prefs.getBoolean("isPlayingRandom", false)){
                    playRandomSong();
                }
            }
        });
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public void sendNotification(String title, String author) {
        Intent intent = new Intent(context, MainActivity.class);
        PendingIntent pIntent = PendingIntent.getActivity(context, (int) System.currentTimeMillis(), intent, 0);

        Notification n  = new Notification.Builder(context)
                .setContentTitle(title)
                .setContentText(author)
                .setSmallIcon(R.drawable.ic_queue_music_black_24dp)
                //.setSmallIcon(R.drawable.icon)
                //.setContentIntent(pIntent)
                .setAutoCancel(true)
                .addAction(R.drawable.ic_queue_music_black_24dp, "Music", pIntent)
                .build();

        NotificationManager notificationManager =
                (NotificationManager) context.getSystemService(NOTIFICATION_SERVICE);

        notificationManager.notify(0, n);
    }

    public void playRandomSong(){
        r = new Random();
        currentPlayingIndex = r.nextInt(songs.getSongArrayList().size());
        Toast.makeText(context, "Random song", Toast.LENGTH_SHORT).show();
        playSong(context, currentPlayingIndex);
        setTitle(songs.getSongArrayList().get(currentPlayingIndex).getSongTitle());
    }

    public void playNextSong(){
        if(currentPlayingIndex == songs.getSongArrayList().size() - 1){
            currentPlayingIndex = 0;
            playSong(context, currentPlayingIndex);
        }
        else {
            Toast.makeText(context, "Next song", Toast.LENGTH_SHORT).show();
            currentPlayingIndex++;
            playSong(context, currentPlayingIndex);
        }
        setTitle(songs.getSongArrayList().get(currentPlayingIndex).getSongTitle());
    }

    public void playSong(Context context, int songIndex){
        if(mediaPlayer.isPlaying()) mediaPlayer.stop();
        mediaPlayer = MediaPlayer.create(context, songs.getSongArrayList().get(songIndex).getId());
        this.context = context;
        currentPlayingIndex = songIndex;
        setOnCompletionListener();
        mediaPlayer.start();
        sendNotification(songs.getSongArrayList().get(songIndex).getSongTitle(), songs.getSongArrayList().get(songIndex).getSongAuthor());
        //handler.postDelayed(updateSeekbar, 0);
    }

    public void playSong(){
        mediaPlayer.start();
    }

    public void pauseSong(){
        mediaPlayer.pause();
    }

    public void stopSong(){
        mediaPlayer.stop();
    }

    public void seekForward(){
        mediaPlayer.seekTo(mediaPlayer.getCurrentPosition() + SEEK_RATE);
    }

    public void seekBackward(){
        mediaPlayer.seekTo(mediaPlayer.getCurrentPosition() - SEEK_RATE);
    }

    public boolean isPlaying(){
        return mediaPlayer.isPlaying();
    }

    public void restartCurrentSong(){
        mediaPlayer.pause();
        mediaPlayer.seekTo(0);
        mediaPlayer.start();
    }

    public SharedPreferences getSharedPreferences(){
        return context.getSharedPreferences("MyPreferences", 0);
    }

    public MediaPlayer getMediaPlayer(){
        return mediaPlayer;
    }

    public void setMediaPlayer(MediaPlayer mediaPlayer){
        this.mediaPlayer = mediaPlayer;
    }

    public int getCurrentPlayingIndex(){
        return currentPlayingIndex;
    }

    public void setTitle(String title){
        MainActivity.titleTextView.setText(title);
    }

}