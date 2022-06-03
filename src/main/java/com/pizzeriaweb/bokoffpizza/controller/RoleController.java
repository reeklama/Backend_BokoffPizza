package com.pizzeriaweb.bokoffpizza.controller;

import com.pizzeriaweb.bokoffpizza.rest.AddRoleRequestDTO;
import com.pizzeriaweb.bokoffpizza.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin( origins = "*", maxAge = 3500)
public class RoleController {

    @Autowired
    UserDetailsServiceImpl userDetailsService;

    @PostMapping("/role")
    public ResponseEntity<?> setRole(@RequestBody AddRoleRequestDTO request) {
        try {
            userDetailsService.setRole(request.getMail(), request.getRoleName());
            return ResponseEntity.ok("Роль изменена");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}

