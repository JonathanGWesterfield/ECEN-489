package com.mp8.jonathanwesterfield.machineproblem8;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity
{
    private VariousAlerts alert;
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
        this.alert = new VariousAlerts(getApplicationContext());

        this.usernameField = (EditText) findViewById(R.id.usernameField);
        this.passwdField = (EditText) findViewById(R.id.passwdField);
        this.createAcctBtn = (Button) findViewById(R.id.accountCreateBtn);
        this.loginBtn = (Button) findViewById(R.id.loginBtn);
    }

    /**
     * Creates an account based on whatever Username and Password is in the textfields
     * unless the textfields are empty
     * @param view
     */
    public void createAccount(View view)
    {
        // test if the textfields are empty
        if(this.usernameField.getText().toString().equalsIgnoreCase("") ||
                this.passwdField.getText().toString().equalsIgnoreCase(""))
            alert.showUserPasswdEmptyAlert(view);

        // TODO: Implement this
    }

    public void login(View view)
    {
        // test if the textfields are empty
        if(this.usernameField.getText().toString().equalsIgnoreCase("") ||
                this.passwdField.getText().toString().equalsIgnoreCase(""))
            alert.showUserPasswdEmptyAlert(view);
        // TODO: Implement this
    }


}
