package lab.nomad.springbootncsevaluation.model.ability_units.enums;

/**
 * 평가 방법을 나타내는 enum 타입입니다.
 * <p>현재 해당 enum은 ExamPaper와 AblilityUnit 두 곳에서 사용되고 있습니다.</p>
 *
 * <ul>
 *     <li>{@link #MULTIPLE_CHOICE}: 선다형 평가를 의미합니다.</li>
 *     <li>{@link #TASK}: 작업형 평가를 의미합니다.</li>
 * </ul>
 */
public enum ExamType {
    /**
     * 선다형 평가를 의미합니다.
     */
    MULTIPLE_CHOICE,
    /**
     * 작업형 평가를 의미합니다.
     */
    TASK
}
