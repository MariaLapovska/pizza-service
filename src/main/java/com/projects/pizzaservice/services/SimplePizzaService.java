package com.projects.pizzaservice.services;

import com.projects.pizzaservice.domain.Pizza;
import org.springframework.stereotype.Service;

/**
 * @author Mariia Lapovska
 */
@Service
public class SimplePizzaService implements PizzaService {

    @Override
    public Pizza getPizzaByID(Integer id) {
        return null;
    }
}
