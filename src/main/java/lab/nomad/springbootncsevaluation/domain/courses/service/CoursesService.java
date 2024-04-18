package lab.nomad.springbootncsevaluation.domain.courses.service;

import lab.nomad.springbootncsevaluation._core.exception.Exception400;
import lab.nomad.springbootncsevaluation._core.exception.ExceptionMessage;
import lab.nomad.springbootncsevaluation.domain.courses.dto.CoursesOneResponseDTO;
import lab.nomad.springbootncsevaluation.domain.courses.dto.CoursesPageResponseDTO;
import lab.nomad.springbootncsevaluation.domain.courses.dto.CoursesSaveRequestDTO;
import lab.nomad.springbootncsevaluation.domain.courses.dto.CoursesSaveResponseDTO;
import lab.nomad.springbootncsevaluation.model.courses.Courses;
import lab.nomad.springbootncsevaluation.model.courses.CoursesRepository;
import lab.nomad.springbootncsevaluation.model.users.Users;
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

    public CoursesPageResponseDTO page(Pageable pageable, String searchValue, Users user) {

        // 검색 키워드 OOO
        // 해당 User(강사)와 관련된 키워드 결과만 보여줌
        if (!(searchValue == null)) {

            return new CoursesPageResponseDTO(user, coursesRepository.findByNameContainsAndUserId(pageable, searchValue, user.getId()));
        }

        // 검색 키워드 XXX
        // 해당 User(강사)의 과정을 모두 보여줌
        Page<Courses> pagedCoursesPS = coursesRepository.findByUserId(pageable, user.getId());

        return new CoursesPageResponseDTO(user, pagedCoursesPS);
    }

    // JPA 사용, User ID로 조회
    public CoursesOneResponseDTO one(Long id, Users user) {
        Courses coursesPS = coursesRepository.findByIdAndUserId(id, user.getId())
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
