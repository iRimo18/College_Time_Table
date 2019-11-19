package com.zoho.rimo.msec.resources;

/**
 * Instructor.java- It is denoting the subject teacher
 * @author Rimo-7487
 * @version 1.0
 */
public class Instructor {

    private String id;
    private String name;

    public Instructor(String id, String name)
    {
        this.id = id;
        this.name = name;
    }

    public String getId()
    {
        return id;
    }

    public String getName()
    {
        return name;
    }

    public String toString()
    {
        return name;
    }

}
