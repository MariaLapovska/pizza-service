package com.projects.pizzaservice.domain;

import java.math.BigDecimal;

/**
 * @author Mariia Lapovska
 */
public interface Discount {

    BigDecimal applyDiscount(Order order);
}
