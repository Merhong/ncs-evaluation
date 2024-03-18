package lab.nomad.springbootncsevaluation.domain.ability_units.service;

import lab.nomad.springbootncsevaluation._core.exception.Exception400;
import lab.nomad.springbootncsevaluation._core.exception.ExceptionMessage;
import lab.nomad.springbootncsevaluation.domain.ability_units.dto.*;
import lab.nomad.springbootncsevaluation.model.ability_units.AbilityUnits;
import lab.nomad.springbootncsevaluation.model.ability_units.AbilityUnitsRepository;
import lab.nomad.springbootncsevaluation.model.ability_units._enums.ExamType;
import lab.nomad.springbootncsevaluation.model.ability_units.elements.AbilityUnitElements;
import lab.nomad.springbootncsevaluation.model.ability_units.elements.AbilityUnitElementsRepository;
import lab.nomad.springbootncsevaluation.model.ability_units.elements.items.AbilityUnitElementItems;
import lab.nomad.springbootncsevaluation.model.ability_units.elements.items.AbilityUnitElementItemsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class AbilityUnitsService {
    private final AbilityUnitsRepository abilityUnitsRepository;
    private final AbilityUnitElementsRepository abilityUnitElementsRepository;
    private final AbilityUnitElementItemsRepository abilityUnitElementItemsRepository;

    @Transactional
    public AbilityUnitSaveResponseDTO save(AbilityUnitSaveRequestDTO requestDTO) {

        AbilityUnits abilityUnitForSave = AbilityUnits.builder()
                .code(requestDTO.getCode())
                .name(requestDTO.getName())
                .grade(requestDTO.getGrade())
                .purpose(requestDTO.getPurpose())
                .totalTime(requestDTO.getTotalTime())
                .examTypeList(requestDTO.getExamTypeList().stream().map(s -> ExamType.valueOf(s)).toList())
                .build();

        AbilityUnits abilityUnitPS = abilityUnitsRepository.save(abilityUnitForSave);

        return new AbilityUnitSaveResponseDTO(abilityUnitPS);
    }

    public AbilityUnitPageResponseDTO page(Pageable pageable, String searchValue) {

        if (!(searchValue == null)) {
            return new AbilityUnitPageResponseDTO(abilityUnitsRepository.findAllByNameContains(pageable, searchValue));
        }

        Page<AbilityUnits> pagedAbilityUnitPS = abilityUnitsRepository.findAll(pageable);

        return new AbilityUnitPageResponseDTO(pagedAbilityUnitPS);
    }

    public AbilityUnitOneResponseDTO one(Long id) {

        AbilityUnits abilityUnitPS = abilityUnitsRepository.findById(id)
                .orElseThrow(() -> new Exception400(ExceptionMessage.NOT_FOUND_ABILITY_UNIT.getMessage()));

        List<AbilityUnitElements> abilityUnitElementPSList =
                abilityUnitElementsRepository.findAllByAbilityUnit(abilityUnitPS);

        List<AbilityUnitElementItems> abilityUnitElementItemPSList = new ArrayList<>();

        abilityUnitElementPSList.stream()
                .forEach(element ->
                    abilityUnitElementItemPSList.addAll(abilityUnitElementItemsRepository.findAllByAbilityUnitElement(element))
                    );

        System.out.println("테스트 : " + abilityUnitElementItemPSList.size());
        return new AbilityUnitOneResponseDTO(abilityUnitPS, abilityUnitElementPSList, abilityUnitElementItemPSList);
    }

    @Transactional
    public AbilityUnitUpdateResponseDTO update(Long id, AbilityUnitUpdateRequestDTO requestDTO) {

        AbilityUnits abilityUnitPS = abilityUnitsRepository.findById(id)
                .orElseThrow(() -> new Exception400(ExceptionMessage.NOT_FOUND_ABILITY_UNIT.getMessage()));

        abilityUnitPS.update(requestDTO.getCode(), requestDTO.getName(), requestDTO.getPurpose(),
                requestDTO.getGrade(), requestDTO.getTotalTime(), requestDTO.getExamTypeList());

        return new AbilityUnitUpdateResponseDTO(abilityUnitsRepository.save(abilityUnitPS));
    }
}
