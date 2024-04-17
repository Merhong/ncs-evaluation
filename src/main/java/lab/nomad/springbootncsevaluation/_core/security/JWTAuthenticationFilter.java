package lab.nomad.springbootncsevaluation._core.security;

import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lab.nomad.springbootncsevaluation._core.exception.Exception401;
import lab.nomad.springbootncsevaluation._core.exception.Exception403;
import lab.nomad.springbootncsevaluation._core.exception.ExceptionMessage;
import lab.nomad.springbootncsevaluation.model.users.Users;
import lab.nomad.springbootncsevaluation.model.users._enums.UserRole;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

// JWT를 통한 인증 처리 필터
@Slf4j
public class JWTAuthenticationFilter extends OncePerRequestFilter {
    final JWTProvider provider;

    public JWTAuthenticationFilter(JWTProvider provide) {
        this.provider = provide;
    }

    @Override
    public void doFilterInternal (
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain chain
    ) throws IOException, ServletException {
        var jwt = resolveToken(request);
        if (StringUtils.hasText(jwt)) {

            if (!provider.validateToken(jwt)) {
                throw new Exception403("접근이 거부되었습니다.");
            }

            try {
                var authentication = provider.getAuthentication(jwt);
                SecurityContextHolder.getContext().setAuthentication(authentication);
            } catch (TokenExpiredException | SignatureVerificationException exception) {
                exception.printStackTrace();
            }
        }
        chain.doFilter(request, response);
    }

    String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader(JWTProvider.HEADER);
        if (StringUtils.hasText(bearerToken)) {
            return bearerToken;
        }
        return null;
    }

}
