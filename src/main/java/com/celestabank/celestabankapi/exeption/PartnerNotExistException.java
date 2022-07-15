package com.celestabank.celestabankapi.exeption;

public class PartnerNotExistException extends RuntimeException {
    public PartnerNotExistException(String message){
        super(message);
    }
}

