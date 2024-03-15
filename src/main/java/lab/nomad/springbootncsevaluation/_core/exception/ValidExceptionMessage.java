package lab.nomad.springbootncsevaluation._core.exception;

public enum ValidExceptionMessage {
    INVALID_USERNAME(Message.INVALID_USERNAME),
    INVALID_PASSWORD("패스워드는 4에서 20자 이내여야 합니다."),
    INVALID_NAME("이름은 15자 이내여야 합니다."),
    INVALID_EMAIL("이메일 형식이 올바르지 않습니다.");

    final private String message;

    ValidExceptionMessage(String message) {
        this.message = message;
    }

    public static class Message {
        public static final String INVALID_USERNAME = "아이디는 4에서 15자 이내여야 합니다.";
        public static final String INVALID_PASSWORD = "패스워드는 4에서 20자 이내여야 합니다.";
        public static final String INVALID_NAME = "이름은 15자 이내여야 합니다.";
        public static final String INVALID_EMAIL = "이메일 형식이 올바르지 않습니다.";
    }
}
