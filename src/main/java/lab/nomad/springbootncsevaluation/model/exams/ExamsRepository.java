package lab.nomad.springbootncsevaluation.model.exams;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Meta;

import java.util.List;
import java.util.Optional;

public interface ExamsRepository extends JpaRepository<Exams, Long> {

    // 시험에 시험지가 포함되어 있는지 확인하는 쿼리 메소드
    boolean existsByExamPaperId(Long id);

    Page<Exams> findByStudentNameContaining(String searchValue, Pageable pageable);

    List<Exams> findByStudentId(Long id);

    //시험id와 강사id를 이용하여 시험을 찾는 메서드
    //Optional<Exams> findByIdAndUserId(Long id, Long userId);

//    @Meta(comment = "시험에서 하나의 코스과정을 찾는 쿼리 메소드")
//    Optional<Exams> findbyIdAndStudentId(Long id, Long studentId);
//
//    @Meta(comment = "시험강사와 과정강사가 같은지 비교하는메서드")
//    Optional<Object> findByIdAndUserId(Long courseId, Long userId);
}
