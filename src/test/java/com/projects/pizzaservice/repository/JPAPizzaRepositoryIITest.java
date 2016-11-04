package com.projects.pizzaservice.repository;

import com.projects.pizzaservice.domain.Pizza;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

import static org.junit.Assert.assertTrue;

/**
 * @author Mariia Lapovska
 */
@Transactional
public class JPAPizzaRepositoryIITest extends RepositoryITestConfig {

    @Autowired
    private PizzaRepository pizzaRepository;

    @Test
    public void savePizzaTest() {
        Pizza pizza = new Pizza(null, "Hawaii", new BigDecimal("10.0"),
                Pizza.Type.MEAT);
        pizza = pizzaRepository.save(pizza);
        assertTrue(pizza.getId() != null);
    }
}