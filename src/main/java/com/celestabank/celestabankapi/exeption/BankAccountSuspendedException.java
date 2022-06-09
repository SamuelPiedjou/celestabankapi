package com.celestabank.celestabankapi.exeption;

public class BankAccountSuspendedException extends RuntimeException {
    public BankAccountSuspendedException(String s) {
        super(s);
    }
}
