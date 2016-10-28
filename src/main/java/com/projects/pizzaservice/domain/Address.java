package com.projects.pizzaservice.domain;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author Mariia Lapovska
 */
@Entity
@Table(name = "ADDRESS")
public class Address implements Serializable {

    @TableGenerator(
            name = "addressGen",
            table = "ADDRESS_ID_GEN",
            pkColumnName = "GEN_KEY",
            valueColumnName = "GEN_VALUE",
            pkColumnValue = "ADDRESS_ID"
    )

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "addressGen")
    private Integer id;

    private String country;

    private String city;

    private String street;

    private Integer apartment;

    public Address() {
    }

    public Address(Integer id, String country, String city, String street,
                   Integer apartment) {
        this.id = id;
        this.country = country;
        this.city = city;
        this.street = street;
        this.apartment = apartment;
    }

    public Integer getId() {
        return id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public Integer getApartment() {
        return apartment;
    }

    public void setApartment(Integer apartment) {
        this.apartment = apartment;
    }

    @Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                ", country='" + country + '\'' +
                ", city='" + city + '\'' +
                ", street='" + street + '\'' +
                ", apartment=" + apartment +
                '}';
    }
}
