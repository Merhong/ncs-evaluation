package lab.nomad.springbootncsevaluation._core.exception;

public enum ExceptionMessage {
    COMMON_FORBIDDEN("해당 기능을 사용할 권한이 없습니다."),
    INVALID_ROLE("존재하지 않는 역할입니다.: "),
    INVALID_TOKEN("유효하지 않은 토큰입니다."),
    IS_NOT_REFRESH_TOKEN("refreshToken이 아닙니다."),
    EXPIRED_TOKEN("토큰 유효 기간이 만료되었습니다."),
    LOGIN_FAIL("아이디 혹은 비밀번호가 틀렸습니다.");

    final private String message;

    ExceptionMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
