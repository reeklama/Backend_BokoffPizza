package com.pizzeriaweb.bokoffpizza.model;

import com.pizzeriaweb.bokoffpizza.entity.Product;
import lombok.Data;

@Data
public class ProductModel {
    String name;

    public static ProductModel toModel(Product product){
        ProductModel  productModel = new ProductModel();
        productModel.setName(product.getName());
        return productModel;
    }
}
