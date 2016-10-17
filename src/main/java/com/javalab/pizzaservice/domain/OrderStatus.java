package com.javalab.pizzaservice.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Mariia Lapovska
 */
public enum OrderStatus {
    NEW("IN_PROGRESS", "CANCELED"), IN_PROGRESS("DONE"), CANCELED, DONE;

    private List<String> allowedTransitions = new ArrayList<>();

    OrderStatus(String... transitions) {
        if (transitions != null) {
            Collections.addAll(allowedTransitions, transitions);
        }
    }

    public boolean isAllowed(String transition) {
        return allowedTransitions.contains(transition);
    }
}