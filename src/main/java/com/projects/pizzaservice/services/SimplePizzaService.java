package com.projects.pizzaservice.services;

import com.projects.pizzaservice.domain.Pizza;
import com.projects.pizzaservice.repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Mariia Lapovska
 */
@Service
public class SimplePizzaService implements PizzaService {

    private final PizzaRepository pizzaRepository;

    @Autowired
    public SimplePizzaService(PizzaRepository pizzaRepository) {
        this.pizzaRepository = pizzaRepository;
    }

    @Override
    public Pizza findPizzaById(Integer id) {
        return pizzaRepository.find(id);
    }
}
