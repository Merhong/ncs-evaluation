package lab.nomad.springbootncsevaluation.domain.exams._results.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ExamResultsPageResponseDTO {
    private Long id;
    private String studentName;
    private String tel;
    private Integer grade;
    private Integer totalPoint;
    private String status;
    private String color;

}