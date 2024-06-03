package lab.nomad.springbootncsevaluation.domain.exams._papers.dto;

import jakarta.validation.constraints.NotEmpty;
import lab.nomad.springbootncsevaluation.model.ability_units._enums.ExamType;
import lombok.Getter;
import lombok.Setter;

import static lab.nomad.springbootncsevaluation._core.exception.ValidExceptionMessage.Message.EMPTY_EXAM_PAPER_NAME;
import static lab.nomad.springbootncsevaluation._core.exception.ValidExceptionMessage.Message.EMPTY_EXAM_TYPE;

@Getter
@Setter
public class ExamPaperUpdateRequestDTO {

    @NotEmpty(message = EMPTY_EXAM_PAPER_NAME)
    private String name;

    // Controller에서 examType을 String으로 받아오면서 null 처리함
    @NotEmpty(message = EMPTY_EXAM_TYPE)
    private String examType;
}
