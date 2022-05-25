package com.pizzeriaweb.bokoffpizza.model;

import com.pizzeriaweb.bokoffpizza.entity.Product;

public class ProductModel {
    String name;

    public static ProductModel toModel(Product product){
        ProductModel  productModel = new ProductModel();
        productModel.setName(product.getName());
        return productModel;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
