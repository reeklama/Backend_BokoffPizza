package com.pizzeriaweb.bokoffpizza.model;

import com.pizzeriaweb.bokoffpizza.entity.DishSize;
public class DishSizeModel {
    private Long id;
    private Long dish_id;
    private Integer size;
    private Integer price;

    public static DishSizeModel toModel(DishSize dishSize){
        DishSizeModel dishSizeModel = new DishSizeModel();
        dishSizeModel.setId(dishSize.getId());
        dishSizeModel.setDish_id(dishSize.getDish().getId());
        dishSizeModel.setSize(dishSize.getSize());
        dishSizeModel.setPrice(dishSize.getPrice());
        return dishSizeModel;
    }

    public DishSizeModel() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getDish_id() {
        return dish_id;
    }

    public void setDish_id(Long dish_id) {
        this.dish_id = dish_id;
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
