package com.pizzeriaweb.bokoffpizza.entity;

import lombok.*;

import java.io.Serializable;
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class OrderAndDish implements Serializable {

    private Order order;

    private Long dish_size_id;
}
