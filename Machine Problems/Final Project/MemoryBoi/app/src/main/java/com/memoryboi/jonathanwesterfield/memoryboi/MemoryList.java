package com.memoryboi.jonathanwesterfield.memoryboi;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.*;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class MemoryList extends Fragment
{
    private ListView memListView;
    private Encode encode;
    private MemoryAdapter memAdap;

    private DatabaseReference dbRef;
    private DatabaseReference dbTable;
    private FirebaseDatabase fireDB;
    private FirebaseAuth mAuth;
    private FirebaseUser fUser;
    private ArrayList<MemoryObj> retMemoryList;

    public MemoryList()
    {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_camera_button, container, false);

        this.memListView = (ListView) view.findViewById(R.id.memoryListView);
        this.encode = new Encode();
        this.encode.pull();

        mAuth = FirebaseAuth.getInstance();
        fUser = mAuth.getCurrentUser();
        fireDB = FirebaseDatabase.getInstance();
        dbRef = fireDB.getReference();
        dbTable = dbRef.child("memories/");

        return view;
    }

    public void pull()
    {
        Query query = dbTable.orderByKey();
        query.addListenerForSingleValueEvent(getValueEventListener());
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
                    retMemoryList = new ArrayList<>();
                    //Toast.makeText(getApplicationContext(),"listening",Toast.LENGTH_SHORT).show();
                    for (DataSnapshot snapshot : dataSnapshot.getChildren())
                    {
                        MemoryObj memory = snapshot.getValue(MemoryObj.class);

                        retMemoryList.add(memory);
                    }
                    System.out.println("Query Finished");

                    fillMemoryList();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError)
            {
                System.out.println("ERROR: query failed");
            }
        };

        return valueEventListener;
    }

    public void fillMemoryList()
    {
        memAdap = new MemoryAdapter(getContext(), this.retMemoryList);
        this.memListView.setAdapter(memAdap);
    }


}
