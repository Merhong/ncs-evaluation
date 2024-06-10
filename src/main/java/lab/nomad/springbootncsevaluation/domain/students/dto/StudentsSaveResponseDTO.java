package lab.nomad.springbootncsevaluation.domain.students.dto;

import lab.nomad.springbootncsevaluation.model.courses.Courses;
import lab.nomad.springbootncsevaluation.model.students.Students;
import lab.nomad.springbootncsevaluation.model.students._enums.StudentStatus;
import lab.nomad.springbootncsevaluation.model.users.Users;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class StudentsSaveResponseDTO {
    private StudentsDTO student;

    public StudentsSaveResponseDTO(Students student) {
        this.student = new StudentsDTO(student);
    }

    @Getter
    public static class StudentsDTO {
        private Long id;
        private String tel;
        private String name;
        private StudentStatus status;
        private LocalDateTime createDate;
        private LocalDateTime updateDate;
        private CoursesDTO coursesDTO;

        public StudentsDTO(Students student) {
            this.id = student.getId();
            this.tel = student.getTel();
            this.name = student.getName();
            this.status = student.getStatus();
            this.createDate = student.getCreateDate();
            this.updateDate = student.getUpdateDate();
            this.coursesDTO = new CoursesDTO(student.getCourse());
        }
    }

    @Getter
    public static class CoursesDTO {
        private Long id;
        private String name;
        private String academyName;
        private LocalDateTime createDate;
        private LocalDateTime updateDate;
        private UserDTO userDTO;

        public CoursesDTO(Courses course) {
            this.id = course.getId();
            this.name = course.getName();
            this.academyName = course.getAcademyName();
            this.createDate = course.getCreateDate();
            this.updateDate = course.getUpdateDate();
            this.userDTO = new UserDTO(course.getUser());
        }
    }

    @Getter
    public static class UserDTO {
        private Long id;
        private String name;
        private String username;
        private String email;
        private String tel;
        private String role;
        private LocalDateTime createDate;
        private LocalDateTime updateDate;

        public UserDTO(Users user) {
            this.id = user.getId();
            this.name = user.getName();
            this.username = user.getUsername();
            this.email = user.getEmail();
            this.tel = user.getTel();
            this.role = user.getRole()
                    .getText();
            this.createDate = user.getCreateDate();
            this.updateDate = user.getUpdateDate();
        }
    }

}
