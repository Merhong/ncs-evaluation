package lab.nomad.springbootncsevaluation.model.students;

import jakarta.persistence.*;
import lab.nomad.springbootncsevaluation.model.courses.Courses;
import lab.nomad.springbootncsevaluation.model.students._enums.StudentStatus;
import lab.nomad.springbootncsevaluation.model.users.Users;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

/**
 * <p>students 테이블과 매핑되는 Entity 클래스입니다.</p>
 * <p>students 테이블에는 학생 이름(실명), 개인정보, 서명 등 학생 정보가 저장되어 있습니다.</p>
 * <p></p>
 * <p>생성자는 기본생성자, 전체 매개변수 생성자를 가지고 있습니다. 또한, 빌드패턴을 사용하여 인스턴스할 수 있습니다.</p>
 *
 * @author NomadHuns
 */
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor
@Table(name = "students")
@Getter
@Entity
public class Students {
    /**
     * <p>students 테이블의 primary key에 해당하는 필드입니다.</p>
     * <p>해당 필드의 타입은 {@link Long} 타입입니다.</p>
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Students Entity와 ManyToOne 관계를 가지는 Courses Entity입니다.
     *
     * <p>각 Students는 특정 Courses에 속합니다.
     * 이 필드를 통해 Students의 소유자인 Courses에 접근할 수 있습니다.</p>
     * <p>해당 필드의 타입은 {@link Courses} 타입입니다.</p>
     */
    @ManyToOne(fetch = FetchType.LAZY)
    private Courses course;

    /**
     * <p>students 테이블의 tel 컬럼에 매핑되는 필드입니다.</p>
     * <p>학생의 연락처를 저장합니다.</p>
     * <p>해당 필드의 타입은 {@link String} 타입입니다.</p>
     */
    private String tel;

    /**
     * <p>students 테이블의 name 컬럼에 매핑되는 필드입니다.</p>
     * <p>학생 사용자의 이름(실명)을 저장합니다.</p>
     * <p>해당 필드의 타입은 {@link String} 타입입니다.</p>
     */
    private String name;

    /**
     * <p>students 테이블의 status 컬럼에 매핑되는 필드입니다.</p>
     * <p>학생의 과정 수강 상태를 저장합니다.</p>
     * <p>해당 필드의 타입은 {@link StudentStatus} 타입입니다.</p>
     */
    @Enumerated(EnumType.STRING)
    private StudentStatus status;

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
     * <p>테이블의 update_date 칼럼에 매핑되는 필드입니다.</p>
     * <p>해당 데이터가 삭제된 일시를 저장합니다.</p>
     * <p>해당 값이 Null이라면 삭제되지 않은 데이터입니다.</p>
     * <p>해당 필드의 타입은 {@link LocalDateTime} 타입입니다.</p>
     */
    private LocalDateTime deleteDate;

    /**
     * <p>전체 매개변수 생성자입니다.</p>
     * <p>빌드 패턴을 사용하여 해당 객체를 인스턴스 할 수 있습니다.</p>
     */
    @Builder
    public Students(Long id, Courses course, String name, String tel, StudentStatus studentStatus,
                   LocalDateTime createDate, LocalDateTime updateDate, LocalDateTime deleteDate) {
        this.id = id;
        this.course = course;
        this.name = name;
        this.tel = tel;
        this.status = studentStatus;
        this.createDate = createDate;
        this.updateDate = updateDate;
        this.deleteDate = deleteDate;
    }
    // 학생 정보를 업데이트하는 메서드
    public void update(String name, String tel, StudentStatus status) {
        // 각 필드를 새 값으로 업데이트
        this.name = name;
        this.tel = tel;
        this.status = status;
    }

    public Students delete() {
        this.deleteDate = LocalDateTime.now();

        return this;
    }


}
