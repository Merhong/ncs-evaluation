package lab.nomad.springbootncsevaluation.domain.exams._results._multiple_items.dto;

import lab.nomad.springbootncsevaluation.model.ability_units.AbilityUnits;
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
import lab.nomad.springbootncsevaluation.model.users.Users;
import lombok.Getter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Stream;

@Getter
public class ExamResultMultipleItemsPageResponseDTO {
    private List<ExamResultMultipleItemsDTO> items;
    private PageableDTO pageable;

    // 생성자
    public ExamResultMultipleItemsPageResponseDTO(Page<ExamResultMultipleItems> itemsPage) {
        this.items = itemsPage.getContent()
                .stream()
                .map(ExamResultMultipleItemsDTO::new)
                .toList();
        this.pageable = new PageableDTO(itemsPage);
    }

    @Getter
    public static class ExamResultMultipleItemsDTO {
        private Long id;
        private Integer point;
        private String comment;
        private LocalDateTime cretedate;
        private LocalDateTime updatedate;
        private ExamResultsDTO examResultDTO;
        private List<ExamPaperMultipleQuestionsDTO> questionDTO;
        private List<ExamPaperMultipleQuestionAnswersDTO> answerDTO;

        // 생성자
        ExamResultMultipleItemsDTO(ExamResultMultipleItems item) {
            this.id = item.getId();
            this.point = item.getPoint();
            this.comment = item.getComment();
            this.cretedate = item.getCreateDate();
            this.updatedate = item.getUpdateDate();
            this.examResultDTO = new ExamResultsDTO(item.getExamResult());
            this.questionDTO = Stream.of(item.getExamPaperQuestion())
                    .map(ExamPaperMultipleQuestionsDTO::new)
                    .toList();
            this.answerDTO = Stream.of(item.getExamPaperMultipleQuestionAnswers())
                    .map(ExamPaperMultipleQuestionAnswersDTO::new)
                    .toList();
        }
    }

    @Getter
    public static class ExamResultsDTO {
        private Long id;
        private Integer totalPoint;
        private Integer grade;
        private String comment;
        private ExamResultStatus status;
        private ExamsDTO examsDTO;

        public ExamResultsDTO(ExamResults examResults) {
            this.id = examResults.getId();
            this.totalPoint = examResults.getTotalPoint();
            this.grade = examResults.getGrade();
            this.comment = examResults.getComment();
            this.status = examResults.getStatus();
            this.examsDTO = new ExamsDTO(examResults.getExam());
        }

        @Getter
        public static class ExamsDTO {
            private Long id;
            private LocalDateTime createDate;
            private LocalDateTime updateDate;
            private StudentsDTO studentsDTO;
            private ExamPapersDTO examPapersDTO;

            public ExamsDTO(Exams exams) {
                this.id = exams.getId();
                this.createDate = exams.getCreateDate();
                this.updateDate = exams.getUpdateDate();
                this.studentsDTO = new StudentsDTO(exams.getStudent());
                this.examPapersDTO = new ExamPapersDTO(exams.getExamPaper());
            }

            @Getter
            public static class StudentsDTO {
                private Long id;
                private String name;
                private String tel;
                private StudentStatus status;

                public StudentsDTO(Students students) {
                    this.id = students.getId();
                    this.name = students.getName();
                    this.tel = students.getTel();
                    this.status = students.getStatus();
                }
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

                public ExamPapersDTO(ExamPapers examPaper) {
                    this.id = examPaper.getId();
                    this.name = examPaper.getName();
                    this.examType = examPaper.getExamType();
                    this.createDate = examPaper.getCreateDate();
                    this.updateDate = examPaper.getUpdateDate();
                    this.userDTO = new UserDTO(examPaper.getUser());
                    this.abilityUnitDTO = new AbilityUnitDTO(examPaper.getAbilityUnit());
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
        }
    }

    @Getter
    public static class ExamPaperMultipleQuestionsDTO {
        private Long id;
        private Integer no;
        private String content;
        private Integer point;
        private String comment;

        // 생성자
        public ExamPaperMultipleQuestionsDTO(ExamPaperMultipleQuestions examPaperMultipleQuestion) {
            this.id = examPaperMultipleQuestion.getId();
            this.no = examPaperMultipleQuestion.getNo();
            this.content = examPaperMultipleQuestion.getContent();
            this.point = examPaperMultipleQuestion.getPoint();
            this.comment = examPaperMultipleQuestion.getComment();
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

    @Getter
    public static class PageableDTO {
        private int pageNumber;
        private int pageSize;
        private int totalPages;
        private long totalElements;
        private boolean last;
        private int numberOfElements;
        private boolean empty;
        private Sort sort;

        public PageableDTO(Page<ExamResultMultipleItems> itemsPage) {
            this.pageNumber = itemsPage.getNumber();
            this.pageSize = itemsPage.getSize();
            this.totalPages = itemsPage.getTotalPages();
            this.totalElements = itemsPage.getTotalElements();
            this.last = itemsPage.isLast();
            this.numberOfElements = itemsPage.getNumberOfElements();
            this.empty = itemsPage.isEmpty();
            this.sort = itemsPage.getSort();
        }
    }
}

