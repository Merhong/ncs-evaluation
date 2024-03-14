package lab.nomad.springbootncsevaluation._core.exception;

import lab.nomad.springbootncsevaluation._core.utils.APIUtils;
import org.springframework.http.HttpStatus;

public class Exception401 extends RuntimeException {
    public Exception401(String message) {
        super(message);
    }

    public APIUtils.APIResult<?> body(){
        return APIUtils.error(getMessage(), HttpStatus.UNAUTHORIZED);
    }

    public HttpStatus status(){
        return HttpStatus.UNAUTHORIZED;
    }
}
