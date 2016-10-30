package com.projects.pizzaservice;

import com.projects.pizzaservice.domain.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;

/**
 * @author Mariia Lapovska
 */
public class JPARunner {
    public static void main(String[] args) {
        Pizza pizza = new Pizza(null, "Hawaii", new BigDecimal("100"), Pizza.Type.VEGETARIAN);
        Address address = new Address(null, "Ukraine", "Kiev", "Frunze", 122);
        MemberCard card = new MemberCard(null, LocalDate.now(), new BigDecimal("0.00"));
        Customer customer = new Customer(null, "Maria", address, card);
        Order order = new Order(null, customer, Arrays.asList(pizza));

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("jpa");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction tx = entityManager.getTransaction();

        entityManager.clear();

        tx.begin();
        entityManager.persist(pizza);
        entityManager.persist(card);
        entityManager.persist(customer);
        entityManager.persist(order);
        tx.commit();
        entityManager.close();

        Integer id = pizza.getId();

        entityManager = entityManagerFactory.createEntityManager();

        Pizza pizzaRetrieved = entityManager.find(Pizza.class, id);
        System.out.println(pizzaRetrieved);
        entityManagerFactory.close();
    }
}
