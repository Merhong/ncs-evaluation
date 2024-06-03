package lab.nomad.springbootncsevaluation.domain.exams.dto;

import lab.nomad.springbootncsevaluation.model.exams._enums.ExamStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ExamsDeleteRequestDTO {
    private  Long studentsId;
    private  Long examPaperId;
    private  Long coursesId;
    private ExamStatus status;

}
