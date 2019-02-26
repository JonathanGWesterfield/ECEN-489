package com.newsboi.jonathanwesterfield.newsboi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.async.*;
import com.koushikdutta.ion.*;
import com.google.gson.*;

public class MainActivity extends AppCompatActivity {

    private String apiKey = "36c22d187f7b4bbeb0b749be85785cf7";
    private String newsURL = "https://newsapi.org/v2/top-headlines?" +
            "country=us&apiKey=" + apiKey;
    private Button getSavedButton;
    private TextView updateView;

    private NewsObj newsObj;
    private Gson gson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeInterfaces();
        getNews();
    }

    public void initializeInterfaces()
    {
        this.getSavedButton = (Button) findViewById(R.id.saveBtn);
        this.updateView = (TextView) findViewById(R.id.updateTimeTextView);
    }

    public void printNewsObj()
    {
        System.out.println(this.newsObj.toString());
    }

    public void getNews()
    {
        Ion.with(this)
                .load(this.newsURL)
                .as(new TypeToken<NewsObj>(){})
                .setCallback(new FutureCallback<NewsObj>()
                {
                    public void onCompleted(Exception e, NewsObj result)
                    {
                        // System.out.println("\n" + result);
                        // Log.v("ion", result.toString());
                        System.out.println("\nGot the Articles\n");
                        newsObj = result;
                        printNewsObj();
                    }
                });
    }
}
