package lab.nomad.springbootncsevaluation.domain.auth.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lab.nomad.springbootncsevaluation._core.exception.ValidExceptionMessage;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequestDTO {

    @NotEmpty(message = ValidExceptionMessage.Message.INVALID_USERNAME)
    @Size(min = 4, max = 15, message = ValidExceptionMessage.Message.INVALID_USERNAME)
    private String username;

    @NotEmpty(message = ValidExceptionMessage.Message.INVALID_PASSWORD)
    @Size(min = 4, max = 20, message = ValidExceptionMessage.Message.INVALID_PASSWORD)
    private String password;
}
