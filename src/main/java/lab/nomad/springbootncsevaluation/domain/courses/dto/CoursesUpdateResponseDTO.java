package lab.nomad.springbootncsevaluation.domain.courses.dto;

import lab.nomad.springbootncsevaluation.model.courses.Courses;
import lab.nomad.springbootncsevaluation.model.users.Users;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CoursesUpdateResponseDTO {
    CoursesDTO course;

    public CoursesUpdateResponseDTO(Users user, Courses course) {
        this.course = new CoursesDTO(user, course);
    }

    // courses + user 내용을 가지는 DTO
    @Getter
    public static class CoursesDTO {
        private Long id;
        private String name;
        private String academyName;
        private LocalDateTime createDate;
        private LocalDateTime updateDate;
        private UserDTO userDTO;

        public CoursesDTO(Users user, Courses course) {
            this.id = course.getId();
            this.name = course.getName();
            this.academyName = course.getAcademyName();
            this.createDate = course.getCreateDate();
            this.updateDate = course.getUpdateDate();
            this.userDTO = new UserDTO(user);
        }
    }

    // 민감한 정보(패스워드)를 제외한 userDTO
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
