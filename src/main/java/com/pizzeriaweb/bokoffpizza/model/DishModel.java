package com.pizzeriaweb.bokoffpizza.model;

import com.pizzeriaweb.bokoffpizza.entity.Dish;
import com.pizzeriaweb.bokoffpizza.entity.DishSize;
import com.pizzeriaweb.bokoffpizza.entity.Product;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

public class DishModel {
    private Long id;
    private String name;
    private String pictureURL;

    private Set<Product> products;

    private Set<DishSizeModel> dishSizeModels;

    public static DishModel toModel(Dish dish){
        DishModel dishModel = new DishModel();
        dishModel.setId(dish.getId());
        dishModel.setName(dish.getName());
        dishModel.setPictureURL(dish.getPictureURL());
        dishModel.setProducts(dish.getProducts());
        Set<DishSizeModel> dishSizeModelsSet = new HashSet<>();
        for (DishSize dishSize: dish.getDishSizes()){
            dishSizeModelsSet.add(DishSizeModel.toModel(dishSize));
        }
        dishModel.setDishSizeModels(dishSizeModelsSet);
        return dishModel;
    }

    public DishModel() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPictureURL() {
        return pictureURL;
    }

    public void setPictureURL(String pictureURL) {
        this.pictureURL = pictureURL;
    }

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }

    public Set<DishSizeModel> getDishSizeModels() {
        return dishSizeModels;
    }

    public void setDishSizeModels(Set<DishSizeModel> dishSizeModels) {
        this.dishSizeModels = dishSizeModels;
    }
}
