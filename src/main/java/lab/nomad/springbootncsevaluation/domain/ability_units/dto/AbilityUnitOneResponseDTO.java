package lab.nomad.springbootncsevaluation.domain.ability_units.dto;

import lab.nomad.springbootncsevaluation.model.ability_units.AbilityUnits;
import lab.nomad.springbootncsevaluation.model.ability_units.elements.AbilityUnitElements;
import lab.nomad.springbootncsevaluation.model.ability_units.elements.items.AbilityUnitElementItems;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class AbilityUnitOneResponseDTO {
    private AbilityUnitDTO abilityUnit;

    public AbilityUnitOneResponseDTO(AbilityUnits abilityUnit,
                                     List<AbilityUnitElements> abilityUnitElementList,
                                     List<AbilityUnitElementItems> abilityUnitElementItemList) {
        this.abilityUnit = new AbilityUnitDTO(abilityUnit, abilityUnitElementList, abilityUnitElementItemList);
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
        private List<AbilityUnitElementDTO> abilityUnitElementList;
        private LocalDateTime createDate;
        private LocalDateTime updateDate;

        public AbilityUnitDTO(AbilityUnits abilityUnit,
                              List<AbilityUnitElements> abilityUnitElementList,
                              List<AbilityUnitElementItems> abilityUnitElementItemList) {
            this.id = abilityUnit.getId();
            this.code = abilityUnit.getCode();
            this.name = abilityUnit.getName();
            this.purpose = abilityUnit.getPurpose();
            this.grade = abilityUnit.getGrade();
            this.totalTime = abilityUnit.getTotalTime();
            this.examTypeList = abilityUnit.getExamTypeList().stream().map(examType -> examType.name()).toList();
            this.abilityUnitElementList = abilityUnitElementList.stream()
                    .map(element -> {
                        var filteredAbilityUnitElementItemList =
                                abilityUnitElementItemList.stream()
                                        .filter(item -> item.getAbilityUnitElement().getId() == element.getId()).toList();

                        return new AbilityUnitElementDTO(element, filteredAbilityUnitElementItemList);
                    }).toList();
            this.createDate = abilityUnit.getCreateDate();
            this.updateDate = abilityUnit.getUpdateDate();
        }

        @Getter
        public static class AbilityUnitElementDTO {
            private Long id;
            private String code;
            private String name;
            private List<AbilityUnitElementItemDTO> abilityUnitElementItemList;
            private LocalDateTime createDate;
            private LocalDateTime updateDate;

            public AbilityUnitElementDTO(
                    AbilityUnitElements abilityUnitElement,
                    List<AbilityUnitElementItems> abilityUnitElementItemList) {

                this.id = abilityUnitElement.getId();
                this.code = abilityUnitElement.getCode();
                this.name = abilityUnitElement.getName();
                this.abilityUnitElementItemList = abilityUnitElementItemList.stream()
                        .map(item -> new AbilityUnitElementItemDTO(item)).toList();
                this.createDate = abilityUnitElement.getCreateDate();
                this.updateDate = abilityUnitElement.getUpdateDate();
            }

            @Getter
            public static class AbilityUnitElementItemDTO {
                private Long id;
                private String content;
                private LocalDateTime createDate;
                private LocalDateTime updateDate;

                public AbilityUnitElementItemDTO(AbilityUnitElementItems abilityUnitElementItem) {
                    this.id = abilityUnitElementItem.getId();
                    this.content = abilityUnitElementItem.getContent();
                    this.createDate = abilityUnitElementItem.getCreateDate();
                    this.updateDate = abilityUnitElementItem.getUpdateDate();
                }
            }
        }
    }
}