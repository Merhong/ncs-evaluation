package lab.nomad.springbootncsevaluation._core.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableMethodSecurity(securedEnabled = true)
// @PreAuthorize 활성화
// [예제]
// 1. @PreAuthorize("hasRole('USER')")
// 2. @Secured("IS_AUTHENTICATED_ANONYMOUSLY")
// 3. @Secured("ROLE_TELLER")
public class SecurityConfig {

    final JWTProvider provider;

    public SecurityConfig(JWTProvider provider) {
        this.provider = provider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .exceptionHandling((exceptions) -> exceptions
                        .authenticationEntryPoint(new JwtAuthenticationEntryPoint())
                        .accessDeniedHandler(new JwtAccessDeniedHandler())
                )
                .csrf(AbstractHttpConfigurer::disable)
                .headers(AbstractHttpConfigurer::disable)
                .httpBasic(withDefaults())
                .sessionManagement((session) ->
                                session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )

                .cors(httpSecurityCorsConfigurer -> httpSecurityCorsConfigurer.configurationSource(corsConfigurationSource())) // Bean 기본 이름이 corsConfigurationSource
                .authorizeHttpRequests((authorize) ->
                        authorize
                                // 회원가입 url
                                .requestMatchers("/join", "/api/v1/auth/join").permitAll()
                                .requestMatchers("/api/v1/courses/**", "/api/v1/ability-units/", "/api/v1/students/**").authenticated()
                                .requestMatchers("/admin/**", "/api/admin/**").hasAnyAuthority("ADMIN")
                                .anyRequest().permitAll()
                        //

                )

                .apply(new SecurityJwtConfig(provider));
        return http.build();
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        var configuration = new CorsConfiguration();
        configuration.addAllowedHeader("*");
        configuration.addAllowedMethod("*");
        configuration.setAllowedOrigins(
                List.of(
                        "http://localhost",
                        "http://localhost:8080"
                )
        );

        configuration.addExposedHeader("Authorization");

        configuration.setAllowCredentials(true); // 클라이언트에서 쿠키 요청 허용
        configuration.addExposedHeader(JWTProvider.HEADER);

        var source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }


}