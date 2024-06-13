package lab.nomad.springbootncsevaluation.domain.exams._results._multiple_items.dto;

import lab.nomad.springbootncsevaluation.model.ability_units._enums.ExamType;
import lab.nomad.springbootncsevaluation.model.exams.Exams;
import lab.nomad.springbootncsevaluation.model.exams.papers.ExamPapers;
import lab.nomad.springbootncsevaluation.model.exams.papers.multiple_questions.ExamPaperMultipleQuestions;
import lab.nomad.springbootncsevaluation.model.exams.papers.multiple_questions.answers.ExamPaperMultipleQuestionAnswers;
import lab.nomad.springbootncsevaluation.model.exams.results.ExamResults;
import lab.nomad.springbootncsevaluation.model.exams.results._enums.ExamResultStatus;
import lab.nomad.springbootncsevaluation.model.exams.results.multiple_items.ExamResultMultipleItems;
import lab.nomad.springbootncsevaluation.model.students.Students;
import lab.nomad.springbootncsevaluation.model.students._enums.StudentStatus;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ExamResultMultipleItemsSaveResponseDTO {
    private ExamResultMultipleItemsDTO examResultMultipleItem;

    // 생성자
    public ExamResultMultipleItemsSaveResponseDTO(ExamResultMultipleItems item) {
        this.examResultMultipleItem = new ExamResultMultipleItemsDTO(item);
    }

    @Getter
    public static class ExamResultMultipleItemsDTO {
        private Long id;
        private Integer point;
        private String comment;
        private LocalDateTime createDate;
        private LocalDateTime updateDate;
        private ExamResultsDTO examResultDTO;
        private ExamPaperMultipleQuestionsDTO questionDTO;
        private ExamPaperMultipleQuestionAnswersDTO answerDTO;

        public ExamResultMultipleItemsDTO(ExamResultMultipleItems item) {
            this.id = item.getId();
            this.point = item.getPoint();
            this.comment = item.getComment();
            this.createDate = item.getCreateDate();
            this.updateDate = item.getUpdateDate();
            this.examResultDTO = new ExamResultsDTO(item.getExamResult());
            this.questionDTO = new ExamPaperMultipleQuestionsDTO(item.getExamPaperQuestion());
            this.answerDTO = new ExamPaperMultipleQuestionAnswersDTO(item.getExamPaperMultipleQuestionAnswers());
        }
    }

    @Getter
    public static class ExamResultsDTO {
        private Long id;
        private Integer totalPoint;
        private Integer grade;
        private String comment;
        private ExamResultStatus status;
        private LocalDateTime createDate;
        private LocalDateTime updateDate;

        public ExamResultsDTO(ExamResults examResults) {
            this.id = examResults.getId();
            this.totalPoint = examResults.getTotalPoint();
            this.grade = examResults.getGrade();
            this.comment = examResults.getComment();
            this.status = examResults.getStatus();
            this.createDate = examResults.getCreateDate();
            this.updateDate = examResults.getUpdateDate();
        }
    }

    @Getter
    public static class ExamPaperMultipleQuestionsDTO {
        private Long id;
        private Integer no;
        private String content;
        private Integer point;
        private String comment;
        private LocalDateTime createDate;
        private LocalDateTime updateDate;

        // 생성자
        public ExamPaperMultipleQuestionsDTO(ExamPaperMultipleQuestions examPaperMultipleQuestion) {
            this.id = examPaperMultipleQuestion.getId();
            this.no = examPaperMultipleQuestion.getNo();
            this.content = examPaperMultipleQuestion.getContent();
            this.point = examPaperMultipleQuestion.getPoint();
            this.comment = examPaperMultipleQuestion.getComment();
            this.createDate = examPaperMultipleQuestion.getCreateDate();
            this.updateDate = examPaperMultipleQuestion.getUpdateDate();
        }
    }

    @Getter
    public static class ExamPaperMultipleQuestionAnswersDTO {
        private Long id;
        private Integer no;
        private String content;
        private Boolean isCorrect;

        // 생성자
        public ExamPaperMultipleQuestionAnswersDTO(ExamPaperMultipleQuestionAnswers examPaperMultipleQuestionAnswer) {
            this.id = examPaperMultipleQuestionAnswer.getId();
            this.no = examPaperMultipleQuestionAnswer.getNo();
            this.content = examPaperMultipleQuestionAnswer.getContent();
            this.isCorrect = examPaperMultipleQuestionAnswer.getIsCorrect();
        }
    }
}
