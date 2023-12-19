package com.example.backend.common.util;

import com.example.backend.common.response.CommonResponse;
import com.example.backend.common.response.ErrorCode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CustomResponseUtil {

    public static void unAuthentication(HttpServletResponse response) {

        response.setContentType("application/json; charset=utf-8");
        response.setStatus(401);
        CommonResponse<?> commonResponse = CommonResponse.fail(ErrorCode.COMMON_UNAUTHORIZED_ACCESS);

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            String responseBody = objectMapper.writeValueAsString(commonResponse);
            response.getWriter().println(responseBody);
        } catch (Exception e) {
            log.error("unAuthentication 파싱 에러");
        }
    }
}
