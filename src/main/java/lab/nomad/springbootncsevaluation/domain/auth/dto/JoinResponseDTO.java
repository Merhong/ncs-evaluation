package lab.nomad.springbootncsevaluation.domain.auth.dto;

import lab.nomad.springbootncsevaluation.model.users.Users;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class JoinResponseDTO {
    private UserDTO user;

    public JoinResponseDTO(Users user) {
        this.user = new UserDTO(user);
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
}
