package lab.nomad.springbootncsevaluation.domain.students.dto;

import lab.nomad.springbootncsevaluation.model.courses.Courses;
import lab.nomad.springbootncsevaluation.model.students.Students;
import lab.nomad.springbootncsevaluation.model.students._enums.StudentStatus;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class StudentsUpdateResponseDTO {
    private StudentsDTO students;

    public StudentsUpdateResponseDTO(Courses courses, Students students) {
        this.students = new StudentsDTO(students, courses);
    }

    @Getter
    @Setter
    public static class StudentsDTO {
        private Long id;
        private String tel;
        private String name;
        private StudentStatus status;
        private CoursesDTO coursesDTO;


        public StudentsDTO(Students students,Courses courses) {
            this.id = students.getId();
            this.tel = students.getTel();
            this.name = students.getName();
            this.status = students.getStatus();
            this.coursesDTO = new CoursesDTO(courses);

        }
    }

    @Getter
    public static class CoursesDTO {
        private Long id;
        private String name;
        private String academyName;


        public CoursesDTO(Courses courses) {
            this.id = courses.getId();
            this.name = courses.getName();
            this.academyName = courses.getAcademyName();
        }
    }
}
