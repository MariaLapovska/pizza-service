package com.javalab.pizzaservice.domain;

import java.util.List;

/**
 * @author Mariia Lapovska
 */
public class Order {

    private static int nextId;

    private int id;
    private Customer customer;
    private List<Pizza> orderedPizzas;

    public Order(Customer customer, List<Pizza> orderedPizzas) {
        id = nextId++;
        this.customer = customer;
        this.orderedPizzas = orderedPizzas;
    }

    public int getId() {
        return id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<Pizza> getOrderedPizzas() {
        return orderedPizzas;
    }

    public void setOrderedPizzas(List<Pizza> orderedPizzas) {
        this.orderedPizzas = orderedPizzas;
    }

    @Override
    public String toString() {
        return id + " " + customer + orderedPizzas;
    }
}