package com.celestabank.celestabankapi.exeption;

public class BankAccountNotActivatedException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    public BankAccountNotActivatedException(String s) {
        super(s);
    }
}
