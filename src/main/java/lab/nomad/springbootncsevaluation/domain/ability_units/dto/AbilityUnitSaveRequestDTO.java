package lab.nomad.springbootncsevaluation.domain.ability_units.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

import static lab.nomad.springbootncsevaluation._core.exception.ValidExceptionMessage.Message.EMPTY_ABILITY_CODE;
import static lab.nomad.springbootncsevaluation._core.exception.ValidExceptionMessage.Message.EMPTY_ABILITY_NAME;

@Getter
@Setter
public class AbilityUnitSaveRequestDTO {

    @NotEmpty(message = EMPTY_ABILITY_CODE)
    private String code;

    @NotEmpty(message = EMPTY_ABILITY_NAME)
    private String name;

    @NotEmpty
    private String purpose;

    @NotNull
    private Integer grade;

    @NotNull
    private Integer totalTime;

    private List<String> examTypeList;
}
