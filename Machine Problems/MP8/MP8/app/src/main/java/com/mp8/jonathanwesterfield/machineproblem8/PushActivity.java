package com.mp8.jonathanwesterfield.machineproblem8;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

// Import this so I don't have to explicitly call my enum class
// Can call like this <character = BART> instead of <character = Student.BART>
import static com.mp8.jonathanwesterfield.machineproblem8.Student.*;

/**
 * Making an Enum class is admittedly redundant but it makes the code more readable than
 * translating the radio buttons into an integer ID like 1,2,3,4
 */
enum Student
{
    BART, LISA, RALPH, MILHOUSE;
}

public class PushActivity extends AppCompatActivity
{
    private EditText courseIDField, courseNameField, gradeField;
    private RadioButton bartBtn, lisaBtn, ralphBtn, milhousebtn;
    private Button pushBtn;

    private String studentID, courseID, courseName, grade;

    // set the defaualt student to Bart since thats how it is in the radio group
    private Student student = BART;

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

        // Set the radiobuttons and their click listeners
        this.bartBtn = (RadioButton) findViewById(R.id.bartBtn);
        View.OnClickListener bartClk = new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                student = BART;
            }
        };
        this.bartBtn.setOnClickListener(bartClk);

        this.lisaBtn = (RadioButton) findViewById(R.id.lisaBtn);
        View.OnClickListener lisaClk = new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                student = LISA;
            }
        };
        this.bartBtn.setOnClickListener(lisaClk);

        this.ralphBtn = (RadioButton) findViewById(R.id.ralphBtn);
        View.OnClickListener ralphClk = new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                student = RALPH;
            }
        };
        this.bartBtn.setOnClickListener(ralphClk);

        this.milhousebtn = (RadioButton) findViewById(R.id.milhouseBtn);
        View.OnClickListener milhouseClk = new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                student = MILHOUSE;
            }
        };
        this.bartBtn.setOnClickListener(milhouseClk);

        this.pushBtn = (Button) findViewById(R.id.pushBtn);
    }

    // TODO: Implement the push button


}
