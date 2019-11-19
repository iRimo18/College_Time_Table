package com.zoho.rimo.msec;

import com.zoho.rimo.msec.algorithm.GeneticAlgorithm;
import com.zoho.rimo.msec.algorithm.Population;
import com.zoho.rimo.msec.algorithm.Schedule;
import com.zoho.rimo.msec.resources.Class;
import com.zoho.rimo.msec.util.DayResolver;

import java.io.File;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

import static com.zoho.rimo.msec.util.DayResolver.TOTAL_WORKING_DAY;

/**
 * Generator.java- This is the generator class that calls the GeneticAlgorithm till fitness is found
 *
 * @author Rimo-7487
 * @version 1.0
 */
public class Generator
{
    private static final int POPULATION_SIZE = 100;
    private InputDetails data;

    public static void main(String[] args)
    {
        Generator generator = new Generator();

        File file = new File("/home/local/ZOHOCORP/rimo-7487/Rimo/github/MSEC_Time_Table/src/input.txt");

        generator.data = new InputDetails(file);

        int generationNumber = 0;
        //generator.printAvailableData();

        GeneticAlgorithm geneticAlgorithm = new GeneticAlgorithm(generator.data);
        Population population = new Population(Generator.POPULATION_SIZE, generator.data).sortByFitness();

        while (population.getSchedules().get(0).getFitness() != 1.0 && generationNumber < 1000) {
            System.out.println("generation number : " + generationNumber + " fitness : " + population.getSchedules().get(0).getFitness());
            System.out.println();
            //generator.printScheduleAsTable(population.getSchedules().get(0));

            population = geneticAlgorithm.evolve(population).sortByFitness();
            ++generationNumber;
        }

        System.out.println("...........................................................................");
        System.out.println("Final schedule found in number " + generationNumber + " generation with fitness " + population.getSchedules().get(0).getFitness());
        generator.printScheduleAsTable(population.getSchedules().get(0));
    }

    private void printScheduleAsTable(Schedule schedule)
    {
        ArrayList<Class> classes = schedule.getClasses();
        String[] dept = new String[] {"II-CSE", "III-CSE"};
        for (int day = 1; day <= TOTAL_WORKING_DAY; day++) {
            for (String department : dept) {
                System.out.println("\n" + department);
                System.out.println("Day\t1\t2\t3\t4\t5\t6\t7");
                System.out.print(DayResolver.getDayOfTheWeek(day));
                Class[] tempclass = new Class[7];
                for (int periodId = 1; periodId <= 7; periodId++) {
                    for (Class period : classes) {
                        if (period.getDay() == day && period.getDept().getName().equals(department) && Integer.parseInt(period.getMeetingTime().getId()) == periodId) {
                            System.out.print("\t" + period.getInstructor().getId());
                            tempclass[periodId - 1] = period;
                        }
                    }
                }
                System.out.println();
                for (Class period : tempclass) {
                    System.out.print("\t" + period.getCourse().getName());
                }
                System.out.println();
            }
        }
        System.out.println("\n...........................................................................\n");
    }

    private void printAvailableData()
    {
        AtomicInteger i = new AtomicInteger();
        System.out.println("\n------ Available Lecturers ------");
        i.set(0);
        data.getInstructors().forEach(x -> System.out.println((i.incrementAndGet()) + ": " + x.getName() + "\n"));

        System.out.println("\n------ Available Course ------");
        data.getCourses().forEach(x -> System.out.println("Course Title: " + x.getName() + "\nInstructor(s): " + x.getInstructors() + "\n"));

        System.out.println("\n------ Available Meeting Times ------");
        i.set(0);
        data.getMeetingTimes().forEach(x -> System.out.println((i.incrementAndGet()) + " :" + x.getTime()));

        System.out.println("\n\n------ Available Classes ------");
        data.getDepts().forEach(x -> System.out.println("Class Name:  " + x.getName() + "\nCourses Offered:  " + x.getCourses() + "\n"));
        System.out.println("...........................................................................");
        System.out.println("...........................................................................");
    }
}
