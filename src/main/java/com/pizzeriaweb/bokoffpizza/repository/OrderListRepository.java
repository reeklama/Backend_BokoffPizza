package com.pizzeriaweb.bokoffpizza.repository;

import com.pizzeriaweb.bokoffpizza.entity.OrderList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderListRepository extends JpaRepository<OrderList, Long> {
}
