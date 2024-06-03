package lab.nomad.springbootncsevaluation.domain.exams._papers.dto;

import lab.nomad.springbootncsevaluation.model.ability_units.AbilityUnits;
import lab.nomad.springbootncsevaluation.model.ability_units._enums.ExamType;
import lab.nomad.springbootncsevaluation.model.exams.papers.ExamPapers;
import lab.nomad.springbootncsevaluation.model.users.Users;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class ExamPaperUpdateResponseDTO {
    private ExamPapersDTO examPaper;

    // 생성자
    public ExamPaperUpdateResponseDTO(ExamPapers examPaper) {
        this.examPaper = new ExamPapersDTO(examPaper);
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
