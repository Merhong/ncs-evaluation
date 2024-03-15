package lab.nomad.springbootncsevaluation.domain.auth.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequestDTO {

    @NotEmpty
    @Size(min = 4, max = 15, message = "유저명은 4에서 15자 이내여야 합니다.")
    private String username;

    @NotEmpty
    @Size(min = 4, max = 20, message = "패스워드는 4에서 20자 이내여야 합니다.")
    private String password;
}
