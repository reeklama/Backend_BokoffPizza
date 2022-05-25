package com.pizzeriaweb.bokoffpizza.model;

import com.pizzeriaweb.bokoffpizza.entity.Dish;
import com.pizzeriaweb.bokoffpizza.entity.DishSize;
import com.pizzeriaweb.bokoffpizza.entity.OrderList;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class OrderListModel {
    private Long amount;
    private Integer size;
    private Integer price;
    private String name;
    private String pictureURL;

    public static OrderListModel toModel (OrderList orderList) {
        OrderListModel orderListModel = new OrderListModel();
        DishSize dishSize = orderList.getDishSize();
        Dish dish = dishSize.getDish();
        orderListModel.setAmount(orderList.getAmount());
        orderListModel.setName(dish.getName());
        orderListModel.setPictureURL(dish.getPictureURL());
        orderListModel.setSize(dishSize.getSize());
        orderListModel.setPrice(dishSize.getPrice());
        return orderListModel;
    }
}
