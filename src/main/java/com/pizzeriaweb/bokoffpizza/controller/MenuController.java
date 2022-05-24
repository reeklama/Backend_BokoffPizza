package com.pizzeriaweb.bokoffpizza.controller;

import com.pizzeriaweb.bokoffpizza.exception.ProductNotFoundException;
import com.pizzeriaweb.bokoffpizza.model.DishModel;
import com.pizzeriaweb.bokoffpizza.rest.MenuUpdateRequestDTO;
import com.pizzeriaweb.bokoffpizza.service.DishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@CrossOrigin( origins = "*", maxAge = 3500)
@RestController
@RequestMapping("/menu")
public class MenuController {

    @Autowired
    DishService dishService;

    @GetMapping
    public ResponseEntity<?> getMenu() {
        try {
            List<DishModel> dishModelList = new ArrayList<>();
            dishService.getDishes().forEach(dish -> dishModelList.add(DishModel.toModel(dish)));
            return ResponseEntity.ok(dishModelList);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<?> addDish(@RequestBody DishModel request){
        try {
            dishService.addDish(request);
        } catch (ProductNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        return ResponseEntity.ok("success");
    }

    @PutMapping
    public ResponseEntity<?> addDish(@RequestBody MenuUpdateRequestDTO request){
        try {
            dishService.updateDish(request.getOldDishName(), request.getNewDish());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        return ResponseEntity.ok("success");
    }

    @DeleteMapping
    public ResponseEntity<?> deleteDish(@RequestBody DishModel request){
        dishService.deleteDish(request);
        return ResponseEntity.ok("success");
    }
}
