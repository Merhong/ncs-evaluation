package lab.nomad.springbootncsevaluation.domain.exams._results._multiple_items.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import static lab.nomad.springbootncsevaluation._core.exception.ValidExceptionMessage.Message.*;

@Getter
@Setter
public class ExamResultMultipleItemsSaveRequestDTO {
    @NotNull(message = EMPTY_EXAM_RESULT)
    private Long resultId;

    @NotNull(message = EMPTY_QUESTION)
    private Long questionId;

    @NotNull(message = EMPTY_QUESTION_ANSWER)
    private Long answerId;

    @NotNull(message = EMPTY_POINT)
    private Integer point; // 배점

    @NotEmpty(message = EMPTY_CONTENT)
    private String comment; // 강사의 평가 내용(EPMQ의 comment와 동일)

}
