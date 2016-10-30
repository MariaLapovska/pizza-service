package com.projects.pizzaservice.services;

import com.projects.pizzaservice.domain.Customer;
import com.projects.pizzaservice.domain.Order;

/**
 * @author Mariia Lapovska
 */
public interface OrderService {
    Order placeNewOrder(Customer customer, Integer ... pizzasId);
}