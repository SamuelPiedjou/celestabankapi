package com.celestabank.celestabankapi.exeption;

public class BankAccountNotActivatedException extends RuntimeException {
    public BankAccountNotActivatedException(String s) {
        super(s);
    }
}
