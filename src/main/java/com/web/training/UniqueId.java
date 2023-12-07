package com.web.training;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UniqueIdValidator.class)
public @interface UniqueId {

	String message() default "Duplicated ID";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

}
