package com.zoho.rimo.msec.algorithm;

import com.zoho.rimo.msec.InputDetails;
import com.zoho.rimo.msec.resources.Class;
import com.zoho.rimo.msec.resources.Course;

import java.util.ArrayList;

import static com.zoho.rimo.msec.util.DayResolver.TOTAL_WORKING_DAY;

/**
 * Schedule.java- This is the Chromosome Class that generates periods and gives fitness based of various conflicts
 *
 * @author Rimo-7487
 * @version 1.0
 */
public class Schedule
{

    private ArrayList<Class> classes;
    private boolean isFitnessChanged = true;
    private double fitness = -1;
    private int classNumb = 0;
    private int numbOfConflicts = 0;
    private InputDetails data;

    public InputDetails getData()
    {
        return data;
    }

    public Schedule(InputDetails data)
    {
        this.data = data;
        classes = new ArrayList<>();
    }

    public Schedule initialize()
    {
        // generating each period, for each dept and each subject, here class is a period
        for (int day = 1; day <= TOTAL_WORKING_DAY; day++) {
            int finalDay = day;
            new ArrayList<>(data.getDepts()).forEach(dept -> new ArrayList<>(data.getMeetingTimes()).forEach(meetingTime -> {
                Class newClass = new Class(classNumb++, dept, meetingTime);
                Course course = dept.getCourses().get((int) (dept.getCourses().size() * Math.random()));
                newClass.setCourse(course);
                newClass.setInstructor(course.getInstructors().get((int) (course.getInstructors().size() * Math.random())));
                newClass.setDay(finalDay);
                classes.add(newClass);
            }));
        }
        return this;
    }

    public int getNumberOfConflicts()
    {
        return numbOfConflicts;
    }

    public ArrayList<Class> getClasses()
    {
        isFitnessChanged = true;
        return classes;
    }

    public double getFitness()
    {
        if (isFitnessChanged) {
            fitness = calculateFitness();
            isFitnessChanged = false;
        }

        return fitness;
    }

    private double calculateFitness()
    {
        numbOfConflicts = 0;

        // for each period checking if in the same time we have same faculty teaching it
        classes.forEach(x -> classes.stream().filter(y -> classes.indexOf(y) >= classes.indexOf(x)).forEach(y -> {
            if (x.getId() != y.getId()) {
                if (x.getDay() == y.getDay() && x.getMeetingTime().getId().equals(y.getMeetingTime().getId()) && x.getDept().getName().equals(y.getDept().getName())) {
                    //System.out.println("same time and same dept conflict");
                    numbOfConflicts++;
                }
                if (x.getDay() == y.getDay() && x.getMeetingTime().getId().equals(y.getMeetingTime().getId()) && !x.getDept().getName().equals(y.getDept().getName()) && x.getInstructor().getName().equals(y.getInstructor().getName())) {
                    //System.out.println("same time and different class and same teacher conflict");
                    numbOfConflicts++;
                }
                if (x.getDay() == y.getDay() && x.getDept().getName().equals(y.getDept().getName()) && (Integer.parseInt(x.getMeetingTime().getId()) + 2 >= Integer.parseInt(y.getMeetingTime().getId())) && x.getCourse().getName().equals(y.getCourse().getName())) {
                    //System.out.println("same dept with 3 period same course conflict");
                    numbOfConflicts++;
                }
            }
        }));

        return 1 / (double) (numbOfConflicts + 1);
    }

    public String toString()
    {
        StringBuilder returnValue = new StringBuilder();
        for (int x = 0; x < classes.size() - 1; x++) {
            returnValue.append(classes.get(x)).append(",");
        }
        returnValue.append(classes.get(classes.size() - 1));
        return returnValue.toString();
    }
}
