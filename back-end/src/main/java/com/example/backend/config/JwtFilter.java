package com.example.backend.config;

import com.example.backend.account.AccountDto;
import com.example.backend.common.util.JwtTokenUtil;
import io.jsonwebtoken.Jwt;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
@Slf4j
public class JwtFilter extends OncePerRequestFilter {
// 토큰이 있는지 항상 체크하는 필터
// 모든 로그인 요청의 문이라 생각

    private final UserDetailsService userDetailsService;
    private final String secretKey;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        final String authorization = request.getHeader(HttpHeaders.AUTHORIZATION);


        // Token 이 없을때
        if (authorization == null || !authorization.startsWith("Bearer ")) {


            filterChain.doFilter(request, response);
            return;
        }

        // Token 꺼내기
        String token = authorization.split(" ")[1];

        // Token Expired 되었는지 여부
        if (JwtTokenUtil.isExpired(token, secretKey)) {
            log.info("Token 이 만료되었습니다");

            // 여기서 리플래시 토큰 시간이나 널 아닌지 체크하고 새로운 엑세스 토큰 발급 로직 추가


            // 새로운 엑세스 토큰 발급 로직 호출 (refreshToken 이용)

            // 리플래시 토큰이 유효한 경우 쿠키에 새로운 리플래시 토큰 설정



            filterChain.doFilter(request, response);
            return;
        }


        // Username 을 Token 에서 꺼내기
        String username = JwtTokenUtil.getUserName(token, secretKey);
        log.info("username: {}", username);

        // 권환 부여
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(username, null, List.of(new SimpleGrantedAuthority(AccountDto.Role.USER.toString())));

        // Detail 에 추가
        authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        filterChain.doFilter(request, response);
    }




}
