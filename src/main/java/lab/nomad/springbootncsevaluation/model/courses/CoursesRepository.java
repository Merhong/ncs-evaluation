package lab.nomad.springbootncsevaluation.model.courses;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Meta;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;


public interface CoursesRepository extends JpaRepository<Courses, Long> {

    @Meta(comment = "키워드 검색(User 자기 자신만 보여야 함.)")
    Page<Courses> findByNameContainsAndUserId(Pageable pageable, String searchValue, Long userId);

    @Meta(comment = "user(강사) id를 이용해서 과정들을 조회하는 쿼리 메소드")
    Page<Courses> findByUserId(Pageable pageable, Long userId);

    @Meta(comment = "과정ID와 유저ID 둘 모두 만족하는 튜플을 찾는 쿼리 메소드")
    Optional<Courses> findByIdAndUserId(Long id, Long userId);

}