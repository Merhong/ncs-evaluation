package lab.nomad.springbootncsevaluation._core.security;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import java.io.IOException;

public class JwtAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException {
//        accessDeniedException = new AccessDeniedException("Sorry you don not enough permissions to access it!");
//        response.sendError(HttpServletResponse.SC_FORBIDDEN, accessDeniedException.getMessage());
        response.setStatus(HttpStatus.FORBIDDEN.value());
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(
                "{\"type\" : \"about:blank\", \"title\" : \"FORBIDDEN\","
                        + "\"status\" : 403 ,"
                        + "\"detail\" : \"" + accessDeniedException.getMessage() + "\","
                        + "\"instance\" : \"" + request.getServletPath() + "\","
                        + "}"
        );


    }
}
