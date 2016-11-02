package com.projects.pizzaservice.web.rest;

import com.projects.pizzaservice.domain.Pizza;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

/**
 * @author Mariia Lapovska
 */
@RestController
public class RESTPizzaController {

    @RequestMapping(value = "pizza/{pizzaID}/{pizzaName}", method =
            RequestMethod.GET)
    public Pizza pizza(@PathVariable("pizzaID") Integer id, @PathVariable
            ("pizzaName") String name) {
        return new Pizza(10, name, new BigDecimal(id), Pizza.Type.MEAT);
    }

    @RequestMapping(value = "lol", method = RequestMethod.POST)
    public Pizza showPizza(@RequestBody Pizza pizza) {
        return pizza;
    }
}