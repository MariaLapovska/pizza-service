package com.projects.pizzaservice.domain;

/*

- включить возможность добавлять в заказ определённое число пицц:
от 1 до 10 пицц (в сумме)
- подсчет стоимости заказа
- скидка в 30% на самую дорогую пиццу в заказе, если количество пицц больше 4-х
- у заказа должен быть статус (NEW, IN_PROGRSS, CANCELED, DONE, … -
с ограничениями на возможные переходы между статусами )
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
    @Column(name="count")
    private Map<Pizza, Integer> pizzas;

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
}
