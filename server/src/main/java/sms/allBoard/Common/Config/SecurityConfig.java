package sms.allBoard.Common.Config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfigurationSource;
import sms.allBoard.Auth.AuthValidator;
import sms.allBoard.Common.Security.Details.MemberDetailsService;
import sms.allBoard.Common.Security.Filter.JwtFilter;
import sms.allBoard.Common.Security.Filter.LoginFilter;
import sms.allBoard.Common.Util.JwtUtil;
import sms.allBoard.Common.Util.RedisUtil;

@EnableWebSecurity
@Configuration
@RequiredArgsConstructor
public class SecurityConfig {
    private final AuthenticationConfiguration authenticationConfiguration;
    private final JwtUtil jwtUtil;
    private final AuthValidator authValidator;
    private final RedisUtil redisUtil;
    private final MemberDetailsService memberDetailsService;
    private final CorsConfigurationSource corsConfigurationSource;

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .logout(AbstractHttpConfigurer::disable)
                .formLogin(AbstractHttpConfigurer::disable)
                .csrf(AbstractHttpConfigurer::disable)
                .cors(cors -> cors.configurationSource(corsConfigurationSource))
                .authorizeHttpRequests(auth -> auth
                        .anyRequest().permitAll()
                )
                .addFilterAt(new LoginFilter(authenticationConfiguration.getAuthenticationManager(), this.jwtUtil, authValidator, redisUtil), UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(new JwtFilter(this.jwtUtil, this.memberDetailsService), LoginFilter.class)
                .build();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
