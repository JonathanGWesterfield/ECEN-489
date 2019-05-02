package com.newsboi.jonathanwesterfield.newsboi;


import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import android.widget.Toast;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

import static android.content.Context.MODE_APPEND;


/**
 * A simple {@link Fragment} subclass.
 */
public class WebButtons extends Fragment
{

    private static final String ARG_PARAM1 = "article_object";
    Button saveBtn;
    Button backBtn;
    private NewsObj.Article article;
    private String saveFilePath = "saved_pages.txt";
    private DatabaseReference dbRef;
    private DatabaseReference dbTable;
    private FirebaseDatabase fireDB;
    private FirebaseAuth mAuth;
    private FirebaseUser fUser;

    public WebButtons() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_web_buttons, container, false);
        this.saveBtn = (Button) view.findViewById(R.id.saveBtn);
        setSaveBtnListener();

        this.backBtn = (Button) view.findViewById(R.id.backBtn);
        this.setBackBtnListener();

        mAuth = FirebaseAuth.getInstance();
        fUser = mAuth.getCurrentUser();
        fireDB = FirebaseDatabase.getInstance();
        dbRef = fireDB.getReference();
        dbTable = dbRef.child("locations/");

        return view;
    }

    public void setSaveBtnListener()
    {
        this.saveBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                onSave(view);
            }
        });
    }

    public void setBackBtnListener()
    {
        this.backBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                returnToPrevActivity(view);
            }
        });
    }

    @Override
    public void onActivityCreated(Bundle savedState)
    {
        super.onActivityCreated(savedState);

        Intent fromMain = getActivity().getIntent();
        this.article = (NewsObj.Article) fromMain.getSerializableExtra(ARG_PARAM1);
    }

    public void returnToPrevActivity(View view)
    {
        Intent mainIntent = new Intent();
        getActivity().finish();
    }

    /**
     * Push the location to firebase
     * @param location
     */
    public void push(LocationObj location)
    {
        DatabaseReference rankey = dbTable.push();
        rankey.setValue(location, new DatabaseReference.CompletionListener()
        {
            @Override
            public void onComplete(@Nullable DatabaseError databaseError, @NonNull DatabaseReference databaseReference)
            {
                // no error
                if (databaseError == null)
                    Toast.makeText(getContext(), "Successful Upload", Toast.LENGTH_SHORT)
                            .show();
                else
                    Toast.makeText(getContext(), "Upload FAILED", Toast.LENGTH_SHORT)
                            .show();
            }
        });

        return;
    }

    /**
     * Gets the user's current location
     * @return location object with the user's location
     */
    public LocationObj getLocation()
    {
        LocationManager locationManager = (LocationManager) getContext().getSystemService(Context.LOCATION_SERVICE);

        Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        if (location == null)
            location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);

        double latitude = 0.0, longitude = 0.0;

        if(location != null)
        {
            latitude = location.getLatitude();
            longitude = location.getLongitude();
        }

        return new LocationObj(latitude, longitude);
    }

    public void onSave(View view)
    {
        try
        {
            NewsPage newsPageFrag = (NewsPage) getFragmentManager().findFragmentById(R.id.newsPageFragment);

            Gson gson = new Gson();
            String articleJson = gson.toJson(article);
            System.out.println("JSON OBJECT");
            System.out.println(articleJson);

            if (isAlreadySaved(articleJson))
                newsPageFrag.showBadInputAlert(view);
            else
            {
                PrintStream out = new PrintStream(getActivity().openFileOutput(this.saveFilePath, MODE_APPEND));
                out.println(articleJson);
                out.close();
                System.out.println("\nSaved Article Object\n");
                newsPageFrag.showArticleSaved(view);
                LocationObj location = getLocation();
                push(location);


                // this.alreadySaved = true;
            }
        }
        catch (IOException e)
        {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    public boolean isAlreadySaved(String json)
    {
        try
        {
            Scanner scanner = new Scanner(getActivity().openFileInput(this.saveFilePath));
            String fileLine;

            while (scanner.hasNext())
            {
                fileLine = scanner.nextLine();
                if(fileLine.equalsIgnoreCase(json))
                    return true;
            }
            return false;
        }
        catch (IOException e)
        {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return false;
    }

}
