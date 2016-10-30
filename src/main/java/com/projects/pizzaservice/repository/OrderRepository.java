package com.projects.pizzaservice.repository;

import com.projects.pizzaservice.domain.Order;

import java.util.List;

/**
 * @author Mariia Lapovska
 */
public interface OrderRepository {
    Order save(Order order);
    Order find(Integer id);
    List<Order> findAll();
}
