package lab.nomad.springbootncsevaluation.model.exams.results.multiple_items;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Meta;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ExamResultMultipleItemsRepository extends JpaRepository<ExamResultMultipleItems, Long> {

    @Meta(comment = "키워드 검색(관리자는 모든 과정을 볼 수 있음)")
    Page<ExamResultMultipleItems> findByExamResultIdAndCommentContains(Long resultId, Pageable pageable, String searchValue);

    @Meta(comment = "키워드 검색(User 자기 자신만 보여야 함.)")
    Page<ExamResultMultipleItems> findByIdAndCommentContains(Long resultId, Pageable pageable, String searchValue,
            Long userId);

    Page<ExamResultMultipleItems> findByExamResultId(Long resultId, Pageable pageable);
   // @Meta(comment = "문제결과값 불러오기")
   // List<ExamResultMultipleItems> findByExamResultId(Long examResultId);

    @Query("SELECT eri FROM ExamResultMultipleItems eri WHERE eri.examResult.id = :examResultId")
    List<ExamResultMultipleItems> findByExamResultId(@Param("examResultId") Long examResultId);
}
