/*  Total amount of thread runs: 4
 *  Average fitness: 9.25
 *  Average time per run: less than a second
 * 
 */
package cs1181project04velasco;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Jo Velasco
 */
public class GeneticAlgorithm {

    public static ArrayList<item> readData(String filename) throws IOException {

        ArrayList<String> name = new ArrayList<>();
        ArrayList<String> costs = new ArrayList<>();            //to add the initial data for conversion
        ArrayList<String> weights = new ArrayList<>();

        List<Double> weight = new ArrayList<>(Arrays.asList());
        ArrayList<Integer> cost = new ArrayList<>();                    //actual data
        ArrayList<item> itemlist = new ArrayList<>();

        try {

            Scanner reader = new Scanner(new File(filename));
            while ((reader.hasNextLine())) {
                String line = reader.nextLine();
                String[] sp = line.split(", ");
                name.add(sp[0]);                                //basic file reading
                weights.add(sp[1]);
                costs.add(sp[2]);
                Integer a = 0;
                Double b = 0.0;

                for (Integer i = 0; i < costs.size(); i++) {                //conversion for costs
                    a = Integer.parseInt(costs.get(i));
                }
                cost.add(a);

                for (int j = 0; j < weights.size(); j++) {                      //conversion for weight
                    b = Double.parseDouble(weights.get(j));
                }
                weight.add(b);

                {

                }
            }
            reader.close();

            for (int i = 0; i < name.size(); i++) {                                             //makes items to pass into arraylist of items
                item newbie = new item(name.get(i), weight.get(i), cost.get(i));
                itemlist.add(newbie);

            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(GeneticAlgorithm.class.getName()).log(Level.SEVERE, null, ex);

        }

        return itemlist;
    }

    public static ArrayList<Chromosome> initializePopulation(ArrayList<item> items, int populationSize) {           //makes the inital population

        ArrayList<Chromosome> population = new ArrayList<>(populationSize);

        for (int i = 0; i < populationSize; i++) {              //makes amount of peeps equal to population size

            population.add(new Chromosome(items));

        }

        return population;

    }

    public static void main(String[] args) throws IOException, InvalidArgumentException, InterruptedException {

        final int popsize = 10;
        final int generations = 20;
        final int NUM_THREADS = 3;
        ArrayList<Chromosome> pop;                              //variables
        ArrayList<Chromosome> nextgen = new ArrayList<>();
        ArrayList<Chromosome> topten = new ArrayList<>();
   
        int count = 0;

        ArrayList<item> read = new ArrayList<>(readData("smallitems.txt"));

        Chromosome optimal = new Chromosome();                  //brute force area
        optimal.truth = BruteForce.getOptimalSet(read);

        System.out.println("Optimal Set: ");

        for (int i = 0; i < optimal.truth.size(); i++) {

            System.out.println(optimal.truth.get(i).toString());            //prints out the optimal set needed for the best bang for your buck
        }

        pop = initializePopulation(read, popsize);

        while (count < generations) {           //keeps creating chromosomes until generation is done

            nextgen.clear();

            Mythread popcreator = new Mythread(pop, nextgen);           //thread for getting fitness of population and adding to nextgen
            popcreator.start();
            
            popcreator.join();

            Crossoverthread crossover = new Crossoverthread(popcreator.getpop(), popcreator.getNextgen());      //thread for making baby chromosomes from mommy and daddy chromosomes
            crossover.start();
            
              crossover.join();

            mutationthread mutation = new mutationthread(crossover.getpop(), crossover.getNextgen());               //allows for a random chance of mutation
            mutation.start();
            
            mutation.join();        //the .join stuff is so nothing crashes terribly, waits for thread to finish before starting

            for (int a = 0; a < popsize; a++) {
                topten.add(mutation.getNextgen().get(a));           //Gets my top ten bset survivors 
            }

            count++;    //to keep track of how many gens

            pop.clear();                            //clears initial populatioon

            for (int i = 0; i < topten.size(); i++) {
                pop.add(topten.get(i));                     //adds top ten, loops it all back
            }

            count++;
                                                    
            if (count < generations) {          //clears to the top ten when generations have been met
                topten.clear();
            }

        }

        System.out.println("Top Star!");

        topten.get(0).toString();       //prints out best one

    }

}
