package lab.nomad.springbootncsevaluation.domain.courses.service;

import lab.nomad.springbootncsevaluation._core.exception.Exception400;
import lab.nomad.springbootncsevaluation._core.exception.ExceptionMessage;
import lab.nomad.springbootncsevaluation.domain.courses.dto.*;
import lab.nomad.springbootncsevaluation.model.courses.Courses;
import lab.nomad.springbootncsevaluation.model.courses.CoursesRepository;
import lab.nomad.springbootncsevaluation.model.users.Users;
import lab.nomad.springbootncsevaluation.model.users._enums.UserRole;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CoursesService {

    // DI
    private final CoursesRepository coursesRepository;

    // 과정 삭제
    // deleteDate에 값이 있으면 삭제된 걸로 간주한다.
    @Transactional
    public CoursesDeleteResponseDTO delete(Long id, Users user) {

        // 관리자 모두 삭제 가능
        if (user.getRole() == UserRole.ROLE_ADMIN) {
            // DB 로직 처리
            Courses coursesPS = coursesRepository.findById(id)
                    .orElseThrow(() -> new Exception400(ExceptionMessage.NOT_FOUND_COURSE.getMessage()));

            // 트랜잭션 처리
            coursesPS.delete();

            return new CoursesDeleteResponseDTO(user, coursesRepository.save(coursesPS));
        }

        // 강사는 자기 과정만 삭제 가능
        else {
            // DB 로직 처리
            Courses coursesPS = coursesRepository.findByIdAndUserIdAndDeleteDateIsNull(id, user.getId())
                    .orElseThrow(() -> new Exception400(ExceptionMessage.COMMON_FORBIDDEN.getMessage()));

            // 트랜잭션 처리
            coursesPS.delete();

            return new CoursesDeleteResponseDTO(user, coursesRepository.save(coursesPS));
        }

    }


    // 과정 수정
    @Transactional
    public CoursesUpdateResponseDTO update(Long id, Users user, CoursesUpdateRequestDTO requestDTO) {

        // 관리자는 모두 변경 가능
        if (user.getRole() == UserRole.ROLE_ADMIN) {
            // DB 로직 처리
            Courses coursePS = coursesRepository.findByIdAndDeleteDateIsNull(id)
                    .orElseThrow(() -> new Exception400(ExceptionMessage.NOT_FOUND_COURSE.getMessage()));

            // 트랜잭션 처리
            coursePS.update(requestDTO.getName(), requestDTO.getAcademyName());

            return new CoursesUpdateResponseDTO(user, coursesRepository.save(coursePS));
        }

        // 강사는 자기 자신만 변경 가능
        else {
            // DB 로직 처리
            Courses coursePS = coursesRepository.findByIdAndUserIdAndDeleteDateIsNull(id, user.getId())
                    .orElseThrow(() -> new Exception400(ExceptionMessage.COMMON_FORBIDDEN.getMessage()));

            // 트랜잭션 처리
            coursePS.update(requestDTO.getName(), requestDTO.getAcademyName());

            return new CoursesUpdateResponseDTO(user, coursesRepository.save(coursePS));
        }
    }


    // 페이징 및 검색
    public CoursesPageResponseDTO page(Pageable pageable, String searchValue, Users user) {
        // 관리자
        // 모든 과정 조회 가능
        if (user.getRole() == UserRole.ROLE_ADMIN) {
            // 검색 키워드 OOO
            // 관련된 키워드 결과 모두 보여줌
            if (!(searchValue == null)) {
                return new CoursesPageResponseDTO(user,
                                                  coursesRepository.findByNameContainsAndDeleteDateIsNull(pageable,
                                                                                                          searchValue));
            }

            // 검색 키워드 XXX
            // 과정을 모두 보여줌
            Page<Courses> pagedCoursesPS = coursesRepository.findByDeleteDateIsNull(pageable);

            return new CoursesPageResponseDTO(user, pagedCoursesPS);
        }

        // 강사와 직원은 자기 과정만 조회 가능
        else {
            // 검색 키워드 OOO
            // 해당 User(강사&직원)와 관련된 키워드 결과만 보여줌
            if (!(searchValue == null)) {
                return new CoursesPageResponseDTO(user,
                                                  coursesRepository.findByNameContainsAndUserIdAndDeleteDateIsNull(
                                                          pageable, searchValue, user.getId()));
            }

            // 검색 키워드 XXX
            // 해당 User(강사&직원)의 과정을 모두 보여줌
            Page<Courses> pagedCoursesPS = coursesRepository.findByUserIdAndDeleteDateIsNull(pageable, user.getId());

            return new CoursesPageResponseDTO(user, pagedCoursesPS);
        }
    }

    // JPA 사용, User ID로 조회
    public CoursesOneResponseDTO one(Long id, Users user) {
        Courses coursesPS = coursesRepository.findByIdAndUserIdAndDeleteDateIsNull(id, user.getId())
                .orElseThrow(() -> new Exception400(ExceptionMessage.NOT_FOUND_COURSE.getMessage()));

        return new CoursesOneResponseDTO(user, coursesPS);
    }

    @Transactional
    public CoursesSaveResponseDTO save(Users user, CoursesSaveRequestDTO requestDTO) {
        Courses coursesForSave = Courses.builder()
                .name(requestDTO.getName())
                .academyName(requestDTO.getAcademyName())
                .user(user)
                .build();

        Courses coursesPS = coursesRepository.save(coursesForSave);

        return new CoursesSaveResponseDTO(user, coursesPS);
    }
}
