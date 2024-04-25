package lab.nomad.springbootncsevaluation.domain.exams._papers._multiple_questions._answers.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import static lab.nomad.springbootncsevaluation._core.exception.ValidExceptionMessage.Message.EMPTY_CONTENT;
import static lab.nomad.springbootncsevaluation._core.exception.ValidExceptionMessage.Message.IS_CORRECT;

@Getter
@Setter
public class ExamPaperMultipleQuestionAnswersSaveRequestDTO {

    // no는 서비스에서 자동으로 처리
    // private Integer no;

    @NotEmpty(message = EMPTY_CONTENT)
    private String content;

    @NotNull(message = IS_CORRECT)
    private Boolean isCorrect;
}
