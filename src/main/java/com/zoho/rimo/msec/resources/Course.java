package com.zoho.rimo.msec.resources;

import java.util.ArrayList;

/**
 * Course.java- It is denoting the subject, that has a number and a name
 *
 * @author Rimo-7487
 * @version 1.0
 */
public class Course
{

    private String number;
    private String name;
    private ArrayList<Instructor> instructors;

    public Course(String number, String name, ArrayList<Instructor> instructors)
    {
        this.number = number;
        this.name = name;
        this.instructors = instructors;
    }

    public String getNumber()
    {
        return number;
    }

    public String getName()
    {
        return name;
    }

    public ArrayList<Instructor> getInstructors()
    {
        return instructors;
    }

    public String toString()
    {
        return name;
    }
}