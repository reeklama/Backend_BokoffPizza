package com.pizzeriaweb.bokoffpizza.exception;

public class RegisteredUserNotFoundException extends Exception{
    public RegisteredUserNotFoundException(String message) {
        super(message);
    }
}
