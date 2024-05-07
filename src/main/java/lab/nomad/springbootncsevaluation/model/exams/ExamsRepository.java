package lab.nomad.springbootncsevaluation.model.exams;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExamsRepository extends JpaRepository<Exams, Long> {

    // 시험에 시험지가 포함되어 있는지 확인하는 쿼리 메소드
    boolean existsByExamPaperId(Long id);

    Page<Exams> findByStudentNameContaining(String searchValue, Pageable pageable);
}
