package com.example.backend.account;




import com.example.backend.common.response.CommonResponse;
import com.example.backend.config.auth.AccountDetails;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;



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
        var JwtTokenDto = accountService.login(accountDto);

        return CommonResponse.success(JwtTokenDto);
    }

    @GetMapping("/users/{accountId}")
    public CommonResponse getAccountMyPage(@PathVariable Long accountId, @AuthenticationPrincipal AccountDetails accountDetails) {
        if (accountId.longValue() != accountDetails.getAccount().getAccountId()) {
            throw new RuntimeException("권한이 없습니다"); // 403 관련 에러 만들어야함
        }
        var nickname = accountDetails.getNickname();
        return CommonResponse.success(nickname);
    }

}
