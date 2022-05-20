package com.pizzeriaweb.bokoffpizza.repository;

import com.pizzeriaweb.bokoffpizza.entity.Customer;
import com.pizzeriaweb.bokoffpizza.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByCustomer(Customer customer);
}
