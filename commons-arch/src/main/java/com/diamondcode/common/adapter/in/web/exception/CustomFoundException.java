package com.diamondcode.common.adapter.in.web.exception;

public class CustomFoundException extends RuntimeException{

    public CustomFoundException(){
        super("There is a register found");
    }
    public CustomFoundException(String message) {
        super(message);
    }
}
