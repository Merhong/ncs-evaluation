package lab.nomad.springbootncsevaluation.domain.exams._papers._multiple_questions.service;

import lab.nomad.springbootncsevaluation._core.exception.Exception400;
import lab.nomad.springbootncsevaluation._core.exception.ExceptionMessage;
import lab.nomad.springbootncsevaluation.domain.exams._papers._multiple_questions.dto.ExamPaperMultipleQuestionsSaveRequestDTO;
import lab.nomad.springbootncsevaluation.domain.exams._papers._multiple_questions.dto.ExamPaperMultipleQuestionsSaveResponseDTO;
import lab.nomad.springbootncsevaluation.model.exams.papers.ExamPapers;
import lab.nomad.springbootncsevaluation.model.exams.papers.ExamPapersRepository;
import lab.nomad.springbootncsevaluation.model.exams.papers.multiple_questions.ExamPaperMultipleQuestions;
import lab.nomad.springbootncsevaluation.model.exams.papers.multiple_questions.ExamPaperMultipleQuestionsRepository;
import lab.nomad.springbootncsevaluation.model.users.Users;
import lab.nomad.springbootncsevaluation.model.users._enums.UserRole;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class ExamPaperMultipleQuestionsService {

    // DI
    private final ExamPapersRepository examPapersRepository;
    private final ExamPaperMultipleQuestionsRepository questionsRepository;

    @Transactional
    public ExamPaperMultipleQuestionsSaveResponseDTO save(Users user, Long id,
                                                          ExamPaperMultipleQuestionsSaveRequestDTO requestDTO) {

        // examPaperId로 시험지 찾기
        ExamPapers examPaperOP = examPapersRepository.findById(id)
                .orElseThrow(() -> new Exception400(ExceptionMessage.NOT_FOUND_EXAM_PAPER.getMessage()));

        // 가장 마지막 문제 번호 찾기
        int lastNo = questionsRepository.findByExamPaperId(id)
                .stream()
                .mapToInt(question -> question.getNo())
                .max()
                .orElse(0);

        // 유저가 관리자라면 진행
        if (user.getRole()
                .equals(UserRole.ROLE_ADMIN)) {
            // 시험지 문제 등록
            ExamPaperMultipleQuestions examPaperMultipleQuestionForSave = ExamPaperMultipleQuestions.builder()
                    .no(lastNo + 1)
                    .content(requestDTO.getContent())
                    .point(requestDTO.getPoint())
                    .comment(requestDTO.getComment())
                    .examPaper(examPaperOP)
                    .build();

            // DB 로직 처리
            ExamPaperMultipleQuestions examPaperMultipleQuestionPS = questionsRepository.save(
                    examPaperMultipleQuestionForSave);

            return new ExamPaperMultipleQuestionsSaveResponseDTO(examPaperMultipleQuestionPS);
        } else {
            // 만약 시험지를 등록한 유저가 아니라면 예외처리
            if (examPaperOP.getUser()
                    .getId() != user.getId()) {
                throw new Exception400(ExceptionMessage.NOT_FOUND_EXAM_PAPER.getMessage());
            }

            // 시험문제 등록
            ExamPaperMultipleQuestions examPaperMultipleQuestionForSave = ExamPaperMultipleQuestions.builder()
                    .no(lastNo + 1)
                    .content(requestDTO.getContent())
                    .point(requestDTO.getPoint())
                    .comment(requestDTO.getComment())
                    .examPaper(examPaperOP)
                    .build();

            // DB 로직 처리
            ExamPaperMultipleQuestions examPaperMultipleQuestionPS = questionsRepository.save(
                    examPaperMultipleQuestionForSave);

            return new ExamPaperMultipleQuestionsSaveResponseDTO(examPaperMultipleQuestionPS);
        }
    }
}
