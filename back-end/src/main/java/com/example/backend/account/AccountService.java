package com.example.backend.account;

import java.util.Map;

public interface AccountService {


    void registerAccounts(AccountDto accountDto);

    String login(AccountDto accountDto);
}
