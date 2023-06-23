package com.vti.Validate;

import com.vti.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class EmailNotExistsValidate  implements ConstraintValidator<EmailNotExists, String> {

    @Autowired
    private IAccountService accountService;


    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return !accountService.isAccountExistsByEmail(s);
    }
}
