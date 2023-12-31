package com.example.backend.account;

import com.example.backend.config.jwt.JwtTokenDto;

import java.util.Map;

public interface AccountService {


    void registerAccounts(AccountDto accountDto);

    JwtTokenDto login(AccountDto accountDto);

    void changeNickname(Long accountId, String nickname);

    String getNickname(Long accountId);
}
