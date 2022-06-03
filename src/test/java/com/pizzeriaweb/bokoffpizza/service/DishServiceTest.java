package com.pizzeriaweb.bokoffpizza.service;

import com.pizzeriaweb.bokoffpizza.entity.*;
import com.pizzeriaweb.bokoffpizza.exception.*;
import com.pizzeriaweb.bokoffpizza.model.DishModel;
import com.pizzeriaweb.bokoffpizza.model.DishSizeModel;
import com.pizzeriaweb.bokoffpizza.model.ProductModel;
import com.pizzeriaweb.bokoffpizza.repository.*;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest

class DishServiceTest {

    @Autowired
    DishService dishService;

    @MockBean
    DishRepository dishRepository;

    @MockBean
    ProductRepository productRepository;

    @MockBean
    DishSizeRepository dishSizeRepository;

    @Test
    void getDishes() throws Exception {
        List<Dish> dishes = new ArrayList<>();
        Dish dish = new Dish();
        dishes.add(dish);
        Mockito.doReturn(dishes)
                .when(dishRepository)
                .findAll();
        List<Dish> testDishes = dishService.getDishes();
        assertThat(testDishes.size(), equalTo(1));
        Mockito.verify(dishRepository, Mockito.times(1)).findAll();
    }

    @Test
    void getDishesFail() {
        List<Dish> dishes = new ArrayList<>();
        Mockito.doReturn(dishes)
                .when(dishRepository)
                .findAll();
        assertThrows(EmptyDishesListException.class, () -> {
            dishService.getDishes();
        });
        Mockito.verify(dishRepository, Mockito.times(1)).findAll();
    }

    @Test
    void addDish() throws ProductNotFoundException {

        ProductModel productModel = new ProductModel();
        productModel.setName("cheese");
        Set<ProductModel> productModels = new HashSet<>();
        productModels.add(productModel);
        Product product = new Product();
        product.setName("cheese");

        DishModel dishModel = new DishModel();
        dishModel.setName("Pizza");
        dishModel.setPictureURL("picture");
        dishModel.setProductModels(productModels);

        Mockito.doReturn(product)
                .when(productRepository)
                .findByName(productModel.getName());

        DishSizeModel dishSizeModel = new DishSizeModel();
        dishSizeModel.setPrice(500);
        dishSizeModel.setSize(30);

        Set<DishSizeModel> dishSizeModels = new HashSet<>();
        dishSizeModels.add(dishSizeModel);

        dishModel.setDishSizeModels(dishSizeModels);

        dishService.addDish(dishModel);

        Set<Product> productSet = new HashSet<>();
        productSet.add(product);

        Dish dish = new Dish();
        dish.setName(dishModel.getName());
        dish.setPictureURL(dishModel.getPictureURL());
        dish.setProducts(productSet);

        Mockito.verify(dishRepository, Mockito.times(1)).save(ArgumentMatchers.isNotNull());
        Mockito.verify(productRepository, Mockito.times(1)).findByName("cheese");

    }

    @Test
    void addDishFail() {
        ProductModel productModel = new ProductModel();
        productModel.setName("cheese");
        Set<ProductModel> productModels = new HashSet<>();
        productModels.add(productModel);
        Product product = new Product();
        product.setName("cheese");

        DishModel dishModel = new DishModel();
        dishModel.setName("Pizza");
        dishModel.setPictureURL("picture");
        dishModel.setProductModels(productModels);

        DishSizeModel dishSizeModel = new DishSizeModel();
        dishSizeModel.setPrice(500);
        dishSizeModel.setSize(30);
        Set<DishSizeModel> dishSizeModels = new HashSet<>();
        dishSizeModels.add(dishSizeModel);

        dishModel.setDishSizeModels(dishSizeModels);

        assertThrows(ProductNotFoundException.class, () -> {
            dishService.addDish(dishModel);;
        });
    }

    @Test
    void deleteDish() {
        Dish dish = new Dish();
        dish.setName("Pizza");

        DishModel dishModel = new DishModel();
        dishModel.setName("Pizza");

        Mockito.doReturn(dish)
                .when(dishRepository)
                .findByName(dishModel.getName());

        dishService.deleteDish(dishModel);

        Mockito.verify(dishRepository, Mockito.times(1)).delete(dish);
    }

    @Test
    void deleteDishFail() {
        Dish dish = new Dish();
        dish.setName("Pizza");

        DishModel dishModel = new DishModel();
        dishModel.setName("Pizza");
        dishService.deleteDish(dishModel);

        Mockito.verify(dishRepository, Mockito.times(0)).delete(dish);
        Mockito.verify(dishRepository, Mockito.times(1)).findByName(dishModel.getName());

    }

    @Test
    void findDishByName() {
        Dish dish = new Dish();
        dish.setName("Pizza");

        Mockito.doReturn(dish)
                .when(dishRepository)
                .findByName(dish.getName());

        dishService.findDishByName(dish.getName());

        Mockito.verify(dishRepository, Mockito.times(1)).findByName(dish.getName());
    }

    @Test
    void updateDish() throws ProductNotFoundException, DishNotFoundException {
        String oldDishName = "Pizza";
        DishModel newDish = new DishModel();
        newDish.setName("newPizza");
        newDish.setPictureURL("picture");

        Dish dish = new Dish();
        dish.setName(oldDishName);
        dish.setId(1L);

        Mockito.doReturn(dish)
                .when(dishRepository)
                .findByName(oldDishName);

        ProductModel productModel = new ProductModel();
        productModel.setName("cheese");
        Set<ProductModel> productModels = new HashSet<>();
        productModels.add(productModel);
        Product product = new Product();
        product.setName("cheese");
        newDish.setProductModels(productModels);

        Mockito.doReturn(product)
                .when(productRepository)
                .findByName(productModel.getName());


        DishSizeModel dishSizeModel = new DishSizeModel();
        dishSizeModel.setPrice(500);
        dishSizeModel.setSize(30);

        Set<DishSizeModel> dishSizeModels = new HashSet<>();
        dishSizeModels.add(dishSizeModel);

        newDish.setDishSizeModels(dishSizeModels);

        DishSize dishSize = new DishSize();
        dishSize.setDish(dish);

        Mockito.doReturn(dishSize)
                .when(dishSizeRepository)
                .findDishSizeByDish_idAndSize(dish.getId(), dishSizeModel.getSize());

        dishService.updateDish(oldDishName, newDish);
        Mockito.verify(dishRepository, Mockito.times(1)).save(ArgumentMatchers.isNotNull());
        Mockito.verify(productRepository, Mockito.times(1)).findByName("cheese");
    }

    @Test
    void updateDishDishNotFound() throws ProductNotFoundException, DishNotFoundException {
        String oldDishName = "Pizza";
        DishModel newDish = new DishModel();
        newDish.setName("newPizza");
        newDish.setPictureURL("picture");

        assertThrows(DishNotFoundException.class, () -> {
            dishService.updateDish(oldDishName, newDish);;
        });

        Mockito.verify(dishRepository, Mockito.times(1)).findByName(oldDishName);
    }

    @Test
    void updateDishProductNotFound() {
        String oldDishName = "Pizza";
        DishModel newDish = new DishModel();
        newDish.setName("newPizza");
        newDish.setPictureURL("picture");

        ProductModel productModel = new ProductModel();
        productModel.setName("cheese");
        Set<ProductModel> productModels = new HashSet<>();
        productModels.add(productModel);
        Product product = new Product();
        product.setName("cheese");
        newDish.setProductModels(productModels);

        Dish dish = new Dish();
        dish.setName(oldDishName);
        dish.setId(1L);

        Mockito.doReturn(dish)
                .when(dishRepository)
                .findByName(oldDishName);

        assertThrows(ProductNotFoundException.class, () -> {
            dishService.updateDish(oldDishName, newDish);;
        });
        Mockito.verify(dishRepository, Mockito.times(1)).findByName(oldDishName);
        Mockito.verify(productRepository, Mockito.times(1)).findByName(product.getName());
    }
}