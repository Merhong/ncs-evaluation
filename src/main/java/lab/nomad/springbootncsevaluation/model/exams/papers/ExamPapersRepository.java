package lab.nomad.springbootncsevaluation.model.exams.papers;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Meta;

public interface ExamPapersRepository extends JpaRepository<ExamPapers, Long> {

    @Meta(comment = "키워드 검색(관리자는 모든 시험지를 볼 수 있음)")
    Page<ExamPapers> findByNameContains(String searchValue, Pageable pageable);

    @Meta(comment = "키워드 검색(강사는 자기 시험지만 볼 수 있음")
    Page<ExamPapers> findByNameContainsAndUserId(String searchValue, Long userId, Pageable pageable);

    @Meta(comment = "강사는 자기 시험지만 볼 수 있음")
    Page<ExamPapers> findByUserId(Long userId, Pageable pageable);

}
