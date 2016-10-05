package com.javalab.pizzaservice.infrastructure;

/**
 * @author Mariia Lapovska
 */
public class InitialContext {

    private static Config config = new JavaConfig();

    public <T> T getInstance(String name) {
        Class<?> type = config.getImplementation(name);
        try {
            return (T) type.newInstance();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
}