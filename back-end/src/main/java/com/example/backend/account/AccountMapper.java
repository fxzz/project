package com.example.backend.account;

import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

@Mapper
public interface AccountMapper {

    void insertAccount(AccountDto accountDto);
    Integer selectUsernameCount(String username);
    Integer selectEmailCount(String email);
    Integer selectNicknameCount(String nickname);
    AccountDto selectUsername(String username);
    AccountDto selectAccount(String username);
    void updateNickname(Map map);
    String getNickname(Long accountId);
}
