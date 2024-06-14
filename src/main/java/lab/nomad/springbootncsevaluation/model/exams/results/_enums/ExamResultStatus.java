package lab.nomad.springbootncsevaluation.model.exams.results._enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 평가 결과를 나타내는 enum 타입입니다.
 *
 * <ul>
 *     <li>{@link #WAIT}: 평가완료후  총평입력을 의미합니다.</li>
 *     <li>{@link #DONE}: 평가 완료를 의미합니다.</li>
 * </ul>
 */
@AllArgsConstructor
@Getter
public enum ExamResultStatus {
    /**
     * 평가 전 상태
     */
    WAIT("총평입력필요"),
    /**
     * 평가 완료
     */
    DONE("평가완료");

    private final String value;
}