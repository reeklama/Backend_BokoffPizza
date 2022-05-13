package com.pizzeriaweb.bokoffpizza.rest;


import com.pizzeriaweb.bokoffpizza.entity.RegisteredUser;
import com.pizzeriaweb.bokoffpizza.entity.Role;
import com.pizzeriaweb.bokoffpizza.exception.UserAlreadyExistsException;
import com.pizzeriaweb.bokoffpizza.repository.RegisteredUserRepository;
import com.pizzeriaweb.bokoffpizza.security.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/registration")
public class RegistrationController {
    @Autowired
    RegisteredUserRepository userRespository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @PostMapping()
    public ResponseEntity<?> register(@RequestBody RegistrationRequestDTO request) {

        if (userRespository.findByMail(request.getMail()) != null) {
            return ResponseEntity.badRequest().body("Пользователь с такой почтой уже существует");
        }
        RegisteredUser user = new RegisteredUser();
        user.setMail(request.getMail());
        if(!request.getPassword().equals(request.getPasswordConfirm())) {
            return ResponseEntity.badRequest().body("Пароли не совпадают!");
        }
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRoles(Collections.singleton(new Role(1L, "ROLE_USER")));
        userRespository.save(user);
        return ResponseEntity.ok("Пользователь зарегистрирован!");
    }
}

