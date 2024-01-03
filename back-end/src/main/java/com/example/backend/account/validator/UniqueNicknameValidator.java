package com.example.backend.account.validator;

import com.example.backend.account.AccountMapper;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class UniqueNicknameValidator implements ConstraintValidator<UniqueNickname, String> {

    private final AccountMapper accountMapper;

    @Override
    public void initialize(UniqueNickname constraintAnnotation) {

    }

    @Override
    public boolean isValid(String nickname, ConstraintValidatorContext constraintValidatorContext) {
        var count = accountMapper.selectNicknameCount(nickname);
        return count == 0;
    }
}
