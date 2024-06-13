package lab.nomad.springbootncsevaluation.model.exams.results.multiple_items;

import jakarta.persistence.*;
import lab.nomad.springbootncsevaluation.model.exams.papers.multiple_questions.ExamPaperMultipleQuestions;
import lab.nomad.springbootncsevaluation.model.exams.papers.multiple_questions.answers.ExamPaperMultipleQuestionAnswers;
import lab.nomad.springbootncsevaluation.model.exams.results.ExamResults;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;


/**
 * <p>exam_result_multiple_items 테이블과 매핑되는 Entity 클래스입니다.</p>
 * <p>exam_result_multiple_items 테이블에는 시험 결과의 문제에 따른 점수가 몇 점으로 채점되었는지에 대한 정보가 저장되어 있습니다.</p>
 * <p></p>
 * <p>생성자는 기본생성자, 전체 매개변수 생성자를 가지고 있습니다. 또한, 빌드패턴을 사용하여 인스턴스할 수 있습니다.</p>
 *
 * @author NomadHuns
 */
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor
@Table(name = "exam_result_multiple_items")
@Getter
@Entity
public class ExamResultMultipleItems {
    /**
     * <p>exam_result_multiple_items 테이블의 primary key에 해당하는 필드입니다.</p>
     * <p>해당 필드의 타입은 {@link Long} 타입입니다.</p>
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * <p>ExamResultPoint Entity와 OneToOne 관계를 가지는 ExamResult Entity입니다.</p>
     *
     * <p>각 ExamResultPoint는 자신의 ExamResult를 가질 수 있습니다.
     * 이 필드를 통해 ExamResultPoint의 ExamResult 정보에 접근할 수 있습니다.</p>
     * <p>해당 필드의 타입은 {@link ExamResults} 타입입니다.</p>
     */
    @ManyToOne(fetch = FetchType.LAZY)
    private ExamResults examResult;

    /**
     * <p>ExamResultPoint Entity와 OneToOne 관계를 가지는 ExamPaperQuestions Entity입니다.</p>
     *
     * <p>어떤 문제를 풀었는지를 의미하는 칼럼입니다.</p>
     * <p>해당 필드의 타입은 {@link ExamPaperMultipleQuestions} 타입입니다.</p>
     */
    @ManyToOne(fetch = FetchType.LAZY)
    private ExamPaperMultipleQuestions examPaperQuestion;

    /**
     * <p>ExamResultPoint Entity와 OneToOne 관계를 가지는 ExamPaperQuestions Entity입니다.</p>
     *
     * <p>어떤 답을 체크하였는지를 의미하는 칼럽입니다.</p>
     * <p>해당 필드의 타입은 {@link ExamPaperMultipleQuestionAnswers} 타입입니다.</p>
     */
    @ManyToOne(fetch = FetchType.LAZY)
    private ExamPaperMultipleQuestionAnswers examPaperMultipleQuestionAnswers;

    /**
     * <p>exam_result_multiple_items 테이블의 point 컬럼에 매핑되는 필드입니다.</p>
     * <p>해당 평가 내용의 배점을 저장합니다.</p>
     * <p>해당 필드의 타입은 {@link Integer} 타입입니다.</p>
     */
    private Integer point;

    /**
     * <p>exam_result_multiple_items 테이블의 comment 컬럼에 매핑되는 필드입니다.</p>
     * <p>해당 시험 문제의 강사의 평가 내용을 저장합니다.</p>
     * <p>해당 필드의 타입은 {@link String} 타입입니다.</p>
     */
    private String comment;

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
    public ExamResultMultipleItems(Long id, ExamResults examResult, ExamPaperMultipleQuestions examPaperQuestion,
            ExamPaperMultipleQuestionAnswers examPaperMultipleQuestionAnswers, Integer point, String comment,
            LocalDateTime createDate, LocalDateTime updateDate) {
        this.id = id;
        this.examResult = examResult;
        this.examPaperQuestion = examPaperQuestion;
        this.examPaperMultipleQuestionAnswers = examPaperMultipleQuestionAnswers;
        this.point = point;
        this.comment = comment;
        this.createDate = createDate;
        this.updateDate = updateDate;
    }
}
