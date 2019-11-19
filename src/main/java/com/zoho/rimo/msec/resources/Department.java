package com.zoho.rimo.msec.resources;

import java.util.ArrayList;

/**
 * Department.java- It is denoting the department with year that can be mentioned in the name
 * @author Rimo-7487
 * @version 1.0
 */
public class Department {

    private String name;
    private ArrayList<Course> courses;

    public Department(String name, ArrayList<Course> courses)
    {
        this.name = name;
        this.courses = courses;
    }

    public String getName()
    {
        return name;
    }

    public ArrayList<Course> getCourses()
    {
        return courses;
    }

}
