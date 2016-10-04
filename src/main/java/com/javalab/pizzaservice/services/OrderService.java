package com.javalab.pizzaservice.services;

import com.javalab.pizzaservice.domain.Customer;
import com.javalab.pizzaservice.domain.Order;

/**
 * @author Mariia Lapovska
 */
public interface OrderService {
    Order placeNewOrder(Customer customer, Integer ... pizzasID);
}