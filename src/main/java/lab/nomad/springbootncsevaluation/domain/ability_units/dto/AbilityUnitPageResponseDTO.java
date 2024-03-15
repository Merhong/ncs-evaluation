package lab.nomad.springbootncsevaluation.domain.ability_units.dto;

import lab.nomad.springbootncsevaluation.model.ability_units.AbilityUnits;
import lombok.Getter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class AbilityUnitPageResponseDTO {
    private List<AbilityUnitDTO> abilityUnit;
    private PageableDTO pageable;

    public AbilityUnitPageResponseDTO(Page<AbilityUnits> abilityUnitsPage) {
        this.abilityUnit = abilityUnitsPage.getContent().stream()
                .map(abilityUnit -> new AbilityUnitDTO(abilityUnit)).toList();
        this.pageable = new PageableDTO(abilityUnitsPage);
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

    @Getter
    public static class PageableDTO {
        private int pageNumber;
        private int pageSize;
        private int totalPages;
        private long totalElements;
        private boolean last;
        private int numberOfElements;
        private boolean empty;
        private Sort sort;

        public PageableDTO(Page<AbilityUnits> abilityUnitsPage) {
            this.pageNumber = abilityUnitsPage.getNumber();
            this.pageSize = abilityUnitsPage.getSize();
            this.totalPages = abilityUnitsPage.getTotalPages();
            this.totalElements = abilityUnitsPage.getTotalElements();
            this.last = abilityUnitsPage.isLast();
            this.numberOfElements = abilityUnitsPage.getNumberOfElements();
            this.empty = abilityUnitsPage.isEmpty();
            this.sort = abilityUnitsPage.getSort();
        }
    }
}
