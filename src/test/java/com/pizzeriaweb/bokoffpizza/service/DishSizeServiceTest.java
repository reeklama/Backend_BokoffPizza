package com.pizzeriaweb.bokoffpizza.service;

import com.pizzeriaweb.bokoffpizza.entity.Dish;
import com.pizzeriaweb.bokoffpizza.entity.DishSize;
import com.pizzeriaweb.bokoffpizza.repository.DishSizeRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class DishSizeServiceTest {

    @Autowired
    DishSizeService dishSizeService;

    @MockBean
    DishSizeRepository dishSizeRepository;

    @Test
    void findByDishAndSize() {
        DishSize dishSize = new DishSize();
        Dish dish = new Dish();
        dish.setId(1L);
        dish.setName("Pizza");
        Integer size = 30;

        Mockito.doReturn(dishSize)
                .when(dishSizeRepository)
                .findDishSizeByDish_idAndSize(dish.getId(), size);

        DishSize testDishSize = dishSizeService.findByDishAndSize(dish, size);

        assertThat(testDishSize, notNullValue());
        Mockito.verify(dishSizeRepository, Mockito.times(1)).findDishSizeByDish_idAndSize(dish.getId(), size);
    }

    @Test
    void save() {
        DishSize dishSize = new DishSize();
        dishSizeService.save(dishSize);

        Mockito.verify(dishSizeRepository, Mockito.times(1)).save(dishSize);
    }
}