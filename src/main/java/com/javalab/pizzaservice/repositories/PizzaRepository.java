package com.javalab.pizzaservice.repositories;

import com.javalab.pizzaservice.domain.Pizza;

/**
 * @author Mariia Lapovska
 */
public interface PizzaRepository {
    Pizza findPizza(Integer id);
}