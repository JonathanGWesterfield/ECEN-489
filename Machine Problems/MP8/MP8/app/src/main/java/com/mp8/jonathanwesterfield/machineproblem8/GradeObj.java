package com.mp8.jonathanwesterfield.machineproblem8;

import java.io.Serializable;

public class GradeObj implements Serializable
{
    private String courseName, grade;
    private int studentID, courseID;

    public GradeObj() { /* empty constructor */ }

    public GradeObj(int studentID, int courseID, String courseName, String grade)
    {
        this.studentID = studentID;
        this.courseID = courseID;
        this.courseName = courseName;
        this.grade = grade;
    }

    public int getCourseID()
    {
        return courseID;
    }

    public String getCourseName()
    {
        return courseName;
    }

    public String getGrade()
    {
        return grade;
    }

    public int getStudentID()
    {
        return studentID;
    }
}
