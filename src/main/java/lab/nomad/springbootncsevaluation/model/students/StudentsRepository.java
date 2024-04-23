package lab.nomad.springbootncsevaluation.model.students;

import lab.nomad.springbootncsevaluation.model.courses.Courses;
import lab.nomad.springbootncsevaluation.model.students.Students;
import lab.nomad.springbootncsevaluation.model.users.Users;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface StudentsRepository extends JpaRepository <Students, Long>{

    //course id 조회하는 쿼리메서드
    Optional<Students> findByCourseId(Long courseId);

    //이름으로 검색하는 쿼리메서드
    Page<Students> findByNameAndDeleteDateIsNull(String searchValue, Pageable pageable);

    //전체조회하는 쿼리메서드
    Page<Students> findAllAndByDeleteDateIsNull(Pageable pageable);



}
