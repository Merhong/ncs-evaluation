package lab.nomad.springbootncsevaluation._core.dev;

import lab.nomad.springbootncsevaluation.model.users.Users;
import lab.nomad.springbootncsevaluation.model.users.UsersRepository;
import lab.nomad.springbootncsevaluation.model.users._enums.UserRole;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class InitData {
    private final UsersRepository usersRepository;

    public void initUser() {
        // 여러개의 User 객체를 담을 리스트 생성
        List<Users> userList = new ArrayList<>();

        // 저장할 엔티티 객체 인스턴스
        Users user = Users.builder()
                .username("admin")
                .password("$2a$10$/CtsnN1nV2qms0WIPP1s6.Ml0c09K0QGU/nEqkIuy1.WrENsLuQxK")
                .email("admin@naver.com")
                .tel("010-1111-2222")
                .name("어드민")
                .role(UserRole.ROLE_ADMIN)
                .build();

        // 리스트에 해당 객체 저장
        userList.add(user);

        // 저장할 엔티티 객체 인스턴스
        user = Users.builder()
                .username("ssar")
                .password("$2a$10$/CtsnN1nV2qms0WIPP1s6.Ml0c09K0QGU/nEqkIuy1.WrENsLuQxK")
                .email("ssar@naver.com")
                .tel("010-1234-5789")
                .name("김교사")
                .role(UserRole.ROLE_TEACHER)
                .build();

        // 리스트에 해당 객체 저장
        userList.add(user);

        // 저장할 엔티티 객체 인스턴스
        user = Users.builder()
                .username("cos")
                .password("$2a$10$/CtsnN1nV2qms0WIPP1s6.Ml0c09K0QGU/nEqkIuy1.WrENsLuQxK")
                .email("cos@naver.com")
                .tel("010-4444-5789")
                .name("한교사")
                .role(UserRole.ROLE_TEACHER)
                .build();

        // 리스트에 해당 객체 저장
        userList.add(user);

        // 저장할 엔티티 객체 인스턴스
        user = Users.builder()
                .username("love")
                .password("$2a$10$/CtsnN1nV2qms0WIPP1s6.Ml0c09K0QGU/nEqkIuy1.WrENsLuQxK")
                .email("love@naver.com")
                .tel("010-4444-5789")
                .name("냥직원")
                .role(UserRole.ROLE_EMP)
                .build();

        // 리스트에 해당 객체 저장
        userList.add(user);

        // 리스트에 담긴 엔티티 객체 모두 DB에 저장
        usersRepository.saveAll(userList);
    }
}
