package com.celestabank.celestabankapi.exeption;

public class AlreadyHaveAnAccountException extends Exception{
    public AlreadyHaveAnAccountException(String message) {
        super(message);
    }
}
