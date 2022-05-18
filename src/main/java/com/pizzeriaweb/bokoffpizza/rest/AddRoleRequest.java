package com.pizzeriaweb.bokoffpizza.rest;

import lombok.Data;

@Data
public class AddRoleRequest {
    private String mail;
    private String roleName;
}
