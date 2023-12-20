package com.example.backend.account;


import com.example.backend.common.exception.NotFoundException;
import com.example.backend.common.util.JwtTokenUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountMapper accountService;
    private final PasswordEncoder passwordEncoder;

    @Value("${jwt.token.secret}")
    private String key;

    private Long expireTimeMs = 1000 * 60 * 60l;

    private Long refreshTokenExpireTimeMs = 7 * 24 * 60 * 60 * 1000L;

    @Transactional
    @Override
    public void registerAccounts(AccountDto accountDto) {
        accountDto.setPassword(passwordEncoder.encode(accountDto.getPassword()));
        accountService.insertAccount(accountDto);
    }

    @Override
    public  Map<String, String> login(AccountDto accountDto) {
        var account = accountService.selectUsername(accountDto.getUsername());

        if (account == null || account.getUsername() == null) {
            throw new NotFoundException("아이디 또는 패스워드가 일치하지 않습니다.");
        }

        if (!passwordEncoder.matches(accountDto.getPassword(), account.getPassword())) {
            throw new NotFoundException("아이디 또는 패스워드가 일치하지 않습니다.");
        }

        String accessToken = JwtTokenUtil.createToken(account.getUsername(), key, expireTimeMs);
        String refreshToken = JwtTokenUtil.createRefreshToken(account.getUsername(), key, refreshTokenExpireTimeMs);

        Map<String, String> tokens = new HashMap<>();
        tokens.put("accessToken", accessToken);
        tokens.put("refreshToken", refreshToken);

        return tokens;

    }
}
