package com.javalab.pizzaservice;

import com.javalab.pizzaservice.domain.*;
import com.javalab.pizzaservice.infrastructure.*;
import com.javalab.pizzaservice.repositories.*;
import com.javalab.pizzaservice.services.*;

/**
 * @author Mariia Lapovska
 */
public class AppRunner {

    public static void main(String[] args) throws Exception {
        Context context=new ApplicationContext(new JavaConfig());
        PizzaRepository pizzaRepository=context.getBean("pizzaRepository");
        //System.out.println(pizzaRepository.get(1));

        Customer customer = null;
        Order order;
        OrderService orderService =context.getBean("orderService");
        order = orderService.placeNewOrder(customer, 0, 1, 2);
        System.out.println(order);
    }
}
