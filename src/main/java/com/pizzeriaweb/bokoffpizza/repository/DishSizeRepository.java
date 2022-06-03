package com.pizzeriaweb.bokoffpizza.repository;

import com.pizzeriaweb.bokoffpizza.entity.Dish;
import com.pizzeriaweb.bokoffpizza.entity.DishSize;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DishSizeRepository extends JpaRepository<DishSize, Long> {
    DishSize findDishSizeByDishIdAndSize(Long dishId, Integer size);
    List<DishSize> findByDish(Dish dish);
}
