package com.matera.digitalwallet.exceptions;

public class InvalidAccountException extends RuntimeException {
    public InvalidAccountException(String s) {
        super(s);
    }
}
