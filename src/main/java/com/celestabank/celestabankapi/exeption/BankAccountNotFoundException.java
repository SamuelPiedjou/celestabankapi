package com.celestabank.celestabankapi.exeption;

public class  BankAccountNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    public BankAccountNotFoundException(String message) {
        super(message);
    }
}
