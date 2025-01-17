package lab.nomad.springbootncsevaluation.domain.exams.dto;


import lab.nomad.springbootncsevaluation.model.exams._enums.ExamStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
//학생과 시험지의id를 전달해서 시험 생성
public class ExamsSaveRequestDTO {
    private  Long studentsId;
    private  Long examPaperId;
    private ExamStatus status;


}
