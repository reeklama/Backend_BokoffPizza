package com.pizzeriaweb.bokoffpizza.service;


import com.pizzeriaweb.bokoffpizza.entity.RegisteredUser;
import com.pizzeriaweb.bokoffpizza.entity.Role;
import com.pizzeriaweb.bokoffpizza.exception.RegisteredUserNotFoundException;
import com.pizzeriaweb.bokoffpizza.exception.RoleNotFoundException;
import com.pizzeriaweb.bokoffpizza.repository.RegisteredUserRepository;
import com.pizzeriaweb.bokoffpizza.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class RegisteredUserService {
    @Autowired
    RoleRepository roleRepository;

    @Autowired
    RegisteredUserRepository registeredUserRepository;

    public void addRole(String mail, String roleName) throws Exception {
        Role role = roleRepository.findByName(roleName);
        if (role == null)
            throw new RoleNotFoundException("Не найдена роль с именем: " + roleName);

        RegisteredUser registeredUser = registeredUserRepository.findByMail(mail);
        if (registeredUser == null)
            throw new RegisteredUserNotFoundException("Не найден пользователь с email: " + mail);

        if (registeredUser.getRoles().contains(role))
            return;

        registeredUser.addRole(role);
        registeredUserRepository.save(registeredUser);
    }

    public RegisteredUser findByMail(String mail) throws RegisteredUserNotFoundException {
        RegisteredUser user = registeredUserRepository.findByMail(mail);
        if (user == null){
            throw new RegisteredUserNotFoundException("Не найден пользователь с email: " + mail);
        }
        return user;
    }

    public void updateUserPassword(RegisteredUser user, String password) {
        user.setPassword(password);
        registeredUserRepository.save(user);
    }

    public void deleteUser(RegisteredUser user) {
        registeredUserRepository.delete(user);
    }

    public void saveUser(RegisteredUser user) {
        registeredUserRepository.save(user);
    }
}
