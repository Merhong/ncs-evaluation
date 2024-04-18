package lab.nomad.springbootncsevaluation.domain.ability_units._elements.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

import static lab.nomad.springbootncsevaluation._core.exception.ValidExceptionMessage.Message.EMPTY_ABILITY_CODE;
import static lab.nomad.springbootncsevaluation._core.exception.ValidExceptionMessage.Message.EMPTY_ABILITY_NAME;

@Getter
@Setter
public class AbilityUnitElementUpdateRequestDTO {

    @NotEmpty(message = EMPTY_ABILITY_NAME)
    private String name;

    @NotEmpty(message = EMPTY_ABILITY_CODE)
    private String code;

    private List<String> itemContents;
}
