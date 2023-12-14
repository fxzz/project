package com.example.backend.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HttpBasicConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
           http
               .csrf(AbstractHttpConfigurer::disable)

               .sessionManagement((sessionManagement) ->
                           sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

               .authorizeHttpRequests((authorizeRequests)->
                           authorizeRequests
                                   .requestMatchers("/api/**").permitAll()
                                   .requestMatchers("/error/**").permitAll()
                                   .requestMatchers("/favicon.ico").permitAll()
                                   .anyRequest().authenticated())
           .httpBasic(HttpBasicConfigurer::disable)
           .cors(Customizer.withDefaults());

        return http.build();
    }
}
