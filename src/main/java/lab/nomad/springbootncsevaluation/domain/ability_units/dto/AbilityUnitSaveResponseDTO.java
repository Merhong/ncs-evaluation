package lab.nomad.springbootncsevaluation.domain.ability_units.dto;

import lab.nomad.springbootncsevaluation.model.ability_units.AbilityUnits;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class AbilityUnitSaveResponseDTO {
    AbilityUnitDTO abilityUnit;

    public AbilityUnitSaveResponseDTO(AbilityUnits abilityUnit) {
        this.abilityUnit = new AbilityUnitDTO(abilityUnit);
    }

    @Getter
    public static class AbilityUnitDTO {
        private Long id;
        private String code;
        private String name;
        private String purpose;
        private Integer grade;
        private Integer totalTime;
        private List<String> examTypeList;
        private LocalDateTime createDate;

        public AbilityUnitDTO(AbilityUnits abilityUnit) {
            this.id = abilityUnit.getId();
            this.code = abilityUnit.getCode();
            this.name = abilityUnit.getName();
            this.purpose = abilityUnit.getPurpose();
            this.grade = abilityUnit.getGrade();
            this.totalTime = abilityUnit.getTotalTime();
            this.examTypeList = abilityUnit.getExamTypeList().stream().map(examType -> examType.name()).toList();
            this.createDate = abilityUnit.getCreateDate();
        }
    }
}
