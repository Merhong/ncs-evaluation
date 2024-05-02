package lab.nomad.springbootncsevaluation.domain.exams.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
//학생과 시험지의id를 전달해서 시험 생성
public class ExamsSaveRequestDTO {
    private  Long studentsId;
    private  Long examPaperId;



}
