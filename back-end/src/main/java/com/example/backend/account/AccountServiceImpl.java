package com.example.backend.account;


import com.example.backend.common.exception.NotFoundException;

import com.example.backend.config.auth.AccountDetails;
import com.example.backend.config.jwt.JwtProvider;
import com.example.backend.config.jwt.JwtTokenDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;


@Slf4j
@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountMapper accountMapper;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;



    @Transactional
    @Override
    public void registerAccounts(AccountDto accountDto) {
        accountDto.setPassword(passwordEncoder.encode(accountDto.getPassword()));
        accountMapper.insertAccount(accountDto);
    }

    @Override
    public  JwtTokenDto login(AccountDto accountDto) {
        try {
            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken
                    = new UsernamePasswordAuthenticationToken(accountDto.getUsername(), accountDto.getPassword());
            Authentication authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);
            AccountDetails accountDetails = (AccountDetails) authentication.getPrincipal();
            String JwtToken = JwtProvider.create(accountDetails.getAccount());
            Long accountId = accountDetails.getAccountId();

            return new JwtTokenDto(JwtToken, accountId);
        }catch (Exception e) {
            throw new NotFoundException();
        }

    }

    @Override
    public void changeNickname(Long accountId, String nickname) {
        HashMap map = new HashMap();
        map.put("accountId", accountId);
        map.put("nickname", nickname);

        accountMapper.updateNickname(map);
    }

    @Override
    public String getNickname(Long accountId) {
        return accountMapper.getNickname(accountId);
    }
}
