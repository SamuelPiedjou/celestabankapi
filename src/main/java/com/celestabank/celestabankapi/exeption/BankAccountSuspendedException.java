package com.celestabank.celestabankapi.exeption;

public class BankAccountSuspendedException extends Throwable {
    public BankAccountSuspendedException(String s) {
        super(s);
    }
}
