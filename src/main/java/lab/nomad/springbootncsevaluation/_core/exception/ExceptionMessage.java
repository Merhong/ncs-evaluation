package lab.nomad.springbootncsevaluation._core.exception;

public enum ExceptionMessage {
    COMMON_FORBIDDEN("code1: 해당 기능을 사용할 권한이 없습니다."),
    NOT_FOUND_ABILITY_UNIT("code2: 존재하지 않는 능력 단위입니다."),

    INVALID_ROLE("code3: 존재하지 않는 역할입니다.: "),
    INVALID_TOKEN("code4: 유효하지 않은 토큰입니다."),
    IS_NOT_REFRESH_TOKEN("code5: refreshToken이 아닙니다."),
    EXPIRED_TOKEN("code6: 토큰 유효 기간이 만료되었습니다."),
    LOGIN_FAIL("code7: 아이디 혹은 비밀번호가 틀렸습니다."),
    NOT_FOUND_ABILITY_UNIT_ELEMENT("code8: 존재하지 않는 능력 단위 요소입니다.");

    final private String message;

    ExceptionMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
