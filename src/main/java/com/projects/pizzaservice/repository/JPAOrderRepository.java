package com.projects.pizzaservice.repository;

import com.projects.pizzaservice.domain.Order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.util.List;

/**
 * @author Mariia Lapovska
 */
@Repository("JPAOrderRepository")
@Transactional
public class JPAOrderRepository implements OrderRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Order save(Order order) {
        if (order.getId() == null) {
            entityManager.persist(order);
        } else {
            order = entityManager.merge(order);
        }
        return order;
    }

    @Override
    @Transactional(readOnly = true)
    public Order find(Integer id) {
        return entityManager.find(Order.class, id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Order> findAll() {
        return entityManager.createNamedQuery("Order.findAll", Order.class).getResultList();
    }

}
