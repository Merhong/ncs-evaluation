package lab.nomad.springbootncsevaluation.domain.exams._papers.service;

import lab.nomad.springbootncsevaluation._core.exception.Exception400;
import lab.nomad.springbootncsevaluation._core.exception.ExceptionMessage;
import lab.nomad.springbootncsevaluation.domain.exams._papers.dto.ExamPaperPageResponseDTO;
import lab.nomad.springbootncsevaluation.domain.exams._papers.dto.ExamPaperSaveRequestDTO;
import lab.nomad.springbootncsevaluation.domain.exams._papers.dto.ExamPaperSaveResponseDTO;
import lab.nomad.springbootncsevaluation.model.ability_units.AbilityUnits;
import lab.nomad.springbootncsevaluation.model.ability_units.AbilityUnitsRepository;
import lab.nomad.springbootncsevaluation.model.ability_units._enums.ExamType;
import lab.nomad.springbootncsevaluation.model.exams.papers.ExamPapers;
import lab.nomad.springbootncsevaluation.model.exams.papers.ExamPapersRepository;
import lab.nomad.springbootncsevaluation.model.users.Users;
import lab.nomad.springbootncsevaluation.model.users._enums.UserRole;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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


    // 시험지 목록 페이징 및 검색
    public ExamPaperPageResponseDTO page(Pageable pageable, String searchValue, Users user) {

        // 관리자(모든 시험지 조회 가능)
        if (user.getRole() == UserRole.ROLE_ADMIN) {
            // 검색 키워드 OOO
            if (!(searchValue == null)) {
                return new ExamPaperPageResponseDTO(examPapersRepository.findByNameContains(searchValue, pageable));
            }

            // 검색 키워드 XXX (모든 시험지를 보여줌)
            Page<ExamPapers> pagedExamPapersPS = examPapersRepository.findAll(pageable);

            return new ExamPaperPageResponseDTO(pagedExamPapersPS);
        }

        // 강사(자기 시험지만 조회 가능)
        else {
            // 검색 키워드 OOO
            if (!(searchValue == null)) {
                return new ExamPaperPageResponseDTO(examPapersRepository.findByNameContainsAndUserId(searchValue,
                                                                                                     user.getId(),
                                                                                                     pageable));
            }

            // 검색 키워드 XXX
            Page<ExamPapers> pagedExamPapersPS = examPapersRepository.findByUserId(user.getId(), pageable);

            return new ExamPaperPageResponseDTO(pagedExamPapersPS);
        }
    }


    // 시험지 등록
    @Transactional
    public ExamPaperSaveResponseDTO save(Users user, Long abilityUnitId, ExamPaperSaveRequestDTO requestDTO) {

        // 능력단위 ID로 능력단위를 찾기
        AbilityUnits abilityUnitOP = abilityUnitsRepository.findById(abilityUnitId)
                .orElseThrow(() -> new Exception400(ExceptionMessage.NOT_FOUND_ABILITY_UNIT.getMessage()));

        ExamPapers examPaperForSave = ExamPapers.builder()
                .name(requestDTO.getName())
                .examType(ExamType.valueOf(requestDTO.getExamType()
                                                   .toUpperCase()))
                .user(user)
                .abilityUnit(abilityUnitOP)
                .build();

        // DB 로직 처리
        ExamPapers examPaperPS = examPapersRepository.save(examPaperForSave);

        return new ExamPaperSaveResponseDTO(examPaperPS);
    }
}
