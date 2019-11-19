package com.zoho.rimo.msec.resources;

/**
 * MeetingTime.java- It is denoting the hour of the class
 * @author Rimo-7487
 * @version 1.0
 */
public class MeetingTime {

    private String id;
    private String time;

    public MeetingTime(String id, String time)
    {
        this.id = id;
        this.time = time;
    }

    public String getId()
    {
        return id;
    }

    public String getTime()
    {
        return time;
    }

}
