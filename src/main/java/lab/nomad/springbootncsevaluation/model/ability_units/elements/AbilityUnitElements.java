package lab.nomad.springbootncsevaluation.model.ability_units.elements;

import jakarta.persistence.*;
import lab.nomad.springbootncsevaluation.model.ability_units.AbilityUnits;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

/**
 * <p>ability_unit_elements 테이블과 매핑되는 Entity 클래스입니다.</p>
 * <p>ability_unit_elements 테이블에는 능력단위요소명의 능력단위요소 정보가 저장되어 있습니다.</p>
 * <p></p>
 * <p>생성자는 기본생성자, 전체 매개변수 생성자를 가지고 있습니다. 또한, 빌드패턴을 사용하여 인스턴스할 수 있습니다.</p>
 *
 * @author NomadHuns
 */
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor
@Getter
@Table(name = "ability_unit_elements")
@Entity
public class AbilityUnitElements {
    /**
     * <p>ability_unit_elements 테이블의 primary key에 해당하는 필드입니다.</p>
     * <p>해당 필드의 타입은 {@link Long} 타입입니다.</p>
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * <p>ability_unit_elements 테이블의 name 컬럼에 매핑되는 필드입니다.</p>
     * <p>능력단위요소명을 저장합니다.</p>
     * <p>해당 필드의 타입은 {@link String} 타입입니다.</p>
     */
    private String name;

    /**
     * <p>ability_unit_elements 테이블의 code 컬럼에 매핑되는 필드입니다.</p>
     * <p>능력단위요소코드를 저장합니다.</p>
     * <p>해당 필드의 타입은 {@link String} 타입입니다.</p>
     */
    private String code;

    /**
     * AbilityUnitElement Entity와 ManyToOne 관계를 가지는 AbilityUnit Entity입니다.
     *
     * <p>각 AbilityUnitElement는 특정 AbilityUnit에 속합니다.
     * 이 필드를 통해 AbilityUnitElement의 소유자인 AbilityUnit에 접근할 수 있습니다.</p>
     * <p>해당 필드의 타입은 {@link AbilityUnits} 타입입니다.</p>
     */
    @ManyToOne(fetch = FetchType.EAGER)
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

    /**
     * <p>전체 매개변수 생성자입니다.</p>
     * <p>빌드 패턴을 사용하여 해당 객체를 인스턴스 할 수 있습니다.</p>
     */
    @Builder
    public AbilityUnitElements(Long id, String name, String code, AbilityUnits abilityUnit,
                              LocalDateTime createDate, LocalDateTime updateDate) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.abilityUnit = abilityUnit;
        this.createDate = createDate;
        this.updateDate = updateDate;
    }
}
