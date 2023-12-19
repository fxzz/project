package com.example.backend.config;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK) //가짜 환경에서 테스트
class SecurityConfigTest {

    // 가짜 환경에 등록된 mvc를 di
    @Autowired
    private MockMvc mvc;

    @DisplayName("인가 실패")
    @Test
    public void authorization_failure_test() throws Exception {

        mvc.perform(get("/ap/photo"))
                .andExpect(status().isUnauthorized());
    }

    @DisplayName("인가 성공")
    @Test
    public void authorization_success_test() throws Exception {

        mvc.perform(get("/api/photos")
                        .param("photoId", "1")
                        .param("size", "9"))
                .andExpect(status().isOk());
    }
}