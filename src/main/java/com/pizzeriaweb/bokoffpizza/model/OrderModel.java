package com.pizzeriaweb.bokoffpizza.model;

import com.pizzeriaweb.bokoffpizza.entity.Order;
import com.pizzeriaweb.bokoffpizza.entity.OrderList;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.aspectj.weaver.ast.Or;

import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@Data
public class OrderModel {

    private Long id;

    private java.sql.Timestamp order_date;

    private Long customer_id;

    private Set<OrderListModel> orderListModels;

    public static OrderModel toModel(Order order) {
        OrderModel orderModel = new OrderModel();
        orderModel.setId(order.getId());
        orderModel.setOrder_date(order.getOrder_date());
        orderModel.setCustomer_id(order.getCustomer().getId());
        Set<OrderListModel> orderListModelSet = new HashSet<>();
        for(OrderList orderList : order.getOrderListSet() ) {
            orderListModelSet.add(OrderListModel.toModel(orderList));
        }
        orderModel.setOrderListModels(orderListModelSet);
        return orderModel;
    }
}
