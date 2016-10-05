package com.javalab.pizzaservice.infrastructure;

import java.lang.annotation.*;

/**
 * @author Mariia Lapovska
 */

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Benchmark {
    boolean value() default true;
}