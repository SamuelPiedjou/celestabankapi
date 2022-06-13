package com.celestabank.celestabankapi.exeption;

public class CustomerAlreadyHaveAnAccountException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    public CustomerAlreadyHaveAnAccountException(String s) {
        super(s);
    }
}
