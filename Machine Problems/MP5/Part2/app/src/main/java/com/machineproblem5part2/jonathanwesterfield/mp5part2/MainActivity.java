package com.machineproblem5part2.jonathanwesterfield.mp5part2;

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

public class MainActivity extends AppCompatActivity {

    ArrayList<QuizQuestion> questions;
    TextView scoreView;
    Button resetBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeInterfaces();
        parseQuizQuestions();
    }

    public void initializeInterfaces()
    {
        this.scoreView = (TextView) findViewById(R.id.scoreView);
        this.resetBtn = (Button) findViewById(R.id.resetBtn);
    }

    /**
     * Goes through the file of quiz questions and puts it into a question object. Then
     * stores in the questions object arraylist
     */
    public void parseQuizQuestions()
    {
        ArrayList<String> tempStorage = new ArrayList<>();

        Scanner scanner = new Scanner(getResources().openRawResource(R.raw.quiz_questions));

        for(int i = 0; scanner.hasNextLine(); i++)
        {
            for(int j = 0; j < 6; j++)
            {
                tempStorage.add(scanner.nextLine());
            }

            questions.add(new QuizQuestion(tempStorage));

            for(String element : tempStorage)
            {
                Log.d("LISTING", element);
            }
        }
    }
}
