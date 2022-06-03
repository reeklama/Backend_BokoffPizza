package com.pizzeriaweb.bokoffpizza.service;

import com.pizzeriaweb.bokoffpizza.entity.Customer;
import com.pizzeriaweb.bokoffpizza.entity.Order;
import com.pizzeriaweb.bokoffpizza.entity.RegisteredUser;
import com.pizzeriaweb.bokoffpizza.repository.OrderRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class OrderServiceTest {

    @Autowired
    OrderService orderService;

    @MockBean
    OrderRepository orderRepository;

    @Test
    void getOrdersByUser() {
        List<Order> orders = new ArrayList<>();
        Order order = new Order();
        orders.add(order);
        Customer customer = new Customer();
        RegisteredUser user = new RegisteredUser();
        user.setCustomer(customer);

        Mockito.doReturn(orders)
                .when(orderRepository)
                .findByCustomer(customer);

        List<Order> testOrders = orderService.getOrdersByUser(user);

        assertThat(testOrders.size(), equalTo(1));
        Mockito.verify(orderRepository, Mockito.times(1)).findByCustomer(customer);
    }

    @Test
    void saveOrder() {
        Order order = new Order();
        orderService.saveOrder(order);

        Mockito.verify(orderRepository, Mockito.times(1)).save(order);
    }
}