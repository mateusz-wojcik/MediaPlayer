package com.example.rudy.mediaplayer;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class AboutActivity extends AppCompatActivity {

    private TextView authorTextView, descriptionTextView;
    private Authors authors;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        setToolBarBackground();
        setReferences();
        setText(getAuthorID(getIntent().getIntExtra("id", 0)));
    }


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void setToolBarBackground(){
        getSupportActionBar().setBackgroundDrawable(getDrawable(R.drawable.toolbar_background));
    }

    public void setReferences(){
        authorTextView = findViewById(R.id.textViewAuthor);
        descriptionTextView = findViewById(R.id.textViewDescription);
        authors = new Authors();
    }

    public int getAuthorID(int songId){
        switch (songId){
            case 0: return 0;
            case 1: return 4;
            case 2:
            case 4:
            case 5:
            case 6: return 2;
            case 3: return 3;
        }
        return 0;
    }

    public void setText(int index){
        authorTextView.setText(authors.getAuthorsNames()[index]);
        descriptionTextView.setText(authors.getAuthorsDescription()[index]);
    }
}
