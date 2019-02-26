package com.musicman.jonathanwesterfield.musicman;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private String apiKey = "36c22d187f7b4bbeb0b749be85785cf7";
    private String startFeedURL = "https://newsapi.org/v2/top-headlines?country=us&" +
            "apiKey=" + apiKey;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
