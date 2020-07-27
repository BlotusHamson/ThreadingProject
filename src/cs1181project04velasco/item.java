/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs1181project04velasco;

/**
 *
 * @author Jo Velasco
 */
public class item {

    private final String name;
    private final double weight;
    private final int value;
    private boolean included;

    public item(String name, double weight, Integer value) {
        this.name = name;
        this.weight = weight;
        this.value = value;
    }

    public item(item other) {

    }

    public String getName() {           //gets name
        return name;
    }

    public double getWeight() {     //weight
        return weight;
    }

    public int getValue() {
        return value;               //amount
    }

    public void setIncluded(boolean included) {         //included or not
        this.included = included;
    }

    public boolean isIncluded() {               
        return included;
    }

    @Override
    public String toString() {

        return ("Name: " + name + "  weight: " + weight + "  value: " + value);
    }

}
