package com.celestabank.celestabankapi.exeption;

public class BankAccountSuspendedException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    public BankAccountSuspendedException(String s) {
        super(s);
    }
}
