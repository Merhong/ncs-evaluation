package lab.nomad.springbootncsevaluation._core.dev;

import lab.nomad.springbootncsevaluation.model.ability_units.AbilityUnits;
import lab.nomad.springbootncsevaluation.model.ability_units.AbilityUnitsRepository;
import lab.nomad.springbootncsevaluation.model.ability_units._enums.ExamType;
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
    private final AbilityUnitsRepository abilityUnitsRepository;

    public void initAbilityUnit() {
        // 여러개의 AbilityUnit 객체를 담을 리스트 생성
        List<AbilityUnits> abilityUnitList = new ArrayList<>();

        // 평가 방법 리스트 생성
        List<ExamType> examTypeList = new ArrayList<>();
        examTypeList.add(ExamType.MULTIPLE_CHOICE);

        // 저장할 AbilityUnit 엔티티 객체 인스턴스
        AbilityUnits abilityUnit = AbilityUnits.builder()
                .name("요구사항 확인")
                .code("2000102345_19v4")
                .purpose("요구사항 확인이란 업무 분석가가 수집,분석,정의한 요구사항과 이에 따른 분석모델에 대해서 확인과 현행 시스템에 대해 분석하는 능력이다.")
                .examTypeList(examTypeList)
                .grade(5)
                .totalTime(60)
                .build();
        // 리스트에 해당 객체 저장
        abilityUnitList.add(abilityUnit);

        // 평가 방법 리스트 생성
        examTypeList = new ArrayList<>();
        examTypeList.add(ExamType.MULTIPLE_CHOICE);
        examTypeList.add(ExamType.TASK);

        // 저장할 AbilityUnit 엔티티 객체 인스턴스
        abilityUnit = AbilityUnits.builder()
                .name("데이터 입출력 구현")
                .code("2001020205_19v5")
                .purpose("데이터 입출력 구현이란 응용소프트웨어가 다루어야 하는 데이터 및 이들간의 연관성, 제약조건을 식별하여 논리적으로 조직화하는 능력이다.")
                .examTypeList(examTypeList)
                .grade(5)
                .totalTime(60)
                .build();
        // 리스트에 해당 객체 저장
        abilityUnitList.add(abilityUnit);

        // 저장할 AbilityUnit 엔티티 객체 인스턴스
        abilityUnit = AbilityUnits.builder()
                .name("통합 구현")
                .code("2001020206_19v5")
                .purpose("통합 구현이란 응용소프트웨어가 다루어야 하는 데이터 및 이들간의 연관성, 제약조건을 식별하여 논리적으로 조직화하는 능력이다.")
                .examTypeList(examTypeList)
                .grade(7)
                .totalTime(80)
                .build();
        // 리스트에 해당 객체 저장
        abilityUnitList.add(abilityUnit);

        // 저장할 AbilityUnit 엔티티 객체 인스턴스
        abilityUnit = AbilityUnits.builder()
                .name("정보시스템 이행")
                .code("2001020208_19v4")
                .purpose("정보시스템 이행이란 응용소프트웨어가 다루어야 하는 데이터 및 이들간의 연관성, 제약조건을 식별하여 논리적으로 조직화하는 능력이다.")
                .examTypeList(examTypeList)
                .grade(4)
                .totalTime(40)
                .build();
        // 리스트에 해당 객체 저장
        abilityUnitList.add(abilityUnit);

        // 저장할 AbilityUnit 엔티티 객체 인스턴스
        abilityUnit = AbilityUnits.builder()
                .name("서버프로그램 구현")
                .code("2001020211_19v5")
                .purpose("서버프로그램 구현이란 응용소프트웨어가 다루어야 하는 데이터 및 이들간의 연관성, 제약조건을 식별하여 논리적으로 조직화하는 능력이다.")
                .examTypeList(examTypeList)
                .grade(6)
                .totalTime(60)
                .build();
        // 리스트에 해당 객체 저장
        abilityUnitList.add(abilityUnit);

        // 저장할 AbilityUnit 엔티티 객체 인스턴스
        abilityUnit = AbilityUnits.builder()
                .name("인터페이스 구현")
                .code("2001020212_19v5")
                .purpose("인터페이스 구현이란 응용소프트웨어가 다루어야 하는 데이터 및 이들간의 연관성, 제약조건을 식별하여 논리적으로 조직화하는 능력이다.")
                .examTypeList(examTypeList)
                .grade(3)
                .totalTime(20)
                .build();
        // 리스트에 해당 객체 저장
        abilityUnitList.add(abilityUnit);

        // 저장할 AbilityUnit 엔티티 객체 인스턴스
        abilityUnit = AbilityUnits.builder()
                .name("애플리케이션 배포")
                .code("2001020214_19v5")
                .purpose("애플리케이션 배포이란 응용소프트웨어가 다루어야 하는 데이터 및 이들간의 연관성, 제약조건을 식별하여 논리적으로 조직화하는 능력이다.")
                .examTypeList(examTypeList)
                .grade(7)
                .totalTime(80)
                .build();
        // 리스트에 해당 객체 저장
        abilityUnitList.add(abilityUnit);

        // 저장할 AbilityUnit 엔티티 객체 인스턴스
        abilityUnit = AbilityUnits.builder()
                .name("애플리케이션 리팩토링")
                .code("2001020217_19v5")
                .purpose("애플리케이션 리팩토링이란 응용소프트웨어가 다루어야 하는 데이터 및 이들간의 연관성, 제약조건을 식별하여 논리적으로 조직화하는 능력이다.")
                .examTypeList(examTypeList)
                .grade(2)
                .totalTime(20)
                .build();
        // 리스트에 해당 객체 저장
        abilityUnitList.add(abilityUnit);

        // 저장할 AbilityUnit 엔티티 객체 인스턴스
        abilityUnit = AbilityUnits.builder()
                .name("인터페이스 설계")
                .code("2001020218_19v5")
                .purpose("인터페이스 설계이란 응용소프트웨어가 다루어야 하는 데이터 및 이들간의 연관성, 제약조건을 식별하여 논리적으로 조직화하는 능력이다.")
                .examTypeList(examTypeList)
                .grade(4)
                .totalTime(40)
                .build();
        // 리스트에 해당 객체 저장
        abilityUnitList.add(abilityUnit);

        // 저장할 AbilityUnit 엔티티 객체 인스턴스
        abilityUnit = AbilityUnits.builder()
                .name("애플리케이션 요구사항 분석")
                .code("2001020219_19v5")
                .purpose("애플리케이션 요구사항 분석이란 응용소프트웨어가 다루어야 하는 데이터 및 이들간의 연관성, 제약조건을 식별하여 논리적으로 조직화하는 능력이다.")
                .examTypeList(examTypeList)
                .grade(5)
                .totalTime(50)
                .build();
        // 리스트에 해당 객체 저장
        abilityUnitList.add(abilityUnit);

        // 저장할 AbilityUnit 엔티티 객체 인스턴스
        abilityUnit = AbilityUnits.builder()
                .name("기능 모델링")
                .code("2001020220_19v2")
                .purpose("기능 모델링이란 응용소프트웨어가 다루어야 하는 데이터 및 이들간의 연관성, 제약조건을 식별하여 논리적으로 조직화하는 능력이다.")
                .examTypeList(examTypeList)
                .grade(5)
                .totalTime(50)
                .build();
        // 리스트에 해당 객체 저장
        abilityUnitList.add(abilityUnit);

        // 저장할 AbilityUnit 엔티티 객체 인스턴스
        abilityUnit = AbilityUnits.builder()
                .name("애플리케이션 설계")
                .code("2001020221_19v5")
                .purpose("애플리케이션 설계이란 응용소프트웨어가 다루어야 하는 데이터 및 이들간의 연관성, 제약조건을 식별하여 논리적으로 조직화하는 능력이다.")
                .examTypeList(examTypeList)
                .grade(5)
                .totalTime(50)
                .build();
        // 리스트에 해당 객체 저장
        abilityUnitList.add(abilityUnit);

        // 리스트에 담긴 엔티티 객체 모두 DB에 저장
        abilityUnitsRepository.saveAll(abilityUnitList);
    }

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
