package com.machineproblem4.jonathanwesterfield.part1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void btnClick(View view)
    {
        EditText editText = (EditText) findViewById(R.id.editText);
        String message = editText.getText().toString();
        TextView banner = (TextView) findViewById(R.id.textView);
        banner.setText(message);
        editText.setText("");
    }
}
