package com.celestabank.celestabankapi.exeption;

public class BalanceNotSufficientException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    public BalanceNotSufficientException(String message) {
        super(message);
    }
}
