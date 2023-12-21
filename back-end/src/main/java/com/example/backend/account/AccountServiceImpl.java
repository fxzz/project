package com.example.backend.account;


import com.example.backend.common.exception.NotFoundException;

import com.example.backend.config.auth.AccountDetails;
import com.example.backend.config.jwt.JwtProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



@Slf4j
@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountMapper accountService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;



    @Transactional
    @Override
    public void registerAccounts(AccountDto accountDto) {
        accountDto.setPassword(passwordEncoder.encode(accountDto.getPassword()));
        accountService.insertAccount(accountDto);
    }

    @Override
    public  String login(AccountDto accountDto) {
        try {
            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken
                    = new UsernamePasswordAuthenticationToken(accountDto.getUsername(), accountDto.getPassword());
            Authentication authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);
            AccountDetails accountDetails = (AccountDetails) authentication.getPrincipal();
            return JwtProvider.create(accountDetails.getAccount());
        }catch (Exception e) {
            throw new NotFoundException();
        }

    }
}
