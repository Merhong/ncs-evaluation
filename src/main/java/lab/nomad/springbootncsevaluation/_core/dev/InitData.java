package lab.nomad.springbootncsevaluation._core.dev;

import lab.nomad.springbootncsevaluation.domain.students.StudentsRepository;
import lab.nomad.springbootncsevaluation.model.ability_units.AbilityUnits;
import lab.nomad.springbootncsevaluation.model.ability_units.AbilityUnitsRepository;
import lab.nomad.springbootncsevaluation.model.ability_units._enums.ExamType;
import lab.nomad.springbootncsevaluation.model.ability_units.elements.AbilityUnitElements;
import lab.nomad.springbootncsevaluation.model.ability_units.elements.AbilityUnitElementsRepository;
import lab.nomad.springbootncsevaluation.model.ability_units.elements.items.AbilityUnitElementItems;
import lab.nomad.springbootncsevaluation.model.ability_units.elements.items.AbilityUnitElementItemsRepository;
import lab.nomad.springbootncsevaluation.model.courses.Courses;
import lab.nomad.springbootncsevaluation.model.courses.CoursesRepository;
import lab.nomad.springbootncsevaluation.model.students.Students;
import lab.nomad.springbootncsevaluation.model.students._enums.StudentStatus;
import lab.nomad.springbootncsevaluation.model.users.Users;
import lab.nomad.springbootncsevaluation.model.users.UsersRepository;
import lab.nomad.springbootncsevaluation.model.users._enums.UserRole;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class InitData {
    private final UsersRepository usersRepository;
    private final AbilityUnitsRepository abilityUnitsRepository;
    private final AbilityUnitElementsRepository abilityUnitElementsRepository;
    private final AbilityUnitElementItemsRepository abilityUnitElementItemsRepository;
    private final CoursesRepository coursesRepository;
    private final StudentsRepository studentsRepository;

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

        // 저장할 엔티티 객체 인스턴스
        user = Users.builder()
                .username("guest")
                .password("$2a$10$/CtsnN1nV2qms0WIPP1s6.Ml0c09K0QGU/nEqkIuy1.WrENsLuQxK")
                .email("guest@naver.com")
                .tel("010-4444-5710")
                .name("게스트")
                .role(UserRole.ROLE_GUEST)
                .build();

        // 리스트에 해당 객체 저장
        userList.add(user);

        // 리스트에 담긴 엔티티 객체 모두 DB에 저장
        usersRepository.saveAll(userList);
    }

    public void initAbilityUnitElement() {
        // 여러개의 AbilityUnitElement 객체를 담을 리스트 생성
        List<AbilityUnitElements> abilityUnitElementList = new ArrayList<>();

        // 할당할 AbilityUnit DB로 부터 조회
        Optional<AbilityUnits> abilityUnitOP = abilityUnitsRepository.findById(1L);

        // 해당 아이디 값의 AbilityUnit이 존재할 경우 실행
        if (abilityUnitOP.isPresent()) {
            AbilityUnits abilityUnitPS = abilityUnitOP.get();

            // 저장할 AbilityUnitElement 엔티티 객체 인스턴스
            AbilityUnitElements abilityUnitElement = AbilityUnitElements.builder()
                    .abilityUnit(abilityUnitPS)
                    .code("test1")
                    .name("현행 시스템 분석하기")
                    .build();
            // 리스트에 해당 객체 저장
            abilityUnitElementList.add(abilityUnitElement);

            // 저장할 AbilityUnitElement 엔티티 객체 인스턴스
            abilityUnitElement = AbilityUnitElements.builder()
                    .abilityUnit(abilityUnitPS)
                    .code("test2")
                    .name("요구 사항 확인하기")
                    .build();

            // 리스트에 해당 객체 저장
            abilityUnitElementList.add(abilityUnitElement);

            // 저장할 AbilityUnitElement 엔티티 객체 인스턴스
            abilityUnitElement = AbilityUnitElements.builder()
                    .abilityUnit(abilityUnitPS)
                    .code("test3")
                    .name("분석모델 확인하기")
                    .build();

            // 리스트에 해당 객체 저장
            abilityUnitElementList.add(abilityUnitElement);
        }

        // 리스트에 담긴 엔티티 객체 모두 DB에 저장
        abilityUnitElementsRepository.saveAll(abilityUnitElementList);
    }

    public void initAbilityUnitElementItem() {
        // 여러개의 AbilityUnitElementItem 객체를 담을 리스트 생성
        List<AbilityUnitElementItems> abilityUnitElementItemList = new ArrayList<>();

        // 할당할 AbilityUnitElement DB로 부터 조회
        Optional<AbilityUnitElements> abilityUnitElementOP = abilityUnitElementsRepository.findById(1L);

        // 해당 아이디 값의 AbilityUnitElement이 존재할 경우 실행
        if (abilityUnitElementOP.isPresent()) {
            AbilityUnitElements abilityUnitElement = abilityUnitElementOP.get();

            // 저장할 AbilityUnitElementItem 엔티티 객체 인스턴스
            AbilityUnitElementItems abilityUnitElementItem = AbilityUnitElementItems.builder()
                    .content("개발하고자 하는 응용소프트웨어에 대한 이해를 높이기 위해, 현행 시스템의 적용 현황을 파악함으로써 개발 범위와 향후 개발될 시스템으로의 이행 방향성을 분석할 수 있다.")
                    .abilityUnitElement(abilityUnitElement)
                    .build();
            // 리스트에 해당 객체 저장
            abilityUnitElementItemList.add(abilityUnitElementItem);

            // 저장할 AbilityUnitElementItem 엔티티 객체 인스턴스
            abilityUnitElementItem = AbilityUnitElementItems.builder()
                    .content("개발하고자 하는 응용소프트웨어와 관련된 운영체제, 데이터베이스 관리 시스템, 미들웨어 등의 요구사항을 식별할 수 있다.")
                    .abilityUnitElement(abilityUnitElement)
                    .build();
            // 리스트에 해당 객체 저장
            abilityUnitElementItemList.add(abilityUnitElementItem);

            // 저장할 AbilityUnitElementItem 엔티티 객체 인스턴스
            abilityUnitElementItem = AbilityUnitElementItems.builder()
                    .content("현행 시스템을 분석하여, 개발하고자 하는 응용소프트웨어가 이후 적용될 목표시스템을 명확하고 구체적으로 기술할 수 있다.")
                    .abilityUnitElement(abilityUnitElement)
                    .build();
            // 리스트에 해당 객체 저장
            abilityUnitElementItemList.add(abilityUnitElementItem);
        }

        // 할당할 AbilityUnitElement DB로 부터 조회
        abilityUnitElementOP = abilityUnitElementsRepository.findById(2L);

        // 해당 아이디 값의 AbilityUnitElement이 존재할 경우 실행
        if (abilityUnitElementOP.isPresent()) {
            AbilityUnitElements abilityUnitElement = abilityUnitElementOP.get();

            // 저장할 AbilityUnitElementItem 엔티티 객체 인스턴스
            AbilityUnitElementItems abilityUnitElementItem = AbilityUnitElementItems.builder()
                    .content("소프트웨어 공학기술의 요구사항 분석 기법을 활용하여 업무 분석가가 정의한 응용소프트웨어의 요구사항을 확인할 수 있다.")
                    .abilityUnitElement(abilityUnitElement)
                    .build();
            // 리스트에 해당 객체 저장
            abilityUnitElementItemList.add(abilityUnitElementItem);

            // 저장할 AbilityUnitElementItem 엔티티 객체 인스턴스
            abilityUnitElementItem = AbilityUnitElementItems.builder()
                    .content("업무 분석가가 분석한 요구사항에 대해 정의된 검증 기준과 절차에 따라서 요구사항을 확인할 수 있다.")
                    .abilityUnitElement(abilityUnitElement)
                    .build();
            // 리스트에 해당 객체 저장
            abilityUnitElementItemList.add(abilityUnitElementItem);

            // 저장할 AbilityUnitElementItem 엔티티 객체 인스턴스
            abilityUnitElementItem = AbilityUnitElementItems.builder()
                    .content("업무 분석가가 수집하고 분석한 요구사항이 개발하고자 하는 응용소프트웨어에 미칠 영향에 대해서 검토하고 확인할 수 있다.")
                    .abilityUnitElement(abilityUnitElement)
                    .build();
            // 리스트에 해당 객체 저장
            abilityUnitElementItemList.add(abilityUnitElementItem);
        }

        // 할당할 AbilityUnitElement DB로 부터 조회
        abilityUnitElementOP = abilityUnitElementsRepository.findById(3L);

        // 해당 아이디 값의 AbilityUnitElement이 존재할 경우 실행
        if (abilityUnitElementOP.isPresent()) {
            AbilityUnitElements abilityUnitElement = abilityUnitElementOP.get();

            // 저장할 AbilityUnitElementItem 엔티티 객체 인스턴스
            AbilityUnitElementItems abilityUnitElementItem = AbilityUnitElementItems.builder()
                    .content("소프트웨어 공학기술의 요구사항 분석 기법을 활용하여 업무 분석가가 제시한 분석모델에 대해서 확인할 수 있다.")
                    .abilityUnitElement(abilityUnitElement)
                    .build();
            // 리스트에 해당 객체 저장
            abilityUnitElementItemList.add(abilityUnitElementItem);

            // 저장할 AbilityUnitElementItem 엔티티 객체 인스턴스
            abilityUnitElementItem = AbilityUnitElementItems.builder()
                    .content("업무 분석가가 제시한 분석모델이 개발할 응용소프트웨어에 미칠 영향을 검토하여 기술적인 타당성 조사를 할 수 있다.")
                    .abilityUnitElement(abilityUnitElement)
                    .build();
            // 리스트에 해당 객체 저장
            abilityUnitElementItemList.add(abilityUnitElementItem);

            // 저장할 AbilityUnitElementItem 엔티티 객체 인스턴스
            abilityUnitElementItem = AbilityUnitElementItems.builder()
                    .content("업무 분석가가 제시한 분석모델에 대해서 응용소프트웨어를 개발하기 위해 필요한 추가적인 의견을 제시할 수 있다.")
                    .abilityUnitElement(abilityUnitElement)
                    .build();
            // 리스트에 해당 객체 저장
            abilityUnitElementItemList.add(abilityUnitElementItem);
        }

        // 리스트에 담긴 엔티티 객체 모두 DB에 저장
        abilityUnitElementItemsRepository.saveAll(abilityUnitElementItemList);
    }

    public void initCourse() {
        // 여러개의 Course 객체를 담을 리스트 생성
        List<Courses> courseList = new ArrayList<>();

        // 할당할 Users DB로 부터 조회
        Optional<Users> usersOP1 = usersRepository.findById(1L);
        Optional<Users> usersOP2 = usersRepository.findById(2L);
        Optional<Users> usersOP3 = usersRepository.findById(3L);
        Optional<Users> usersOP4 = usersRepository.findById(4L);

        // 해당 아이디 값의 User가 존재할 경우 실행
        if(usersOP1.isPresent()) {
            Users userPS1 = usersOP1.get();

            // 저장할 Course 엔티티 객체 인스턴스
            // 저장할 엔티티 객체 인스턴스
            Courses course = Courses.builder()
                                    .name("AB1과정")
                                    .academyName("AAA학원")
                                    .user(userPS1)
                                    .build();

            // 리스트에 해당 객체 저장
            courseList.add(course);
        }

        // 해당 아이디 값의 User가 존재할 경우 실행
        if(usersOP2.isPresent()) {
            Users userPS1 = usersOP1.get();
            Users userPS2 = usersOP2.get();

            // 저장할 Course 엔티티 객체 인스턴스
            // 저장할 엔티티 객체 인스턴스
            Courses course = Courses.builder()
                                    .name("B1과정")
                                    .academyName("ssar학원")
                                    .user(userPS2)
                                    .build();

            Courses course1 = Courses.builder()
                                    .name("B2과정")
                                    .academyName("ssar학원")
                                    .user(userPS2)
                                    .build();

            Courses course2 = Courses.builder()
                                     .name("AA과정")
                                     .academyName("AAA학원")
                                     .user(userPS1)
                                     .build();
            // 리스트에 해당 객체 저장
            courseList.add(course);
            courseList.add(course1);
            courseList.add(course2);
        }

        // 해당 아이디 값의 User가 존재할 경우 실행
        if(usersOP3.isPresent()) {
            Users userPS3 = usersOP3.get();

            // 저장할 Course 엔티티 객체 인스턴스
            // 저장할 엔티티 객체 인스턴스
            Courses course = Courses.builder()
                                    .name("C5과정")
                                    .academyName("cos학원")
                                    .user(userPS3)
                                    .build();

            // 리스트에 해당 객체 저장
            courseList.add(course);
        }

        // 해당 아이디 값의 User가 존재할 경우 실행
        if(usersOP4.isPresent()) {
            Users userPS4 = usersOP4.get();

            // 저장할 Course 엔티티 객체 인스턴스
            // 저장할 엔티티 객체 인스턴스
            Courses course = Courses.builder()
                                    .name("D6과정")
                                    .academyName("love학원")
                                    .user(userPS4)
                                    .build();

            // 리스트에 해당 객체 저장
            courseList.add(course);
        }
        // 리스트에 담긴 엔티티 객체 모두 DB에 저장
        coursesRepository.saveAll(courseList);

    }

    public void initStudent(){

        //여러개의 Student 객체를 담을 리스트 생성
        List<Students> studentsList = new ArrayList<>();

        // 할당할 Courses DB로 부터 조회
        Optional<Courses> coursesOP1 = coursesRepository.findById(1L);
        Optional<Courses> coursesOP2 = coursesRepository.findById(2L);
        Optional<Courses> coursesOP3 = coursesRepository.findById(3L);
        Optional<Courses> coursesOP4 = coursesRepository.findById(4L);
        Optional<Courses> coursesOP5 = coursesRepository.findById(5L);

        // 해당 아이디 값의 Course가 존재할 경우 실행
        if(coursesOP1.isPresent()) {
            Courses coursePS1 = coursesOP1.get();

            //저장할 엔티티 객체 인스턴스
            Students student = Students.builder()
                    .course(coursePS1)
                    .name("student1")
                    .tel("010-0000-0000")
                    .studentStatus(StudentStatus.ACTIVE)
                    .build();

            //리스트에 해당 객체 저장
            studentsList.add(student);
        }


        if(coursesOP2.isPresent()) {
            Courses coursePS2 = coursesOP2.get();

            //저장할 엔티티 객체 인스턴스
            Students student = Students.builder()
                    .course(coursePS2)
                    .name("student2")
                    .tel("010-0000-2222")
                    .studentStatus(StudentStatus.ACTIVE)
                    .build();

            //리스트에 해당 객체 저장
            studentsList.add(student);
        }
        if(coursesOP3.isPresent()) {
            Courses coursePS3 = coursesOP3.get();

            //저장할 엔티티 객체 인스턴스
            Students student = Students.builder()
                    .course(coursePS3)
                    .name("student3")
                    .tel("010-0000-3333")
                    .studentStatus(StudentStatus.ACTIVE)
                    .build();

            //리스트에 해당 객체 저장
            studentsList.add(student);
        }


        studentsRepository.saveAll(studentsList);
    }
}
