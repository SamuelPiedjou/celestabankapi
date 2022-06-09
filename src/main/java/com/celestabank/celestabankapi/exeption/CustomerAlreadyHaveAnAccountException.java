package com.celestabank.celestabankapi.exeption;

public class CustomerAlreadyHaveAnAccountException extends Exception {
    public CustomerAlreadyHaveAnAccountException(String s) {
        super(s);
    }
}
