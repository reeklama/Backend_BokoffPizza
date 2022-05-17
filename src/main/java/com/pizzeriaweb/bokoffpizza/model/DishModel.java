package com.pizzeriaweb.bokoffpizza.model;

import com.pizzeriaweb.bokoffpizza.entity.Dish;
import com.pizzeriaweb.bokoffpizza.entity.DishSize;
import com.pizzeriaweb.bokoffpizza.entity.Product;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

public class DishModel {
    private String name;
    private String pictureURL;

    private Set<ProductModel> productsModels;

    private Set<DishSizeModel> dishSizeModels;

    public static DishModel toModel(Dish dish){
        DishModel dishModel = new DishModel();
        dishModel.setName(dish.getName());
        dishModel.setPictureURL(dish.getPictureURL());

        Set<ProductModel> productModelSet = new HashSet<>();
        for (Product product: dish.getProducts()){
            productModelSet.add(ProductModel.toModel(product));
        }
        dishModel.setProductModels(productModelSet);

        Set<DishSizeModel> dishSizeModelsSet = new HashSet<>();
        for (DishSize dishSize: dish.getDishSizes()){
            dishSizeModelsSet.add(DishSizeModel.toModel(dishSize));
        }
        dishModel.setDishSizeModels(dishSizeModelsSet);

        return dishModel;
    }

    public DishModel() {
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

    public Set<ProductModel> getProductModels() {
        return productsModels;
    }

    public void setProductModels(Set<ProductModel> products) {
        this.productsModels = products;
    }

    public Set<DishSizeModel> getDishSizeModels() {
        return dishSizeModels;
    }

    public void setDishSizeModels(Set<DishSizeModel> dishSizeModels) {
        this.dishSizeModels = dishSizeModels;
    }
}
