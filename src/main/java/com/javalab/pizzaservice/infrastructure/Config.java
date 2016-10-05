package com.javalab.pizzaservice.infrastructure;

/**
 * @author Mariia Lapovska
 */
public interface Config {
    Class<?> getImplementation(String name);
}
