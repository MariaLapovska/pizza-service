package com.projects.pizzaservice.services;

import com.projects.pizzaservice.domain.Customer;
import com.projects.pizzaservice.domain.Order;
import org.springframework.stereotype.Service;

/**
 * @author Mariia Lapovska
 */
@Service
public class SimpleOrderService implements OrderService {

    @Override
    public Order placeNewOrder(Customer customer, Integer... pizzasID) {
        return null;
    }
}
