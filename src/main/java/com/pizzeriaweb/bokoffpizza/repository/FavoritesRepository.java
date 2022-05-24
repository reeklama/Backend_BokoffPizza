package com.pizzeriaweb.bokoffpizza.repository;

import com.pizzeriaweb.bokoffpizza.entity.Dish;
import com.pizzeriaweb.bokoffpizza.entity.Favorites;
import com.pizzeriaweb.bokoffpizza.entity.RegisteredUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FavoritesRepository extends JpaRepository<Favorites, Long> {
    List<Favorites> findByRegisteredUser(RegisteredUser registeredUser);
    Favorites findByRegisteredUserAndDish(RegisteredUser registeredUser, Dish dish);
}
