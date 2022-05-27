package com.pizzeriaweb.bokoffpizza.controller;

import com.pizzeriaweb.bokoffpizza.entity.RegisteredUser;
import com.pizzeriaweb.bokoffpizza.exception.RegisteredUserNotFoundException;
import com.pizzeriaweb.bokoffpizza.model.OrderModel;
import com.pizzeriaweb.bokoffpizza.rest.ChangePassRequestDTO;
import com.pizzeriaweb.bokoffpizza.service.OrderService;
import com.pizzeriaweb.bokoffpizza.service.RegisteredUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

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

    @Autowired
    PasswordEncoder passwordEncoder;

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

    @PutMapping("/changepass")
    public ResponseEntity<?> changePassword(@RequestBody ChangePassRequestDTO request) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String mail = auth.getName();
        try {
            RegisteredUser user = registeredUserService.findByMail(mail);
            if(!passwordEncoder.matches(request.getOldPassword(), user.getPassword())) {
                return ResponseEntity.badRequest().body("Пароль не верен ");
            }
            if(!request.getNewPassword().equals(request.getNewPasswordConfirm())) {
                return ResponseEntity.badRequest().body("Пароли не совпадают");
            }
            registeredUserService.updateUserPassword(user, passwordEncoder.encode(request.getNewPassword()));
            return ResponseEntity.ok("Пароль обновлен");
        }
        catch (RegisteredUserNotFoundException e) {
            return ResponseEntity.badRequest().body("Пользователь не найден");
        }
    }
}
