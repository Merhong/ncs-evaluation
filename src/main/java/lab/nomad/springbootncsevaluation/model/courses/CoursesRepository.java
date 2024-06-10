package lab.nomad.springbootncsevaluation.model.courses;

import lab.nomad.springbootncsevaluation.model.exams.Exams;
import lab.nomad.springbootncsevaluation.model.students.Students;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Meta;

import java.util.List;
import java.util.Optional;


public interface CoursesRepository extends JpaRepository<Courses, Long> {

    @Meta(comment = "키워드 검색(관리자는 모든 과정을 볼 수 있음)")
    Page<Courses> findByNameContainsAndDeleteDateIsNull(Pageable pageable, String searchValue);

    @Meta(comment = "관리자는 모든 과정을 볼 수 있음")
    Page<Courses> findByDeleteDateIsNull(Pageable pageable);

    @Meta(comment = "키워드 검색(User 자기 자신만 보여야 함.)")
    Page<Courses> findByNameContainsAndUserIdAndDeleteDateIsNull(Pageable pageable, String searchValue, Long userId);

    @Meta(comment = "user(강사) id를 이용해서 과정들을 조회하는 쿼리 메소드")
    Page<Courses> findByUserIdAndDeleteDateIsNull(Pageable pageable, Long userId);

    @Meta(comment = "과정ID와 유저ID 둘 모두 만족하는 튜플을 찾는 쿼리 메소드")
    Optional<Courses> findByIdAndUserIdAndDeleteDateIsNull(Long id, Long userId);

    @Meta(comment = "삭제된 Log가 없는 과정ID를 만족하는 튜플을 찾는 쿼리 메소드")
    Optional<Courses> findByIdAndDeleteDateIsNull(Long id);

    @Meta(comment = "시험강사와 과정강사가 같은지 비교하는메서드")
    Optional<Object> findByIdAndUserId(Long courseId, Long userId);


}