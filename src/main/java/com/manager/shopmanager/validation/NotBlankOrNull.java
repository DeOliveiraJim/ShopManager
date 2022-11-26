package com.manager.shopmanager.validation;

import java.lang.annotation.*;

import javax.validation.Constraint;
import javax.validation.Payload;

@Documented
@Constraint(validatedBy = NotBlankOrNullValidator.class)
@Target({ ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface NotBlankOrNull {
    String message() default "Invalid string blank or null";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}