package com.pizzeriaweb.bokoffpizza.controller;

import com.pizzeriaweb.bokoffpizza.rest.AddRoleRequestDTO;
import com.pizzeriaweb.bokoffpizza.service.RegisteredUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin( origins = "*", maxAge = 3500)
public class RoleController {

    @Autowired
    RegisteredUserService registeredUserService;

    @PostMapping("/addRole")
    public ResponseEntity<?> addRole(@RequestBody AddRoleRequestDTO request) {
        try {
            registeredUserService.addRole(request.getMail(), request.getRoleName());
            return ResponseEntity.ok("Роль добавлена");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}

