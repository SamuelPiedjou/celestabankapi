package com.celestabank.celestabankapi.exeption;

public class TransactionNotFoundException extends RuntimeException {
    public TransactionNotFoundException(String message){super(message);}
}
