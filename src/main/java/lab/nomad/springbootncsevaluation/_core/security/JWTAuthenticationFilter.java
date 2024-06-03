package lab.nomad.springbootncsevaluation._core.security;

import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lab.nomad.springbootncsevaluation._core.exception.Exception403;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

// JWT를 통한 인증 처리 필터
@Slf4j
public class JWTAuthenticationFilter extends OncePerRequestFilter {
    final JWTProvider provider;

    public JWTAuthenticationFilter(JWTProvider provide) {
        this.provider = provide;
    }

    @Override
    public void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
            FilterChain chain) throws IOException, ServletException {
        var jwt = resolveToken(request);
        if (StringUtils.hasText(jwt)) {

            if (!provider.validateToken(jwt)) {
                throw new Exception403("접근이 거부되었습니다.");
            }

            try {
                var authentication = provider.getAuthentication(jwt);
                SecurityContextHolder.getContext()
                        .setAuthentication(authentication);
            } catch (TokenExpiredException | SignatureVerificationException exception) {
                exception.printStackTrace();
            }
        }
        chain.doFilter(request, response);
    }

    String resolveToken(HttpServletRequest request) {
        // 헤더에서 토큰 가져오기
        String bearerToken = request.getHeader(JWTProvider.HEADER);

        if (StringUtils.hasText(bearerToken)) {
            return bearerToken;
        }

        // 쿠키에서 accessToken 가져오기
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName()
                        .equals("accessToken")) {
                    return URLDecoder.decode(cookie.getValue(), StandardCharsets.UTF_8);
                }
            }
        }
        return null;
    }
}
