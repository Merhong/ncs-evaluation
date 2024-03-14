package lab.nomad.springbootncsevaluation._core.exception;

import lab.nomad.springbootncsevaluation._core.utils.APIUtils;
import org.springframework.http.HttpStatus;

public class Exception403 extends RuntimeException {
    public Exception403(String message) {
        super(message);
    }

    public APIUtils.APIResult<?> body(){
        return APIUtils.error(getMessage(), HttpStatus.FORBIDDEN);
    }

    public HttpStatus status(){
        return HttpStatus.FORBIDDEN;
    }
}
