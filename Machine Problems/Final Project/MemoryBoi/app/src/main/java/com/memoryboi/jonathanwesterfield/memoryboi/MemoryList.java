package com.memoryboi.jonathanwesterfield.memoryboi;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class MemoryList extends Fragment
{
    private ListView memListView;
    private Encode encode;
    private ArrayList<MemoryObj> memoryList;
    private MemoryAdapter memAdap;

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
        sleepTight(800);
        this.memoryList = encode.getRetMemoryList();
        fillListView();
        return view;
    }

    public void fillListView()
    {
        while (this.memoryList == null)
        {
            System.out.println("Nothing returned yet. Keep asking");
            this.memoryList = encode.getRetMemoryList();
        }

        this.memAdap = new MemoryAdapter(getContext(), this.memoryList);
        this.memListView.setAdapter(this.memAdap);
    }

    /**
     * This is used to make the sure that we returned our results from the database. While it
     * is super terrible practice to wait for a set a a wait time instead of actually making sure
     * the results were returned, I just want to finish the project and be done
     * @param time
     */
    public void sleepTight(long time)
    {
        try
        {
            Thread.sleep(time);
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }


}
