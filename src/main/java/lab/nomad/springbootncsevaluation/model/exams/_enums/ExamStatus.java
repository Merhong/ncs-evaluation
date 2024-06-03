package lab.nomad.springbootncsevaluation.model.exams._enums;


import lombok.AllArgsConstructor;
import lombok.Getter;
import org.aspectj.lang.annotation.Before;

/**
 * 학생의 과정 수강 상태를 나타내는 enum 타입입니다.
 *
 * <ul>
 *     <li>{@link # BEFORE_EXAM}: 시험을 치기 전 상태임을 의미합니다.</li>
 *     <li>{@link #AFTRE_EXAM}: 시험을 치고 난 후 상태임을 의미합니다.</li>
 * </ul>
 */
@AllArgsConstructor
@Getter
public enum ExamStatus {

    /**
     * 시험을 치기 전 상태임을 의미합니다
     */
    BEFORE_EXAM("시험치기 전"),

    /**
     * 시험을 치고 난 후 상태임을 의미합니다
     */
    AFTRE_EXAM("시험치고 난 후");

    private  final String value;


}
