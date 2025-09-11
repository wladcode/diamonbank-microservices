package com.diamondcode.common.application.service.validator.password;

import com.diamondcode.common.application.port.in.model.UserDto;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordMatchesValidator implements ConstraintValidator<PasswordMatches, Object> {

    @Override
    public boolean isValid(Object object, ConstraintValidatorContext context) {
        if (object != null ) {
            UserDto user = (UserDto) object;
            if(user.getPassword() != null && user.getMatchingPassword() != null){
                return user.getPassword().equals(user.getMatchingPassword());
            }
        }
        return false;
    }
}
