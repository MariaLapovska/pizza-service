package com.projects.pizzaservice.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * @author Mariia Lapovska
 */
@Entity
@Table
public class MemberCard implements Serializable {

    @TableGenerator(name = "cardGen")

    @Id
    @GeneratedValue(generator = "cardGen")
    private Integer id;

    @Column(nullable = false)
    private LocalDate issueDate;

    @Column(nullable = false)
    private BigDecimal balance;

    public MemberCard() {
    }

    public MemberCard(Integer id, LocalDate issueDate, BigDecimal balance) {
        this.id = id;
        this.issueDate = issueDate;
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

    public void addToBalance(BigDecimal amount) {
        this.balance = this.balance.add(amount);
    }

    public LocalDate getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(LocalDate issueDate) {
        this.issueDate = issueDate;
    }
}
