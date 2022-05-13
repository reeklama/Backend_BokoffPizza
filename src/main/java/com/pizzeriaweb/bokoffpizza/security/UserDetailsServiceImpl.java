package com.pizzeriaweb.bokoffpizza.security;

import com.pizzeriaweb.bokoffpizza.entity.RegisteredUser;
import com.pizzeriaweb.bokoffpizza.entity.Role;
import com.pizzeriaweb.bokoffpizza.exception.UserAlreadyExistsException;
import com.pizzeriaweb.bokoffpizza.repository.RegisteredUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service("userDetailsServiceImpl")
public class UserDetailsServiceImpl implements UserDetailsService {


    private final RegisteredUserRepository userRepository;

    @Autowired
    public UserDetailsServiceImpl(RegisteredUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        RegisteredUser user = userRepository.findByMail(email);
        if(user == null ) {
            throw new UsernameNotFoundException("Пользователь не найден");
        }
        return SecurityUser.fromUser(user);
    }
}