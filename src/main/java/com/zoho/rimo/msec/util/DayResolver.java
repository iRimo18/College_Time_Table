package com.zoho.rimo.msec.util;

public class DayResolver
{
    private DayResolver(){}

    public static final int TOTAL_WORKING_DAY = 3;

    public static String getDayOfTheWeek(int id)
    {
        switch (id) {
            case 1 : return "Mon";
            case 2 : return "Tue";
            case 3 : return "Wed";
            case 4 : return "Thu";
            case 5 : return "Fri";
            case 6 : return "Sat";
            default: throw new UnsupportedOperationException(id + " cannot be a working day of a week");
        }
    }
}
