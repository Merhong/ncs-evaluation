package lab.nomad.springbootncsevaluation.domain.exams._papers.dto;

import lab.nomad.springbootncsevaluation.model.ability_units.AbilityUnits;
import lab.nomad.springbootncsevaluation.model.ability_units._enums.ExamType;
import lab.nomad.springbootncsevaluation.model.exams.papers.ExamPapers;
import lab.nomad.springbootncsevaluation.model.exams.papers.multiple_questions.ExamPaperMultipleQuestions;
import lab.nomad.springbootncsevaluation.model.exams.papers.multiple_questions.answers.ExamPaperMultipleQuestionAnswers;
import lab.nomad.springbootncsevaluation.model.users.Users;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class ExamPaperOneResponseDTO {
    private ExamPapersDTO examPaper;

    public ExamPaperOneResponseDTO(ExamPapers examPaper, List<ExamPaperMultipleQuestions> questionList,
                                   List<ExamPaperMultipleQuestionAnswers> answerList) {
        this.examPaper = new ExamPapersDTO(examPaper, questionList, answerList);
    }

    @Getter
    public static class ExamPapersDTO {
        private Long id;
        private String name;
        private ExamType examType;
        private LocalDateTime createDate;
        private LocalDateTime updateDate;
        private UserDTO userDTO;
        private AbilityUnitDTO abilityUnitDTO;
        private List<ExamPaperMultipleQuestionDTO> questionList;


        public ExamPapersDTO(ExamPapers examPaper, List<ExamPaperMultipleQuestions> questionList,
                             List<ExamPaperMultipleQuestionAnswers> answerList) {
            this.id = examPaper.getId();
            this.name = examPaper.getName();
            this.examType = examPaper.getExamType();
            this.createDate = examPaper.getCreateDate();
            this.updateDate = examPaper.getUpdateDate();
            this.userDTO = new UserDTO(examPaper.getUser());
            this.abilityUnitDTO = new AbilityUnitDTO(examPaper.getAbilityUnit());

            this.questionList = questionList.stream()
                    .map(question -> {
                        var filteredAnswerList = answerList.stream()
                                .filter(answer -> answer.getExamPaperMultipleQuestion()
                                        .getId() == question.getId())
                                .toList();

                        return new ExamPaperMultipleQuestionDTO(question, filteredAnswerList);
                    })
                    .toList();
        }
    }

    @Getter
    public static class ExamPaperMultipleQuestionDTO {
        private Long id;
        private Integer no;
        private String content;
        private Integer point;
        private LocalDateTime createDate;
        private LocalDateTime updateDate;
        private List<ExamPaperMultipleQuestionAnswerDTO> answerList;

        public ExamPaperMultipleQuestionDTO(ExamPaperMultipleQuestions question,
                                            List<ExamPaperMultipleQuestionAnswers> answerList) {
            this.id = question.getId();
            this.no = question.getNo();
            this.content = question.getContent();
            this.point = question.getPoint();
            this.createDate = question.getCreateDate();
            this.updateDate = question.getUpdateDate();
            this.answerList = answerList.stream()
                    .map(answer -> new ExamPaperMultipleQuestionAnswerDTO(answer))
                    .toList();
        }
    }

    @Getter
    public static class ExamPaperMultipleQuestionAnswerDTO {
        private Long id;
        private Integer no;
        private String content;
        private Boolean isCorrect;


        public ExamPaperMultipleQuestionAnswerDTO(ExamPaperMultipleQuestionAnswers answer) {
            this.id = answer.getId();
            this.no = answer.getNo();
            this.content = answer.getContent();
            this.isCorrect = answer.getIsCorrect();
        }
    }

    @Getter
    public static class UserDTO {
        private Long id;
        private String name;
        private String username;
        private String email;
        private String tel;
        private String role;
        private LocalDateTime createDate;
        private LocalDateTime updateDate;

        public UserDTO(Users user) {
            this.id = user.getId();
            this.name = user.getName();
            this.username = user.getUsername();
            this.email = user.getEmail();
            this.tel = user.getTel();
            this.role = user.getRole()
                    .getText();
            this.createDate = user.getCreateDate();
            this.updateDate = user.getUpdateDate();
        }
    }

    @Getter
    public static class AbilityUnitDTO {
        private Long id;
        private String code;
        private String name;
        private String purpose;
        private Integer grade;
        private Integer totalTime;
        private List<String> examTypeList;
        private LocalDateTime createDate;

        public AbilityUnitDTO(AbilityUnits abilityUnit) {
            this.id = abilityUnit.getId();
            this.code = abilityUnit.getCode();
            this.name = abilityUnit.getName();
            this.purpose = abilityUnit.getPurpose();
            this.grade = abilityUnit.getGrade();
            this.totalTime = abilityUnit.getTotalTime();
            this.examTypeList = abilityUnit.getExamTypeList()
                    .stream()
                    .map(examType -> examType.name())
                    .toList();
            this.createDate = abilityUnit.getCreateDate();
        }
    }
}
