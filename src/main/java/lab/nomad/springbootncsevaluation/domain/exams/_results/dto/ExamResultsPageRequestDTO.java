package lab.nomad.springbootncsevaluation.domain.exams._results.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ExamResultsPageRequestDTO {
    private Long id;
    private String studentName;
    private String tel;
    private int grade;
    private int totalPoint;
    private String status;
    private String color;
}