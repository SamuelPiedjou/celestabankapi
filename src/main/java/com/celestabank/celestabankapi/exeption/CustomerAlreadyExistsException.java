package com.celestabank.celestabankapi.exeption;

public class CustomerAlreadyExistsException extends Exception {
    public CustomerAlreadyExistsException(String s) {
        super(s);
    }
}
