package com.pizzeriaweb.bokoffpizza.repository;

import com.pizzeriaweb.bokoffpizza.entity.Dish;
import com.pizzeriaweb.bokoffpizza.entity.DishSize;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DishSizeRepository extends JpaRepository<DishSize, Long> {
    DishSize findDishSizeByDish_idAndSize(Long dish_id, Integer size);
}
