package lab.nomad.springbootncsevaluation._core.utils;

import org.springframework.http.HttpStatus;

public class APIUtils {

    public static <T> APIResult<T> success(T response) {
        return new APIResult<>(true, response, null);
    }

    public static APIResult<?> error(String message, HttpStatus status) {
        return new APIResult<>(false, null, new APIError(message, status.value()));
    }
        public record APIResult<T>(boolean success, T response, APIError error) {
    }

        public record APIError(String message, int status) {
    }
}
