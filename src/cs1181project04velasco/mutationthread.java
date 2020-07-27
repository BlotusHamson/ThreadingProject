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
public class mutationthread extends Thread implements Runnable {

    private  ArrayList<Chromosome> one;
    private  ArrayList<Chromosome> two;

   public  mutationthread(ArrayList<Chromosome> pop, ArrayList<Chromosome> nextgen) {

      this.one = new ArrayList<Chromosome>(pop);
        this.two = new ArrayList<Chromosome>(nextgen);
    }

    @Override
    public void run() {

        Chromosome mutator1;
        Chromosome mutator2;

        for (int r = 0; r < two.size(); r++) {

            Random rng = new Random();
            int n = rng.nextInt(11);

            Collections.shuffle(two);

            mutator1 = two.get(n);
            mutator2 = two.get(n + 1);

        }

        for (int u = 0; u < two.size(); u++) {
            two.get(u).getFitness();

        }

        Collections.sort(two);
       
    }
    
     public ArrayList<Chromosome> getpop(){
         return one;
     }
             
     
     public ArrayList<Chromosome> getNextgen(){
         return two;
     }
}
