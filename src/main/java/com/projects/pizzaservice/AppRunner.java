package com.projects.pizzaservice;

import com.projects.pizzaservice.domain.*;
import com.projects.pizzaservice.services.OrderService;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Arrays;

/**
 * @author Mariia Lapovska
 */
public class AppRunner {
    public static void main(String[] args) {
        Customer customer = null;
        Order order;

        ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("appContext.xml", "repoContext.xml");
        OrderService orderService = context.getBean(OrderService.class);
        order = orderService.placeNewOrder(customer, 1, 2, 3);
        System.out.println(order);

        System.out.println(Arrays.toString(context.getBeanDefinitionNames()));
        context.close();
    }
}
