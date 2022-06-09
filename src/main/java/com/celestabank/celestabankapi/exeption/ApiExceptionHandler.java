package com.celestabank.celestabankapi.exeption;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.ZoneId;
import java.time.ZonedDateTime;
@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(value = {AdminNotFoundException.class, AlreadyHaveAnAccountException.class,BalanceNotSufficientException.class,
                                BankAccountNotFoundException.class,BankAccountNotActivatedException.class,BankAccountSuspendedException.class,BeneficiaryNotFoundException.class,
                                CustomerNotFoundException.class,CustomerAlreadyHaveAnAccountException.class,CustomerAlreadyExistsException.class,DetailsNotFoundException.class,
                                EmptyListtException.class,InvalidDetailsException.class,InvalidAccountException.class,NoSuchCustomerExistsException.class,TransactionNotFoundException.class})
    public ResponseEntity<Object> handleApiRequestException(ApiRequestException e){
        HttpStatus badRequest = HttpStatus.BAD_REQUEST;
        ApiException apiException = new ApiException(
                e.getMessage(),
                badRequest,
                ZonedDateTime.now(ZoneId.of("Z"))
        );
        return  new ResponseEntity<>(apiException, badRequest);
    }
}
