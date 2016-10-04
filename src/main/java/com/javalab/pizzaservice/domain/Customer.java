package com.javalab.pizzaservice.domain;

/**
 * @author Mariia Lapovska
 */
public class Customer {

    private static int nextId;

    private int id;
    private String name;

    public Customer(String name) {
        id = nextId++;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return id + " " + name;
    }
}
