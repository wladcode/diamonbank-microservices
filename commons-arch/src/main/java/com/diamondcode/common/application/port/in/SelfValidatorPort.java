package com.diamondcode.common.application.port.in;

import javax.validation.ConstraintViolationException;

public interface SelfValidatorPort {

    void validateData(Object tarjet) throws ConstraintViolationException;
}
