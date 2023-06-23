package com.vti.Validate;

import com.vti.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class IdExistsValidate implements ConstraintValidator<IdExists, Integer> {
    @Autowired
    private IAccountService accountService;
    @Override
    public boolean isValid(Integer integer, ConstraintValidatorContext constraintValidatorContext) {
        return accountService.isAccountExistsById(integer);
    }
}
