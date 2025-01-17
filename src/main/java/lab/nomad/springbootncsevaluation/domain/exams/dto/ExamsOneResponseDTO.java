package lab.nomad.springbootncsevaluation.domain.exams.dto;

import lab.nomad.springbootncsevaluation.model.ability_units.AbilityUnits;
import lab.nomad.springbootncsevaluation.model.ability_units._enums.ExamType;
import lab.nomad.springbootncsevaluation.model.exams.Exams;
import lab.nomad.springbootncsevaluation.model.exams._enums.ExamStatus;
import lab.nomad.springbootncsevaluation.model.exams.papers.ExamPapers;
import lab.nomad.springbootncsevaluation.model.students.Students;
import lab.nomad.springbootncsevaluation.model.students._enums.StudentStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ExamsOneResponseDTO {
    private ExamsDTO exams;
    public ExamsOneResponseDTO(Exams exams){this.exams= new ExamsDTO(exams);}


    @Getter
    @Setter
    public  static class ExamsDTO {
        private long id;
        private StudentsDTO students;
        private ExamPaperDTO examPapers;
        private ExamStatus status;

        public ExamsDTO(Exams exams) {
            this.id = exams.getId();
            this.students = new StudentsDTO(exams.getStudent());
            this.examPapers = new ExamPaperDTO(exams.getExamPaper());
            this.status = exams.getStatus();
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
