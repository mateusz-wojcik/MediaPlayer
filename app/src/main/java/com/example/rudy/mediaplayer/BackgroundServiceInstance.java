package com.example.rudy.mediaplayer;

/**
 * Created by Rudy on 23.05.2018.
 */

public class BackgroundServiceInstance {
    private static BackgroundService backgroundService;

    public BackgroundServiceInstance(){
        backgroundService = new BackgroundService();
    }

    public static BackgroundService getInstance(){
        return backgroundService;
    }
}
