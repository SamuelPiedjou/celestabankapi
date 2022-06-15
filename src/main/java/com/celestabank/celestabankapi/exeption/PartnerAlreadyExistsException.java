package com.celestabank.celestabankapi.exeption;

public class PartnerAlreadyExistsException extends RuntimeException {
    public PartnerAlreadyExistsException(String s) {
        super(s);
    }
}
