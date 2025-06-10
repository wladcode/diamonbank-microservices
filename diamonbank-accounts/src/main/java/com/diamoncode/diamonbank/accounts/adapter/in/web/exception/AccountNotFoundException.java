package com.diamoncode.diamonbank.accounts.adapter.in.web.exception;

public class AccountNotFoundException extends RuntimeException{
    public AccountNotFoundException(String message) {
        super(message);
    }
}
