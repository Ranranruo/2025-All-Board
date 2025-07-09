package com.allboard.auth.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfigurationSource;

@EnableWebSecurity
@Configuration
@RequiredArgsConstructor
public class SecurityConfig {
    private final CorsConfigurationSource corsConfigurationSource;

    // security 설정
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                // 로그아웃 기능 비활성화
                .logout(AbstractHttpConfigurer::disable)
                // 폼 로그인 기능 비활성화
                .formLogin(AbstractHttpConfigurer::disable)
                // csrf 비활성화
                .csrf(AbstractHttpConfigurer::disable)
                // cors 설정 객체로 설정
                .cors(cors -> cors.configurationSource(corsConfigurationSource))
                // 요청별 권한 설정
                .authorizeHttpRequests(auth -> auth
                        .anyRequest().permitAll()
                )
                // 로그인 필터 UsernamePasswordAuthenticationFilter랑 갈아끼운다.
                .build();
    }

    // 비밀번호 암호와 알고리즘 BCrypt 사용
    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}