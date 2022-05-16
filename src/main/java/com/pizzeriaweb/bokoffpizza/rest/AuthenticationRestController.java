package com.pizzeriaweb.bokoffpizza.rest;

import com.pizzeriaweb.bokoffpizza.entity.RegisteredUser;
import com.pizzeriaweb.bokoffpizza.repository.RegisteredUserRepository;
import com.pizzeriaweb.bokoffpizza.security.JwtTokenProvider;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@CrossOrigin( origins = "*", maxAge = 3500)
@RestController
@RequestMapping("/api/v1/auth")
public class AuthenticationRestController {

    private final AuthenticationManager authenticationManager;
    private RegisteredUserRepository userRepository;
    private JwtTokenProvider jwtTokenProvider;

    public AuthenticationRestController(AuthenticationManager authenticationManager, RegisteredUserRepository userRepository, JwtTokenProvider jwtTokenProvider) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @PostMapping("/login")
    public ResponseEntity<?> authenticate(@RequestBody AuthenticationRequestDTO request) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getMail(), request.getPassword()));
            RegisteredUser user = userRepository.findByMail(request.getMail());
            if(user == null) {
                throw new UsernameNotFoundException("Пользователь не найден");
            }
            String token = jwtTokenProvider.createToken(request.getMail(), user.getRoles().stream().findFirst().get().toString());
            Map<Object, Object> response = new HashMap<>();
            response.put("mail", request.getMail());
            response.put("token", token);
            return ResponseEntity.ok(response);
        } catch (AuthenticationException e) {
            return new ResponseEntity<>("Неверная почта или пароль", HttpStatus.FORBIDDEN);
        }
    }

    @PostMapping("/logout")
    public void logout(HttpServletRequest request, HttpServletResponse response) {
        SecurityContextLogoutHandler securityContextLogoutHandler = new SecurityContextLogoutHandler();
        securityContextLogoutHandler.logout(request, response, null);
    }
}