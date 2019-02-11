package com.machineproblem5.jonathanwesterfield.mp5;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.os.*;
import java.io.*;
import java.util.stream.*;
import java.util.*;
import java.nio.file.*;

public class TextActivity extends AppCompatActivity {

    File dir;
    ArrayList<String> fileList;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text);
        this.dir = android.os.Environment.getExternalStorageDirectory();
        this.textView = (TextView) findViewById(R.id.textActTextView);

        //contains list of all files ending with
        this.fileList = new ArrayList<String>();
        listAssetFiles(this.dir.getAbsolutePath());
        printFileList();

    }
    
    public void printFileList()
    {
        System.out.println("Number of Files in Storage: " + this.fileList.size());
        this.textView.setText("Number of Files in Storage: " + this.fileList.size());
        System.out.println("Files in External Storage:");

        for (int i = 0; i < fileList.size(); i++)
        {
            System.out.println("File: " + fileList.get(i));
            this.textView.append(fileList.get(i) + ", ");
        }

    }

    private boolean listAssetFiles(String path) {

        String [] list;
        try {
            list = getAssets().list(path);
            if (list.length > 0) {
                // This is a folder
                for (String file : list) {
                    if (!listAssetFiles(path + "/" + file))
                        return false;
                    else {
                        // This is a file
                        // TODO: add file name to an array list
                    }
                }
            }
        } catch (IOException e) {
            return false;
        }

        return true;
    }

}
