package com.pizzeriaweb.bokoffpizza.rest;

import com.pizzeriaweb.bokoffpizza.model.DishModel;
import lombok.Data;

@Data
public class MenuUpdateRequestDTO {
    String oldDishName;
    DishModel newDish;
}
