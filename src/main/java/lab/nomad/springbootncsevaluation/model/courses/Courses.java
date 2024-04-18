package lab.nomad.springbootncsevaluation.model.courses;

import jakarta.persistence.*;
import lab.nomad.springbootncsevaluation.model.users.Users;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

/**
 * <p>courses 테이블과 매핑되는 Entity 클래스입니다.</p>
 * <p>courses 테이블에는 과정명, 학원명 등의 과정에 대한 정보가 저장되어 있습니다.</p>
 * <p></p>
 * <p>생성자는 기본생성자, 전체 매개변수 생성자를 가지고 있습니다. 또한, 빌드패턴을 사용하여 인스턴스할 수 있습니다.</p>
 *
 * @author NomadHuns
 */
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor
@Getter
@Table(name = "courses")
@Entity
public class Courses {
    /**
     * <p>courses 테이블의 primary key에 해당하는 필드입니다.</p>
     * <p>해당 필드의 타입은 {@link Long} 타입입니다.</p>
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Courses Entity와 ManyToOne 관계를 가지는 Users Entity입니다.
     *
     * <p>각 Courses는 특정 Users에 속합니다.
     * 이 필드를 통해 Courses의 소유자인 Usesr에 접근할 수 있습니다.</p>
     * <p>해당 필드의 타입은 {@link Users} 타입입니다.</p>
     */
    @ManyToOne(fetch = FetchType.LAZY)
    private Users user;

    /**
     * <p>courses 테이블의 name 컬럼에 매핑되는 필드입니다.</p>
     * <p>과정명을 저장합니다.</p>
     * <p>해당 필드의 타입은 {@link String} 타입입니다.</p>
     */
    @Column(nullable = false)
    private String name;

    /**
     * <p>courses 테이블의 academy_name 컬럼에 매핑되는 필드입니다.</p>
     * <p>학원명을 저장합니다.</p>
     * <p>해당 필드의 타입은 {@link String} 타입입니다.</p>
     */
    @Column(nullable = false)
    private String academyName;

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
     * <p>전체 매개변수 생성자입니다.</p>
     * <p>빌드 패턴을 사용하여 해당 객체를 인스턴스 할 수 있습니다.</p>
     */
    private LocalDateTime deleteDate;
    @Builder
    public Courses(Long id, Users user, String name, String academyName,
                   LocalDateTime createDate, LocalDateTime updateDate, LocalDateTime deleteDate) {
        this.id = id;
        this.user = user;
        this.name = name;
        this.academyName = academyName;
        this.createDate = createDate;
        this.updateDate = updateDate;
        this.deleteDate = deleteDate;
    }

}