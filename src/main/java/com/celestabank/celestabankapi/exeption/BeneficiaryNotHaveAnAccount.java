package com.celestabank.celestabankapi.exeption;

public class BeneficiaryNotHaveAnAccount extends RuntimeException {
    public BeneficiaryNotHaveAnAccount(String s) {
        super(s);
    }
}
