package com.example.rudy.mediaplayer;

import android.content.SharedPreferences;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;

public class OptionsActivity extends AppCompatActivity {

    private RadioButton radioButtonPlayNext, radioButtonLoop, radioButtonRandom;
    private RadioGroup radioGroup;


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);
        setToolBarBackground();
        setReferences();
        setStartPositions();
    }

    @Override
    public void onBackPressed(){
        saveDataSharedPreferences();
        super.onBackPressed();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void setToolBarBackground(){
        getSupportActionBar().setBackgroundDrawable(getDrawable(R.drawable.toolbar_background));
    }

    public void setReferences(){
        radioGroup = findViewById(R.id.radioGroup);
        radioButtonPlayNext = findViewById(R.id.radioButtonPlayNext);
        radioButtonLoop = findViewById(R.id.radioButtonLoop);
        radioButtonRandom = findViewById(R.id.radioButtonRandom);
    }

    public void setStartPositions(){
        SharedPreferences prefs = getSharedPreferences();
        System.out.println(prefs.getBoolean("isPlayingRandom", false));
        radioButtonRandom.setChecked(prefs.getBoolean("isPlayingRandom", false));
        radioButtonLoop.setChecked(prefs.getBoolean("isLooping", false));
        radioButtonPlayNext.setChecked(prefs.getBoolean("isPlayingNext", true));
    }

    public void saveDataSharedPreferences(){
        SharedPreferences settings = getSharedPreferences();
        SharedPreferences.Editor editor = settings.edit();
        System.out.println(radioButtonRandom.isChecked());
        editor.putBoolean("isPlayingNext", radioButtonPlayNext.isChecked());
        editor.putBoolean("isPlayingRandom", radioButtonRandom.isChecked());
        editor.putBoolean("isLooping", radioButtonLoop.isChecked());
        editor.apply();
    }

    public SharedPreferences getSharedPreferences(){
        return getApplicationContext().getSharedPreferences("MyPreferences", 0);
    }

}
