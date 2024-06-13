package lab.nomad.springbootncsevaluation.model.exams.results;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface ExamResultsRepository extends JpaRepository<ExamResults, Long> {
    //하나의과정에서 여러학생들을 시험결과불러올때
    @Query("SELECT er FROM ExamResults er WHERE er.exam.student.course.id = :courseId")
    List<ExamResults> findByCourseId(@Param("courseId") Long courseId);

    //시험ID중복확인
    Optional<ExamResults> findByExamId(Long examId);
}
