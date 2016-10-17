package com.javalab.pizzaservice.domain;

/**
 * @author Mariia Lapovska
 */
public class Customer {

    private static int nextId;

    private int id;
    private String name;
    private String deliveryAddress;

    public Customer(String name, String deliveryAddress) {
        id = nextId++;
        this.name = name;
        this.deliveryAddress = deliveryAddress;
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

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    @Override
    public String toString() {
        return id + " " + name + " " + deliveryAddress;
    }
}
