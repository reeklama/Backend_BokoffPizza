package com.pizzeriaweb.bokoffpizza.exception;

public class TooShortPasswordException extends Exception{
    public TooShortPasswordException(String msg) {
        super(msg);
    }
}
