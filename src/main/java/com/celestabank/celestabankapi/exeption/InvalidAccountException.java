package com.celestabank.celestabankapi.exeption;

public class InvalidAccountException extends  RuntimeException {
    private static final long serialVersionUID = 1L;
    public InvalidAccountException(String message){super(message);}
}
