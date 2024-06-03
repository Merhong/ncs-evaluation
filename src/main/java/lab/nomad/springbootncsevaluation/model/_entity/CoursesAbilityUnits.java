package lab.nomad.springbootncsevaluation.model._entity;


import jakarta.persistence.*;
import lab.nomad.springbootncsevaluation.model.ability_units.AbilityUnits;
import lab.nomad.springbootncsevaluation.model.courses.Courses;
import lab.nomad.springbootncsevaluation.model.users.Users;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor
@Getter
@Table(name = "courses_ability_units")
@Entity
public class CoursesAbilityUnits {
    /**
     * <p>CoursesAbilityUnits 테이블의 primary key에 해당하는 필드입니다.</p>
     * <p>해당 필드의 타입은 {@link Long} 타입입니다.</p>
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * CoursesAbilityUnits Entity와 ManyToOne 관계를 가지는 Courses Entity입니다.
     *
     * <p>각 CoursesAbilityUnits 특정 Courses에 속합니다.
     * 이 필드를 통해 CoursesAbilityUnits의 소유자인 Courses에 접근할 수 있습니다.</p>
     * <p>해당 필드의 타입은 {@link Users} 타입입니다.</p>
     */
    @ManyToOne(fetch = FetchType.LAZY)
    private Courses course;

    /**
     * CoursesAbilityUnits Entity와 ManyToOne 관계를 가지는 AbilityUnits Entity입니다.
     *
     * <p>각 CoursesAbilityUnits 특정 AbilityUnits에 속합니다.
     * 이 필드를 통해 CoursesAbilityUnits의 소유자인 AbilityUnits에 접근할 수 있습니다.</p>
     * <p>해당 필드의 타입은 {@link Users} 타입입니다.</p>
     */
    @ManyToOne(fetch = FetchType.LAZY)
    private AbilityUnits abilityUnit;

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
    public CoursesAbilityUnits(Long id, Courses course, AbilityUnits abilityUnit, LocalDateTime createDate,
            LocalDateTime updateDate) {
        this.id = id;
        this.course = course;
        this.abilityUnit = abilityUnit;
        this.createDate = createDate;
        this.updateDate = updateDate;
    }
}
