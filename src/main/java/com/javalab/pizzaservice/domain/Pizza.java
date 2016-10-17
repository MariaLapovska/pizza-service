package com.javalab.pizzaservice.domain;

/**
 * @author Mariia Lapovska
 */
public class Pizza {

    private static int nextId;

    private int id;
    private String name;
    private double price;
    private PizzaType pizzaType;

    public Pizza(String name, double price, PizzaType pizzaType) {
        id = nextId++;
        this.name = name;
        this.price = price;
        this.pizzaType = pizzaType;
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public PizzaType getPizzaType() {
        return pizzaType;
    }

    public void setPizzaType(PizzaType pizzaType) {
        this.pizzaType = pizzaType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (! (o instanceof Pizza)) {
            return false;
        }

        Pizza that = (Pizza) o;

        return name.equals(that.name) && price == that.price && pizzaType
                .equals(that.pizzaType);
    }

    @Override
    public String toString() {
        return id + " " + name + " " + price + " " + pizzaType;
    }
}
