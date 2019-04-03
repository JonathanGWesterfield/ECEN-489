package com.mp8.jonathanwesterfield.machineproblem8;

import java.util.ArrayList;
import java.util.List;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder>
{
    private List<String> courseList;
    private List<String> gradesList;
    private List<String> studentList;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView txtHeader;
        public TextView txtFooter;
        public View layout;

        public ViewHolder(View v)
        {
            super(v);
            layout = v;
            txtHeader = (TextView) v.findViewById(R.id.courseNameView);
            txtFooter = (TextView) v.findViewById(R.id.gradeView);
        }
    }

    public void add(String student, String courseName, String grade)
    {
        studentList.add(student);
        courseList.add(courseName);
        gradesList.add(grade);
    }

    public void remove(int position)
    {
        studentList.remove(position);
        courseList.remove(position);
        gradesList.remove(position);
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public MyAdapter(List<String> studentNames, List<String> courseNames, List<String> grades)
    {
        studentList = studentNames;
        courseList = courseNames;
        gradesList = grades;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType)
    {
        // create a new view
        LayoutInflater inflater = LayoutInflater.from(
                parent.getContext());
        View v =
                inflater.inflate(R.layout.row_layout, parent, false);
        // set the view's size, margins, paddings and layout parameters
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position)
    {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        final String name = studentList.get(position);
        final String course = courseList.get(position);
        final String grade = gradesList.get(position);
        holder.txtHeader.setText(name + ", " + course);
        holder.txtHeader.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                remove(position);
            }
        });

        holder.txtFooter.setText(grade);
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount()
    {
        return courseList.size();
    }

}