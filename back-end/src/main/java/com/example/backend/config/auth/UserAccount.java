package com.example.backend.config.auth;

import com.example.backend.account.AccountDto;
import lombok.Getter;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;



@Getter
public class UserAccount extends User {

    private AccountDto account;

    public UserAccount(AccountDto account) {
        super(account.getUsername(), account.getPassword(), AuthorityUtils.createAuthorityList("ROLE_"+account.getRole().toString()));
        this.account = account;
    }
}
