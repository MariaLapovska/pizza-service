package com.projects.pizzaservice.domain;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author Mariia Lapovska
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(exclude = "id")
@Entity
@Table(
        name = "CUSTOMER"
)
public class Customer implements Serializable {

    @TableGenerator(
            name = "customerGen",
            table = "ADDRESS_ID_GEN",
            pkColumnName = "GEN_KEY",
            valueColumnName = "GEN_VALUE",
            pkColumnValue = "CUSTOMER_ID"
    )

    @Id
    @Column(name = "CUSTOMER_ID")
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "customerGen")
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;

    @OneToOne
    @Column(unique = true)
    private Address address;

    @OneToOne
    @Column(unique = true)
    private MemberCard memberCard;
}