package com.pizzeriaweb.bokoffpizza.service;

import com.pizzeriaweb.bokoffpizza.entity.Dish;
import com.pizzeriaweb.bokoffpizza.entity.Favorites;
import com.pizzeriaweb.bokoffpizza.entity.FavoritesId;
import com.pizzeriaweb.bokoffpizza.entity.RegisteredUser;
import com.pizzeriaweb.bokoffpizza.exception.DishNotFoundException;
import com.pizzeriaweb.bokoffpizza.exception.FavoriteDishNotFoundException;
import com.pizzeriaweb.bokoffpizza.model.DishModel;
import com.pizzeriaweb.bokoffpizza.repository.FavoritesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FavoritesService {

    @Autowired
    UserDetailsServiceImpl registeredUserService;

    @Autowired
    FavoritesRepository favoritesRepository;

    @Autowired
    DishService dishService;

    public List<DishModel> getFavoritesDishesByMais(String mail) {
        RegisteredUser registeredUser = registeredUserService.findUserByMail(mail);
        List<Favorites> favorites = favoritesRepository.findByRegisteredUser(registeredUser);
        return favorites.stream()
                .map(favorites1 -> DishModel.toModel(favorites1.getDish()))
                .collect(Collectors.toList());
    }

    public void addFavoriteDish(String mail, String dishName) throws DishNotFoundException {
        RegisteredUser registeredUser = registeredUserService.findUserByMail(mail);
        Dish dish = dishService.findDishByName(dishName);
        FavoritesId favoritesId = new FavoritesId(dish, registeredUser);
        Favorites favorites = new Favorites();
        favorites.setId(favoritesId);
        favoritesRepository.save(favorites);
    }

    public void deleteFavoriteDish(String mail, String dishName) throws DishNotFoundException, FavoriteDishNotFoundException {
        RegisteredUser registeredUser = registeredUserService.findUserByMail(mail);
        Dish dish = dishService.findDishByName(dishName);
        Favorites favorites = favoritesRepository.findByRegisteredUserAndDish(registeredUser, dish);
        if (favorites == null){
            throw new FavoriteDishNotFoundException("Не найдено избранное блюдо с именем: " + dish.getName()
                    + " пользователя: " + registeredUser.getMail());
        }
        favoritesRepository.delete(favorites);
    }
}
