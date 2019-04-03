package com.mp8.jonathanwesterfield.machineproblem8;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.mp8.jonathanwesterfield.machineproblem8.Student.*;

public class PullActivity extends AppCompatActivity
{
    private RecyclerView recView;
    private Button query1Btn, query2Btn, pushBtn, signOutBtn;
    private EditText idField;
    private int idChoice;
    private DBAccesObj db;
    private DatabaseReference dbRef;
    private DatabaseReference dbTable;
    private FirebaseDatabase fireDB;
    private FirebaseAuth mAuth;
    private FirebaseUser fUser;
    private List<GradeObj> retGradeList;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    // Static map for the student ID's
    private final Map<Integer, String> studentIDs = new HashMap<Integer, String> ()
    {
        {
            put(123, "Bart");
            put(888, "Lisa");
            put(404, "Ralph");
            put(456, "Milhouse");
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pull);

        Intent intent = getIntent();

        getDBConn();

        initInterfaces();
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
        this.recView = (RecyclerView) findViewById(R.id.recyclerView);
        this.query1Btn = (Button) findViewById(R.id.query1Btn);
        this.query2Btn = (Button) findViewById(R.id.query2Btn);
        this.pushBtn = (Button) findViewById(R.id.pushBtn);
        this.signOutBtn = (Button) findViewById(R.id.signOutBtn);
        this.idField = (EditText) findViewById(R.id.studentIDField);
    }

    public void setSignOutBtn(View view)
    {
        mAuth.signOut();
        fUser = null;
        Toast.makeText(getApplicationContext(),"Logged Out",Toast.LENGTH_SHORT).show();
        finish();
    }

    public void pushBtnClk(View view)
    {
        Intent intent = new Intent(this, PushActivity.class);
        startActivity(intent);
    }


    /**
     * Click for query 1 which is get all grades for student with a particular ID
     * @param view
     */
    public void query1Clk(View view)
    {
        try
        {
            idChoice = Integer.parseInt(idField.getText().toString());

            Query query = dbTable.orderByChild("student_id").equalTo(idChoice);
            query.addListenerForSingleValueEvent(getValueEventListener());
        }
        catch (NumberFormatException e)
        {
            VariousAlerts.showFieldIsNumericAlert(view, this, "ID");
        }
    }

    /**
     * Click for query 2 which
     * @param view
     */
    public void query2Clk(View view)
    {
        try
        {
            idChoice = Integer.parseInt(idField.getText().toString());

            // The actual statement that queries the database
            Query query = dbTable.orderByChild("student_id").startAt(idChoice);

            query.addListenerForSingleValueEvent(getValueEventListener());
        }
        catch (NumberFormatException e)
        {
            VariousAlerts.showFieldIsNumericAlert(view, this, "ID");
        }
    }

    /**
     * Event listener for both queries. Since it just fills up the GradeObj arraylist,
     * can be used for both query 1 and 2.
     * @return
     */
    public ValueEventListener getValueEventListener()
    {
        ValueEventListener valueEventListener = new ValueEventListener()
        {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot)
            {
                if (dataSnapshot.exists())
                {
                    // clear the arraylist to refill it
                    retGradeList = new ArrayList<>();
                    //Toast.makeText(getApplicationContext(),"listening",Toast.LENGTH_SHORT).show();
                    for (DataSnapshot snapshot : dataSnapshot.getChildren())
                    {
                        GradeObj grade = snapshot.getValue(GradeObj.class);

                        retGradeList.add(grade);
                    }
                    Toast.makeText(getApplicationContext(), "Query Finished", Toast.LENGTH_SHORT).show();

                    fillAdapter();
                    showRecyclerView();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError)
            {
                Toast.makeText(getApplicationContext(), "Query Failed", Toast.LENGTH_SHORT).show();
            }
        };

        return valueEventListener;
    }

    public void showRecyclerView()
    {
        this.recView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recView.setLayoutManager(layoutManager);
        this.recView.setAdapter(mAdapter);
    }

    public void fillAdapter()
    {
        List<String> tempCourses = new ArrayList<>();
        List<String> tempGrades = new ArrayList<>();
        List<String> tempNames = new ArrayList<>();

        for (GradeObj obj : this.retGradeList)
        {
            tempNames.add(studentIDs.get(obj.getstudent_id()));
            tempCourses.add(obj.getcourse_name());
            tempGrades.add(obj.getgrade());
        }
        mAdapter = new MyAdapter(tempNames, tempCourses, tempGrades);
    }
}
