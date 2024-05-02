package lab.nomad.springbootncsevaluation.domain.exams.service;


import jakarta.persistence.Id;
import lab.nomad.springbootncsevaluation._core.exception.Exception400;
import lab.nomad.springbootncsevaluation._core.exception.ExceptionMessage;
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
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

}

