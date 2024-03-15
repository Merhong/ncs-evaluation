package lab.nomad.springbootncsevaluation.domain.auth.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lab.nomad.springbootncsevaluation._core.exception.ValidExceptionMessage;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReLoginRequestDTO {

    @NotEmpty
    private String refreshToken;
}
