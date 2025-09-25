package choplan.db.application.properties.choplan.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import choplan.db.application.properties.choplan.security.JwtAuthenticationFilter;
import jakarta.servlet.http.HttpServletResponse;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    public SecurityConfig(JwtAuthenticationFilter jwtAuthenticationFilter) {
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable()) // REST API는 CSRF 비활성화
            .authorizeHttpRequests(auth -> auth
                // 회원가입 / 로그인은 모두 접근 허용
                .requestMatchers("/admin/signup", "/admin/login").permitAll()
                .requestMatchers("/customer/signup", "/customer/login").permitAll()
                .requestMatchers("/owner/signup", "/owner/login").permitAll()

                // 권한별 보호된 API
                .requestMatchers("/admin/**").hasRole("ADMIN")
                .requestMatchers("/customer/**").hasRole("CUSTOMER")
                .requestMatchers("/owner/**").hasRole("OWNER")

                // 그 외는 인증 필요
                .anyRequest().authenticated()
            )
            .exceptionHandling(ex -> ex
                .authenticationEntryPoint(authenticationEntryPoint()) // 401 처리
                .accessDeniedHandler(accessDeniedHandler())           // 403 처리
            )
            .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class) // JWT 필터 등록
            .formLogin(form -> form.disable()); // 폼 로그인 비활성화 (REST API 스타일)

        return http.build();
    }

    // 비밀번호 암호화
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // 401 인증 실패 핸들러
    @Bean
    public AuthenticationEntryPoint authenticationEntryPoint() {
        return (request, response, authException) -> {
            response.setContentType("application/json;charset=UTF-8");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("{\"error\":\"인증이 필요합니다.\"}");
        };
    }

    // 403 권한 없음 핸들러
    @Bean
    public AccessDeniedHandler accessDeniedHandler() {
        return (request, response, accessDeniedException) -> {
            response.setContentType("application/json;charset=UTF-8");
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            response.getWriter().write("{\"error\":\"접근 권한이 없습니다.\"}");
        };
    }
}
