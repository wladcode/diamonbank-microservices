package com.diamoncode.diamonbank.accounts.adapter.in.web.exception;

public class ClientValidationException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public ClientValidationException(String message) {
        super(message);
    }

    public ClientValidationException(String message, Throwable cause) {
        super(message, cause);
    }

    public ClientValidationException(Throwable cause) {
        super(cause);
    }
}
