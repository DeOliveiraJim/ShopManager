package com.manager.shopmanager.validation;

import java.lang.annotation.*;

import javax.validation.Constraint;
import javax.validation.Payload;

@Documented
@Constraint(validatedBy = { OpeningTimesValidator.class })
@Target({ ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidOpeningTimes {
    String message() default "The value is not valid";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}