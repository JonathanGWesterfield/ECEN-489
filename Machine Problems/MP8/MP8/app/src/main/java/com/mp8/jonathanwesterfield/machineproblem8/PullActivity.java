package com.mp8.jonathanwesterfield.machineproblem8;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class PullActivity extends AppCompatActivity
{
    private RecyclerView recView;
    private Button query1Btn, query2Btn, pushBtn, signOutBtn;
    private EditText idField;
    private String idChoice;
    private DBAccesObj db;
    private FirebaseAuth mAuth;
    private FirebaseUser fUser;
    private String email, passwd;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pull);

        Intent intent = getIntent();

        this.email = intent.getExtras().getString("email");
        this.passwd = intent.getExtras().getString("passwd");

        mAuth = FirebaseAuth.getInstance();
        fUser = mAuth.getCurrentUser();

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
    // TODO: Implement both query buttons
}
