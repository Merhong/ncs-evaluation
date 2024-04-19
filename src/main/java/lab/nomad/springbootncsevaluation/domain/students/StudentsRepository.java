package lab.nomad.springbootncsevaluation.domain.students;

import lab.nomad.springbootncsevaluation.model.courses.Courses;
import lab.nomad.springbootncsevaluation.model.students.Students;
import lab.nomad.springbootncsevaluation.model.users.Users;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface StudentsRepository extends JpaRepository <Students, Long>{

   // Optional<Students> findAll();

    //course id 조회하는 메서드
    Optional<Students> findByCourseId(Long courseId);


    Page<Students> findByName(String searchValue, Pageable pageable);
}
