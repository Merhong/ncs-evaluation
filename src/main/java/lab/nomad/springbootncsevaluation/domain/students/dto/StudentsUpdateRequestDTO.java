package lab.nomad.springbootncsevaluation.domain.students.dto;

import jakarta.validation.constraints.NotEmpty;
import lab.nomad.springbootncsevaluation.model.students._enums.StudentStatus;
import lombok.Getter;
import lombok.Setter;

import static lab.nomad.springbootncsevaluation._core.exception.ValidExceptionMessage.Message.EMPTY_COURSE_NAME;

//학생수정내용(학생이름,학생번호,상태)
@Getter
@Setter


public class StudentsUpdateRequestDTO {

    @NotEmpty(message = EMPTY_COURSE_NAME)
    private  String name;

    @NotEmpty(message = EMPTY_COURSE_NAME)
    private  String tel;

    @NotEmpty(message = EMPTY_COURSE_NAME)
    private StudentStatus status;


}
