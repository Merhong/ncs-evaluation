package lab.nomad.springbootncsevaluation.model.exams.papers;

import jakarta.persistence.*;
import lab.nomad.springbootncsevaluation.model.ability_units.AbilityUnits;
import lab.nomad.springbootncsevaluation.model.ability_units._enums.ExamType;
import lab.nomad.springbootncsevaluation.model.users.Users;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

/**
 * <p>exam_papers 테이블과 매핑되는 Entity 클래스입니다.</p>
 * <p>exam_papers 테이블에는 평가지명, 평가할 능력 단위, 평가지 생성 사용자 등의 평가지 정보가 저장되어 있습니다.</p>
 * <p></p>
 * <p>생성자는 기본생성자, 전체 매개변수 생성자를 가지고 있습니다. 또한, 빌드패턴을 사용하여 인스턴스할 수 있습니다.</p>
 *
 * @author NomadHuns
 */
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor
@Getter
@Table(name = "exam_papers")
@Entity
public class ExamPapers {
    /**
     * <p>exam_papers 테이블의 primary key에 해당하는 필드입니다.</p>
     * <p>해당 필드의 타입은 {@link Long} 타입입니다.</p>
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * ExamPaper Entity와 ManyToOne 관계를 가지는 AbilityUnit Entity입니다.
     *
     * <p>각 ExamPaper는 특정 AbilityUnit에 속합니다.
     * 이 필드를 통해 ExamPaper의 소유자인 AbilityUnit에 접근할 수 있습니다.</p>
     * <p>해당 필드의 타입은 {@link AbilityUnits} 타입입니다.</p>
     */
    @ManyToOne(fetch = FetchType.LAZY)
    private AbilityUnits abilityUnit;

    /**
     * ExamPapers Entity와 ManyToOne 관계를 가지는 Users Entity입니다.
     *
     * <p>각 ExamPapers는 특정 Users에 속합니다.
     * 이 필드를 통해 ExamPapers의 소유자인 Usesr에 접근할 수 있습니다.</p>
     * <p>해당 필드의 타입은 {@link Users} 타입입니다.</p>
     */
    @ManyToOne(fetch = FetchType.LAZY)
    private Users user;

    /**
     * <p>exam_papers 테이블의 name 컬럼에 매핑되는 필드입니다.</p>
     * <p>평가지 이름(평가지명)을 저장합니다.</p>
     * <p>해당 필드의 타입은 {@link String} 타입입니다.</p>
     */
    private String name;

    /**
     * <p>exam_papers 테이블의 exam_type 컬럼에 매핑되는 필드입니다.</p>
     * <p>학원 등록 상태를 저장합니다.</p>
     * <p>해당 필드의 타입은 {@link ExamType} 타입입니다.</p>
     */
    @Enumerated(EnumType.STRING)
    private ExamType examType;

    /**
     * <p>테이블의 create_date 칼럼에 매핑되는 필드입니다.</p>
     * <p>해당 데이터의 생성일시를 저장합니다.</p>
     * <p>해당 필드의 타입은 {@link LocalDateTime} 타입입니다.</p>
     */
    @CreatedDate
    private LocalDateTime createDate;

    /**
     * <p>테이블의 update_date 칼럼에 매핑되는 필드입니다.</p>
     * <p>해당 데이터의 가장 최근 수정일시를 저장합니다.</p>
     * <p>해당 필드의 타입은 {@link LocalDateTime} 타입입니다.</p>
     */
    @LastModifiedDate
    private LocalDateTime updateDate;

    /**
     * <p>테이블의 delete_date 칼럼에 매핑되는 필드입니다.</p>
     * <p>해당 데이터의 삭제일시를 저장합니다.</p>
     * <p>해당 필드의 타입은 {@link LocalDateTime} 타입입니다.</p>
     */
    private LocalDateTime deleteDate;

    /**
     * <p>전체 매개변수 생성자입니다.</p>
     * <p>빌드 패턴을 사용하여 해당 객체를 인스턴스 할 수 있습니다.</p>
     */
    @Builder
    public ExamPapers(Long id, AbilityUnits abilityUnit, Users user, String name, ExamType examType,
                      LocalDateTime createDate, LocalDateTime updateDate, LocalDateTime deleteDate) {
        this.id = id;
        this.abilityUnit = abilityUnit;
        this.user = user;
        this.name = name;
        this.examType = examType;
        this.createDate = createDate;
        this.updateDate = updateDate;
        this.deleteDate = deleteDate;
    }


    // 수정시 ExamType이 소문자로 입력됐을경우 대문자로 변환해서 넣어준다.
    public ExamPapers update(String name, String examType) {
        this.name = name;
        this.examType = ExamType.valueOf(examType.toUpperCase());

        return this;
    }

    public ExamPapers delete() {
        this.deleteDate = LocalDateTime.now();

        return this;
    }

    // 수정시 ExamType이 소문자로 입력됐을경우 대문자로 변환해서 넣어준다.
    public ExamPapers update(String name, String examType) {
        this.name = name;
        this.examType = ExamType.valueOf(examType.toUpperCase());

        return this;
    }

    public ExamPapers delete() {
        this.deleteDate = LocalDateTime.now();

        return this;
    }


}
