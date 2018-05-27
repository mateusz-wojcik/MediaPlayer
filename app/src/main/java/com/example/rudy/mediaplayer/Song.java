package com.example.rudy.mediaplayer;

import android.media.MediaPlayer;

/**
 * Created by Rudy on 18.05.2018.
 */

public class Song {

    private String title, author, time;
    private int id;

    public Song(String title, String author, int id){
        this.title = title;
        this.author = author;
        this.id = id;
    }

    public String getSongTitle() {
        return title;
    }

    public void setSongTitle(String title) {
        this.title = title;
    }

    public String getSongAuthor() {
        return author;
    }

    public void setSongAuthor(String author) {
        this.author = author;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
