package com.example.rudy.mediaplayer;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Rudy on 19.05.2018.
 */

public class Settings {
    private Boolean isLooping, isPlayingNext, isPlayingRandom;

    public Settings(Boolean isLooping, Boolean isPlayingNext, Boolean isPlayingRandom){
        this.isLooping = isLooping;
        this.isPlayingNext = isPlayingNext;
        this.isPlayingRandom = isPlayingRandom;
    }

    public boolean isPlayingRandom() {
        return isPlayingRandom;
    }

    public void setPlayingRandom(boolean playingRandom) {
        isPlayingRandom = playingRandom;
    }

    public boolean isPlayingNext() {
        return isPlayingNext;
    }

    public void setPlayingNext(boolean playingNext) {
        isPlayingNext = playingNext;
    }

    public boolean isLooping() {
        return isLooping;
    }

    public void setLooping(boolean looping) {
        isLooping = looping;
    }

}
