package lab.nomad.springbootncsevaluation.model.users;

import jakarta.persistence.*;
import lab.nomad.springbootncsevaluation.model.users._enums.UserRole;
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
@Table(name = "users")
@Entity
public class Users {

    /**
     * <p>users 테이블의 primary key에 해당하는 필드입니다.</p>
     * <p>해당 필드의 타입은 {@link Long} 타입입니다.</p>
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * <p>users 테이블의 name 칼럼에 매핑되는 필드입니다.</p>
     * <p>사용자의 이름을 저장합니다.</p>
     * <p>해당 필드의 타입은 {@link String} 타입입니다.</p>
     */
    private String name;

    /**
     * <p>users 테이블의 username 컬럼에 매핑되는 필드입니다.</p>
     * <p>사용자의 유저네임(아이디)을 저장합니다.</p>
     * <p>해당 필드의 타입은 {@link String} 타입입니다.</p>
     */
    private String username;

    /**
     * <p>users 테이블의 password 컬럼에 매핑되는 필드입니다.</p>
     * <p>사용자의 비밀번호를 저장합니다.</p>
     * <p>해당 필드의 타입은 {@link String} 타입입니다.</p>
     */
    private String password;

    /**
     * <p>users 테이블의 email 컬럼에 매핑되는 필드입니다.</p>
     * <p>사용자의 이메일 주소를 저장합니다.</p>
     * <p>해당 필드의 타입은 {@link String} 타입입니다.</p>
     */
    private String email;

    /**
     * <p>users 테이블의 tel 컬럼에 매핑되는 필드입니다.</p>
     * <p>사용자의 연락처를 저장합니다.</p>
     * <p>해당 필드의 타입은 {@link String} 타입입니다.</p>
     */
    private String tel;

    /**
     * <p>users 테이블의 role 컬럼에 매핑되는 필드입니다.</p>
     * <p>사용자의 역할을 저장합니다.</p>
     * <p>해당 필드의 타입은 {@link UserRole} Enum 타입입니다.</p>
     */
    @Enumerated(EnumType.STRING)
    private UserRole role;

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
    public Users(Long id, String name, String username, String password, String email, String tel, UserRole role,
                LocalDateTime createDate, LocalDateTime updateDate, LocalDateTime deleteDate) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.password = password;
        this.email = email;
        this.tel = tel;
        this.role = role;
        this.createDate = createDate;
        this.updateDate = updateDate;
        this.deleteDate = deleteDate;
    }

}
