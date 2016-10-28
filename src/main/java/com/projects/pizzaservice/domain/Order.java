package com.projects.pizzaservice.domain;

/*

- включить возможность добавлять в заказ определённое число пицц:
от 1 до 10 пицц (в сумме)
- подсчет стоимости заказа
- скидка в 30% на самую дорогую пиццу в заказе, если количество пицц больше 4-х
- стоимость заказа должна заноситься на накопительную карту пользователя если она есть
- из стоимости заказа вычитается 10% от общей суммы на накопительной карте,
но не больше чем 30% стоимости заказа

*/

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author Mariia Lapovska
 */
@Component
@Scope("prototype")
@Entity
@Table(name = "ORDER")
public class Order implements Serializable {

    @TableGenerator(
            name = "orderGen",
            table = "ORDER_ID_GEN",
            pkColumnName = "GEN_KEY",
            valueColumnName = "GEN_VALUE",
            pkColumnValue = "ORDER_ID"
    )

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "orderGen")
    private Integer id;

    @ManyToOne
    private Customer customer;

    @ElementCollection
    @CollectionTable
    @MapKeyClass(Pizza.class)
    @MapKeyColumn(name = "pizza_id")
    @Column(name="amount")
    private Map<Pizza, Long> pizzas;

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
        this.id = id;
        this.customer = customer;
        this.pizzas = pizzas.stream().collect(
                Collectors.groupingBy(
                        Function.identity(), Collectors.counting()
                )
        );
    }

    public void setPizzas(List<Pizza> pizzas) {
        this.pizzas = pizzas.stream().collect(
                Collectors.groupingBy(
                        Function.identity(), Collectors.counting()
                )
        );
    }

    public Status getStatus() {
        return status;
    }

    public boolean setStatus(Status newStatus) {
        if (this.status.isTransmutableTo(newStatus)) {
            if (Status.DONE.equals(newStatus)) {
                MemberCard card = customer.getMemberCard();
                if (card != null) {
                    card.setBalance(card.getBalance().add(getPrice()));
                }
            }
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

    public BigDecimal getPrice() {
        BigDecimal price = new BigDecimal(0);

        if (pizzas.size() > 4) {
            price = getPriceWithMostExpensivePizzaDiscounted();
        } else if (pizzas.size() > 0){
            price = getOrdinaryPrice();
        }

        return loyalCustomerDiscount(price).stripTrailingZeros();
    }

    private BigDecimal loyalCustomerDiscount(BigDecimal price) {
        MemberCard card = customer.getMemberCard();
        if (card != null) {
            BigDecimal tenPercentOfCard = percent("0.10", card.getBalance());
            BigDecimal thirtyPercentOfPrice = percent("0.30", price);
            if (thirtyPercentOfPrice.compareTo(tenPercentOfCard) < 0) {
                return price.subtract(thirtyPercentOfPrice);
            } else {
                return price.subtract(tenPercentOfCard);
            }
        }
        return price;
    }

    private BigDecimal percent(String rate, BigDecimal amount) {
        return amount.multiply(new BigDecimal(rate));
    }

    private BigDecimal getOrdinaryPrice() {
        BigDecimal price = new BigDecimal(0);
        for (Pizza pizza : pizzas.keySet()) {
            price = price.add(pizza.getPrice().multiply(new BigDecimal(pizzas.get(pizza))));
        }
        return price;
    }

    private BigDecimal getPriceWithMostExpensivePizzaDiscounted() {
        Pizza mostExpensivePizza = getMostExpensivePizza();
        BigDecimal price = getOrdinaryPrice();
        return price.subtract(discount("0.3", mostExpensivePizza));
    }

    private BigDecimal discount(String discountRate, Pizza mostExpensivePizza) {
        return mostExpensivePizza.getPrice().multiply(new BigDecimal(discountRate))
                .round(new MathContext(2, RoundingMode.UP));
    }

    private Pizza getMostExpensivePizza() {
        List<Pizza> pizzas = new ArrayList<>(this.pizzas.keySet());
        Pizza mostExpensivePizza = pizzas.get(0);
        for (Pizza pizza : pizzas) {
            if (pizza.getPrice().compareTo(mostExpensivePizza.getPrice()) > 0) {
                mostExpensivePizza = pizza;
            }
        }
        return mostExpensivePizza;
    }
}
