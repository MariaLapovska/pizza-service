package com.javalab.pizzaservice.services;

/**
 * @author Mariia Lapovska
 */
public class TestSomeService implements SomeService {
    @Override
    public String getString() {
        return "String";
    }

    public void destroy() {
        System.out.println("Destroy");
    }
}
