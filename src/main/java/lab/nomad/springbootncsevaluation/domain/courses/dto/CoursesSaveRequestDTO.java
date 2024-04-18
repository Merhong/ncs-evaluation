package lab.nomad.springbootncsevaluation.domain.courses.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

import static lab.nomad.springbootncsevaluation._core.exception.ValidExceptionMessage.Message.EMPTY_ACADEMY_NAME;
import static lab.nomad.springbootncsevaluation._core.exception.ValidExceptionMessage.Message.EMPTY_COURSE_NAME;

@Getter
@Setter
public class CoursesSaveRequestDTO {

    @NotEmpty(message = EMPTY_COURSE_NAME)
    private String name;

    @NotEmpty(message = EMPTY_ACADEMY_NAME)
    private String academyName;

}
