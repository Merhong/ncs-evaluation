package lab.nomad.springbootncsevaluation.domain.exams._results.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Getter
@Setter
public class ExamResultsSaveRequestDTO {
    private Long examsId;
    private Integer totalPoint;
    private Map<Long, Long> selectedAnswers; // 질문 ID와 선택된 답변 ID를 매핑하는 맵

}
