package com.zoho.rimo.msec.algorithm;

import com.zoho.rimo.msec.InputDetails;

import java.util.Random;
import java.util.stream.IntStream;

/**
 * GeneticAlgorithm.java- GeneticAlgorithm is the one performing crossover and mutation and elitism
 *
 * @author Rimo-7487
 * @version 1.0
 */
public class GeneticAlgorithm
{

    private InputDetails data;
    private static final double MUTATION_RATE = 0.1;
    private static final double CROSSOVER_RATE = 1.0; // higher the cross lesser the iteration
    private static final double PROBABILITY_OF_GETTING_ONE_FROM_TWO = 0.5;
    private static final int TOURNAMENT_SELECTION_SIZE = 10;
    private static final int NUMB_OF_ELITE_SCHEDULES = 2;

    public GeneticAlgorithm(InputDetails data)
    {
        this.data = data;
    }

    public Population evolve(Population population)
    {
        return mutatePopulation(crossoverPopulation(population));
    }

    private Population crossoverPopulation(Population population)
    {
        Population crossoverPopulation = new Population(population.getSchedules().size(), data);
        // Elitism will keep the schedule same for some number! here its 1
        IntStream.range(0, NUMB_OF_ELITE_SCHEDULES)
                .forEach(x -> crossoverPopulation.getSchedules().set(x, population.getSchedules().get(x)));

        IntStream.range(NUMB_OF_ELITE_SCHEDULES, population.getSchedules().size()).forEach(x -> {
            // set the Chromosome here, generate two best fit parent schedules to be crossed and set as the resultant schedule
            Schedule schedule1 = selectTournamentPopulation(population).sortByFitness().getSchedules().get(0);
            //System.out.println(" Schedule 1 fitness " + schedule1.getFitness());
            //printScheduleAsTable(schedule1);
            Schedule schedule2 = selectTournamentPopulation(population).sortByFitness().getSchedules().get(0);
            //System.out.println(" Schedule 2 fitness " + schedule1.getFitness());
            //printScheduleAsTable(schedule1);

            crossoverPopulation.getSchedules().set(x, crossoverSchedule(schedule1, schedule2));
        });

        return crossoverPopulation;
    }

    private Schedule crossoverSchedule(Schedule schedule1, Schedule schedule2)
    {
        // again generate new schedules to be selected from
        Schedule crossoverSchedule = new Schedule(data).initialize();

        // set the Genes here
        IntStream.range(0, crossoverSchedule.getClasses().size()).forEach(x -> {
            if (new Random().nextDouble() > PROBABILITY_OF_GETTING_ONE_FROM_TWO) {
                crossoverSchedule.getClasses().set(x, schedule1.getClasses().get(x));
            }
            else {
                crossoverSchedule.getClasses().set(x, schedule2.getClasses().get(x));
            }
        });

        return crossoverSchedule;
    }

    private Population mutatePopulation(Population population)
    {
        Population mutatePopulation = new Population(population.getSchedules().size(), data);

        IntStream.range(0, NUMB_OF_ELITE_SCHEDULES).forEach(x -> mutatePopulation.getSchedules().set(x, population.getSchedules().get(x)));

        IntStream.range(NUMB_OF_ELITE_SCHEDULES, population.getSchedules().size()).forEach(x -> {
            mutatePopulation.getSchedules().set(x, mutateSchedule(population.getSchedules().get(x)));
        });
        return mutatePopulation;
    }

    private Schedule mutateSchedule(Schedule mutateSchedule)
    {
        Schedule schedule = new Schedule(data).initialize();

        IntStream.range(0, mutateSchedule.getClasses().size()).forEach(x -> mutateSchedule.getClasses().set(x, schedule.getClasses().get(x)));

        return mutateSchedule;
    }

    private Population selectTournamentPopulation(Population population)
    {
        // generate new population of tournament size, this generated new schedules again internally
        Population tournamentPopulation = new Population(TOURNAMENT_SELECTION_SIZE, data);

        // set the schedules of the tournamentPopulation to randomly picked schedule from original population
        IntStream.range(0, TOURNAMENT_SELECTION_SIZE)
                .forEach(x -> tournamentPopulation.getSchedules().set(x, population.getSchedules().get((int) (new Random().nextDouble() * population.getSchedules().size()))));

        return tournamentPopulation;
    }
}

