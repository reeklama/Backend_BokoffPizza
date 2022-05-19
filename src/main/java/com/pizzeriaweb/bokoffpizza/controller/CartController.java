package com.pizzeriaweb.bokoffpizza.controller;

import com.pizzeriaweb.bokoffpizza.entity.*;
import com.pizzeriaweb.bokoffpizza.model.CustomerModel;
import com.pizzeriaweb.bokoffpizza.repository.OrderListRepository;
import com.pizzeriaweb.bokoffpizza.rest.DishFromOrderDTO;
import com.pizzeriaweb.bokoffpizza.rest.OrderRequestDTO;
import com.pizzeriaweb.bokoffpizza.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;

@CrossOrigin( origins = "*", maxAge = 3500)
@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    OrderService orderService;

    @Autowired
    OrderListService orderListService;
    @Autowired
    UserDetailsServiceImpl userDetailsServiceImpl;

    @Autowired
    CustomerService customerService;

    @Autowired
    DishService dishService;

    @Autowired
    DishSizeService dishSizeService;

    @Autowired
    OrderListRepository orderListRepository;

    @GetMapping()
    public ResponseEntity<?> checkUserData() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        boolean isLoggedIn = auth.isAuthenticated() && !(auth instanceof AnonymousAuthenticationToken);
        if(!isLoggedIn) {
            return ResponseEntity.badRequest().body("Нет информации о пользователе");
        }

        String mail = auth.getName();
        Customer customer = userDetailsServiceImpl.findCustomerByUserMail(mail);
        if(customer == null) {
            return ResponseEntity.badRequest().body("Нет информации о пользователе");
        }
        CustomerModel customerModel = CustomerModel.toModel(customer);

        return ResponseEntity.ok(customerModel);
    }

    @PostMapping
    public ResponseEntity<?> insertOrderInDB(@RequestBody OrderRequestDTO request) {

        Customer customer = customerService.saveCustomerByOrderRequest(request);

        Order order = new Order();
        order.setOrder_date(new Timestamp(System.currentTimeMillis()));
        order.setCustomer(customer);
        orderService.saveOrder(order);

        for(DishFromOrderDTO dishDTO : request.getDishes()) {
            OrderList orderList = new OrderList();
            orderList.setOrder(order);

            Dish dish = dishService.findDishByName(dishDTO.getDishName());

            DishSize dishSize = dishSizeService.findByDishAndSize(dish, dishDTO.getSize());

            orderList.setDishSize(dishSize);
            orderList.setAmount(dishDTO.getAmount());

            orderListService.saveOrderList(orderList);
        }

        return ResponseEntity.ok("Заказ сохранен");
    }

}
