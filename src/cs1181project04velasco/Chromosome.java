/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs1181project04velasco;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Jo Velasco
 */
public class Chromosome extends ArrayList<item> implements Comparable<Chromosome> {

    ArrayList<item> chromo = new ArrayList<>();                 //Arraylist for list of items
    ArrayList<item> truth = new ArrayList<>();                      //Arraylist for INCLUDED items
    public static long dummy = 0;

    public Chromosome() {

    }

    Chromosome(ArrayList<item> items) {

        Random rand = new Random();                 

        for (int i = 0; i < items.size(); i++) {
            chromo.add(items.get(i));               //Adds items into chromosome
        }

        for (int i = 0; i < chromo.size(); i++) {               //randomly decides on whether or not the item is included
            int n = rand.nextInt(10);
            if (n <= 5) {
                chromo.get(i).setIncluded(true);        //item = included
            } else {
                chromo.get(i).setIncluded(false);       //item = NOT included
            }
        }

        for (int i = 0; i < chromo.size(); i++) 
            if (chromo.get(i).isIncluded() == true) {
                truth.add(chromo.get(i));                       //adds all included items into one arraylist

            }
        }

    

    public Chromosome crossover(Chromosome other) {                     

        ArrayList<item> childlist = new ArrayList<>();          //Arraylist for kiddo
        Random rand = new Random();

        for (int i = 0; i < this.chromo.size(); ++i) {              

            int n = rand.nextInt(10);                               //Randomly chooses whether or not kiddo gets a certain parent's gene

            if (n <= 5) {
                childlist.add(this.chromo.get(i));
            }
            if (n > 5) {
                childlist.add(other.chromo.get(i));
            }

        }

        Chromosome child = new Chromosome(childlist);               //Creates offspirng of parent chromosomes
        return child;
    }

    public void mutate() {

        for (int i = 0; i < this.chromo.size(); i++) {                  //randomly chooses a number for each index of the item arraylist 
            Random rand = new Random();                                         //if it's one it will mutate that index item 
            int n = rand.nextInt(10);
            if (n == 1) {
                if (this.chromo.get(i).isIncluded() == true) {
                    this.chromo.get(i).setIncluded(false);                      //if n = 1, if isincluded = false, it becomes true, and vice versa
                }
                if (this.chromo.get(i).isIncluded() == false) {
                    this.chromo.get(i).setIncluded(true);
                }
            }
        }

    }

    public double getFitness() {
        double weightcount = 0;                                 //counters for the item value/weight
        int valuecount = 0;

        dummy = 0;
        for (int i = 0; i < this.size() * 1000; i++) {
            dummy += i;
        }

        for (int i = 0; i < truth.size(); i++) {
            weightcount += truth.get(i).getWeight();
            valuecount += truth.get(i).getValue();                  //adds the weight/value of each item
        }

        if (weightcount > 10) {
            return 0.0;                                         //returns 0 if over 10 pounds, returns value amount if under or equal to 10
        } else {

            return valuecount;
        }

    }

    @Override
    public int compareTo(Chromosome t) {

        if (t.getFitness() < this.getFitness()) {
            return -1;
        }

        if (t.getFitness() > this.getFitness()) {               //if passed in chromsome is less tahn this.getfitness, returns -1- if other way around, returns regular 1
            return 1;

        }
        if (t.getFitness() == this.getFitness()) {          //if equal returns zero
            return 0;

        }
        return (int) (this.getFitness() - t.getFitness());          //I know this is redundant cause it won't ever reach but it gets angry if I dont have anytthing to return

    }

    public String toString() {

        int value = 0;
        double weight = 0;
        System.out.println("Items: ");

        for (int i = 0; i < truth.size(); i++) {

            value += truth.get(i).getValue();           //counts weight and value of included items
            weight += truth.get(i).getWeight();

            System.out.println(". Name: " + truth.get(i).getName() + "  Value:  " + truth.get(i).getValue());

        }

        System.out.println("total:" + value);
        System.out.println("Weight: " + weight);
        return null;
    }

}
