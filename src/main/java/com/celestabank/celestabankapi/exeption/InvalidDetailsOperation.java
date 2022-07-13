package com.celestabank.celestabankapi.exeption;

public class InvalidDetailsOperation extends RuntimeException {
    private static final long serialVersionUID = 1L;
    public InvalidDetailsOperation(String message){super(message);}
}
