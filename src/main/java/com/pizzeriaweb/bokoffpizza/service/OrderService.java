package com.pizzeriaweb.bokoffpizza.service;

import com.pizzeriaweb.bokoffpizza.entity.Order;
import com.pizzeriaweb.bokoffpizza.entity.RegisteredUser;
import com.pizzeriaweb.bokoffpizza.repository.OrderRepository;
import com.pizzeriaweb.bokoffpizza.repository.RegisteredUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    @Autowired
    OrderRepository orderRepository;

    public List<Order> getOrdersByUser(RegisteredUser user) {
        List<Order> orders = orderRepository.findByCustomer(user.getCustomer());
        return orders;
    }

    public void saveOrder(Order order) {
        orderRepository.save(order);
    }
}
