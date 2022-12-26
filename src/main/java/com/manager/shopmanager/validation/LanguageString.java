package com.manager.shopmanager.validation;

import java.lang.annotation.*;

import javax.validation.Constraint;
import javax.validation.Payload;

@Documented
@Constraint(validatedBy = { LanguageValidator.class })
@Target({ ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface LanguageString {
    String message() default "The value is not a language";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}