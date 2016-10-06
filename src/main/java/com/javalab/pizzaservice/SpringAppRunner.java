package com.javalab.pizzaservice;

import org.springframework.context.ConfigurableApplicationContext;

import com.javalab.pizzaservice.domain.*;
import com.javalab.pizzaservice.repositories.*;
import com.javalab.pizzaservice.services.*;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Arrays;

/**
 * @author Mariia Lapovska
 */
public class SpringAppRunner {

    public static void main(String[] args) {
        ConfigurableApplicationContext repoContext = new ClassPathXmlApplicationContext("repoContext.xml");
        System.out.println(Arrays.toString(repoContext.getBeanDefinitionNames()));

        ConfigurableApplicationContext serviceContext = new ClassPathXmlApplicationContext(
                new String[]{"serviceContext.xml"}, repoContext);
        System.out.println(Arrays.toString(serviceContext.getBeanDefinitionNames()));

        System.out.println(serviceContext.getBean("T1", SomeService.class).getString());

        PizzaRepository pizzaRepository= (PizzaRepository) repoContext.getBean("pizzaRepository");
        System.out.println(pizzaRepository.findPizza(1));

        OrderService orderService= (OrderService) serviceContext.getBean("orderService");
        Order order=orderService.placeNewOrder(null,1,2,3);
        System.out.println(order);

        repoContext.close();
        serviceContext.close();
    }
}
