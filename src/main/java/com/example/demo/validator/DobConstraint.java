package com.example.demo.validator;

import com.nimbusds.jose.Payload;

import javax.validation.Constraint;
import java.lang.annotation.*;

import static java.lang.annotation.ElementType.FIELD;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {DobValidator.class})
public @interface DobConstraint {
    String message() default "Invalid date of birth";

    int min();

    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
