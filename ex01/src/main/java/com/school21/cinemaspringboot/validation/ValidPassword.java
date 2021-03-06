package com.school21.cinemaspringboot.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ValidPasswordMatch.class)
@Documented
public @interface ValidPassword {

    String message() default "{validation.pass}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
