package lab.nomad.springbootncsevaluation.model.exams;

import jakarta.persistence.*;
import lab.nomad.springbootncsevaluation.model.students.Students;
import lab.nomad.springbootncsevaluation.model.exams.papers.ExamPapers;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

/**
 * <p>exams 테이블과 매핑되는 Entity 클래스입니다.</p>
 * <p>exams 테이블에는 해당 학생 어떤 과목을 어떤 시험지로 평가를 진행하였는지에 대한 정보가 저장되어 있습니다.</p>
 * <p></p>
 * <p>생성자는 기본생성자, 전체 매개변수 생성자를 가지고 있습니다. 또한, 빌드패턴을 사용하여 인스턴스할 수 있습니다.</p>
 *
 * @author NomadHuns
 */
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor
@Table(name = "exams")
@Getter
@Entity
public class Exams {
    /**
     * <p>exams 테이블의 primary key에 해당하는 필드입니다.</p>
     * <p>해당 필드의 타입은 {@link Long} 타입입니다.</p>
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Exam Entity와 ManyToOne 관계를 가지는 Student Entity입니다.
     *
     * <p>각 Exam는 특정 Student에 속합니다.
     * 이 필드를 통해 Exam의 소유자인 Student에 접근할 수 있습니다.</p>
     * <p>해당 필드의 타입은 {@link Students} 타입입니다.</p>
     */
    @ManyToOne(fetch = FetchType.LAZY)
    private Students student;

    /**
     * Exam Entity와 ManyToOne 관계를 가지는 ExamPaper Entity입니다.
     *
     * <p>각 Exam는 특정 ExamPaper에 속합니다.
     * 이 필드를 통해 Exam의 소유자인 ExamPaper에 접근할 수 있습니다.</p>
     * <p>해당 필드의 타입은 {@link ExamPapers} 타입입니다.</p>
     */
    @ManyToOne(fetch = FetchType.LAZY)
    private ExamPapers examPaper;

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
    public Exams(Long id, Students student, ExamPapers examPaper,
                LocalDateTime createDate, LocalDateTime updateDate) {
        this.id = id;
        this.student = student;
        this.examPaper = examPaper;
        this.createDate = createDate;
        this.updateDate = updateDate;
    }
}
