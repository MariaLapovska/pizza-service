package com.projects.pizzaservice.services;

import com.projects.pizzaservice.domain.Pizza;

/**
 * @author Mariia Lapovska
 */
public interface PizzaService {
    Pizza getPizzaByID(Integer id);
}
