package com.javalab.pizzaservice;

import com.javalab.pizzaservice.domain.Customer;
import com.javalab.pizzaservice.domain.Order;
import com.javalab.pizzaservice.infrastructure.ApplicationContext;
import com.javalab.pizzaservice.infrastructure.Context;
import com.javalab.pizzaservice.infrastructure.JavaConfig;
import com.javalab.pizzaservice.services.OrderService;

/**
 * @author Mariia Lapovska
 */
public class PizzaApp {

    public static void main(String[] args) {
        Customer customer = new Customer("Ivan Ivanov");
        Context context = new ApplicationContext(new JavaConfig());
        OrderService orderService = context.getBean("orderService");

        Order order = orderService.placeNewOrder(customer, 0, 1, 2);
        System.out.println(order);

        order = orderService.placeNewOrder(customer, 2, 2, 2);
        System.out.println(order);
    }
}