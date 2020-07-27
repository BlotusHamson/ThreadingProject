/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs1181project04velasco;

import java.util.ArrayList;

/**
 *
 * @author Jo Velasco
 */
public class Mythread extends Thread implements Runnable {

    private ArrayList<Chromosome> one;
    private ArrayList<Chromosome> two;

    public Mythread(ArrayList<Chromosome> one, ArrayList<Chromosome> two) {
        this.one = new ArrayList<Chromosome>(one);
        this.two = new ArrayList<Chromosome>(two);

    }

    @Override
    public void run() {

        for (int z = 0; z < one.size(); ++z) {

            one.get(z).getFitness();
            two.add(one.get(z));

        }
    }

    public ArrayList<Chromosome> getpop() {
        return one;
    }

    public ArrayList<Chromosome> getNextgen() {
        return two;
    }
}
