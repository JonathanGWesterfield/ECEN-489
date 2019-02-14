package com.machineproblem5part2.jonathanwesterfield.machineproblem5part2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import android.view.View.*;
import android.content.Context.*;
import java.util.*;
import android.util.Log;
import java.io.*;
import android.app.*;

import org.w3c.dom.Text;

public class QuestionActivity extends AppCompatActivity {

    TextView questionView;
    TextView choiceAView;
    TextView choiceBView;
    TextView choiceCView;
    TextView choiceDView;
    EditText answerBox;

    Button submitBtn;
    QuizQuestion questionObj;
    int currScore;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        Intent fromMain = getIntent();
        this.questionObj = (QuizQuestion)fromMain.getSerializableExtra("question_object");
        this.currScore = fromMain.getIntExtra("score", 0);

    }

    public void initializeInterfaces()
    {
        this.questionView = (TextView) findViewById(R.id.questionView);
        this.choiceAView = (TextView) findViewById(R.id.choiceAView);
        this.choiceBView = (TextView) findViewById(R.id.choiceBView);
        this.choiceCView = (TextView) findViewById(R.id.choiceCView);
        this.choiceDView = (TextView) findViewById(R.id.choiceDView);
        this.answerBox = (EditText) findViewById(R.id.answerBox);
        this.submitBtn = (Button) findViewById(R.id.submitBtn);
    }
}
