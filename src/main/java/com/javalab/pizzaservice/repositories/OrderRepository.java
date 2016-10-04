package com.javalab.pizzaservice.repositories;

import com.javalab.pizzaservice.domain.Order;

/**
 * @author Mariia Lapovska
 */
public interface OrderRepository {
    void saveOrder(Order order);
}