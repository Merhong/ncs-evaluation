package lab.nomad.springbootncsevaluation.model.ability_units;

import jakarta.persistence.*;
import lab.nomad.springbootncsevaluation._core.utils.ExamTypeStringArrayConverter;
import lab.nomad.springbootncsevaluation.model.ability_units._enums.ExamType;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>ability_units 테이블과 매핑되는 Entity 클래스입니다.</p>
 * <p>ability_units 테이블에는 능력단위코드, 능력단위명 등의 능력단위 정보가 저장되어 있습니다.</p>
 * <p></p>
 * <p>생성자는 기본생성자, 전체 매개변수 생성자를 가지고 있습니다. 또한, 빌드패턴을 사용하여 인스턴스할 수 있습니다.</p>
 *
 * @author NomadHuns
 */
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor
@Getter
@Table(name = "ability_units")
@Entity
public class AbilityUnits {
    /**
     * <p>ability_units 테이블의 primary key에 해당하는 필드입니다.</p>
     * <p>해당 필드의 타입은 {@link Long} 타입입니다.</p>
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * <p>ability_units 테이블의 code 컬럼에 매핑되는 필드입니다.</p>
     * <p>능력단위코드를 저장합니다.</p>
     * <p>해당 필드의 타입은 {@link String} 타입입니다.</p>
     */
    private String code;

    /**
     * <p>ability_units 테이블의 name 컬럼에 매핑되는 필드입니다.</p>
     * <p>능력단위명을 저장합니다.</p>
     * <p>해당 필드의 타입은 {@link String} 타입입니다.</p>
     */
    private String name;

    /**
     * <p>ability_units 테이블의 purpose 컬럼에 매핑되는 필드입니다.</p>
     * <p>능력단위정의(훈련목표)를 저장합니다.</p>
     * <p>해당 필드의 타입은 {@link String} 타입입니다.</p>
     */
    private String purpose;

    /**
     * <p>ability_units 테이블의 grade 컬럼에 매핑되는 필드입니다.</p>
     * <p>해당 능력단위의 수준를 저장합니다.</p>
     * <p>해당 필드의 타입은 {@link Integer} 타입입니다.</p>
     */
    private Integer grade;

    /**
     * <p>ability_units 테이블의 total_time 컬럼에 매핑되는 필드입니다.</p>
     * <p>해당 능력단위의 훈련 시간을 저장합니다.</p>
     * <p>해당 필드의 타입은 {@link Integer} 타입입니다.</p>
     */
    private Integer totalTime;

    /**
     * <p>ability_units 테이블의 exam_type_list 컬럼에 매핑되는 필드입니다.</p>
     * <p>해당 능력단위가 수행할 수 있는 평가방법들을 저장합니다.</p>
     * <p>DB에 리스트가 저장될 때에는 ,를 구분자로 사용하여 하나의 칼럼에 저장됩니다.</p>
     * <p>해당 필드의 타입은 {@link List}<{@link ExamType}> 타입입니다.</p>
     */
    @Convert(converter = ExamTypeStringArrayConverter.class)
    private List<ExamType> examTypeList = new ArrayList<>(); // 평가방법은 한 개 이상

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
    public AbilityUnits(Long id, String code, String name, String purpose,
                       Integer grade, Integer totalTime, List<ExamType> examTypeList,
                       LocalDateTime createDate, LocalDateTime updateDate) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.purpose = purpose;
        this.grade = grade;
        this.totalTime = totalTime;
        this.examTypeList = examTypeList;
        this.createDate = createDate;
        this.updateDate = updateDate;
    }

    public AbilityUnits update(String code, String name, String purpose,
                  Integer grade, Integer totalTime, List<String> examTypeList) {
        this.code = code;
        this.name = name;
        this.purpose = purpose;
        this.grade = grade;
        this.totalTime = totalTime;
        this.examTypeList = examTypeList.stream().map(s -> ExamType.valueOf(s)).toList();

        return this;
    }
}