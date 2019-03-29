package com.mp8.jonathanwesterfield.machineproblem8;

import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

enum Character
{
    BART, LISA, RALPH, MILHOUSE;
}

public class PushActivity extends AppCompatActivity
{
    private EditText courseIDField, courseNameField, gradeField;
    private RadioButton bartBtn, lisaBtn, ralphBtn, milhousebtn;
    private Button pushBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_push);

        initInterfaces();
    }

    public void initInterfaces()
    {
        this.courseIDField = (EditText) findViewById(R.id.courseIDField);
        this.courseNameField = (EditText) findViewById(R.id.courseNameField);
        this.gradeField = (EditText) findViewById(R.id.gradeField);

        this.bartBtn = (RadioButton) findViewById(R.id.bartBtn);
        this.lisaBtn = (RadioButton) findViewById(R.id.lisaBtn);
        this.ralphBtn = (RadioButton) findViewById(R.id.ralphBtn);
        this.milhousebtn = (RadioButton) findViewById(R.id.milhouseBtn);

        this.pushBtn = (Button) findViewById(R.id.pushBtn);
    }
}
