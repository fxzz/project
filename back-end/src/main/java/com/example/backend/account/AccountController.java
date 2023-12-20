package com.example.backend.account;




import com.example.backend.common.response.CommonResponse;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;


    @PostMapping("/users")
    public CommonResponse registerAccounts(@Valid AccountDto.RegisterAccountRequest request) {
        var accountDto = AccountDto.of(request);
        accountService.registerAccounts(accountDto);
        return CommonResponse.success("OK");
    }

    @PostMapping("/login")
    public CommonResponse login(@Valid AccountDto.LoginAccountRequest request, HttpServletResponse response) {
        var accountDto = AccountDto.of(request);
        var tokens = accountService.login(accountDto);

        var accessToken = tokens.get("accessToken");
        var refreshToken = tokens.get("refreshToken");

        //TODO 쿠키 저장 안됨 나중에 해결
//        Cookie cookie = new Cookie("refreshToken", refreshToken);
//        cookie.setMaxAge(7 * 24 * 60 * 60);
//        cookie.setPath("/");
//        cookie.setHttpOnly(true);
//        response.addCookie(cookie);

        return CommonResponse.success(accessToken);
    }

}
