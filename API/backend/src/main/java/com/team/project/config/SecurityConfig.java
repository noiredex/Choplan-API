package com.team.project.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Value; //11
import org.springframework.context.annotation.Bean; //1
import org.springframework.context.annotation.Configuration; //2
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity; //9
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain; //7
import org.springframework.web.cors.CorsConfiguration; //12
import org.springframework.web.cors.CorsConfigurationSource; //14
import org.springframework.web.cors.UrlBasedCorsConfigurationSource; //13

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Value("${app.cors-allowed-origin}")
    private String allowedOrign;

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                // CSRF 보호 비활성화 (REST API이므로)
                .csrf(csrf -> csrf.disable())

                // 세션을 사용하지 않도록 설정 (STATELESS)
                .sessionManagement(s -> s.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

                // CORS 설정
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))

                // 모든 요청에 대해 접근 허용 (필요에 따라 경로별 권한 설정 추가)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                        .requestMatchers("/api/v1/payments/**").permitAll()
                        .anyRequest().permitAll())
                .headers(h -> h.frameOptions(f -> f.disable()));

        return http.build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration conf = new CorsConfiguration();
        // 프론트엔드 개발 서버 주소 허용
        conf.setAllowedOrigins(List.of(allowedOrign));
        conf.setAllowedMethods(List.of("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS"));
        conf.setAllowedHeaders(List.of("*"));
        conf.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", conf);
        return source;
    }
}
