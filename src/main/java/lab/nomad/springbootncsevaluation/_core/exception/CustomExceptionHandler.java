package lab.nomad.springbootncsevaluation._core.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler {

    // Exception401 예외 처리 등록
    @ExceptionHandler(Exception401.class)
    public ResponseEntity<?> unAuthorized(Exception401 e){
        return new ResponseEntity<>(e.body(), e.status());
    }

    // Exception403 예외 처리 등록
    @ExceptionHandler(Exception403.class)
    public ResponseEntity<?> forbidden(Exception403 e){
        return new ResponseEntity<>(e.body(), e.status());
    }

    // Exception400 예외 처리 등록
    @ExceptionHandler(Exception400.class)
    public ResponseEntity<?> badRequest(Exception400 e){
        return new ResponseEntity<>(e.body(), e.status());
    }

    // Exception500 예외 처리 등록
    @ExceptionHandler(Exception500.class)
    public ResponseEntity<?> internalServerError(Exception500 e){
        return new ResponseEntity<>(e.body(), e.status());
    }
}
