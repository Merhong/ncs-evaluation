package lab.nomad.springbootncsevaluation.domain.ability_units.service;

import lab.nomad.springbootncsevaluation.domain.ability_units.dto.AbilityUnitSaveRequestDTO;
import lab.nomad.springbootncsevaluation.domain.ability_units.dto.AbilityUnitSaveResponseDTO;
import lab.nomad.springbootncsevaluation.model.ability_units.AbilityUnits;
import lab.nomad.springbootncsevaluation.model.ability_units.AbilityUnitsRepository;
import lab.nomad.springbootncsevaluation.model.ability_units._enums.ExamType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class AbilityUnitsService {
    private final AbilityUnitsRepository abilityUnitsRepository;

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
}
