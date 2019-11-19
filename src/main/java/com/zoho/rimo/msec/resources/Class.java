package com.zoho.rimo.msec.resources;

/**
 * Class.java- This is the Gene, basic unit, It is denoting the period, that has a dept name, subject, teacher and time
 * @author Rimo-7487
 * @version 1.0
 */
public class Class {

    private int id;
    private Department dept;
    private Course course;
    private Instructor instructor;
    private MeetingTime meetingTime;
    private int day;

    public Class(int id, Department dept, MeetingTime meetingTime)
    {
        this.id = id;
        this.dept = dept;
        this.meetingTime = meetingTime;
    }

    public void setInstructor(Instructor instructor)
    {
        this.instructor = instructor;
    }

    public void setCourse(Course course)
    {
        this.course = course;
    }


    public int getId()
    {
        return id;
    }

    public Department getDept()
    {
        return dept;
    }

    public Course getCourse()
    {
        return course;
    }

    public Instructor getInstructor()
    {
        return instructor;
    }

    public MeetingTime getMeetingTime()
    {
        return meetingTime;
    }

    public String toString()
    {
        return "{" + dept.getName() + "," + course.getName() + "," + instructor.getName() + "," + meetingTime.getTime()+ "}";
    }

    public void setDay(int day)
    {
        this.day = day;
    }

    public int getDay()
    {
        return day;
    }
}
