package com.manager.shopmanager.validation;

import java.lang.annotation.*;

import javax.validation.Constraint;
import javax.validation.Payload;

@Documented
@Constraint(validatedBy = { DetailListValidator.class })
@Target({ ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidDetails {
    String message() default "The value have defined a language several times";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}