package com.pizzeriaweb.bokoffpizza.rest;

import lombok.Data;

import java.util.List;

@Data
public class OrderRequestDTO {

   private String firstName;

   private String phoneNumber;

   private String address;

   List<DishFromOrderDTO> dishes;
}
