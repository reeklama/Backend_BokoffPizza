package com.pizzeriaweb.bokoffpizza.controller;

import com.pizzeriaweb.bokoffpizza.entity.RegisteredUser;
import com.pizzeriaweb.bokoffpizza.exception.RegisteredUserNotFoundException;
import com.pizzeriaweb.bokoffpizza.model.OrderModel;
import com.pizzeriaweb.bokoffpizza.service.OrderService;
import com.pizzeriaweb.bokoffpizza.service.RegisteredUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/cabinet")
@CrossOrigin( origins = "*", maxAge = 3500)
public class CabinetController {

    @Autowired
    OrderService orderService;

    @Autowired
    RegisteredUserService registeredUserService;

    @GetMapping
    public ResponseEntity<?> cab(Principal principal){
        try {
            RegisteredUser registeredUser = registeredUserService.findByMail(principal.getName());
            List<OrderModel> ordersList =  new ArrayList<>();
            orderService.getOrdersByUser(registeredUser).forEach(order ->
                    ordersList.add(OrderModel.toModel(order)));
            return ResponseEntity.ok(ordersList);
        } catch (RegisteredUserNotFoundException e) {
            return ResponseEntity.badRequest().body(e);
        }
    }
}
