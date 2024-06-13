package lab.nomad.springbootncsevaluation.domain.exams._results.service;

import lab.nomad.springbootncsevaluation.domain.exams._results.dto.ExamResultsSaveRequestDTO;
import lab.nomad.springbootncsevaluation.domain.exams._results.dto.ExamResultsSaveResponseDTO;
import lab.nomad.springbootncsevaluation.domain.exams.dto.ExamsSaveResponseDTO;
import lab.nomad.springbootncsevaluation.model.exams.Exams;
import lab.nomad.springbootncsevaluation.model.exams.ExamsRepository;
import lab.nomad.springbootncsevaluation.model.exams.papers.multiple_questions.ExamPaperMultipleQuestions;
import lab.nomad.springbootncsevaluation.model.exams.papers.multiple_questions.ExamPaperMultipleQuestionsRepository;
import lab.nomad.springbootncsevaluation.model.exams.papers.multiple_questions.answers.ExamPaperMultipleQuestionAnswersRepository;
import lab.nomad.springbootncsevaluation.model.exams.results.ExamResults;
import lab.nomad.springbootncsevaluation.model.exams.results.ExamResultsRepository;
import lab.nomad.springbootncsevaluation.model.exams.results._enums.ExamResultStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ExamResultsService {
    @Autowired
    private ExamsRepository examsRepository;

    @Autowired
    private ExamResultsRepository examResultsRepository;
    @Autowired
    private ExamPaperMultipleQuestionsRepository examPaperMultipleQuestionsRepository;
    @Autowired
    private ExamPaperMultipleQuestionAnswersRepository examPaperMultipleQuestionAnswersRepository;

    @Transactional
    public ExamResultsSaveResponseDTO save(Long examId, ExamResultsSaveRequestDTO requestDTO) {
        // 시험 ID로 시험 정보 조회
        Exams exam = examsRepository.findById(examId)
                .orElseThrow(() -> new RuntimeException("Exam not found with id: " + examId));

        // 시험 문제 목록 조회
        List<ExamPaperMultipleQuestions> questions = examPaperMultipleQuestionsRepository.findByExamPaperId(exam.getExamPaper().getId());

        // 총 점수 초기화
        int totalPoints = 0;

        // 제출된 답변을 기반으로 총 점수 계산
        for (ExamPaperMultipleQuestions question : questions) {
            // 각 문제에 대해 제출된 답변 ID 가져오기
            Long selectedAnswerId = requestDTO.getSelectedAnswers().get(question.getId());
            // 해당 답변이 정답인지 확인
            boolean isCorrect = examPaperMultipleQuestionAnswersRepository.findById(selectedAnswerId)
                    .map(answer -> answer.getIsCorrect())
                    .orElse(false);

            // 정답일 경우 해당 문제의 배점을 총 점수에 더함
            if (isCorrect) {
                totalPoints += question.getPoint();
            }
        }

        // 시험 결과 엔티티 생성
        ExamResults examResults = ExamResults.builder()
                .exam(exam)
                .totalPoint(totalPoints)
                .status(ExamResultStatus.DONE)
                .createDate(LocalDateTime.now())
                .updateDate(LocalDateTime.now())
                .build();

        // 시험 결과 저장
        examResults = examResultsRepository.save(examResults);

        // 시험 결과 저장 응답 DTO 생성
        return new ExamResultsSaveResponseDTO(examResults);
    }
}