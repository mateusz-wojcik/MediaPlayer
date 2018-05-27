package com.example.rudy.mediaplayer;

import java.util.ArrayList;

/**
 * Created by Rudy on 19.05.2018.
 */

public class Songs {
    static String[] songs = {
            "C:\\Users\\Rudy\\AndroidStudioProjects\\MediaPlayer\\app\\src\\main\\res\\raw\\crystallize.mp3",
            "C:\\Users\\Rudy\\AndroidStudioProjects\\MediaPlayer\\app\\src\\main\\res\\raw\\chcialembyc.mp3",
            "C:\\Users\\Rudy\\AndroidStudioProjects\\MediaPlayer\\app\\src\\main\\res\\raw\\song_2.mp3",
            "C:\\Users\\Rudy\\AndroidStudioProjects\\MediaPlayer\\app\\src\\main\\res\\raw\\song_1.mp3",
            "C:\\Users\\Rudy\\AndroidStudioProjects\\MediaPlayer\\app\\src\\main\\res\\raw\\song_3.mp3",
            "C:\\Users\\Rudy\\AndroidStudioProjects\\MediaPlayer\\app\\src\\main\\res\\raw\\song_4.mp3",
            "C:\\Users\\Rudy\\AndroidStudioProjects\\MediaPlayer\\app\\src\\main\\res\\raw\\song_5.mp3",
            "C:\\Users\\Rudy\\AndroidStudioProjects\\MediaPlayer\\app\\src\\main\\res\\raw\\elements.mp3"
    };

    public Songs(){
        fillSongList();
    }


    private ArrayList<Song> songArrayList;

    public void fillSongList(){
        songArrayList = new ArrayList<>();

        songArrayList.add(new Song("Crystallize", "Lindsey Stirling", R.raw.crystallize));
        songArrayList.add(new Song("Chcialbym byc", "Krysztof Krawczyk", R.raw.chcialembyc));
        songArrayList.add(new Song("Firefly", "NCS", R.raw.song_2));
        songArrayList.add(new Song("Costam", "Ketsa", R.raw.song_1));
        songArrayList.add(new Song("Cloud 9", "NCS", R.raw.song_3));
        songArrayList.add(new Song("Hope", "NCS", R.raw.song_4));
        songArrayList.add(new Song("Aperture", "NCS", R.raw.song_5));
        songArrayList.add(new Song("Elements", "Lindsey Stirling", R.raw.elements));

    }

    public ArrayList<Song> getSongArrayList(){
        return songArrayList;
    }
}
