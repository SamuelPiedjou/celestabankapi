package com.celestabank.celestabankapi.exeption;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class AppExceptionHandler  extends ResponseEntityExceptionHandler {
    @ExceptionHandler({ATMNotExistException.class})
    public ResponseEntity<Object> handleATMNotFound(ATMNotExistException ex , WebRequest request){
        HttpStatus notFound = HttpStatus.NOT_FOUND;
        return   new ResponseEntity<Object>(new ApiError(ex.getMessage(), notFound, LocalDateTime.now()), notFound);
    }
    @ExceptionHandler({ATMAlreadyExistsException.class})
    public ResponseEntity<Object> handleATMExist(ATMAlreadyExistsException ex , WebRequest request){
        HttpStatus notFound = HttpStatus.FOUND;
        return   new ResponseEntity<Object>(new ApiError(ex.getMessage(), notFound, LocalDateTime.now()), notFound);
    }
    @ExceptionHandler({AdminNotFoundException.class})
    public ResponseEntity<Object> handleAdminNotFound(AdminNotFoundException ex , WebRequest request){
        HttpStatus notFound = HttpStatus.NOT_FOUND;
        return   new ResponseEntity<Object>(new ApiError(ex.getMessage(), notFound, LocalDateTime.now()), notFound);
    }
    @ExceptionHandler({BalanceNotSufficientException.class})
    public ResponseEntity<Object> handleBalanceNotSufficient(BalanceNotSufficientException ex , WebRequest request){
        HttpStatus notFound = HttpStatus.BAD_REQUEST;
        return   new ResponseEntity<Object>(new ApiError(ex.getMessage(), notFound, LocalDateTime.now()), notFound);
    }
    @ExceptionHandler({BankAccountNotFoundException.class})
    public ResponseEntity<Object> handleBankNotFound(BankAccountNotFoundException ex , WebRequest request){
        HttpStatus notFound = HttpStatus.NOT_FOUND;
        return   new ResponseEntity<Object>(new ApiError(ex.getMessage(), notFound, LocalDateTime.now()), notFound);
    }
    @ExceptionHandler({BankAccountNotActivatedException.class})
    public ResponseEntity<Object> handleBankNotActive(BankAccountNotActivatedException ex , WebRequest request){
        HttpStatus notFound = HttpStatus.METHOD_NOT_ALLOWED;
        return   new ResponseEntity<Object>(new ApiError(ex.getMessage(), notFound, LocalDateTime.now()), notFound);
    } @ExceptionHandler({BankAccountSuspendedException.class})
    public ResponseEntity<Object> handleBankSuspended(BankAccountSuspendedException ex , WebRequest request){
        HttpStatus notFound = HttpStatus.METHOD_NOT_ALLOWED;
        return   new ResponseEntity<Object>(new ApiError(ex.getMessage(), notFound, LocalDateTime.now()), notFound);
    } @ExceptionHandler({BeneficiaryNotFoundException.class})
    public ResponseEntity<Object> handleBeneficiaryNotFound(BeneficiaryNotFoundException ex , WebRequest request){
        HttpStatus notFound = HttpStatus.NOT_FOUND;
        return   new ResponseEntity<Object>(new ApiError(ex.getMessage(), notFound, LocalDateTime.now()), notFound);
    } @ExceptionHandler({BeneficiaryNotHaveAnAccount.class})
    public ResponseEntity<Object> handleBeneficiaryNotHaveAccount(BeneficiaryNotHaveAnAccount ex , WebRequest request){
        HttpStatus notFound = HttpStatus.NOT_FOUND;
        return   new ResponseEntity<Object>(new ApiError(ex.getMessage(), notFound, LocalDateTime.now()), notFound);
    } @ExceptionHandler({CustomerAlreadyExistsException.class})
    public ResponseEntity<Object> handleCustomerAlreadyExist(CustomerAlreadyExistsException ex , WebRequest request){
        HttpStatus notFound = HttpStatus.BAD_REQUEST;
        return   new ResponseEntity<Object>(new ApiError(ex.getMessage(), notFound, LocalDateTime.now()), notFound);
    } @ExceptionHandler({CustomerAlreadyHaveAnAccountException.class})
    public ResponseEntity<Object> handleCustomerHaveAccount(CustomerAlreadyHaveAnAccountException ex , WebRequest request){
        HttpStatus notFound = HttpStatus.METHOD_NOT_ALLOWED;
        return   new ResponseEntity<Object>(new ApiError(ex.getMessage(), notFound, LocalDateTime.now()), notFound);
    }
    @ExceptionHandler({CustomerNotFoundException.class})
    public ResponseEntity<Object> handleCustomerNotFound(CustomerNotFoundException ex , WebRequest request){
        HttpStatus notFound = HttpStatus.NOT_FOUND;
        return   new ResponseEntity<Object>(new ApiError(ex.getMessage(), notFound, LocalDateTime.now()), notFound);
    }
    @ExceptionHandler({NoSuchCustomerExistsException.class})
    public ResponseEntity<Object> handleCustomerNotFound(NoSuchCustomerExistsException ex , WebRequest request){
        HttpStatus notFound = HttpStatus.NOT_FOUND;
        return   new ResponseEntity<Object>(new ApiError(ex.getMessage(), notFound, LocalDateTime.now()), notFound);
    }
    @ExceptionHandler({NoSuchATMExistsException.class})
    public ResponseEntity<Object> handleATMNotFound(NoSuchATMExistsException ex , WebRequest request){
        HttpStatus notFound = HttpStatus.NOT_FOUND;
        return   new ResponseEntity<Object>(new ApiError(ex.getMessage(), notFound, LocalDateTime.now()), notFound);
    }
    @ExceptionHandler({PartnerAlreadyExistsException.class})
    public ResponseEntity<Object> handlePartnerNotFound(PartnerAlreadyExistsException ex , WebRequest request){
        HttpStatus methodNotAllowed = HttpStatus.METHOD_NOT_ALLOWED;
        return   new ResponseEntity<Object>(new ApiError(ex.getMessage(), methodNotAllowed, LocalDateTime.now()), methodNotAllowed);
    }
    @ExceptionHandler({PartnerNotExistException.class})
    public ResponseEntity<Object> handlePartnerNotFound(PartnerNotExistException ex , WebRequest request){
        HttpStatus notFound = HttpStatus.NOT_FOUND;
        return   new ResponseEntity<Object>(new ApiError(ex.getMessage(), notFound, LocalDateTime.now()), notFound);
    }
    @ExceptionHandler({TransactionNotFoundException.class})
    public ResponseEntity<Object> handleTransactionNotFound(TransactionNotFoundException ex , WebRequest request){
        HttpStatus notFound = HttpStatus.NOT_FOUND;
        return   new ResponseEntity<Object>(new ApiError(ex.getMessage(), notFound, LocalDateTime.now()), notFound);
    }

}
