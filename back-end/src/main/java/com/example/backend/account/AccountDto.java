package com.example.backend.account;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDateTime;

@Getter
public class AccountDto {

    private Long accountId;
    private String username;
    private String password;
    private String email;
    private String nickname;
    private Role role;
    private LocalDateTime createdAt;



    private AccountDto(String username, String password, String email, String nickname, Role role) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.nickname = nickname;
        this.role = role;
    }

    public enum Role {
        USER, ADMIN;
    }


    public static AccountDto of(RegisterAccountRequest request) {
        return new AccountDto(request.getUsername(), request.getPassword(), request.getEmail(), request.getUsername(), Role.USER);
    }

    @Getter
    @Setter
    @ToString
    public static class RegisterAccountRequest {

        @NotBlank(message = "아이디는 필수입니다 3~20자")
        @Pattern(regexp = "^[ㄱ-ㅎ가-힣a-z0-9_-]{3,20}$", message = "아이디는 필수입니다 3~20자")
        private String username;

        @NotBlank(message = "패스워드는 필수입니다 3~50자")
        @Length(min = 3, max = 50, message = "패스워드는 필수입니다 3~50자")
        private String password;

        @Email(message = "이메일은 필수입니다.")
        @NotBlank(message = "이메일은 필수입니다.")
        private String email;
    }
}
