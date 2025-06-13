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
import sms.allBoard.Auth.Details.MemberDetailsService;
import sms.allBoard.Auth.Filter.JWTFilter;
import sms.allBoard.Auth.Filter.LoginFilter;
import sms.allBoard.Auth.JWT.JWTProvider;
import sms.allBoard.Common.Util.Redis.RedisService;

@EnableWebSecurity
@Configuration
@RequiredArgsConstructor
public class SecurityConfig {
    private final AuthenticationConfiguration authenticationConfiguration;
    private final JWTProvider jwtProvider;
    private final AuthValidator authValidator;
    private final RedisService redisService;
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
                .addFilterAt(new LoginFilter(authenticationConfiguration.getAuthenticationManager(), this.jwtProvider, authValidator, redisService), UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(new JWTFilter(this.jwtProvider, this.memberDetailsService), LoginFilter.class)
                .build();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
