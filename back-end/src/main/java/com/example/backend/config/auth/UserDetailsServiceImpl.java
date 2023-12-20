package com.example.backend.config.auth;

import com.example.backend.account.AccountMapper;
import com.example.backend.account.AccountService;
import com.example.backend.common.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final AccountMapper accountMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var account = accountMapper.selectUsername(username);
        log.info("어카운트: {}", account);
        if (account == null) {
            throw new NotFoundException();
        }

        return new UserAccount(account);
    }
}
