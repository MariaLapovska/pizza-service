package com.javalab.pizzaservice.services;

import com.javalab.pizzaservice.domain.Pizza;

/**
 * @author Mariia Lapovska
 */
public interface PizzaService {
    Pizza findPizza(int id);
}