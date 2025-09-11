package com.diamondcode.common.application.service.util;


public class ServiceException extends RuntimeException {



    public ServiceException(String message) {
        super(String.format(message));
    }
}
