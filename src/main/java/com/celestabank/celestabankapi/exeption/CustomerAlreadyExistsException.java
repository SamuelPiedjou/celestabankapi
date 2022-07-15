package com.celestabank.celestabankapi.exeption;

public class CustomerAlreadyExistsException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    public CustomerAlreadyExistsException(String s) {
        super(s);
    }
}
