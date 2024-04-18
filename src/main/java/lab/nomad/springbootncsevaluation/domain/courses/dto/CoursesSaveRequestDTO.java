package lab.nomad.springbootncsevaluation.domain.courses.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class CoursesSaveRequestDTO {
    private String name;

    private String academyName;

    // User는 로그인된 상태로만 과정을 등록하므로 거기서 User 정보를 가지고온다!!!
}
