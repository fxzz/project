package com.example.backend.account.validator;

import com.example.backend.account.AccountMapper;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
public class UniqueUsernameValidator implements ConstraintValidator<UniqueUsername, String>  {

    private final AccountMapper accountMapper;

    @Override
    public void initialize(UniqueUsername constraintAnnotation) {

    }

    @Override
    public boolean isValid(String username, ConstraintValidatorContext constraintValidatorContext) {
        var count = accountMapper.selectUsernameCount(username);
        return count == 0;
    }
}
