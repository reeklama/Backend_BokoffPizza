package com.pizzeriaweb.bokoffpizza.service;


import com.pizzeriaweb.bokoffpizza.entity.Dish;
import com.pizzeriaweb.bokoffpizza.repository.DishRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DishService {
    @Autowired
    DishRepository dishRepository;

    public List<Dish> getDishes() throws Exception {
        List<Dish> dishes = dishRepository.findAll();
        if (dishes.size() == 0){
            throw new Exception("Список блюд пуст");
        }
        return dishes;
    }

    public Dish findDishByName(String name) {
       return dishRepository.findDishByName(name);
    }
}
