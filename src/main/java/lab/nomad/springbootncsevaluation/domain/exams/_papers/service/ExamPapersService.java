package lab.nomad.springbootncsevaluation.domain.exams._papers.service;

import lab.nomad.springbootncsevaluation._core.exception.Exception400;
import lab.nomad.springbootncsevaluation._core.exception.ExceptionMessage;
import lab.nomad.springbootncsevaluation.domain.exams._papers.dto.ExamPaperSaveRequestDTO;
import lab.nomad.springbootncsevaluation.domain.exams._papers.dto.ExamPaperSaveResponseDTO;
import lab.nomad.springbootncsevaluation.model.ability_units.AbilityUnits;
import lab.nomad.springbootncsevaluation.model.ability_units.AbilityUnitsRepository;
import lab.nomad.springbootncsevaluation.model.ability_units._enums.ExamType;
import lab.nomad.springbootncsevaluation.model.exams.papers.ExamPapers;
import lab.nomad.springbootncsevaluation.model.exams.papers.ExamPapersRepository;
import lab.nomad.springbootncsevaluation.model.users.Users;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class ExamPapersService {
    // DI
    private final ExamPapersRepository examPapersRepository;
    private final AbilityUnitsRepository abilityUnitsRepository;

    @Transactional
    public ExamPaperSaveResponseDTO save(Users user, Long abilityUnitId, ExamPaperSaveRequestDTO requestDTO) {

        // 능력단위 ID로 능력단위를 찾기
        AbilityUnits abilityUnitOP = abilityUnitsRepository.findById(abilityUnitId)
                .orElseThrow(() -> new Exception400(ExceptionMessage.NOT_FOUND_ABILITY_UNIT.getMessage()));

        // 시험지 등록
        ExamPapers examPaperForSave = ExamPapers.builder()
                .name(requestDTO.getName())
                .examType(ExamType.valueOf(requestDTO.getExamType().toUpperCase()))
                .user(user)
                .abilityUnit(abilityUnitOP)
                .build();

        // DB 로직 처리
        ExamPapers examPaperPS = examPapersRepository.save(examPaperForSave);

        return new ExamPaperSaveResponseDTO(examPaperPS);
    }
}
