package lab.nomad.springbootncsevaluation.model.exams;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExamsRepository extends JpaRepository<Exams, Long> {

    Page<Exams> findByStudentNameContaining(String searchValue, Pageable pageable);
}
