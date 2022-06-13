package com.celestabank.celestabankapi.exeption;

public class AlreadyHaveAnAccountException extends RuntimeException{
    private static final long serialVersionUID = 1L;
    public AlreadyHaveAnAccountException(String message) {
        super(message);
    }
}
