package lab.nomad.springbootncsevaluation.model.exams.papers.multiple_questions.answers;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface ExamPaperMultipleQuestionAnswersRepository extends
        JpaRepository<ExamPaperMultipleQuestionAnswers, Long> {

    // 마지막 해답 번호를 찾는 쿼리 메소드
    List<ExamPaperMultipleQuestionAnswers> findByExamPaperMultipleQuestionId(Long id);
}
