package com.projects.pizzaservice.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * @author Mariia Lapovska
 */
@Entity
@Table(name = "CARD")
public class MemberCard implements Serializable {

    @TableGenerator(
            name = "cardGen",
            table = "CARD_ID_GEN",
            pkColumnName = "GEN_KEY",
            valueColumnName = "GEN_VALUE",
            pkColumnValue = "CARD_ID"
    )

    @Id
    @GeneratedValue(generator = "cardGen")
    private Integer id;

    private LocalDate date;

    private BigDecimal balance;

    public MemberCard() {
    }

    public MemberCard(Integer id, LocalDate date, BigDecimal balance) {
        this.id = id;
        this.date = date;
        this.balance = balance;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
