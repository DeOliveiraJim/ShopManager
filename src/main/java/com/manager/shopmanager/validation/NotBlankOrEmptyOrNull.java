package com.manager.shopmanager.validation;

import java.lang.annotation.*;

import javax.validation.Constraint;
import javax.validation.Payload;

@Documented
@Constraint(validatedBy = { NotBlankEmptyOrNullStringValidator.class, NotBlankEmptyOrNullCollectionValidator.class })
@Target({ ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface NotBlankOrEmptyOrNull {
    String message() default "The value is empty, it should be null or not empty";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}