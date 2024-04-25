package lab.nomad.springbootncsevaluation.domain.exams._papers._multiple_questions._answers.service;

import lab.nomad.springbootncsevaluation._core.exception.Exception400;
import lab.nomad.springbootncsevaluation._core.exception.ExceptionMessage;
import lab.nomad.springbootncsevaluation.domain.exams._papers._multiple_questions._answers.dto.ExamPaperMultipleQuestionAnswersSaveRequestDTO;
import lab.nomad.springbootncsevaluation.domain.exams._papers._multiple_questions._answers.dto.ExamPaperMultipleQuestionAnswersSaveResponseDTO;
import lab.nomad.springbootncsevaluation.model.exams.papers.ExamPapers;
import lab.nomad.springbootncsevaluation.model.exams.papers.ExamPapersRepository;
import lab.nomad.springbootncsevaluation.model.exams.papers.multiple_questions.ExamPaperMultipleQuestions;
import lab.nomad.springbootncsevaluation.model.exams.papers.multiple_questions.ExamPaperMultipleQuestionsRepository;
import lab.nomad.springbootncsevaluation.model.exams.papers.multiple_questions.answers.ExamPaperMultipleQuestionAnswers;
import lab.nomad.springbootncsevaluation.model.exams.papers.multiple_questions.answers.ExamPaperMultipleQuestionAnswersRepository;
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
public class ExamPaperMultipleQuestionAnswersService {

    // DI
    private final ExamPaperMultipleQuestionsRepository questionsRepository;
    private final ExamPaperMultipleQuestionAnswersRepository answersRepository;
    private final ExamPapersRepository examPapersRepository;

    @Transactional
    public ExamPaperMultipleQuestionAnswersSaveResponseDTO save(Users user,
            Long id,
            ExamPaperMultipleQuestionAnswersSaveRequestDTO requestDTO) {

        // questionId로 문제 찾기
        ExamPaperMultipleQuestions questionOP = questionsRepository.findById(id)
                .orElseThrow(() -> new Exception400(ExceptionMessage.NOT_FOUND_QUESTION.getMessage()));

        // 가장 마지막 해답 번호 찾기
        int lastNo = answersRepository.findByExamPaperMultipleQuestionId(id)
                .stream()
                .mapToInt(answer -> answer.getNo())
                .max()
                .orElse(0);

        // 유저가 관리자라면 모든 시험지 문제에 해답 등록 가능
        if (user.getRole()
                .equals(UserRole.ROLE_ADMIN)) {
            // 시험지 문제 해답 등록
            ExamPaperMultipleQuestionAnswers answerForSave = ExamPaperMultipleQuestionAnswers.builder()
                    .no(lastNo + 1)
                    .content(requestDTO.getContent())
                    .isCorrect(requestDTO.getIsCorrect())
                    .examPaperQuestion(questionOP)
                    .build();

            // DB 로직 처리
            ExamPaperMultipleQuestionAnswers answerPS = answersRepository.save(answerForSave);

            return new ExamPaperMultipleQuestionAnswersSaveResponseDTO(answerPS);
        } else {
            // 만약 시험지를 등록한 유저가 아니라면 예외처리
            ExamPapers examPaperOP = examPapersRepository.findById(questionOP.getExamPaper()
                                                                           .getId())
                    .orElseThrow(() -> new Exception400(ExceptionMessage.NOT_FOUND_EXAM_PAPER.getMessage()));

            if (examPaperOP.getUser()
                    .getId() != user.getId()) {
                throw new Exception400(ExceptionMessage.NOT_FOUND_EXAM_PAPER.getMessage());
            }

            // 문제에 해답 등록
            ExamPaperMultipleQuestionAnswers answerForSave = ExamPaperMultipleQuestionAnswers.builder()
                    .no(lastNo + 1)
                    .content(requestDTO.getContent())
                    .isCorrect(requestDTO.getIsCorrect())
                    .examPaperQuestion(questionOP)
                    .build();

            // DB 로직 처리
            ExamPaperMultipleQuestionAnswers answerPS = answersRepository.save(answerForSave);

            return new ExamPaperMultipleQuestionAnswersSaveResponseDTO(answerPS);
        }
    }
}
