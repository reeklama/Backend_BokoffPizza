package com.pizzeriaweb.bokoffpizza.model;

import com.pizzeriaweb.bokoffpizza.entity.DishSize;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

public class DishSizeModel {
    private Integer size;
    private Integer price;

    public static DishSizeModel toModel(DishSize dishSize){
        DishSizeModel dishSizeModel = new DishSizeModel();
        dishSizeModel.setSize(dishSize.getSize());
        dishSizeModel.setPrice(dishSize.getPrice());
        return dishSizeModel;
    }

    public DishSizeModel() {
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
