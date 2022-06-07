package com.celestabank.celestabankapi.exeption;

public class CustomerAlreadyExistsException extends RuntimeException {
    public CustomerAlreadyExistsException(String s) {
        super(s);
    }
}
