package com.example.rudy.mediaplayer;

/**
 * Created by Rudy on 24.05.2018.
 */

public class Authors {
    private String[] authorsDescription = {
            "American violinist and singer. Born in 1986. 4 albums already",
            "Sample description",
            "NCS - No Copyright Sounds is a project which target is to create free and open music for everyone",
            "Nie mam pojecia kto to jest i skad to to tutaj",
            "Nie trzeba nikomu przedstawiac. Klasa sama w sobie"
    };
    private String[] authorsNames = {
            "Lindsey Stirling",
            "Sample name",
            "NCS",
            "Ketsa",
            "Krzysztof Krawczyk"
    };

    public Authors(){}

    public String[] getAuthorsDescription(){
        return authorsDescription;
    }

    public String[] getAuthorsNames(){
        return authorsNames;
    }
}
