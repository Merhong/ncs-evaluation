package lab.nomad.springbootncsevaluation.domain.ability_units._elements.dto;

import lab.nomad.springbootncsevaluation.model.ability_units.AbilityUnits;
import lab.nomad.springbootncsevaluation.model.ability_units.elements.AbilityUnitElements;
import lab.nomad.springbootncsevaluation.model.ability_units.elements.items.AbilityUnitElementItems;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class AbilityUnitElementUpdateResponseDTO {
    AbilityUnitElementDTO abilityUnitElement;

    public AbilityUnitElementUpdateResponseDTO(
            AbilityUnitElements abilityUnitElement,
            List<AbilityUnitElementItems> abilityUnitElementItemList) {

        this.abilityUnitElement = new AbilityUnitElementDTO(abilityUnitElement, abilityUnitElementItemList);
    }
    @Getter
    public static class AbilityUnitElementDTO {
        private Long id;
        private String code;
        private String name;
        private List<AbilityUnitElementItemDTO> abilityUnitElementItemList;
        private LocalDateTime createDate;

        public AbilityUnitElementDTO(
                AbilityUnitElements abilityUnitElement,
                List<AbilityUnitElementItems> abilityUnitElementItemList) {

            this.id = abilityUnitElement.getId();
            this.code = abilityUnitElement.getCode();
            this.name = abilityUnitElement.getName();
            this.abilityUnitElementItemList = abilityUnitElementItemList.stream()
                    .map(item -> new AbilityUnitElementItemDTO(item)).toList();
            this.createDate = abilityUnitElement.getCreateDate();

        }

        @Getter
        public static class AbilityUnitElementItemDTO {
            private Long id;
            private String content;
            private LocalDateTime createDate;

            public AbilityUnitElementItemDTO(AbilityUnitElementItems abilityUnitElementItem) {
                this.id = abilityUnitElementItem.getId();
                this.content = abilityUnitElementItem.getContent();
                this.createDate = abilityUnitElementItem.getCreateDate();
            }
        }
    }
}
