package lab.nomad.springbootncsevaluation.model.students._enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 학생의 과정 수강 상태를 나타내는 enum 타입입니다.
 *
 * <ul>
 *     <li>{@link #DROP}: 학생이 중도에 포기한 상태임을 의미합니다.</li>
 *     <li>{@link #ACTIVE}: 학생이 과정을 수강중인 상태임을 의미합니다.</li>
 * </ul>
 */
@AllArgsConstructor
@Getter
public enum StudentStatus {
    /**
     * 학생이 중도에 포기한 상태임을 의미합니다.
     */
    DROP("중도포기"),
    /**
     * 학생이 과정을 수강중인 상태임을 의미합니다.
     */
    ACTIVE("수강중");

    private final String value;
}
