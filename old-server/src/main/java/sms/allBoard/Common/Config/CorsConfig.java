package sms.allBoard.Common.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
public class CorsConfig {

    // cors 관련 설정
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();

        // Origin 허용 (예: http://localhost:3000 등)
        corsConfiguration.addAllowedOrigin("http://127.0.0.1:5500");

        // 요청 헤더 허용 (예: Authorization, Content-Type 등)
        corsConfiguration.addAllowedHeader("*");

        // HTTP 메서드 허용 (예: GET, POST, PUT, DELETE 등)
        corsConfiguration.addAllowedMethod("*");

        // 인증 정보(쿠키 등) 포함 허용
        corsConfiguration.setAllowCredentials(true);

        // 경로에 대해 위 CORS 설정 적용
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfiguration);

        return source;
    }

}
