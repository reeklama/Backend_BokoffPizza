package com.pizzeriaweb.bokoffpizza.service;

import com.pizzeriaweb.bokoffpizza.entity.OrderList;
import com.pizzeriaweb.bokoffpizza.repository.OrderListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderListService {

    @Autowired
    OrderListRepository orderListRepository;

    public void saveOrderList(OrderList orderList) {
        orderListRepository.save(orderList);
    }
}
