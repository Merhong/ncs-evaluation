package lab.nomad.springbootncsevaluation.model.students;

import lab.nomad.springbootncsevaluation.model.courses.Courses;
import lab.nomad.springbootncsevaluation.model.exams.Exams;
import lab.nomad.springbootncsevaluation.model.students.Students;
import lab.nomad.springbootncsevaluation.model.users.Users;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Meta;

import java.util.List;
import java.util.Optional;

public interface StudentsRepository extends JpaRepository <Students, Long>{

    //course id 조회 하는 쿼리 메서드
   // Optional<Students> findByCourseId(Long courseId);

    //이름으로 검색 하는 쿼리 메서드
    Page<Students> findByNameAndDeleteDateIsNull(String searchValue, Pageable pageable);

    // 과정 ID로 검색 하는 쿼리 메서드
    Page<Students> findByNameContainsAndCourseIdAndDeleteDateIsNull(String searchValue, Long courseId, Pageable pageable);

    //전체 조회 하는 쿼리 메서드
    Page<Students> findAllAndByDeleteDateIsNull(Pageable pageable);

    // 과정 ID로 전체 조회 하는 쿼리 메서드
    Page<Students> findByCourseIdAndDeleteDateIsNull(Long courseId, Pageable pageable);

    //해당 과정에 속한 모든 학생을 조회 하는 메서드
    List<Students> findByCourseId(Long courseId);
}
