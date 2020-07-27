/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs1181project04velasco;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 *
 * @author Jo Velasco
 */
public class BruteForce {

    /**
     *
     * @param items
     * @return
     * @throws InvalidArgumentException
     */
    public static ArrayList<item> getOptimalSet(ArrayList<item> items) throws InvalidArgumentException {

        Chromosome parent1 = new Chromosome();
        Chromosome parent2 = new Chromosome();
        Chromosome child = new Chromosome();
        ArrayList<Chromosome> generation = new ArrayList<>();
        boolean one = true;
        boolean two = false;

        int count = 0;

        boolean cont = true;

        if (items.size() > 10) {

            throw new InvalidArgumentException();
        }

        for (int i = 0; i < items.size(); i++) {

            items.get(i).setIncluded(two);
            parent1.chromo.add(items.get(i));

        }

        for (int i = 0; i < items.size(); i++) {

            items.get(i).setIncluded(one);
            parent2.chromo.add(items.get(i));

        }

        while (count<1000) {

            child = parent1.crossover(parent2);

            if (child.chromo == parent1.chromo || child.chromo == parent2.chromo) {
                cont = false;
            }

            generation.add(child);
            ++count;

        }

        Chromosome mutator1;
        Chromosome mutator2;

        for (int r = 0; r < generation.size(); r++) {

            Random rng = new Random();
            int n = rng.nextInt(10);

            Collections.shuffle(generation);

            mutator1 = generation.get(n);
            mutator2 = generation.get(n + 1);

        }

        for (int u = 0; u < generation.size(); u++) {
            Collections.shuffle(generation);

            generation.get(u).compareTo(generation.get(u));

        }

        for (int u = 0; u < generation.size(); u++) {
            generation.get(u).getFitness();

        }

        Collections.sort(generation);

        return generation.get(0).truth;

    }
}
