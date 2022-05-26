package com.pizzeriaweb.bokoffpizza.rest;

import lombok.Data;

@Data
public class ChangePassRequestDTO {

    private String oldPassword;

    private String newPassword;

    private String newPasswordConfirm;
}
