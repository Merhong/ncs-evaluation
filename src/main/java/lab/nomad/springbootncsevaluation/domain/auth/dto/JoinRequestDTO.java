package lab.nomad.springbootncsevaluation.domain.auth.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lab.nomad.springbootncsevaluation._core.exception.ValidExceptionMessage;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JoinRequestDTO {

    @NotEmpty(message = ValidExceptionMessage.Message.INVALID_USERNAME)
    @Size(min = 4, max = 15, message = ValidExceptionMessage.Message.INVALID_USERNAME)
    private String username;

    @NotEmpty(message = ValidExceptionMessage.Message.INVALID_PASSWORD)
    @Size(min = 4, max = 20, message = ValidExceptionMessage.Message.INVALID_PASSWORD)
    private String password;

    @NotEmpty(message = ValidExceptionMessage.Message.INVALID_NAME)
    @Size(max = 15, message = ValidExceptionMessage.Message.INVALID_NAME)
    private String name;

    @NotEmpty(message = ValidExceptionMessage.Message.INVALID_EMAIL)
    @Pattern(regexp = "^[\\w._%+-]+@[\\w.-]+\\.[a-zA-Z]{2,6}$", message = ValidExceptionMessage.Message.INVALID_EMAIL)
    private String email;

    @NotEmpty
    private String tel;

    @NotEmpty
    private String role;

}
