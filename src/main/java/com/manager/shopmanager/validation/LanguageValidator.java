package com.manager.shopmanager.validation;

import java.util.Locale;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class LanguageValidator implements ConstraintValidator<LanguageString, String> {

    static final String[] languages = Locale.getISOLanguages();

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        for (String l : languages) {
            if (l.equals(value))
                return true;
        }
        return false;
    }

}
