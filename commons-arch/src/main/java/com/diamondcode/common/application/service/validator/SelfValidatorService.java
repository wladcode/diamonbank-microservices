package com.diamondcode.common.application.service.validator;

import com.diamondcode.common.application.port.in.SelfValidatorPort;
import com.diamondcode.common.application.service.UseCase;
import lombok.extern.slf4j.Slf4j;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@UseCase
public class SelfValidatorService implements SelfValidatorPort {

    private Validator validator;

    public SelfValidatorService(){
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator= factory.getValidator();
    }

    /**
     * Evaluates all Bean Validations on the attributes of this
     * instance.
     */
    @Override
    public void validateData(Object tarjet) throws ConstraintViolationException{
        Set<ConstraintViolation<Object>> violations = validator.validate(tarjet);

        if(!violations.isEmpty()){

            List<String> errors = violations.stream()
                    .map(item -> item.getPropertyPath()+ " - " + item.getMessage()).collect(Collectors.toList());

            log.error("Error when validating object: {}",errors );
            throw new ConstraintViolationException(violations);
        }
    }
}
