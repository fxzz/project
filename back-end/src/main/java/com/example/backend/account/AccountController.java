package com.example.backend.account;




import com.example.backend.common.response.CommonResponse;
import com.example.backend.config.auth.AccountDetails;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

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
    public CommonResponse login(@Valid AccountDto.LoginAccountRequest request) {
        var accountDto = AccountDto.of(request);
        var jwt = accountService.login(accountDto);

        return CommonResponse.success(jwt);
    }

    @GetMapping("/users/{id}")
    public CommonResponse getAccountMyPage(@PathVariable Long id, @AuthenticationPrincipal AccountDetails accountDetails) {
        if (id.longValue() != accountDetails.getAccount().getAccountId()) {
            throw new RuntimeException("권한이 없습니다"); // 403 관련 에러 만들어야함
        }
        return CommonResponse.success("OK");
    }

}
