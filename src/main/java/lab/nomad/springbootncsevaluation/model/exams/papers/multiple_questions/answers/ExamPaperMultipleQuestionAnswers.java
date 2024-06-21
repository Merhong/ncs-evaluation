package lab.nomad.springbootncsevaluation.model.exams.papers.multiple_questions.answers;

import jakarta.persistence.*;
import lab.nomad.springbootncsevaluation.model.exams.papers.multiple_questions.ExamPaperMultipleQuestions;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor
@Table(name = "exam_paper_multiple_question_answers")
@Getter
@Entity
public class ExamPaperMultipleQuestionAnswers {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private ExamPaperMultipleQuestions examPaperMultipleQuestion;

    private Integer no;
    private String content;
    private Boolean isCorrect;

    @Builder
    public ExamPaperMultipleQuestionAnswers(Long id, ExamPaperMultipleQuestions examPaperQuestion, Integer no,
                                            String content, Boolean isCorrect) {
        this.id = id;
        this.examPaperMultipleQuestion = examPaperQuestion;
        this.no = no;
        this.content = content;
        this.isCorrect = isCorrect;
    }


    // 새로운 setter 메서드 추가
    public void setExamPaperMultipleQuestion(ExamPaperMultipleQuestions examPaperMultipleQuestion) {
        this.examPaperMultipleQuestion = examPaperMultipleQuestion;
    }
}