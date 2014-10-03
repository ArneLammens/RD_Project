package com.realdolmen.domain.validator;

import org.hibernate.validator.constraints.*;
import org.hibernate.validator.constraints.Email;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Created by ALMAU78 on 2/10/2014.
 */
public class EmailValidator implements ConstraintValidator<Email,String>
{


    public void initialize(org.hibernate.validator.constraints.Email contraintAnnotation)
    {


    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value.matches("^[_A-Za-z0-9-]+(\\\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\\\.[A-Za-z0-9-]+)*(\\\\.[A-Za-z]{2,})$");
    }
}
