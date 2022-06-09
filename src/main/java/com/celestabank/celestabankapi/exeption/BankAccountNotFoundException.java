package com.celestabank.celestabankapi.exeption;

public class  BankAccountNotFoundException extends RuntimeException {
    public BankAccountNotFoundException(String message) {
        super(message);
    }
}
