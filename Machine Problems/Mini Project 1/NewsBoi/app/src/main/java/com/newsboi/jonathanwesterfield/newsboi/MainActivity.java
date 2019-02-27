package com.newsboi.jonathanwesterfield.newsboi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.reflect.TypeToken;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.*;
import com.google.gson.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    private String apiKey = "36c22d187f7b4bbeb0b749be85785cf7";
    private String newsURL = "https://newsapi.org/v2/top-headlines?" +
            "country=us&apiKey=" + apiKey;
    private Button getSavedButton;
    private Button refreshBtn;
    private TextView updateView;
    private ListView listView;
    private NewsAdapter newsAdapter;
    private NewsObj.Article articleList[];
    private int chosenArticle = -1;

    private NewsObj newsObj;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeInterfaces();
        getNews();

    }

    public void initializeInterfaces()
    {
        this.refreshBtn = (Button) findViewById(R.id.refreshButton);
        this.getSavedButton = (Button) findViewById(R.id.saveBtn);
        this.updateView = (TextView) findViewById(R.id.updateTimeTextView);
        this.listView = (ListView) findViewById(R.id.list);
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
                        System.out.println("\nGot the Articles\n");
                        setUpdatedView();
                        newsObj = result;
                        articleList = result.getArticles();
                        fillListView();
                    }
                });
    }

    /**
     * Displays when the most recent news update was
     */
    public void setUpdatedView()
    {
        DateFormat dateFormat = new SimpleDateFormat("h:mm a, MM/dd/yy ");
        Date date = new Date();
        this.updateView.setText("News Updated as of: " + dateFormat.format(date));
    }

    public void fillListView()
    {
        this.newsAdapter = new NewsAdapter(getApplicationContext(), articleList);
        this.listView.setAdapter(newsAdapter);
        setListViewClickListener();
    }

    /** Listener Section */

    public void onRefreshClk(View view)
    {
        getNews();
    }

    public void setListViewClickListener()
    {
        this.listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int articleIndex, long l)
            {
                switchToNewsLookActivity(articleIndex);
            }
        });
    }

    public void switchToNewsLookActivity(int index)
    {
        Intent showArticle = new Intent(this, NewsLookActivity.class);
        showArticle.putExtra("article_object", this.articleList[index]);
        startActivity(showArticle);
    }
}
