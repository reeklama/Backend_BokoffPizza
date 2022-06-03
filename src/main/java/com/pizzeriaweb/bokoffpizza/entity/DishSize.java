package com.pizzeriaweb.bokoffpizza.entity;

import javax.persistence.*;

@Entity
public class DishSize {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Dish dish;
    private Integer size;
    private Integer price;

    public DishSize() {
    }

    public DishSize(Dish dish, Integer size, Integer price) {
        this.dish = dish;
        this.size = size;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Dish getDish() {
        return dish;
    }

    public void setDish(Dish dish) {
        this.dish = dish;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }
}
