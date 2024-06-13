package lab.nomad.springbootncsevaluation.model.exams.results;

import jakarta.persistence.*;
import lab.nomad.springbootncsevaluation.model.exams.Exams;
import lab.nomad.springbootncsevaluation.model.exams.results._enums.ExamResultStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

/**
 * <p>exam_results 테이블과 매핑되는 Entity 클래스입니다.</p>
 * <p>exam_results 테이블에는 해당 시험의 총점, 등급 등의 시험 결과 정보가 저장되어 있습니다.</p>
 * <p></p>
 * <p>생성자는 기본생성자, 전체 매개변수 생성자를 가지고 있습니다. 또한, 빌드패턴을 사용하여 인스턴스할 수 있습니다.</p>
 *
 * @author NomadHuns
 */
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor
@Table(name = "exam_results")
@Getter
@Entity
public class ExamResults {
    /**
     * <p>exam_results 테이블의 primary key에 해당하는 필드입니다.</p>
     * <p>해당 필드의 타입은 {@link Long} 타입입니다.</p>
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * <p>ExamResult Entity와 OneToOne 관계를 가지는 Exam Entity입니다.</p>
     *
     * <p>각 ExamResult는 자신의 Exam를 가질 수 있습니다.
     * 이 필드를 통해 ExamResult의 Exam 정보에 접근할 수 있습니다.</p>
     * <p>해당 필드의 타입은 {@link Exams} 타입입니다.</p>
     */
    @OneToOne(fetch = FetchType.LAZY)
    private Exams exam;

    /**
     * <p>exam_results 테이블의 total_point 컬럼에 매핑되는 필드입니다.</p>
     * <p>해당 시험의 평가 후 총 점수를 저장합니다.</p>
     * <p>해당 필드의 타입은 {@link Integer} 타입입니다.</p>
     */
    private Integer totalPoint;

    /**
     * <p>exam_results 테이블의 grade 컬럼에 매핑되는 필드입니다.</p>
     * <p>해당 시험의 평가 후 성취수준을 저장합니다.</p>
     * <p>해당 필드의 타입은 {@link Integer} 타입입니다.</p>
     */
    private Integer grade;

    /**
     * <p>exam_results 테이블의 comment 컬럼에 매핑되는 필드입니다.</p>
     * <p>해당 시험의 평가 후 강사의 평가총평을 저장합니다.</p>
     * <p>해당 필드의 타입은 {@link String} 타입입니다.</p>
     */
    private String comment;

    /**
     * <p>exam_results 테이블의 pass_status 컬럼에 매핑되는 필드입니다.</p>
     * <p>해당 시험의 평가 상태를 저장합니다.</p>
     * <p>해당 필드의 타입은 {@link ExamResultStatus} 타입입니다.</p>
     */
    @Enumerated(EnumType.STRING)
    private ExamResultStatus status;

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

    @Builder
    public ExamResults(Long id, Exams exam, Integer totalPoint,
                      Integer grade, String comment,ExamResultStatus status,
                      LocalDateTime createDate, LocalDateTime updateDate) {
        this.id = id;
        this.exam = exam;
        this.totalPoint = totalPoint;
        this.grade = grade;
        this.comment = comment;
        this.status = status;
        this.createDate = createDate;
        this.updateDate = updateDate;
    }
}
