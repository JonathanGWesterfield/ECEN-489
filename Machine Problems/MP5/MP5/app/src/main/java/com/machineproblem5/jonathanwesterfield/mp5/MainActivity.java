package com.machineproblem5.jonathanwesterfield.mp5;

import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import android.view.View.*;


public class MainActivity extends AppCompatActivity {

    RadioButton dancQueen;
    RadioButton dontStopMe;
    RadioButton bunDem;
    int songChoice;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.dancQueen = (RadioButton) findViewById(R.id.dancQnBtn);
        OnClickListener dancQueenBtnClk = new OnClickListener ()
        {
            public void onClick(View v)
            {
                //Your Implementaions...
            }
        };
        this.dancQueen.setOnClickListener(dancQueenBtnClk);

        this.dontStopMe = (RadioButton) findViewById(R.id.dntStopMeNowBtn);
        OnClickListener dontStopMeNowBtnClk = new OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

            }
        };
        this.dontStopMe.setOnClickListener(dontStopMeNowBtnClk);

        this.bunDem = (RadioButton) findViewById(R.id.bunDemBtn);
        OnClickListener bunDemBtnClk = new OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

            }
        };
        this.bunDem.setOnClickListener(dontStopMeNowBtnClk);

    }


}
