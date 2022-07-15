package com.celestabank.celestabankapi.exeption;

public class BeneficiaryNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    public BeneficiaryNotFoundException(String msg) {
        super(msg);
    }
}
