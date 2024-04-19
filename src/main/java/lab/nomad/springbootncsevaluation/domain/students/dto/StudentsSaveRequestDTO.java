package lab.nomad.springbootncsevaluation.domain.students.dto;


import jakarta.validation.constraints.NotEmpty;
import lab.nomad.springbootncsevaluation.model.courses.Courses;
import lab.nomad.springbootncsevaluation.model.students._enums.StudentStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentsSaveRequestDTO {



    @NotEmpty
    private  String tel;

    @NotEmpty
    private  String name;

    private  StudentStatus status;

    private Long courseId; // courseId를 저장하는 변수 추가

}
