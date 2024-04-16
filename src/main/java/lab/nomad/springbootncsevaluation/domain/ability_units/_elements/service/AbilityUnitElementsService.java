package lab.nomad.springbootncsevaluation.domain.ability_units._elements.service;

import lab.nomad.springbootncsevaluation._core.exception.Exception400;
import lab.nomad.springbootncsevaluation._core.exception.ExceptionMessage;
import lab.nomad.springbootncsevaluation.domain.ability_units._elements.dto.AbilityUnitElementSaveRequestDTO;
import lab.nomad.springbootncsevaluation.domain.ability_units._elements.dto.AbilityUnitElementSaveResponseDTO;
import lab.nomad.springbootncsevaluation.domain.ability_units._elements.dto.AbilityUnitElementUpdateRequestDTO;
import lab.nomad.springbootncsevaluation.domain.ability_units._elements.dto.AbilityUnitElementUpdateResponseDTO;
import lab.nomad.springbootncsevaluation.domain.ability_units.dto.AbilityUnitSaveRequestDTO;
import lab.nomad.springbootncsevaluation.domain.ability_units.dto.AbilityUnitSaveResponseDTO;
import lab.nomad.springbootncsevaluation.model.ability_units.AbilityUnits;
import lab.nomad.springbootncsevaluation.model.ability_units.AbilityUnitsRepository;
import lab.nomad.springbootncsevaluation.model.ability_units._enums.ExamType;
import lab.nomad.springbootncsevaluation.model.ability_units.elements.AbilityUnitElements;
import lab.nomad.springbootncsevaluation.model.ability_units.elements.AbilityUnitElementsRepository;
import lab.nomad.springbootncsevaluation.model.ability_units.elements.items.AbilityUnitElementItems;
import lab.nomad.springbootncsevaluation.model.ability_units.elements.items.AbilityUnitElementItemsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class AbilityUnitElementsService {
    private final AbilityUnitsRepository abilityUnitsRepository;
    private final AbilityUnitElementsRepository abilityUnitElementsRepository;
    private final AbilityUnitElementItemsRepository abilityUnitElementItemsRepository;

    @Transactional
    public AbilityUnitElementSaveResponseDTO save(Long abilityUnitId, List<AbilityUnitElementSaveRequestDTO> requestDTOList) {

        // 능력 단위 불러오기
        // 존재하지 않는다면 400 예외 처리
        AbilityUnits abilityUnitPS = abilityUnitsRepository.findById(abilityUnitId)
                .orElseThrow(() -> new Exception400(ExceptionMessage.NOT_FOUND_ABILITY_UNIT.getMessage()));

        List<AbilityUnitElementItems> abilityUnitElementItemPSList = new ArrayList<>();
        List<AbilityUnitElements> abilityUnitElementPSList = new ArrayList<>();


        requestDTOList.stream().forEach(requestDTO -> {

            // 능력 단위 요소 저장
            AbilityUnitElements abilityUnitElementForSave = AbilityUnitElements.builder()
                    .abilityUnit(abilityUnitPS)
                    .name(requestDTO.getName())
                    .code(requestDTO.getCode())
                    .build();

            AbilityUnitElements abilityUnitElementPS =
                    abilityUnitElementsRepository.save(abilityUnitElementForSave);

            abilityUnitElementPSList.add(abilityUnitElementPS);

            // 능력 단위 요소 수행 준거 저장
            List<AbilityUnitElementItems> abilityUnitElementItemListForSave = requestDTO.getItemContents().stream()
                    .map(s -> AbilityUnitElementItems.builder()
                            .abilityUnitElement(abilityUnitElementPS)
                            .content(s)
                            .build()).toList();

            List<AbilityUnitElementItems> abilityUnitElementItemList =
                    abilityUnitElementItemsRepository.saveAll(abilityUnitElementItemListForSave);

            abilityUnitElementItemPSList.addAll(abilityUnitElementItemList);

        });

        // 응답 DTO 리턴
        return new AbilityUnitElementSaveResponseDTO(abilityUnitElementPSList, abilityUnitElementItemPSList);

    }

    @Transactional
    public AbilityUnitElementUpdateResponseDTO update(Long abilityUnitId, Long elementId, AbilityUnitElementUpdateRequestDTO requestDTO) {

        // 능력 단위 불러오기
        // 존재하지 않는다면 400 예외 처리
        abilityUnitsRepository.findById(abilityUnitId)
                .orElseThrow(() -> new Exception400(ExceptionMessage.NOT_FOUND_ABILITY_UNIT.getMessage()));

        // 능력 단위 요소 불러오기
        // 존재하지 않는다면 400 예외 처리
        AbilityUnitElements abilityUnitElementPS = abilityUnitElementsRepository.findById(elementId)
                .orElseThrow(() -> new Exception400(ExceptionMessage.NOT_FOUND_ABILITY_UNIT_ELEMENT.getMessage()));

        // 능력 단위 요소 수행 준거 불러오기
        List<AbilityUnitElementItems> abilityUnitElementItemPSList =
                abilityUnitElementItemsRepository.findAllByAbilityUnitElement(abilityUnitElementPS);

        // 능력 단위 요소 수행 준거 삭제
        abilityUnitElementItemsRepository.deleteAll(abilityUnitElementItemPSList);

        var abilityUnitElementItemForSave = requestDTO.getItemContents()
                .stream()
                .map(s -> AbilityUnitElementItems.builder()
                        .abilityUnitElement(abilityUnitElementPS)
                        .content(s).build()).toList();

        // 능력 단위 요소 수행 준거 엔티티 저장
        abilityUnitElementItemPSList = abilityUnitElementItemsRepository.saveAll(abilityUnitElementItemForSave);

        return new AbilityUnitElementUpdateResponseDTO(abilityUnitElementPS, abilityUnitElementItemPSList);
    }
}
