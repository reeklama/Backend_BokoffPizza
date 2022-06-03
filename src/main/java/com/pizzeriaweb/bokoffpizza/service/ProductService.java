package com.pizzeriaweb.bokoffpizza.service;

import com.pizzeriaweb.bokoffpizza.entity.Dish;
import com.pizzeriaweb.bokoffpizza.entity.Product;
import com.pizzeriaweb.bokoffpizza.exception.EmptyProductsListException;
import com.pizzeriaweb.bokoffpizza.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;

    public List<Product> getProducts() throws Exception {
        List<Product> products = productRepository.findAll();
        if (products.size() == 0){
            throw new EmptyProductsListException("Список ингредиентов пуст");
        }
        return products;
    }

    public void addProduct(String name){
        if (productRepository.findByName(name) != null)
            return;

        Product product = new Product();
        product.setName(name);
        productRepository.save(product);
    }

    public void deleteProduct(String name){
        Product product = productRepository.findByName(name);
        if (product == null)
            return;

        for (Dish dish: product.getDishes()){
            dish.getProducts().remove(product);
        }
        productRepository.delete(product);
    }
}
