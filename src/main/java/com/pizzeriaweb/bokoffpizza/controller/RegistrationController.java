package com.pizzeriaweb.bokoffpizza.controller;


import com.pizzeriaweb.bokoffpizza.entity.RegisteredUser;
import com.pizzeriaweb.bokoffpizza.entity.Role;
import com.pizzeriaweb.bokoffpizza.repository.RegisteredUserRepository;
import com.pizzeriaweb.bokoffpizza.rest.RegistrationRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.regex.Pattern;

@CrossOrigin( origins = "*", maxAge = 3500)
@RestController
@RequestMapping("/registration")
public class RegistrationController {

    private static final Integer minPasswordLength = 6;
    @Autowired
    RegisteredUserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    final String regexPattern = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";

    @PostMapping()
    public ResponseEntity<?> register(@RequestBody RegistrationRequestDTO request) {

        if(!Pattern.compile(regexPattern).matcher(request.getMail()).matches()) {
            return ResponseEntity.badRequest().body("Некорректный формат почты");
        }
        if (userRepository.findByMail(request.getMail()) != null) {
            return ResponseEntity.badRequest().body("Пользователь с такой почтой уже существует");
        }
        RegisteredUser user = new RegisteredUser();
        user.setMail(request.getMail());
        if(!request.getPassword().equals(request.getPasswordConfirm())) {
            return ResponseEntity.badRequest().body("Пароли не совпадают");
        }
        if(request.getPassword().length() < minPasswordLength) {
            return ResponseEntity.badRequest().body("Минимальная длина пароля - " + minPasswordLength + " символов");
        }
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(new Role(1L, "ROLE_USER"));
        user.setBanned(false);
        userRepository.save(user);
        return ResponseEntity.ok("Пользователь зарегистрирован!");
    }
}

