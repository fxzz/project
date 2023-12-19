package com.example.backend.account;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@ActiveProfiles("test")
class AccountControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private AccountMapper accountMapper;


    private static final Integer EXPECTED_COUNT_ZERO = 0;


    @DisplayName("회원 가입 성공 - 입력값 정상")
    @Test
    void signUp_success() throws Exception {
        var emailCount = performSignUpAndVerify("yyyyy", "12345", "email@gmail.com");
        assertNotNull(emailCount);
    }

    @DisplayName("회원 가입 실패 - username 유효성 규칙에 맞지 않음")
    @Test
    void signUp_fail_usernameValidation() throws Exception {
        var emailCount = performSignUpAndVerify("y", "12345", "email@gmail.com");
        assertEquals(EXPECTED_COUNT_ZERO, emailCount);
    }

    @DisplayName("회원 가입 실패 - password 유효성 규칙에 맞지 않음")
    @Test
    void signUp_fail_passwordValidation() throws Exception {
        var emailCount = performSignUpAndVerify("yyyyy", "1", "email@gmail.com");
        assertEquals(EXPECTED_COUNT_ZERO, emailCount);
    }

    @DisplayName("회원 가입 실패 - email 유효성 규칙에 맞지 않음")
    @Test
    void signUp_fail_emailValidation() throws Exception {
        var emailCount = performSignUpAndVerify("y", "12345", "email");
        assertEquals(EXPECTED_COUNT_ZERO, emailCount);
    }

    private Integer performSignUpAndVerify(String username, String password, String email) throws Exception {
        mvc.perform(post("/api/users")
                .param("username", username)
                .param("password", password)
                .param("email", email)
        );
        return accountMapper.selectEmailCount(email);
    }
}