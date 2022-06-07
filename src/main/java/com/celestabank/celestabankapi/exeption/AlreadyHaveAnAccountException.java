package com.celestabank.celestabankapi.exeption;

public class AlreadyHaveAnAccountException extends RuntimeException{
    public AlreadyHaveAnAccountException(String message) {
        super(message);
    }
}
