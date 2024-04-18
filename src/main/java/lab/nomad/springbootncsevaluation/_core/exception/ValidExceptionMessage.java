package lab.nomad.springbootncsevaluation._core.exception;

public enum ValidExceptionMessage {
    INVALID_USERNAME(Message.INVALID_USERNAME),
    INVALID_PASSWORD("패스워드는 4에서 20자 이내여야 합니다."),
    INVALID_NAME("이름은 15자 이내여야 합니다."),
    INVALID_EMAIL("이메일 형식이 올바르지 않습니다."),
    NULL_COURSE_NAME(Message.EMPTY_COURSE_NAME),
    EMPTY_ACADEMY_NAME(Message.EMPTY_ACADEMY_NAME),
    EMPTY_ABILITY_CODE(Message.EMPTY_ABILITY_CODE),
    EMPTY_ABILITY_NAME(Message.EMPTY_ABILITY_NAME);

    final private String message;

    ValidExceptionMessage(String message) {
        this.message = message;
    }

    public static class Message {
        public static final String INVALID_USERNAME = "아이디는 4에서 15자 이내여야 합니다.";
        public static final String INVALID_PASSWORD = "패스워드는 4에서 20자 이내여야 합니다.";
        public static final String INVALID_NAME = "이름은 15자 이내여야 합니다.";
        public static final String INVALID_EMAIL = "이메일 형식이 올바르지 않습니다.";
        public static final String EMPTY_COURSE_NAME = "과정명을 입력해주세요.";
        public static final String EMPTY_ACADEMY_NAME = "학원명을 입력해주세요.";
        public static final String EMPTY_ABILITY_CODE = "능력단위(능력단위요소) 코드를 입력하세요.";
        public static final String EMPTY_ABILITY_NAME = "능력단위(능력단위요소)명 입력하세요.";

    }
}
