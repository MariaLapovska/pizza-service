package com.projects.pizzaservice.web.rest;

import com.projects.pizzaservice.domain.Pizza;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.math.BigDecimal;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

/**
 * @author Mariia Lapovska
 */
@RestController
public class RESTPizzaController {

    @RequestMapping(value = "pizza/{pizzaID}", method =
            RequestMethod.GET)
    public ResponseEntity<Pizza> find(@PathVariable("pizzaID") Integer id) {
        Pizza pizza = new Pizza(10, "lol", new BigDecimal(id), Pizza.Type.MEAT);

        if (pizza == null) {
            return new ResponseEntity<>(pizza, HttpStatus.NOT_FOUND);
        }

        Link link = linkTo(methodOn(RESTPizzaController.class).find(id))
                .withSelfRel();

        return new ResponseEntity<>(pizza, HttpStatus.FOUND);
    }

    @RequestMapping(value = "lol", method = RequestMethod.POST)
    public ResponseEntity<Void> create(@RequestBody Pizza pizza,
                                       UriComponentsBuilder uriComponentsBuilder) {
        System.out.println(pizza);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(
                uriComponentsBuilder.path("/pizza/{pizzaID}")
                        .buildAndExpand(pizza.getId()).toUri());

        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }
}