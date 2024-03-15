package lab.nomad.springbootncsevaluation.domain.ability_units.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class AbilityUnitSaveRequestDTO {

    @NotEmpty
    private String code;

    @NotEmpty
    private String name;

    @NotEmpty
    private String purpose;

    @NotNull
    private Integer grade;

    @NotNull
    private Integer totalTime;

    private List<String> examTypeList;
}
