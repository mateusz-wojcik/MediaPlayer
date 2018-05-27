package com.example.rudy.mediaplayer;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final int SEEK_RATE = 10000;
    private static final int DELAY_MILLIS = 50;
    private static final int START_SONG_INDEX = 0;
    static Handler handler = new Handler();
    private ListView listView;
    private Songs songs;
    private static SeekBar seekBar;
    static Button playStopButton, forwardButton, rewindButton;
    static TextView titleTextView;
    private Runnable updateSeekbar = new Runnable() {
        @Override
        public void run() {
            seekBar.setMax(BackgroundServiceInstance.getInstance().getMediaPlayer().getDuration());
            seekBar.setProgress(BackgroundServiceInstance.getInstance().getMediaPlayer().getCurrentPosition());
            handler.postDelayed(this, DELAY_MILLIS);
        }
    };
    private BackgroundServiceInstance backgroundServiceInstance;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setToolBarBackground();
        setReferences();
        initService();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_activity_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public void setSeekBarListener(SeekBar seekBar){
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if(fromUser){
                    BackgroundServiceInstance.getInstance().getMediaPlayer().seekTo(progress);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    public void initService(){
        if(BackgroundServiceInstance.getInstance() == null){
            saveDefaultSettings();
            backgroundServiceInstance = new BackgroundServiceInstance();
            getApplicationContext().startService(new Intent(this, BackgroundService.class));
            playSong(START_SONG_INDEX);
        }
    }

    public void setReferences(){
        songs = new Songs();
        playStopButton = findViewById(R.id.buttonPlayStop);
        forwardButton = findViewById(R.id.buttonRight);
        rewindButton = findViewById(R.id.buttonLeft);
        listView = findViewById(R.id.listViewSong);
        titleTextView = findViewById(R.id.textViewAuthor);
        if(BackgroundServiceInstance.getInstance() != null) titleTextView.setText(songs.getSongArrayList().get(BackgroundServiceInstance.getInstance().getCurrentPlayingIndex()).getSongTitle());
        seekBar = findViewById(R.id.seekBar);
        setSeekBarListener(seekBar);
        calculateTimes();
        listView.setAdapter(new ListViewAdapter(this, songs.getSongArrayList()));
    }

    public void playSong(int songIndex){
        titleTextView.setText(songs.getSongArrayList().get(songIndex).getSongTitle());
        if(BackgroundServiceInstance.getInstance().getMediaPlayer() != null && BackgroundServiceInstance.getInstance().getMediaPlayer().isPlaying()){
            BackgroundServiceInstance.getInstance().stopSong();
        }
        BackgroundServiceInstance.getInstance().playSong(getApplicationContext(), songIndex);
        handler.postDelayed(updateSeekbar, 0);

    }

    public void playStopButtonOnClick(android.view.View view){
        if(BackgroundServiceInstance.getInstance().getMediaPlayer().isPlaying()){
            BackgroundServiceInstance.getInstance().pauseSong();
            playStopButton.setBackgroundResource(R.drawable.ic_play_circle_filled_black_24dp);
        }
        else {
            BackgroundServiceInstance.getInstance().playSong();
            playStopButton.setBackgroundResource(R.drawable.ic_pause_circle_filled_black_24dp);
        }
    }

    public void leftButtonOnClick(android.view.View view){
        BackgroundServiceInstance.getInstance().seekBackward();
    }

    public void rightButtonOnClick(android.view.View view){
        BackgroundServiceInstance.getInstance().seekForward();
    }

    public void aboutOnClick(MenuItem item){
        Intent intent = new Intent(getApplicationContext(), AboutActivity.class);
        intent.putExtra("id", BackgroundServiceInstance.getInstance().getCurrentPlayingIndex());
        startActivity(intent);
    }

    public void optionsOnClick(MenuItem item){
        Intent intent = new Intent(getApplicationContext(), OptionsActivity.class);
        startActivity(intent);
    }

    public void saveDefaultSettings(){
        SharedPreferences settings = getApplicationContext().getSharedPreferences("MyPreferences", 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putBoolean("isPlayingNext", true);
        editor.putBoolean("isPlayingRandom", false);
        editor.putBoolean("isLooping", false);
        editor.apply();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void setToolBarBackground(){
        getSupportActionBar().setBackgroundDrawable(getDrawable(R.drawable.toolbar_background));
    }

    public void readSettingsPreferences(){
        SharedPreferences settings = getApplicationContext().getSharedPreferences("MyPreferences", 0);

    }

    public SharedPreferences getSharedPreferences(){
        return getApplicationContext().getSharedPreferences("MyPreferences", 0);
    }

    public void calculateTimes(){
        ArrayList<Song> songList = songs.getSongArrayList();
        for(Song s : songList){
            s.setTime(calculateTime(s.getId()));
        }
    }

    public String calculateTime(int resourceID){
       int duration = MediaPlayer.create(MainActivity.this, resourceID).getDuration();
       int minutes = duration / 60000;
       int seconds = (duration % 60000) / 1000;
       return minutes + ":" + (seconds < 10 ? "0" + seconds : seconds);
    }


}
