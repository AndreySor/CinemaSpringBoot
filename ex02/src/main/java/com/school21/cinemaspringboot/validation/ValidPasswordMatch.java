package com.school21.cinemaspringboot.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ValidPasswordMatch implements ConstraintValidator<ValidPassword, String> {

    @Override
    public void initialize(ValidPassword constraintAnnotation) {

    }

    @Override
    public boolean isValid(String pass, ConstraintValidatorContext constraintValidatorContext) {
        return pass.matches("(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{8,}");
    }
}
