package com.celestabank.celestabankapi.exeption;

public class ApiRequestException extends  RuntimeException {
    public  ApiRequestException(String s){

    }

    public ApiRequestException (String s, Throwable cause){
        super  (s, cause);
    }
}
