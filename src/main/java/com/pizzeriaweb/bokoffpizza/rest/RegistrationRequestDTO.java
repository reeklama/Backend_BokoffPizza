package com.pizzeriaweb.bokoffpizza.rest;

import lombok.Data;

@Data
public class RegistrationRequestDTO {
    private String mail;
    private String password;
    private String passwordConfirm;
}
