package lab.nomad.springbootncsevaluation.model.exams.papers.multiple_questions.answers;

import lab.nomad.springbootncsevaluation.model.exams.papers.multiple_questions.ExamPaperMultipleQuestions;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface ExamPaperMultipleQuestionAnswersRepository extends
        JpaRepository<ExamPaperMultipleQuestionAnswers, Long> {

    // 문제에 대한 답안들을 찾는 쿼리 메소드
    List<ExamPaperMultipleQuestionAnswers> findAllByExamPaperMultipleQuestion(ExamPaperMultipleQuestions question);

    // 마지막 답안 번호를 찾는 쿼리 메소드
    List<ExamPaperMultipleQuestionAnswers> findByExamPaperMultipleQuestionId(Long id);
}
