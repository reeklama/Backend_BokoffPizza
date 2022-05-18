package com.pizzeriaweb.bokoffpizza.rest;

import lombok.Data;

@Data
public class DishFromOrderDTO {

    private String dishName;

    private Integer size;

    private String unit;

    private Long amount;

    private Integer price;
}
