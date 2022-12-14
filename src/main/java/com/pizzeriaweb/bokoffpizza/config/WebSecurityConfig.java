package com.pizzeriaweb.bokoffpizza.config;

import com.pizzeriaweb.bokoffpizza.security.JwtConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final JwtConfigurer jwtConfigurer;

    public WebSecurityConfig(JwtConfigurer jwtConfigurer) {
        this.jwtConfigurer = jwtConfigurer;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/menu").permitAll()
                .antMatchers("/registration").not().fullyAuthenticated()
                .antMatchers("/auth/login").permitAll()
                .antMatchers("/cart").not().hasAnyRole("SYSADMIN", "MODERATOR")
                .antMatchers("/ban").hasRole("SYSADMIN")
                .antMatchers("/users").hasRole("SYSADMIN")
                .antMatchers("/deleteuser").hasRole("SYSADMIN")
                .antMatchers("/role").hasRole("SYSADMIN")
                .antMatchers("/product").hasRole("MODERATOR")
                .antMatchers("/cabinet").hasAnyRole("SYSADMIN", "MODERATOR", "USER")
                .antMatchers("/changepass").hasAnyRole("SYSADMIN", "MODERATOR", "USER")
                .antMatchers("/favorites").hasAnyRole("SYSADMIN", "MODERATOR", "USER")
                .anyRequest()
                .authenticated()
                .and()
                .apply(jwtConfigurer);
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    protected PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(12);
    }
}