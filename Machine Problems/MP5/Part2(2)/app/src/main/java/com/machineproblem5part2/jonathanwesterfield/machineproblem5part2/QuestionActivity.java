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

public class QuestionActivity extends AppCompatActivity
{

    TextView questionView;
    TextView choiceAView;
    TextView choiceBView;
    TextView choiceCView;
    TextView choiceDView;
    EditText answerBox;

    ArrayList<String> answerChoices;

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

        this.answerChoices = new ArrayList<String>();
        pullAnswerChoices();

        initializeInterfaces();
        populateViews();

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

    public void pullAnswerChoices()
    {
        for(String choice : this.questionObj.getAnswerChoices())
            this.answerChoices.add(choice);
    }

    public void populateViews()
    {
        this.questionView.setText(questionObj.getQuestion());
        this.choiceAView.setText(this.answerChoices.get(0));
        this.choiceBView.setText(this.answerChoices.get(1));
        this.choiceCView.setText(this.answerChoices.get(2));
        this.choiceDView.setText(this.answerChoices.get(3));
    }

    public void checkAnswer(View view)
    {
        String userAnswer = this.answerBox.getText().toString();

        // Sanitize the inputs
        userAnswer = userAnswer.trim();
        userAnswer = userAnswer.replaceAll("[-+.^:,\n\t\r]","");

        System.out.println("User Answer: " + userAnswer);

        if(!userAnswer.equalsIgnoreCase("A") && !userAnswer.equalsIgnoreCase("B")
                && !userAnswer.equalsIgnoreCase("C") && !userAnswer.equalsIgnoreCase("D"))
        {
            showBadInputAlert(view);
            return;
        }

    }

    public void showQuestionResultAlert(View view, boolean correct)
    {
        this.answerBox.getText().clear();

        String title;
        String message;

        if(correct)
        {
            title = "CORRECT!!!";
            message = "You got the correct answer! You've gained a point!";
        }
        else
        {
            title = "WRONG!!!";
            message = "Do generally better. You gain zero points.";
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Stop It. Get Some Help.").
                setMessage("Enter the letters A, B, C or D as your answer choices!")
                .setNeutralButton("OK", null);;

        // Create the AlertDialog
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public void showBadInputAlert(View view)
    {
        this.answerBox.getText().clear();

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Stop It. Get Some Help.").
                setMessage("Enter the letters A, B, C or D as your answer choices!")
                .setNeutralButton("OK", null);;

        // Create the AlertDialog
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
