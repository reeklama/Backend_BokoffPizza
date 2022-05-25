package com.pizzeriaweb.bokoffpizza.repository;

import com.pizzeriaweb.bokoffpizza.entity.Dish;
import com.pizzeriaweb.bokoffpizza.entity.DishSize;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DishSizeRepository extends JpaRepository<DishSize, Long> {
    DishSize findDishSizeByDish_idAndSize(Long dish_id, Integer size);
    List<DishSize> findByDish(Dish dish);
}
