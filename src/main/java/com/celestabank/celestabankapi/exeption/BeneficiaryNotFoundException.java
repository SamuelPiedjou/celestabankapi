package com.celestabank.celestabankapi.exeption;

public class BeneficiaryNotFoundException extends RuntimeException {
    public BeneficiaryNotFoundException(String msg) {
        super(msg);
    }
}
