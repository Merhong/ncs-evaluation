package lab.nomad.springbootncsevaluation.model.exams.papers;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Meta;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ExamPapersRepository extends JpaRepository<ExamPapers, Long> {

    @Meta(comment = "키워드 검색(관리자는 모든 시험지를 볼 수 있음)")
    Page<ExamPapers> findByNameContainsAndDeleteDateIsNull(String searchValue, Pageable pageable);

    @Meta(comment = "키워드 검색(강사는 자기 시험지만 볼 수 있음")
    Page<ExamPapers> findByNameContainsAndUserIdAndDeleteDateIsNull(String searchValue, Long userId, Pageable pageable);

    @Meta(comment = "관리자는 모든 시험지를 볼 수 있음")
    Page<ExamPapers> findByDeleteDateIsNull(Pageable pageable);

    @Meta(comment = "강사는 자기 시험지만 볼 수 있음")
    Page<ExamPapers> findByUserIdAndDeleteDateIsNull(Long userId, Pageable pageable);

    @Meta(comment = "시험지ID와 유저ID 둘 모두 만족하는 튜플을 찾는 쿼리 메소드")
    Optional<ExamPapers> findByIdAndUserIdAndDeleteDateIsNull(Long id, Long userId);

    @Meta(comment = "삭제된 Log가 없는 시험지ID를 만족하는 튜플을 찾는 쿼리 메소드")
    Optional<ExamPapers> findByIdAndDeleteDateIsNull(Long id);

    @Query("SELECT e FROM ExamPapers e LEFT JOIN FETCH e.abilityUnit WHERE e.id = :id")
    Optional<ExamPapers> findByIdWithAbilityUnit(@Param("id") Long id);
}
