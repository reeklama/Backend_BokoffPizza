package com.pizzeriaweb.bokoffpizza.rest;

import lombok.Data;

import java.util.List;

@Data
public class OrderRequestDTO {

   private String first_name;

   private String phone_number;

   private String address;

   List<DishFromOrderDTO> dishes;
}
