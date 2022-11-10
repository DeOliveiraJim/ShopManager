package com.manager.shopmanager.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;


public class PositiveOrZeroValidator implements ConstraintValidator<PositiveOrZero, Integer > {
    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext context) {
        return value >= 0;
    }

}


