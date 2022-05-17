package com.pizzeriaweb.bokoffpizza.controller;

import com.pizzeriaweb.bokoffpizza.rest.AddRoleRequest;
import com.pizzeriaweb.bokoffpizza.service.RegisteredUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin( origins = "*", maxAge = 3500)
public class RoleController {

    @Autowired
    RegisteredUserService registeredUserService;

    @GetMapping("/addRole")
    public ResponseEntity<?> addRole(@RequestBody AddRoleRequest request) {
        try {
            registeredUserService.addRole(request.getMale(), request.getRoleName());
            return ResponseEntity.ok("Роль добавлена");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}

