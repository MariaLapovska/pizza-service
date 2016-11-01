package com.projects.pizzaservice.services;

import com.projects.pizzaservice.domain.Customer;
import com.projects.pizzaservice.domain.DiscountManager;
import com.projects.pizzaservice.domain.Order;
import com.projects.pizzaservice.domain.Pizza;
import com.projects.pizzaservice.infrastructure.Benchmark;
import com.projects.pizzaservice.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Mariia Lapovska
 */
@Service
public class SimpleOrderService implements OrderService {

    private final OrderRepository orderRepository;
    private final PizzaService pizzaService;
    private final DiscountManager discountManager;

    @Autowired
    public SimpleOrderService(OrderRepository orderRepository, PizzaService pizzaService, DiscountManager discountManager) {
        this.orderRepository = orderRepository;
        this.pizzaService = pizzaService;
        this.discountManager = discountManager;
    }

    @Override
    @Benchmark
    public Order placeNewOrder(Customer customer, Integer... pizzasId) {
        List<Pizza> pizzas = new ArrayList<>();

        for (Integer id : pizzasId){
            pizzas.add(pizzaService.findPizzaById(id));
        }

        Order newOrder = createNewOrder();

        newOrder.setCustomer(customer);
        newOrder.setPizzas(pizzas);
        discountManager.applyDiscounts(newOrder);

        orderRepository.save(newOrder);

        return newOrder;
    }

    @Lookup("order")
    Order createNewOrder() {
        throw new IllegalStateException();
    }
}
