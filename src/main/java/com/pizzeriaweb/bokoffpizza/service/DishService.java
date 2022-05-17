package com.pizzeriaweb.bokoffpizza.service;


import com.pizzeriaweb.bokoffpizza.entity.Dish;
import com.pizzeriaweb.bokoffpizza.entity.DishSize;
import com.pizzeriaweb.bokoffpizza.entity.Product;
import com.pizzeriaweb.bokoffpizza.exception.EmptyDishesListException;
import com.pizzeriaweb.bokoffpizza.exception.ProductNotFoundException;
import com.pizzeriaweb.bokoffpizza.model.DishModel;
import com.pizzeriaweb.bokoffpizza.model.DishSizeModel;
import com.pizzeriaweb.bokoffpizza.model.ProductModel;
import com.pizzeriaweb.bokoffpizza.repository.DishRepository;
import com.pizzeriaweb.bokoffpizza.repository.DishSizeRepository;
import com.pizzeriaweb.bokoffpizza.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class DishService {
    @Autowired
    DishRepository dishRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    DishSizeRepository dishSizeRepository;

    public List<Dish> getDishes() throws Exception {
        List<Dish> dishes = dishRepository.findAll();
        if (dishes.size() == 0){
            throw new EmptyDishesListException("Список блюд пуст");
        }
        return dishes;
    }

    public void addDish(DishModel dishModel) throws ProductNotFoundException {
        Set<Product> productSet = new HashSet<>();
        for (ProductModel productModel: dishModel.getProductModels()){
            Product product = productRepository.findByName(productModel.getName());
            if (product == null){
                throw new ProductNotFoundException("Не найден продукт: " + productModel.getName());
            }
            productSet.add(product);
        }

        Dish dish = new Dish();
        dish.setName(dishModel.getName());
        dish.setPictureURL(dishModel.getPictureURL());
        dish.setProducts(productSet);
        dishRepository.save(dish);

        for (DishSizeModel dishSizeModel: dishModel.getDishSizeModels()){
            DishSize dishSize = new DishSize();
            dishSize.setDish(dish);
            dishSize.setSize(dishSizeModel.getSize());
            dishSize.setPrice(dishSizeModel.getPrice());
            dishSizeRepository.save(dishSize);
        }
    }

    public void deleteDish(DishModel dishModel){
        Dish dish = dishRepository.findByName(dishModel.getName());
        if (dish == null){
            return;
        }

        dishRepository.delete(dish);
    }
}
