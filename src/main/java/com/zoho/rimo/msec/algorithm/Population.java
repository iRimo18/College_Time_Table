package com.zoho.rimo.msec.algorithm;

import com.zoho.rimo.msec.InputDetails;

import java.util.ArrayList;
import java.util.stream.IntStream;

/**
 * Population.java- This is the Population, that has number of Schedules(Chromosome) and has a custom sorting logic
 *
 * @author Rimo-7487
 * @version 1.0
 */
public class Population
{

    private ArrayList<Schedule> schedules;

    public Population(int size, InputDetails data)
    {
        schedules = new ArrayList<>(size);
        IntStream.range(0, size).forEach(x -> schedules.add(new Schedule(data).initialize()));
    }

    public ArrayList<Schedule> getSchedules()
    {
        return this.schedules;
    }

    public void setSchedules(ArrayList<Schedule> schedules)
    {
        this.schedules = schedules;
    }

    public Population sortByFitness()
    {
        schedules.sort((schedule1, schedule2) -> {
            int returnValue = 0;

            if (schedule1.getFitness() > schedule2.getFitness()) {
                returnValue = -1; // don't swap
            }
            else if (schedule1.getFitness() < schedule2.getFitness()) {
                returnValue = 1; // swap
            }

            return returnValue;
        });

        return this;
    }
}
