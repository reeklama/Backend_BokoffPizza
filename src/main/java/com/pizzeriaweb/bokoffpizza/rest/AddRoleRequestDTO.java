package com.pizzeriaweb.bokoffpizza.rest;

import lombok.Data;

@Data
public class AddRoleRequestDTO {
    private String mail;
    private String roleName;
}
