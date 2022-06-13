package com.celestabank.celestabankapi.exeption;

public class DetailsNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    public DetailsNotFoundException(String message){super(message);}
}
