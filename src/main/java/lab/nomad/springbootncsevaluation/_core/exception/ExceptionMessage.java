package lab.nomad.springbootncsevaluation._core.exception;

public enum ExceptionMessage {
    LOGIN_FAIL("아이디 혹은 비밀번호가 틀렸습니다.");

    final private String message;

    ExceptionMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
