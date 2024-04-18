package lab.nomad.springbootncsevaluation.domain.courses.dto;

import lab.nomad.springbootncsevaluation.model.courses.Courses;
import lab.nomad.springbootncsevaluation.model.users.Users;
import lombok.Getter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class CoursesPageResponseDTO {
    private List<CoursesDTO> course;
    private PageableDTO pageable;


    public CoursesPageResponseDTO(Users user, Page<Courses> coursesPage) {
        this.course = coursesPage.getContent()
                                 .stream()
                                 .map(course -> new CoursesDTO(course.getUser(), course))
                                 .toList();
        this.pageable = new PageableDTO(coursesPage);
    }

    // 페이징시 과정에 담을 내용
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

        public UserDTO(Users user) {
            this.id = user.getId();
            this.name = user.getName();
            this.username = user.getUsername();
            this.email = user.getEmail();
            this.tel = user.getTel();
            this.role = user.getRole()
                            .getText();
            this.createDate = user.getCreateDate();
        }
    }


    @Getter
    public static class PageableDTO {
        private int pageNumber;
        private int pageSize;
        private int totalPages;
        private long totalElements;
        private boolean last;
        private int numberOfElements;
        private boolean empty;
        private Sort sort;

        public PageableDTO(Page<Courses> coursesPage) {
            this.pageNumber = coursesPage.getNumber();
            this.pageSize = coursesPage.getSize();
            this.totalPages = coursesPage.getTotalPages();
            this.totalElements = coursesPage.getTotalElements();
            this.last = coursesPage.isLast();
            this.numberOfElements = coursesPage.getNumberOfElements();
            this.empty = coursesPage.isEmpty();
            this.sort = coursesPage.getSort();
        }
    }
}
