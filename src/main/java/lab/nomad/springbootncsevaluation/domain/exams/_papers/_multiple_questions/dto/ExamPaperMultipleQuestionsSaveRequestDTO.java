package lab.nomad.springbootncsevaluation.domain.exams._papers._multiple_questions.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import static lab.nomad.springbootncsevaluation._core.exception.ValidExceptionMessage.Message.EMPTY_CONTENT;
import static lab.nomad.springbootncsevaluation._core.exception.ValidExceptionMessage.Message.EMPTY_POINT;

@Getter
@Setter
public class ExamPaperMultipleQuestionsSaveRequestDTO {

    // no는 서비스에서 자동 설정됨
    // private Integer no;

    @NotEmpty(message = EMPTY_CONTENT)
    private String content;

    @NotNull(message = EMPTY_POINT)
    private Integer point;
}
