package com.mp8.jonathanwesterfield.machineproblem8;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

// Import this so I don't have to explicitly call my enum class
// Can call like this <character = BART> instead of <character = Student.BART>
import java.util.HashMap;
import java.util.Map;

import static com.mp8.jonathanwesterfield.machineproblem8.Student.*;

/**
 * Making an Enum class is admittedly redundant but it makes the code more readable than
 * translating the radio buttons into an integer ID like 1,2,3,4. Also simplifies
 * things later on down the line as seen in the studentID's HashMap
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

    private DatabaseReference dbRef;
    private DatabaseReference dbTable;
    private FirebaseDatabase fireDB;
    private FirebaseAuth mAuth;
    private FirebaseUser fUser;

    // Static map for the student ID's
    private final Map<Student, Integer> studentIDs = new HashMap<Student, Integer>()
    {
        {
            put(BART, 123);
            put(LISA, 888);
            put(RALPH, 404);
            put(MILHOUSE, 456);
        }
    };

    private String courseName, grade;
    private int studentID, courseID;

    // set the default student to Bart since thats how it is in the radio group
    private Student student = BART;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_push);

        initInterfaces();
        getDBConn();
    }

    public void getDBConn()
    {
        mAuth = FirebaseAuth.getInstance();
        fUser = mAuth.getCurrentUser();
        fireDB = FirebaseDatabase.getInstance();
        dbRef = fireDB.getReference();
        dbTable = dbRef.child("simpsons/grades/");
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
        this.lisaBtn.setOnClickListener(lisaClk);

        this.ralphBtn = (RadioButton) findViewById(R.id.ralphBtn);
        View.OnClickListener ralphClk = new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                student = RALPH;
            }
        };
        this.ralphBtn.setOnClickListener(ralphClk);

        this.milhousebtn = (RadioButton) findViewById(R.id.milhouseBtn);
        View.OnClickListener milhouseClk = new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                student = MILHOUSE;
            }
        };
        this.milhousebtn.setOnClickListener(milhouseClk);

        this.pushBtn = (Button) findViewById(R.id.pushBtn);
    }

    public void pushClk(View view)
    {
        courseID = Integer.parseInt(courseIDField.getText().toString());
        courseName = courseNameField.getText().toString();
        grade = gradeField.getText().toString();
        studentID = studentIDs.get(student);

        //String studentID, String courseID, String courseName, String grade)
        GradeObj passObj = new GradeObj(studentID, courseID, courseName, grade);


        // DatabaseReference insLoc = dbRef.child("simpsons/grades/");
        DatabaseReference ranKey = dbTable.push();
        ranKey.setValue(passObj, new DatabaseReference.CompletionListener()
        {
            public void onComplete(DatabaseError err, DatabaseReference ref)
            {
                // no error
                if (err == null)
                    Toast.makeText(getApplicationContext(), "Finished uploading Grades",
                            Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(getApplicationContext(), "FAILED to upload Grades",
                            Toast.LENGTH_SHORT).show();
            }
        });

    }
}