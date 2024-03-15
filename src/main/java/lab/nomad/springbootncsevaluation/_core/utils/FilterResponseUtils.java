package lab.nomad.springbootncsevaluation._core.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletResponse;
import lab.nomad.springbootncsevaluation._core.exception.Exception401;
import lab.nomad.springbootncsevaluation._core.exception.Exception403;

import java.io.IOException;

public class FilterResponseUtils {
    private final static String CONTENT_TYPE = "application/json; charset=utf-8";

    public static void unAuthorized(HttpServletResponse resp, Exception401 e) throws IOException {
        resp.setStatus(e.status().value());
        resp.setContentType(CONTENT_TYPE);
        ObjectMapper om = new ObjectMapper();
        String responseBody = om.writeValueAsString(e.body());
        resp.getWriter().println(responseBody);
    }

    public static void forbidden(HttpServletResponse resp, Exception403 e) throws IOException {
        resp.setStatus(e.status().value());
        resp.setContentType(CONTENT_TYPE);
        ObjectMapper om = new ObjectMapper();
        String responseBody = om.writeValueAsString(e.body());
        resp.getWriter().println(responseBody);
    }
}
