package com.celestabank.celestabankapi.exeption;

public class CustomerAlreadyHaveAnAccountException extends RuntimeException {
    public CustomerAlreadyHaveAnAccountException(String s) {
        super(s);
    }
}
