package com.celestabank.celestabankapi.exeption;

public class NoSuchCustomerExistsException extends RuntimeException {
    public NoSuchCustomerExistsException(String s) {
        super(s);
    }
}
