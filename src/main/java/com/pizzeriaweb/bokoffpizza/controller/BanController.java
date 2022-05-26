package com.pizzeriaweb.bokoffpizza.controller;

import com.pizzeriaweb.bokoffpizza.entity.RegisteredUser;
import com.pizzeriaweb.bokoffpizza.exception.RegisteredUserNotFoundException;
import com.pizzeriaweb.bokoffpizza.rest.UserListDTO;
import com.pizzeriaweb.bokoffpizza.service.RegisteredUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin( origins = "*", maxAge = 3500)
@RestController
@RequestMapping("/ban")
public class BanController {
    @Autowired
    RegisteredUserService registeredUserService;

    @PostMapping()
    public ResponseEntity<?> banUsers(@RequestBody UserListDTO banList) {
        List<String> badMails = new ArrayList<>();
        for(String mail : banList.getMails()) {
            try {
                RegisteredUser user = registeredUserService.findByMail(mail);
                user.setBanned(true);
                registeredUserService.saveUser(user);

            } catch (RegisteredUserNotFoundException e) {
                badMails.add(mail);
            }
        }
        if(badMails.size() > 0) {
            StringBuilder badMailsSb = new StringBuilder();
            for(String badMail : badMails) {
                badMailsSb.append(badMail + "\n");
            }
            return ResponseEntity.ok("Пользователи с такими почтами не найдены: " + badMailsSb + "Остальные указанные пользователи заблокированы");
        }

        return ResponseEntity.ok("Пользователи заблокированы");
    }
}
