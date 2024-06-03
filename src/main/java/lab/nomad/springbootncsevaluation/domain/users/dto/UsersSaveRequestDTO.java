package lab.nomad.springbootncsevaluation.domain.users.dto;


import lab.nomad.springbootncsevaluation.model.users._enums.UserRole;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsersSaveRequestDTO {
    private String username;
    private String name;
    private String password;
    private String email;
    private UserRole role;
    private String tel;
}
