package lab.nomad.springbootncsevaluation.domain.exams._results._multiple_items.service;

import lab.nomad.springbootncsevaluation._core.exception.Exception400;
import lab.nomad.springbootncsevaluation._core.exception.ExceptionMessage;
import lab.nomad.springbootncsevaluation.domain.exams._results._multiple_items.dto.ExamResultMultipleItemsPageResponseDTO;
import lab.nomad.springbootncsevaluation.domain.exams._results._multiple_items.dto.ExamResultMultipleItemsSaveRequestDTO;
import lab.nomad.springbootncsevaluation.domain.exams._results._multiple_items.dto.ExamResultMultipleItemsSaveResponseDTO;
import lab.nomad.springbootncsevaluation.model.exams.papers.multiple_questions.ExamPaperMultipleQuestions;
import lab.nomad.springbootncsevaluation.model.exams.papers.multiple_questions.ExamPaperMultipleQuestionsRepository;
import lab.nomad.springbootncsevaluation.model.exams.papers.multiple_questions.answers.ExamPaperMultipleQuestionAnswers;
import lab.nomad.springbootncsevaluation.model.exams.papers.multiple_questions.answers.ExamPaperMultipleQuestionAnswersRepository;
import lab.nomad.springbootncsevaluation.model.exams.results.ExamResults;
import lab.nomad.springbootncsevaluation.model.exams.results.ExamResultsRepository;
import lab.nomad.springbootncsevaluation.model.exams.results.multiple_items.ExamResultMultipleItems;
import lab.nomad.springbootncsevaluation.model.exams.results.multiple_items.ExamResultMultipleItemsRepository;
import lab.nomad.springbootncsevaluation.model.users.Users;
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
public class ExamResultMultipleItemsService {

    // DI
    private final ExamResultMultipleItemsRepository itemsRepository;
    private final ExamResultsRepository resultsRepository;
    private final ExamPaperMultipleQuestionsRepository questionsRepository;
    private final ExamPaperMultipleQuestionAnswersRepository answersRepository;

    // 채점 문제 페이지(리스트) 조회
    public ExamResultMultipleItemsPageResponseDTO page(Long resultId, Pageable pageable, String searchValue, Users user) {

        // 검색어 있는 경우
        if (!(searchValue == null)) {
            return new ExamResultMultipleItemsPageResponseDTO(
                    itemsRepository.findByExamResultIdAndCommentContains(resultId, pageable, searchValue));
        }

        // 검색 키워드 없는 경우
        Page<ExamResultMultipleItems> itemsPagePS = itemsRepository.findByExamResultId(resultId, pageable);

        return new ExamResultMultipleItemsPageResponseDTO(itemsPagePS);
    }


    // 문제 채점 등록
    @Transactional
    public ExamResultMultipleItemsSaveResponseDTO save(Long examResultId, Long questionId, Long answerId,
            ExamResultMultipleItemsSaveRequestDTO requestDTO) {

        // ExamResult 조회
        ExamResults examResultOP = resultsRepository.findById(examResultId)
                .orElseThrow(() -> new Exception400(ExceptionMessage.NOT_FOUND_EXAM_RESULT.getMessage()));

        // EPMQ 조회
        ExamPaperMultipleQuestions questionOP = questionsRepository.findById(questionId)
                .orElseThrow(() -> new Exception400(ExceptionMessage.NOT_FOUND_QUESTION.getMessage()));

        // EPMQA 조회
        ExamPaperMultipleQuestionAnswers answerOP = answersRepository.findById(answerId)
                .orElseThrow(() -> new Exception400(ExceptionMessage.NOT_FOUND_QUESTION_ANSWER.getMessage()));

        // DB 로직 처리
        ExamResultMultipleItems itemForSave = ExamResultMultipleItems.builder()
                .point(requestDTO.getPoint())
                .comment(requestDTO.getComment())
                .examResult(examResultOP)
                .examPaperQuestion(questionOP)
                .examPaperMultipleQuestionAnswers(answerOP)
                .build();

        ExamResultMultipleItems itemPS = itemsRepository.save(itemForSave);

        return new ExamResultMultipleItemsSaveResponseDTO(itemPS);
    }

}
