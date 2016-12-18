package com.webinson.semantv.utils;

import org.primefaces.validate.bean.ClientConstraint;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.*;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

/**
 * Created by Slavo on 11/12/2016.
 */
@Target({METHOD,FIELD,ANNOTATION_TYPE})
@Retention(RUNTIME)
@Constraint(validatedBy=EmailConstraintValidator.class)
@ClientConstraint(resolvedBy=EmailClientValidationConstraint.class)
@Documented
public @interface Email {

    String message() default "{org.primefaces.examples.primefaces}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}