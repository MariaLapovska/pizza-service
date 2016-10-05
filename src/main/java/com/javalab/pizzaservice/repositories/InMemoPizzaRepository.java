package com.javalab.pizzaservice.repositories;

import com.javalab.pizzaservice.domain.Pizza;
import com.javalab.pizzaservice.domain.PizzaType;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Mariia Lapovska
 */
public class InMemoPizzaRepository implements PizzaRepository {

    private final static Map<Integer, Pizza> pizzas = new HashMap<>();

    public void init() {
        pizzas.put(0, new Pizza("Hawaii", 80.32, PizzaType.VEGETARIAN));
        pizzas.put(1, new Pizza("Meat Heaven", 100.32, PizzaType.MEAT));
        pizzas.put(2, new Pizza("Fishy", 111., PizzaType.SEA));
    }

    @Override
    public Pizza findPizza(Integer id) {
        return pizzas.get(id);
    }
}
