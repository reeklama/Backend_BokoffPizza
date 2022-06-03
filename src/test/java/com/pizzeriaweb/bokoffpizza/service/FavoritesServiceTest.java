package com.pizzeriaweb.bokoffpizza.service;

import com.pizzeriaweb.bokoffpizza.entity.Dish;
import com.pizzeriaweb.bokoffpizza.entity.Favorites;
import com.pizzeriaweb.bokoffpizza.entity.Product;
import com.pizzeriaweb.bokoffpizza.entity.RegisteredUser;
import com.pizzeriaweb.bokoffpizza.exception.DishNotFoundException;
import com.pizzeriaweb.bokoffpizza.exception.FavoriteDishNotFoundException;
import com.pizzeriaweb.bokoffpizza.repository.FavoritesRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

@RunWith(SpringRunner.class)
@SpringBootTest
class FavoritesServiceTest {

    @Autowired
    FavoritesService favoritesService;

    @MockBean
    UserDetailsServiceImpl registeredUserService;

    @MockBean
    DishService dishService;

    @MockBean
    FavoritesRepository favoritesRepository;

    @Test
    void getFavoritesDishesByMais() {
        RegisteredUser registeredUser = new RegisteredUser();
        Mockito.doReturn(registeredUser)
                .when(registeredUserService)
                .findUserByMail("mail");
        Mockito.doReturn(new ArrayList<>())
                .when(favoritesRepository)
                .findByRegisteredUser(registeredUser);
        assertTrue(favoritesService.getFavoritesDishesByMais("mail").isEmpty());
    }

    @Test
    void addFavoriteDish() throws DishNotFoundException {
        favoritesService.addFavoriteDish("mail", "name");
        Mockito.verify(favoritesRepository, Mockito.times(1)).save(ArgumentMatchers.any(Favorites.class));
    }

    @Test
    void deleteFavoriteDish1() throws DishNotFoundException {
        Dish dish = new Dish();
        RegisteredUser registeredUser = new RegisteredUser();
        Mockito.doReturn(dish)
                .when(dishService)
                .findDishByName("name");
        Mockito.doReturn(registeredUser)
                .when(registeredUserService)
                .findUserByMail("mail");
        assertThrows(FavoriteDishNotFoundException.class, ()-> favoritesService.deleteFavoriteDish("mail", "name"));
    }

    @Test
    void deleteFavoriteDish2() throws DishNotFoundException, FavoriteDishNotFoundException {
        Dish dish = new Dish();
        RegisteredUser registeredUser = new RegisteredUser();
        Mockito.doReturn(dish)
                .when(dishService)
                .findDishByName("name");
        Mockito.doReturn(registeredUser)
                .when(registeredUserService)
                .findUserByMail("mail");
        Mockito.doReturn(new Favorites())
                .when(favoritesRepository)
                .findByRegisteredUserAndDish(registeredUser, dish);
        favoritesService.deleteFavoriteDish("mail", "name");
        Mockito.verify(favoritesRepository, Mockito.times(1)).delete(ArgumentMatchers.any(Favorites.class));
    }
}