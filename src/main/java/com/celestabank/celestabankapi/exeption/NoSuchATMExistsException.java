package com.celestabank.celestabankapi.exeption;

public class NoSuchATMExistsException extends RuntimeException {
    public NoSuchATMExistsException(String s) {
        super(s);
    }
}
