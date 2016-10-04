package com.javalab.pizzaservice.repositories;

import com.javalab.pizzaservice.domain.Order;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Mariia Lapovska
 */
public class InMemoOrderRepository implements OrderRepository {
    private List<Order> orders = new ArrayList<>();

    @Override
    public void saveOrder(Order order) {
        orders.add(order);
    }
}
