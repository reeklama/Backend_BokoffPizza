package com.pizzeriaweb.bokoffpizza.service;

import com.pizzeriaweb.bokoffpizza.entity.Customer;
import com.pizzeriaweb.bokoffpizza.entity.RegisteredUser;
import com.pizzeriaweb.bokoffpizza.entity.Role;
import com.pizzeriaweb.bokoffpizza.exception.RegisteredUserNotFoundException;
import com.pizzeriaweb.bokoffpizza.exception.RoleNotFoundException;
import com.pizzeriaweb.bokoffpizza.exception.UserAlreadyExistsException;
import com.pizzeriaweb.bokoffpizza.repository.RegisteredUserRepository;
import com.pizzeriaweb.bokoffpizza.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service("userDetailsServiceImpl")
public class UserDetailsServiceImpl implements UserDetailsService {


    @Autowired
    RoleRepository roleRepository;

    @Autowired
    RegisteredUserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        RegisteredUser user = findUserByMail(email);
        return new org.springframework.security.core.userdetails.User(
                user.getMail(), user.getPassword(),
                user.getAuthorities());
    }

    public RegisteredUser findUserByMail(String mail) {
        RegisteredUser user = userRepository.findByMail(mail);
        if(user == null ) {
            throw new UsernameNotFoundException("Пользователь не найден");
        }
        return user;
    }

    public Customer findCustomerByUserMail(String mail) {
        RegisteredUser user = userRepository.findByMail(mail);
        if(user == null ) {
            throw new UsernameNotFoundException("Пользователь не найден");
        }
        return user.getCustomer();
    }

    public void setRole(String mail, String roleName) throws Exception {
        Role role = roleRepository.findByName(roleName);
        if (role == null)
            throw new RoleNotFoundException("Не найдена роль с именем: " + roleName);

        RegisteredUser registeredUser = userRepository.findByMail(mail);
        if (registeredUser == null)
            throw new RegisteredUserNotFoundException("Не найден пользователь с email: " + mail);

        if (registeredUser.getRole().getName().equals(roleName))
            return;

        registeredUser.setRole(role);
        userRepository.save(registeredUser);
    }



    public void updateUserPassword(RegisteredUser user, String password) {
        user.setPassword(password);
        userRepository.save(user);
    }

    public void deleteUser(RegisteredUser user) {
        userRepository.delete(user);
    }

    public void saveUser(RegisteredUser user) {
        userRepository.save(user);
    }

    public List<RegisteredUser> findAll() {
        return userRepository.findAll();
    }
}