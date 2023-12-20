package com.example.backend.account;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AccountMapper {

    void insertAccount(AccountDto accountDto);
    Integer selectUsernameCount(String username);
    Integer selectEmailCount(String email);
    Integer selectNicknameCount(String nickname);
    AccountDto selectUsername(String username);
    AccountDto selectUser(String username);
}
