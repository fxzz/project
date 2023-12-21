package com.example.backend.config;


import com.example.backend.common.util.CustomResponseUtil;


import com.example.backend.config.jwt.JwtAuthorizationFilter;
import lombok.RequiredArgsConstructor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HttpBasicConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Slf4j
@EnableWebSecurity
@Configuration
@RequiredArgsConstructor
public class SecurityConfig {


    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    public class CustomSecurityFilterManager extends AbstractHttpConfigurer<CustomSecurityFilterManager, HttpSecurity> {
        @Override
        public void configure(HttpSecurity builder) throws Exception {
            AuthenticationManager authenticationManager = builder.getSharedObject(AuthenticationManager.class);
            builder.addFilter(new JwtAuthorizationFilter(authenticationManager));
            // 시큐리티 관련 필터
            super.configure(builder);
        }
    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)

                .sessionManagement((sessionManagement) ->
                        sessionManagement
                                .sessionCreationPolicy(SessionCreationPolicy.STATELESS))


                .authorizeHttpRequests((authorizeRequests) ->
                        authorizeRequests
                                .requestMatchers("/api/", "/api/login", "/api/users", "/api/photos/**", "/api/file/**").permitAll() //TODO 나중에 추가
                                .requestMatchers("/error/**").permitAll()
                                .requestMatchers("/favicon.ico").permitAll()
                                .requestMatchers(HttpMethod.POST, "/api/photos").authenticated()
                                .anyRequest().authenticated())

                .httpBasic(HttpBasicConfigurer::disable)

                .formLogin(AbstractHttpConfigurer::disable)

                .cors(cors -> cors.configurationSource(configurationSource()))

                .headers(authorize -> authorize
                        .frameOptions(frameOptions -> frameOptions.disable()))

                // requestMatchers 에 걸리면 CustomResponseUtil.unAuthentication(response) 응답을 보냄
                .exceptionHandling(exceptionHandling -> exceptionHandling
                        .authenticationEntryPoint((request, response, authException) -> {
                            CustomResponseUtil.unAuthentication(response);
                        }))
                .apply(new CustomSecurityFilterManager())
        ;

        return http.build();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        log.info("BCryptPasswordEncoder 등록");
        return new BCryptPasswordEncoder();
    }

    @Bean
    public CorsConfigurationSource configurationSource() {
        log.info("configurationSource 등록");
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.addAllowedHeader("*");
        corsConfiguration.addAllowedMethod("*"); //GET, POST, PUT, DELETE, Javascript 허용
        corsConfiguration.addAllowedOriginPattern("*"); //TODO 나중에 리엑트 IP만 허용으로 교체
        corsConfiguration.setAllowCredentials(true); // 클라이언트에서 쿠키 요청 허용
        corsConfiguration.addExposedHeader("Authorization");
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfiguration);
        return source;
    }
}
