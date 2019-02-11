package com.machineproblem5.jonathanwesterfield.mp5;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.os.*;


public class MainActivity extends AppCompatActivity {

    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.textView = (TextView) findViewById(R.id.textView);
    }

    public void onTextBtnClick(View view)
    {
        if(!checkExtStorage())
        {
            System.err.println("External Storage could not be accessed\nExiting");
            System.exit(-1);
        }
        Intent intent = new Intent(this, TextActivity.class);
        startActivity(intent);
    }

    public void onImageBtnClick(View view)
    {
        if(!checkExtStorage())
        {
            System.err.println("External Storage could not be accessed\nExiting");
            System.exit(-1);
        }
        Intent intent = new Intent(this, ImageActivity.class);
        startActivity(intent);
    }

    public void onMediaBtnClick(View view)
    {
        if(!checkExtStorage())
        {
            System.err.println("External Storage could not be accessed\nExiting");
            System.exit(-1);
        }
        Intent intent = new Intent(this, MediaActivity.class);
        startActivity(intent);
    }

    public boolean checkExtStorage()
    {
        /* Checks if external storage is available to at least read */
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state) ||
                Environment.MEDIA_MOUNTED_READ_ONLY.equals(state))
        {
            textView.setText("External Storage Found");
            return true;
        }

        textView.setText("ERROR: Cannot Read From External Storage!!");
        return false;
    }
}
