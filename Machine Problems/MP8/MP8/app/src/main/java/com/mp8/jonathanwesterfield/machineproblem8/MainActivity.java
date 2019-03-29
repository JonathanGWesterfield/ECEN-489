package com.mp8.jonathanwesterfield.machineproblem8;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity
{
    private EditText usernameField, passwdField;
    private Button createAcctBtn, loginBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initInterfaces();
    }

    public void initInterfaces()
    {
        this.usernameField = (EditText) findViewById(R.id.usernameField);
        this.passwdField = (EditText) findViewById(R.id.passwdField);
        this.createAcctBtn = (Button) findViewById(R.id.accountCreateBtn);
        this.loginBtn = (Button) findViewById(R.id.loginBtn);
    }
}
