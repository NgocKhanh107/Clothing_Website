package com.duan.validator;

import com.duan.dto.ForgotPassword;
import com.duan.entities.Users;
import com.duan.repositories.userRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
@Component
public class ForgotPassValidator implements Validator {
    @Autowired
    private userRepo dao;
    @Override
    public boolean supports(Class<?> clazz) {
        return clazz == ForgotPassword.class;
    }

    @Override
    public void validate(Object target, Errors errors) {
        ForgotPassword entity = (ForgotPassword) target;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "NotBlank.forgotPassword.email");

        if(!errors.hasFieldErrors()) {
            Users user = dao.findByEmail(entity.getEmail());

            if(user == null) {
                errors.rejectValue("email", "NotFind.forgotPassword.email");
            }
        }

    }
}
