package com.pizzeriaweb.bokoffpizza.controller;

import com.pizzeriaweb.bokoffpizza.model.DishModel;
import com.pizzeriaweb.bokoffpizza.service.DishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.ArrayList;
import java.util.List;


@CrossOrigin( origins = "*", maxAge = 3500)
@RestController
public class MenuController {

    @Autowired
    DishService dishService;

    @GetMapping("/menu")
    public ResponseEntity<?> menuList() {
        try {
            List<DishModel> dishModelList = new ArrayList<>();
            dishService.getDishes().forEach(dish -> dishModelList.add(DishModel.toModel(dish)));
            return ResponseEntity.ok(dishModelList);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
