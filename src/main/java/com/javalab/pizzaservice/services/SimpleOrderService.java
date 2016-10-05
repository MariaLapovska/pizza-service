package com.javalab.pizzaservice.services;

import com.javalab.pizzaservice.domain.*;
import com.javalab.pizzaservice.infrastructure.Benchmark;
import com.javalab.pizzaservice.repositories.InMemoOrderRepository;
import com.javalab.pizzaservice.repositories.OrderRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Mariia Lapovska
 */
public class SimpleOrderService implements OrderService {
    private final OrderRepository orderRepository;
    private final PizzaService pizzaService;

    public SimpleOrderService(OrderRepository orderRepository, PizzaService pizzaService) {
        this.orderRepository = orderRepository;
        this.pizzaService = pizzaService;
    }

    @Override
    @Benchmark(false)
    public Order placeNewOrder(Customer customer, Integer... pizzasID) {
        List<Pizza> pizzas = new ArrayList<>();

        for(Integer id : pizzasID){
            pizzas.add(findPizzaById(id));
        }

        Order newOrder = new Order(customer, pizzas);
        orderRepository.saveOrder(newOrder);

        return newOrder;
    }

    private Pizza findPizzaById(int id) {
        return pizzaService.findPizza(id);
    }
}