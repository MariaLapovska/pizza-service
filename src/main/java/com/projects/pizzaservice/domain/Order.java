package com.projects.pizzaservice.domain;

/*

- включить возможность добавлять в заказ определённое число пицц:
от 1 до 10 пицц (в сумме)
*/

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author Mariia Lapovska
 */
@Component
@Scope("prototype")
@Entity
@Table
public class Order implements Serializable {

    @TableGenerator(name = "orderGen")

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "orderGen")
    private Integer id;

    @ManyToOne
    private Customer customer;

    @ElementCollection
    @CollectionTable
    @MapKeyClass(Pizza.class)
    @MapKeyColumn(name = "pizza_id")
    @Column
    private Map<Pizza, Integer> pizzas;

    private BigDecimal discountAmount;

    @Enumerated(EnumType.STRING)
    private Status status;

    public enum Status {
        NEW("IN_PROGRESS", "CANCELLED", "DONE"),
        IN_PROGRESS("CANCELLED", "DONE"),
        CANCELLED(),
        DONE();

        private List<String> allowedTransitions;

        Status(String ... statuses) {
            this.allowedTransitions = Arrays.asList(statuses);
        }

        boolean isTransmutableTo(Status status) {
            return allowedTransitions.contains(status.name());
        }
    }

    public Order() {
    }

    public Order(Integer id, Customer customer, List<Pizza> pizzas) {
        this.status = Status.NEW;
        this.discountAmount = BigDecimal.ZERO;
        this.id = id;
        this.customer = customer;
        setPizzas(pizzas);
    }

    public void addToDiscountAmount(BigDecimal amountToAdd) {
        discountAmount = discountAmount.add(amountToAdd);
    }

    public BigDecimal getDiscountAmount() {
        return discountAmount;
    }

    public void addPizzas(Pizza pizza, Integer amount) {
        pizzas.merge(pizza, amount, Integer::sum);
    }

    public int getPizzasQuantity() {
        return pizzas.values().stream().mapToInt(Integer::intValue).sum();
    }

    public Map<Pizza, Integer> getPizzas() {
        return pizzas;
    }

    public void setPizzas(List<Pizza> pizzas) {
        this.pizzas = pizzas.stream().collect(
                Collectors.toMap(s -> s, s -> 1, Integer::sum)
        );
    }

    public Status getStatus() {
        return status;
    }

    public boolean setStatus(Status newStatus) {
        if (this.status.isTransmutableTo(newStatus)) {
            this.status = newStatus;

            return true;
        }

        return false;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", customer=" + customer +
                ", pizzas=" + pizzas +
                '}';
    }

    public boolean depositTotalOnMemberCard() {
        if (customer.getMemberCard() != null) {
            customer.getMemberCard().addToBalance(getTotalWithDiscount());

            return true;
        }

        return false;
    }

    public BigDecimal getTotal() {
        BigDecimal price = BigDecimal.ZERO;

        for (Map.Entry<Pizza, Integer> pizzaEntry : pizzas.entrySet()) {
            price = price.add(pizzaEntry.getKey().getPrice().multiply(new BigDecimal(pizzaEntry.getValue())));
        }

        return price;
    }

    public BigDecimal getTotalWithDiscount() {
        return getTotal().subtract(discountAmount);
    }
}
