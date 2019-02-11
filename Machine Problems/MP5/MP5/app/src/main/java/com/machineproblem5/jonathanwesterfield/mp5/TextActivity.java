package com.machineproblem5.jonathanwesterfield.mp5;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.os.*;
import java.io.*;
import java.util.*;

public class TextActivity extends AppCompatActivity {

    File dir;
    ArrayList<String> fileList;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text);
        this.dir = android.os.Environment.getExternalStorageDirectory();

        //contains list of all files ending with
        this.fileList = new ArrayList<String>();
        getFilesList(this.dir);
        printFileList();

    }
    
    public void printFileList()
    {
        System.out.println("Files in External Storage:");
        if(!(fileList.size() > 0))
        {
            for (String file: this.fileList)
            {
                System.out.println("File: " + file);
            }
            return;
        }
        System.out.println("No Files Found!");

    }

    public void getFilesList(File dir) {
        File listFile[] = dir.listFiles();

        if (listFile != null)
        {
            for (int i = 0; i < listFile.length; i++)
            {

                if (listFile[i].isDirectory())
                {
                    // if its a directory need to get the files under that directory
                    getFilesList(listFile[i]);
                }
                else
                {
                    // add path of  files to your arraylist for later use

                    //Do what ever u want
                    fileList.add(listFile[i].getAbsolutePath());
                }
            }
        }
    }
}
