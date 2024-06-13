package lab.nomad.springbootncsevaluation.domain.exams._results.dto;


import lab.nomad.springbootncsevaluation.domain.exams.dto.ExamsOneResponseDTO;
import lab.nomad.springbootncsevaluation.model.ability_units._enums.ExamType;
import lab.nomad.springbootncsevaluation.model.exams.Exams;
import lab.nomad.springbootncsevaluation.model.exams.papers.ExamPapers;
import lab.nomad.springbootncsevaluation.model.exams.results.ExamResults;
import lab.nomad.springbootncsevaluation.model.exams.results._enums.ExamResultStatus;
import lab.nomad.springbootncsevaluation.model.students.Students;
import lab.nomad.springbootncsevaluation.model.students._enums.StudentStatus;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ExamResultsSaveResponseDTO {
    private  ExamresultsDTO examresults;
    public  ExamResultsSaveResponseDTO(ExamResults examResults){this.examresults= new ExamresultsDTO(examResults);}

    @Getter
    @Setter
    public  static  class ExamresultsDTO{
        private Long id;
        private  Integer totalPoint;
        private  Integer grade;
        private  String comment;
        private ExamResultStatus status;
        private LocalDateTime createDate;
        private LocalDateTime updateDate;
        private ExamsDTO exams;

        public  ExamresultsDTO(ExamResults examResults){
            this.id = examResults.getId();
            this.totalPoint = examResults.getTotalPoint();
            this.grade = examResults.getGrade();
            this.comment = examResults.getComment();
            this.status = examResults.getStatus();
            this.createDate = examResults.getCreateDate();
            this.updateDate = examResults.getUpdateDate();

        }
        @Getter
        public  static  class  ExamsDTO{
            private  Long id;
            private LocalDateTime createDate;
            private LocalDateTime updateDate;
            private StudentsDTO students;
            private ExamPaperDTO examPapers;

            public ExamsDTO(Exams exams) {
                this.id = exams.getId();
                this.createDate = exams.getCreateDate();
                this.updateDate = exams.getUpdateDate();
                this.students = new StudentsDTO(exams.getStudent());
                this.examPapers = new ExamPaperDTO(exams.getExamPaper());
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

            public static class ExamPaperDTO {
                private Long id;
                private String name;
                private ExamType examType;
                private String abilityUnitName;

                public ExamPaperDTO(ExamPapers examPapers) {
                    this.id = examPapers.getId();
                    this.name = examPapers.getName();
                    this.examType = examPapers.getExamType();
                    this.abilityUnitName = examPapers.getAbilityUnit().getName();
                }

            }

        }



    }
}
