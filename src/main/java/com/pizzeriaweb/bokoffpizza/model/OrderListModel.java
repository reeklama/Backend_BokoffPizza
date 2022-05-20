package com.pizzeriaweb.bokoffpizza.model;

import com.pizzeriaweb.bokoffpizza.entity.OrderList;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class OrderListModel {
    private Long order_id;

    private Long dish_size_id;

    public static OrderListModel toModel (OrderList orderList) {
        OrderListModel orderListModel = new OrderListModel();
        orderListModel.setOrder_id(orderList.getOrder().getId());
        orderListModel.setDish_size_id(orderList.getDishSize().getId());
        return orderListModel;
    }
}
