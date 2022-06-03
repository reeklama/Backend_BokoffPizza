package com.pizzeriaweb.bokoffpizza.service;

import com.pizzeriaweb.bokoffpizza.entity.Dish;
import com.pizzeriaweb.bokoffpizza.entity.DishSize;
import com.pizzeriaweb.bokoffpizza.repository.DishSizeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DishSizeService {

    @Autowired
    DishSizeRepository dishSizeRepository;

    public DishSize findByDishAndSize(Dish dish, Integer size) {
        return dishSizeRepository.findDishSizeByDishIdAndSize(dish.getId(), size);
    }

    public void save(DishSize dishSize) {
        dishSizeRepository.save(dishSize);
    }
}
