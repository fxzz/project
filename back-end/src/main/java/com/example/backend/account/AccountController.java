package com.example.backend.account;


import com.example.backend.common.response.CommonResponse;
import com.example.backend.photo.PhotoDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
