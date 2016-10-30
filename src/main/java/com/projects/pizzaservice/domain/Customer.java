package com.projects.pizzaservice.domain;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author Mariia Lapovska
 */
@Entity
@Table
public class Customer implements Serializable {

    @TableGenerator(name = "customerGen")

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "customerGen")
    private Integer id;

    @Column(nullable = false)
    private String name;

    @OneToOne
    private Address address;

    @OneToOne
    private MemberCard memberCard;

    public Customer() {
    }

    public Customer(Integer id, String name, Address address, MemberCard
            memberCard) {
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

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
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
