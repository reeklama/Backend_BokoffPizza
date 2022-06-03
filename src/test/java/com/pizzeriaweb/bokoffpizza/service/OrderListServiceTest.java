package com.pizzeriaweb.bokoffpizza.service;

import com.pizzeriaweb.bokoffpizza.entity.OrderList;
import com.pizzeriaweb.bokoffpizza.repository.OrderListRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class OrderListServiceTest {

    @Autowired
    OrderListService orderListService;

    @MockBean
    OrderListRepository orderListRepository;

    @Test
    void saveOrderList() {
        OrderList orderList =  new OrderList();

        orderListService.saveOrderList(orderList);

        Mockito.verify(orderListRepository, Mockito.times(1)).save(orderList);
    }
}