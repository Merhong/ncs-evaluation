package lab.nomad.springbootncsevaluation.domain.users.dto;

import lab.nomad.springbootncsevaluation.model.users.Users;
import lombok.Getter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class UsersPageResponseDTO {
    private List<UserDTO> userList;
    private PageableDTO pageable;

    public UsersPageResponseDTO(Page<Users> usersPage) {
        this.userList = usersPage.getContent().stream().map(user -> new UserDTO(user)).toList();
        this.pageable = new PageableDTO(usersPage);
    }

    @Getter
    public static class UserDTO {
        private Long id;
        private String username;
        private String name;
        private String email;
        private String tel;
        private String role;
        private LocalDateTime createDate;

        public UserDTO(Users user) {
            this.id = user.getId();
            this.username = user.getUsername();
            this.name = user.getName();
            this.email = user.getEmail();
            this.tel = user.getTel();
            this.role = user.getRole().getText();
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

        public PageableDTO(Page<Users> usersPage) {
            this.pageNumber = usersPage.getNumber();
            this.pageSize = usersPage.getSize();
            this.totalPages = usersPage.getTotalPages();
            this.totalElements = usersPage.getTotalElements();
            this.last = usersPage.isLast();
            this.numberOfElements = usersPage.getNumberOfElements();
            this.empty = usersPage.isEmpty();
            this.sort = usersPage.getSort();
        }
    }
}
