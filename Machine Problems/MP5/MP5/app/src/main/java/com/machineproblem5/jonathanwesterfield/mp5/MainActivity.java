package com.machineproblem5.jonathanwesterfield.mp5;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.os.*;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public boolean onTextBtnClick(View view)
    {

    }

    public boolean onMediaBtnClick(View view)
    {

    }

    public boolean onImageBtnClick(View view)
    {

    }

    public boolean checkExtStorage()
    {
        /* Checks if external storage is available to at least read */
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state) ||
                Environment.MEDIA_MOUNTED_READ_ONLY.equals(state))
        {
            return true;
        }
        return false;
    }
}
