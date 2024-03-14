package lab.nomad.springbootncsevaluation._core.security;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lab.nomad.springbootncsevaluation._core.exception.Exception401;
import lab.nomad.springbootncsevaluation._core.exception.Exception403;
import lab.nomad.springbootncsevaluation._core.utils.FilterResponseUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.io.IOException;
import java.io.PrintWriter;

// 시큐리티 설정 클래스
@Slf4j
@RequiredArgsConstructor
@Configuration
public class SecurityConfig {

    // 비밀번호 해쉬화
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // AuthenticationManager IOC에 띄우기
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    // 커스텀 필터 등록하는 곳
    public static class CustomSecurityFilterManager extends AbstractHttpConfigurer<CustomSecurityFilterManager, HttpSecurity> {
        @Override
        public void configure(HttpSecurity builder) throws Exception {
            AuthenticationManager authenticationManager = builder.getSharedObject(AuthenticationManager.class);

            // JWT 필터 등록
            builder.addFilter(new JWTAuthenticationFilter(authenticationManager));

            super.configure(builder);
        }
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // CSRF 해제
        // TODO postman 테스트용!!
        http.csrf(httpSecurityCsrfConfigurer -> httpSecurityCsrfConfigurer.disable());

        // iframe 거부
        http.headers(httpSecurityHeadersConfigurer ->
                httpSecurityHeadersConfigurer.frameOptions(frameOptionsConfig -> frameOptionsConfig.sameOrigin()));

        // cors 재설정
        // TODO 실제 서비스시 재설정 필요!!
        http.cors(httpSecurityCorsConfigurer -> httpSecurityCorsConfigurer.configurationSource(configurationSource()));

        // jSessionId 사용 거부
        http.sessionManagement(httpSecuritySessionManagementConfigurer ->
                httpSecuritySessionManagementConfigurer.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        // 기본 form 로그인 해제 (UsernamePasswordAuthenticationFilter 비활성화)
        http.formLogin(httpSecurityFormLoginConfigurer ->
                httpSecurityFormLoginConfigurer.loginPage("/login")
                        .loginProcessingUrl("/api/v1/login")
                        .successHandler(new CustomLoginSuccessHandler()));

        // 기본 로그인 인증창 비활성화
        http.httpBasic(httpSecurityHttpBasicConfigurer -> httpSecurityHttpBasicConfigurer.disable());

        // 커스텀 필터 적용
        http.apply(new CustomSecurityFilterManager());

        // 인증 실패 처리
        http.exceptionHandling(httpSecurityExceptionHandlingConfigurer ->
                httpSecurityExceptionHandlingConfigurer.authenticationEntryPoint((request, response, authException) -> {
                    log.warn("인증되지 않은 사용자가 자원에 접근하려 합니다 : "+authException.getMessage());
                    FilterResponseUtils.unAuthorized(response, new Exception401("인증되지 않았습니다"));}
                ).accessDeniedHandler((request, response, accessDeniedException) -> {
                    log.warn("권한이 없는 사용자가 자원에 접근하려 합니다 : "+accessDeniedException.getMessage());
                    FilterResponseUtils.forbidden(response, new Exception403("권한이 없습니다"));
                }));

        // 인증, 권한 필터 설정
        http.authorizeRequests(
                authorize -> authorize
                        .requestMatchers("/test").permitAll()
                        .requestMatchers("/login", "/join").permitAll()
                        .requestMatchers("/courses/**", "/main", "/orders/**").authenticated()
                        .requestMatchers("/admin/**", "/api/admin/**").access("hasRole('ADMIN')")
                        .anyRequest().permitAll()
        );

        return http.build();
    }

    public CorsConfigurationSource configurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.addAllowedHeader("*");
        configuration.addAllowedMethod("*"); // GET, POST, PUT, DELETE (Javascript 요청 허용)
        configuration.addAllowedOriginPattern("*"); // 모든 IP 주소 허용 (프론트 앤드 IP만 허용 react)
        configuration.setAllowCredentials(true); // 클라이언트에서 쿠키 요청 허용
        configuration.addExposedHeader("Authorization");
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}

class CustomLoginSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(
            HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        response.setStatus(HttpServletResponse.SC_OK); // set status code to 200
        PrintWriter writer = response.getWriter();
        writer.write("Login Successful");
        writer.flush();
    }
}