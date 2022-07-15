package com.celestabank.celestabankapi.exeption;

public class ATMAlreadyExistsException extends RuntimeException {
    public ATMAlreadyExistsException(String s) {
        super(s);
    }
}
