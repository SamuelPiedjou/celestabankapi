package com.celestabank.celestabankapi.exeption;

public class AdminNotFoundException extends  RuntimeException{
    public AdminNotFoundException(String message){super(message);}
    private static final long serialVersionUID = 1L;
}
