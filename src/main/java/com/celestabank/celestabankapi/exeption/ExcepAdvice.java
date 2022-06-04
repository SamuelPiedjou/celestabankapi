package com.celestabank.celestabankapi.exeption;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

public class ExcepAdvice {
    @ResponseBody
    @ResponseStatus(value= HttpStatus.NOT_FOUND)
    @ExceptionHandler({Exception.class})
    public ErrorMapper handleException(Exception e, HttpServletRequest req) {
        String res=e.getMessage();
        String url=req.getRequestURL().toString();
        return new ErrorMapper(url, res,new Date());
    }
}
