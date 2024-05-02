package lab.nomad.springbootncsevaluation.domain.exams.service;


import jakarta.persistence.Id;
import lab.nomad.springbootncsevaluation._core.exception.Exception400;
import lab.nomad.springbootncsevaluation._core.exception.ExceptionMessage;
import lab.nomad.springbootncsevaluation._core.security.CustomUserDetails;
import lab.nomad.springbootncsevaluation.domain.exams.dto.ExamsOneResponseDTO;
import lab.nomad.springbootncsevaluation.domain.exams.dto.ExamsPageResponseDTO;
import lab.nomad.springbootncsevaluation.domain.exams.dto.ExamsSaveRequestDTO;
import lab.nomad.springbootncsevaluation.domain.exams.dto.ExamsSaveResponseDTO;
import lab.nomad.springbootncsevaluation.model.exams.Exams;
import lab.nomad.springbootncsevaluation.model.exams.papers.ExamPapers;
import lab.nomad.springbootncsevaluation.model.exams.ExamsRepository;
import lab.nomad.springbootncsevaluation.model.exams.papers.ExamPapersRepository;
import lab.nomad.springbootncsevaluation.model.students.Students;
import lab.nomad.springbootncsevaluation.model.exams.papers.ExamPapers;
import lab.nomad.springbootncsevaluation.model.students.StudentsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Optional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class ExamsService {
    private final ExamsRepository examsRepository;
    private final ExamPapersRepository examPapersRepository;
    private final StudentsRepository studentsRepository;


    @Transactional
    //ExamsSaveRequestDTO를 받아서 시험을 저장하고, 결과를 ExamsSaveResponseDTO로 반환
    public ExamsSaveResponseDTO save(Long id, ExamsSaveRequestDTO requestDTO) {
        // 요청 DTO에서 필요한 데이터 추출
        Long studentId = requestDTO.getStudentsId();
        Long examPaperId = requestDTO.getExamPaperId();

        // 학생과 시험지를 찾음
        Optional<Students> studentOptional = studentsRepository.findById(studentId);
        Optional<ExamPapers> examPaperOptional = examPapersRepository.findById(examPaperId);

        // 학생이나 시험지가 존재하지 않는 경우 예외 처리
        if (studentOptional.isEmpty() || examPaperOptional.isEmpty()) {
            throw new Exception400(ExceptionMessage.NOT_FOUND_STUDENT_EXAMPAPER.getMessage());
        }

        // 학생과 시험지가 존재하는 경우 엔티티 생성
        Students student = studentOptional.get();
        ExamPapers examPaper = examPaperOptional.get();

        Exams exams = Exams.builder()
                .student(student)
                .examPaper(examPaper)
                .build();

        // 엔티티 저장
        Exams savedExamPS = examsRepository.save(exams);

        // 결과 DTO 생성
        ExamsSaveResponseDTO responseDTO = new ExamsSaveResponseDTO(savedExamPS);

        return responseDTO;


    }
    // 시험 조회 (검색 기능 포함)
    public Page<ExamsPageResponseDTO> page(String searchValue, Pageable pageable) {
        Page<Exams> pageExams;

        // 검색어가 있는 경우
        if (searchValue != null && !searchValue.isEmpty()) {
            pageExams = examsRepository.findByStudentNameContaining(searchValue, pageable);
        } else { // 검색어가 없는 경우, 전체 조회
            pageExams = examsRepository.findAll(pageable);
        }

        // ExamsPageResponseDTO 페이지로 변환하여 반환
        return pageExams.map(exam -> new ExamsPageResponseDTO(exam));
    }

    //시험상세조회
    public ExamsOneResponseDTO one(Long id) {
        Optional<Exams> optionalExam = examsRepository.findById(id);

        // 특정 ID의 시험이 존재하지 않는 경우 404 에러를 발생시킵니다.
        Exams exam = optionalExam.orElseThrow(() ->
                new Exception400(ExceptionMessage.NOT_FOUND_EXAM.getMessage()));

        // ExamsOneResponseDTO로 변환하여 반환
        return new ExamsOneResponseDTO(exam);
    }
}

