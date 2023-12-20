package com.example.backend.account;

import java.util.Map;

public interface AccountService {


    void registerAccounts(AccountDto accountDto);

    Map<String, String> login(AccountDto accountDto);
}
