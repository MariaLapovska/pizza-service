package com.projects.pizzaservice.domain;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author Mariia Lapovska
 */
@Entity
@Table(name = "CUSTOMER")
public class Customer implements Serializable {

    @TableGenerator(
            name = "customerGen",
            table = "CUSTOMER_ID_GEN",
            pkColumnName = "GEN_KEY",
            valueColumnName = "GEN_VALUE",
            pkColumnValue = "CUSTOMER_ID"
    )

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "customerGen")
    private Integer id;

    private String name;

    private String address;

    @OneToOne
    private MemberCard memberCard;

    public Customer() {
    }

    public Customer(Integer id, String name, String address, MemberCard memberCard) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.memberCard = memberCard;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public MemberCard getMemberCard() {
        return memberCard;
    }

    public void setMemberCard(MemberCard memberCard) {
        this.memberCard = memberCard;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
