package com.projects.pizzaservice.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author Mariia Lapovska
 */
@Entity
@Table
@NamedQueries({
        @NamedQuery(name = "Pizza.findAll",
                query = "SELECT p FROM Pizza p"),
        @NamedQuery(name = "Pizza.findAllByType",
                query = "SELECT p FROM Pizza p WHERE p.type = :type")
})
public class Pizza implements Serializable {

    @TableGenerator(name = "pizzaGen")

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "pizzaGen")
    private Integer id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private BigDecimal price;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Type type;

    public enum Type {
        VEGETARIAN, SEA, MEAT
    }

    public Pizza() {
    }

    public Pizza(Integer id, String name, BigDecimal price, Type type) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.type = type;
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

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Pizza{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", type=" + type +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (! (o instanceof Pizza)) {
            return false;
        }

        Pizza that = (Pizza) o;

        if (this.id != null && this.id.equals(that.id)) {
            return true;
        }
        if (this.name == null || !this.name.equals(that.name)) {
            return false;
        }
        if (this.price == null || !this.price.equals(that.price)) {
            return false;
        }

        return this.type.equals(that.type);
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);

        return result;
    }
}
