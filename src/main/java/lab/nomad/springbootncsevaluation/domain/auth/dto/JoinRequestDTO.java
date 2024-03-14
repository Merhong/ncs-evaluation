package lab.nomad.springbootncsevaluation.domain.auth.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JoinRequestDTO {

    @NotEmpty
    @Size(min = 4, max = 15, message = "유저명은 4에서 15자 이내여야 합니다.")
    private String username;

    @NotEmpty
    @Size(min = 4, max = 20, message = "패스워드는 4에서 20자 이내여야 합니다.")
    private String password;

    @NotEmpty
    @Size(max = 15, message = "이름은 15자 이내여야 합니다.")
    private String name;

    @NotEmpty
    @Pattern(regexp = "^[\\w._%+-]+@[\\w.-]+\\.[a-zA-Z]{2,6}$", message = "이메일 형식으로 작성해주세요")
    private String email;

    @NotEmpty
    private String tel;

    @NotEmpty
    private String role;

}
