package lab.nomad.springbootncsevaluation._core.security;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import java.io.IOException;

public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException authException) throws IOException {
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(
                "{\"type\" : \"about:blank\", \"title\" : \"Unauthorized\","
                        + "\"status\" : 401 ,"
                        + "\"detail\" : \"" + authException.getMessage() + "\","
                        + "\"instance\" : \"" + request.getServletPath() + "\","
                        + "}"
        );
    }
}