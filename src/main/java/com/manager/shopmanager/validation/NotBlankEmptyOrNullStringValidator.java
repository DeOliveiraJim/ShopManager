package com.manager.shopmanager.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class NotBlankEmptyOrNullStringValidator implements ConstraintValidator<NotBlankOrEmptyOrNull, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value == null || value.trim().length() > 0;
    }

}
