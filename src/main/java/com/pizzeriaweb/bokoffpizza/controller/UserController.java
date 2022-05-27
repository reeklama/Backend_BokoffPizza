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
public class UserController {

    @Autowired
    RegisteredUserService registeredUserService;

    @GetMapping("/users")
    public ResponseEntity<?> getUsersMails() {
        List<RegisteredUser> usersList = registeredUserService.findAll();
        List<String> mails = new ArrayList<>();
        for(RegisteredUser user : usersList) {
            mails.add(user.getMail());
        }
        return ResponseEntity.ok(mails);
    }

    @PostMapping("/ban")
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

    @DeleteMapping("/deleteuser")
    public ResponseEntity<?> deleteUsers(@RequestBody UserListDTO request) {
        List<String> badMails = new ArrayList<>();
        for(String mail : request.getMails()) {
            try {
                RegisteredUser user = registeredUserService.findByMail(mail);
                registeredUserService.deleteUser(user);
            } catch (RegisteredUserNotFoundException e) {
                badMails.add(mail);
            }
        }
        if(badMails.size() > 0) {
            StringBuilder badMailsSb = new StringBuilder();
            for(String badMail : badMails) {
                badMailsSb.append(badMail + "\n");
            }
            return ResponseEntity.ok("Пользователи с такими почтами не найдены: " + badMailsSb + "Остальные указанные пользователи удалены");
        }

        return ResponseEntity.ok("Пользователи удалены");
    }
}
