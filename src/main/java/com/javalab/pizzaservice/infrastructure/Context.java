package com.javalab.pizzaservice.infrastructure;

/**
 * @author Mariia Lapovska
 */
public interface Context {
    <T> T getBean(String beanName);
}