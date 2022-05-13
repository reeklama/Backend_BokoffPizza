package com.pizzeriaweb.bokoffpizza.entity;

import lombok.*;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@IdClass(OrderAndDish.class)
@Table(name = "orders_list")
public class OrderList {

    @Id
    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "order_id")
    private Order order;

    @Id
    private Long dish_size_id;


    private Long amount;

}
