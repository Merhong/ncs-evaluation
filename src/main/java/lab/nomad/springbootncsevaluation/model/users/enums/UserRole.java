package lab.nomad.springbootncsevaluation.model.users.enums;

/**
 * 유저의 권한을 나타내는 enum 타입입니다.
 *
 * <ul>
 *     <li>{@link #ROLE_ADMIN}: 서버관리자를 의미합니다.</li>
 *     <li>{@link #ROLE_MANAGER}: 학원관리자를 의미합니다.</li>
 *     <li>{@link #ROLE_EMP}: 직원을 의미합니다.</li>
 *     <li>{@link #ROLE_TEACHER}: 강사를 의미합니다.</li>
 *     <li>{@link #ROLE_STUDENT}: 학생을 의미합니다.</li>
 *     <li>{@link #ROLE_GUEST}: 역할을 할당받지 않은 게스트를 의미합니다.</li>
 * </ul>
 */
public enum UserRole {
    /**
     * 서버관리자를 의미합니다.
     */
    ROLE_ADMIN,
    /**
     * 학원관리자를 의미합니다.
     */
    ROLE_MANAGER,
    /**
     * 직원을 의미합니다.
     */
    ROLE_EMP,
    /**
     * 강사를 의미합니다.
     */
    ROLE_TEACHER,
    /**
     * 학생을 의미합니다.
     */
    ROLE_STUDENT,
    /**
     * 역할을 할당받지 않은 게스트를 의미합니다.
     */
    ROLE_GUEST;
}
