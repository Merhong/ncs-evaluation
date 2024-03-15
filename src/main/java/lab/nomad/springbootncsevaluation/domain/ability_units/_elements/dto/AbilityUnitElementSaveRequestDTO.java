package lab.nomad.springbootncsevaluation.domain.ability_units._elements.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class AbilityUnitElementSaveRequestDTO {

    @NotEmpty
    private String name;

    @NotEmpty
    private String code;

    private List<String> itemContents;
}
