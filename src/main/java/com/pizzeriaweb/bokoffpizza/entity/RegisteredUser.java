package com.pizzeriaweb.bokoffpizza.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "registered_users")
public class RegisteredUser implements UserDetails {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "registered_user_id")
    private Long registeredUserId;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "customer_id")
    private Customer customer;
    private String mail;
    private String password;

    @Column(name = "is_banned")
    private boolean isBanned;

    @Transient
    private String passwordConfirm;

    @ManyToOne(fetch = FetchType.EAGER)
    private Role role;

    public RegisteredUser(){
    }

    public Long getRegisteredUserId() {
        return registeredUserId;
    }

    public void setRegisteredUserId(Long registered_user_id) {
        this.registeredUserId = registered_user_id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) { this.role = role; }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPasswordConfirm() {
        return passwordConfirm;
    }

    public void setPasswordConfirm(String passwordConfirm) {
        this.passwordConfirm = passwordConfirm;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<Role> Roles = new HashSet<>();
        Roles.add(this.getRole());
        return Roles;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return mail;
    }

    @Override
    public boolean isAccountNonExpired() {
        return isBanned;
    }

    @Override
    public boolean isAccountNonLocked() {
        return isBanned;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return isBanned;
    }

    @Override
    public boolean isEnabled() {
        return isBanned;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString(){
        return registeredUserId.toString() + " " + mail + " " + password;
    }

    public boolean isBanned() {
        return isBanned;
    }

    public void setBanned(boolean banned) {
        isBanned = banned;
    }
}


