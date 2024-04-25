package lab.nomad.springbootncsevaluation.model.exams.papers.multiple_questions;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExamPaperMultipleQuestionsRepository extends JpaRepository<ExamPaperMultipleQuestions, Long> {

    // 마지막 문제 번호를 찾는 쿼리 메소드
    List<ExamPaperMultipleQuestions> findByExamPaperId(Long id);
}
