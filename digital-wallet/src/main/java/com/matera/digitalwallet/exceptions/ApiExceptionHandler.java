package com.matera.digitalwallet.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(InvalidAccountException.class)
    public ResponseEntity handleInvalidAccount(InvalidAccountException e) {
        Problem problem = new Problem(e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(problem);
    }

    @ExceptionHandler(InvalidValueException.class)
    public ResponseEntity handleInvalidAccount(InvalidValueException e) {
        Problem problem = new Problem(e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(problem);
    }

    @ExceptionHandler(InsufficientFundsException.class)
    public ResponseEntity handleInvalidAccount(InsufficientFundsException e) {
        Problem problem = new Problem(e.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(problem);
    }

}
