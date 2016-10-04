package com.javalab.pizzaservice.services;

import com.javalab.pizzaservice.domain.Pizza;
import com.javalab.pizzaservice.repositories.PizzaRepository;

/**
 * @author Mariia Lapovska
 */
public class SimplePizzaService implements PizzaService {
    private final PizzaRepository pizzaRepository;

    public SimplePizzaService(PizzaRepository pizzaRepository) {
        this.pizzaRepository = pizzaRepository;
    }

    @Override
    public Pizza findPizza(int id) {
        return pizzaRepository.findPizza(id);
    }
}
