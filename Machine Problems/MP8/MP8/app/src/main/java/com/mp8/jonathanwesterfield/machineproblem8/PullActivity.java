package com.mp8.jonathanwesterfield.machineproblem8;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;
import android.widget.EditText;

public class PullActivity extends AppCompatActivity
{
    private RecyclerView recView;
    private Button query1Btn, query2Btn, pushBtn, signOutBtn;
    private EditText idField;
    private String idChoice;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pull);

        initInterfaces();
    }

    public void initInterfaces()
    {
        this.recView = (RecyclerView) findViewById(R.id.recyclerView);
        this.query1Btn = (Button) findViewById(R.id.query1Btn);
        this.query2Btn = (Button) findViewById(R.id.query2Btn);
        this.pushBtn = (Button) findViewById(R.id.pushBtn);
        this.signOutBtn = (Button) findViewById(R.id.signOutBtn);
        this.idField = (EditText) findViewById(R.id.studentIDField);
    }

    // TODO: Implement signout, both query buttons and push button
}
