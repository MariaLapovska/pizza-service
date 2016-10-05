package com.javalab.pizzaservice.infrastructure;

import com.javalab.pizzaservice.repositories.InMemoOrderRepository;
import com.javalab.pizzaservice.repositories.InMemoPizzaRepository;
import com.javalab.pizzaservice.services.SimpleOrderService;
import com.javalab.pizzaservice.services.SimplePizzaService;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Mariia Lapovska
 */
public class JavaConfig implements Config {
    private final Map<String,Class<?>> classes;

    public JavaConfig() {
        classes = new HashMap<>();

        classes.put("pizzaRepository", InMemoPizzaRepository.class);
        classes.put("orderRepository", InMemoOrderRepository.class);
        classes.put("pizzaService", SimplePizzaService.class);
        classes.put("orderService", SimpleOrderService.class);
    }

    @Override
    public Class<?> getImplementation(String name) {
        return classes.get(name);
    }
}