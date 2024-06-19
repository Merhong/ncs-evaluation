package lab.nomad.springbootncsevaluation.model.exams.papers.multiple_questions;

import jakarta.persistence.*;
import lab.nomad.springbootncsevaluation.model.exams.papers.ExamPapers;
import lab.nomad.springbootncsevaluation.model.exams.papers.multiple_questions.answers.ExamPaperMultipleQuestionAnswers;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>exam_paper_questions 테이블과 매핑되는 Entity 클래스입니다.</p>
 * <p>exam_paper_questions 테이블에는 평가 항목 내용(ex> 로그인 페이지를 구현할 수 있다.), 평가 항목 배점 등의 평가항목 정보가 저장되어 있습니다.</p>
 * <p></p>
 * <p>생성자는 기본생성자, 전체 매개변수 생성자를 가지고 있습니다. 또한, 빌드패턴을 사용하여 인스턴스할 수 있습니다.</p>
 *
 * @author NomadHuns
 */
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor
@Table(name = "exam_paper_multiple_questions")
@Getter
@Entity
public class ExamPaperMultipleQuestions {
    /**
     * <p>exam_paper_questions 테이블의 primary key에 해당하는 필드입니다.</p>
     * <p>해당 필드의 타입은 {@link Long} 타입입니다.</p>
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer no;

    /**
     * <p>exam_paper_questions 테이블의 content 컬럼에 매핑되는 필드입니다.</p>
     * <p>평가 항목 내용 (ex> 로그인 페이지를 구현할 수 있다.)을 저장합니다.</p>
     * <p>해당 필드의 타입은 {@link String} 타입입니다.</p>
     */
    private String content;

    /**
     * <p>exam_paper_questions 테이블의 point 컬럼에 매핑되는 필드입니다.</p>
     * <p>평가 항목 배점을 저장합니다.</p>
     * <p>해당 필드의 타입은 {@link Integer} 타입입니다.</p>
     */
    private Integer point;

    /**
     * <p>exam_paper_questions 테이블의 comment 컬럼에 매핑되는 필드입니다.</p>
     * <p>강사의 평가 내용을 저장합니다.</p>
     * <p>해당 필드의 타입은 {@link String} 타입입니다.</p>
     */
    private String comment;

    /**
     * ExamPaperQuestion Entity와 ManyToOne 관계를 가지는 ExamPaper Entity입니다.
     *
     * <p>각 ExamPaperQuestion는 특정 ExamPaper에 속합니다.
     * 이 필드를 통해 ExamPaperQuestion의 소유자인 ExamPaper에 접근할 수 있습니다.</p>
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
    @OneToMany(mappedBy = "examPaperMultipleQuestion", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<ExamPaperMultipleQuestionAnswers> answers;


    /**
     * <p>전체 매개변수 생성자입니다.</p>
     * <p>빌드 패턴을 사용하여 해당 객체를 인스턴스 할 수 있습니다.</p>
     */
    @Builder
    public ExamPaperMultipleQuestions(Long id, Integer no, String content, Integer point, String comment,
                                      ExamPapers examPaper, LocalDateTime createDate, LocalDateTime updateDate, List<ExamPaperMultipleQuestionAnswers> answers) {
        this.id = id;
        this.no = no;
        this.content = content;
        this.point = point;
        this.comment = comment;
        this.examPaper = examPaper;
        this.createDate = createDate;
        this.updateDate = updateDate;
        this.answers = answers;
    }
}