package lab.nomad.springbootncsevaluation._core.exception;

import lab.nomad.springbootncsevaluation._core.utils.APIUtils;
import org.springframework.http.HttpStatus;

public class Exception500 extends RuntimeException {
    public Exception500(String message) {
        super(message);
    }

    public APIUtils.APIResult<?> body(){
        return APIUtils.error(getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public HttpStatus status(){
        return HttpStatus.INTERNAL_SERVER_ERROR;
    }
}
