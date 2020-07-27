/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs1181project04velasco;

import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author Jo Velasco
 */
public class Crossoverthread extends Thread implements Runnable {

    private ArrayList<Chromosome> one;
    private ArrayList<Chromosome> two;
    Chromosome daddy;
    Chromosome mommy;
    Chromosome child;

   public Crossoverthread(ArrayList<Chromosome> pop, ArrayList<Chromosome> nextgen) {
       this.one = new ArrayList<Chromosome>(pop);
        this.two = new ArrayList<Chromosome>(nextgen);
    }

    @Override
    public void run() {
        for (int z = 0; z < one.size(); ++z) {

            one.get(z).getFitness();
            two.add(one.get(z));

            for (int i = 0; i < one.size() / 2; i++) {

                Collections.shuffle(one);

                daddy = one.get(i);

                mommy = one.get(i + 1);

                child = mommy.crossover(daddy);

                two.add(child);

            }
        }

    }
    
public ArrayList<Chromosome> getpop(){
         return one;
     }
             
     
     public ArrayList<Chromosome> getNextgen(){
         return two;
     }
}
