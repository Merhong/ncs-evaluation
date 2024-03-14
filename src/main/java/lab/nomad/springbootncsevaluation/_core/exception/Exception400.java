package lab.nomad.springbootncsevaluation._core.exception;

import lab.nomad.springbootncsevaluation._core.utils.APIUtils;
import org.springframework.http.HttpStatus;

public class Exception400 extends RuntimeException {
    public Exception400(String message) {
        super(message);
    }

    public APIUtils.APIResult<?> body(){
        return APIUtils.error(getMessage(), HttpStatus.BAD_REQUEST);
    }

    public HttpStatus status(){
        return HttpStatus.BAD_REQUEST;
    }
}
