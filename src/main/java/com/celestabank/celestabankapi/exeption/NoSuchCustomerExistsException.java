package com.celestabank.celestabankapi.exeption;

public class NoSuchCustomerExistsException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    public NoSuchCustomerExistsException(String s) {
        super(s);
    }
}
