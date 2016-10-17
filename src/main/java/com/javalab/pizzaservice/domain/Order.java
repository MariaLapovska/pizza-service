package com.javalab.pizzaservice.domain;

import java.util.List;

/**
 * @author Mariia Lapovska
 */
public class Order {

    private static final int MIN_PIZZA_NUM = 1;
    private static final int MAX_PIZZA_NUM = 10;

    private static int nextId;

    private int id;
    private Customer customer;
    private List<Pizza> orderedPizzas;
    private OrderStatus status;

    public Order(Customer customer) {
        id = nextId++;
        this.customer = customer;
        this.status = OrderStatus.NEW;
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

    public boolean addPizza(Pizza pizza) {
        return orderedPizzas.size() < MAX_PIZZA_NUM && orderedPizzas.add(pizza);
    }

    public boolean removePizza(Pizza pizza) {
        return orderedPizzas.size() > MIN_PIZZA_NUM && orderedPizzas.remove
                (pizza);
    }

    public OrderStatus getStatus() {
        return status;
    }

    public boolean setStatus(OrderStatus status) {
        if (this.status.isAllowed(status.name())) {
            this.status = status;
            return true;
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        return id + " " + customer + orderedPizzas + " " + status;
    }
}