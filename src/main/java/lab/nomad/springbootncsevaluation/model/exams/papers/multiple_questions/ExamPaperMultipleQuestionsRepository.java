package lab.nomad.springbootncsevaluation.model.exams.papers.multiple_questions;

import lab.nomad.springbootncsevaluation.model.exams.papers.ExamPapers;
import lab.nomad.springbootncsevaluation.model.exams.papers.multiple_questions.answers.ExamPaperMultipleQuestionAnswers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ExamPaperMultipleQuestionsRepository extends JpaRepository<ExamPaperMultipleQuestions, Long> {

    // 시험지의 문제들을 찾는 쿼리 메소드
    List<ExamPaperMultipleQuestions> findAllByExamPaper(ExamPapers examPaper);

    // 마지막 문제 번호를 찾는 쿼리 메소드
    List<ExamPaperMultipleQuestions> findByExamPaperId(Long id);

    @Query("SELECT a FROM ExamPaperMultipleQuestionAnswers a WHERE a.examPaperMultipleQuestion.id = :questionId")
    List<ExamPaperMultipleQuestionAnswers> findAnswersByQuestionId(@Param("questionId") Long questionId);
}

