package com.manager.shopmanager.validation;

import java.util.Collection;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@SuppressWarnings("rawtypes")
public class NotBlankEmptyOrNullCollectionValidator
        implements ConstraintValidator<NotBlankOrEmptyOrNull, Collection> {

    @Override
    public boolean isValid(Collection value, ConstraintValidatorContext context) {
        return value == null || value.size() > 0;
    }

}