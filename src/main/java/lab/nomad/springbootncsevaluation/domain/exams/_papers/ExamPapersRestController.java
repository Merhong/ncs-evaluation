package lab.nomad.springbootncsevaluation.domain.exams._papers;

import lab.nomad.springbootncsevaluation._core.security.CustomUserDetails;
import lab.nomad.springbootncsevaluation._core.utils.APIUtils;
import lab.nomad.springbootncsevaluation._core.utils.AuthorityCheckUtils;
import lab.nomad.springbootncsevaluation.domain.exams._papers.dto.ExamPaperDeleteResponseDTO;
import lab.nomad.springbootncsevaluation.domain.exams._papers.dto.ExamPaperOneResponseDTO;
import lab.nomad.springbootncsevaluation.domain.exams._papers.dto.ExamPaperPageResponseDTO;
import lab.nomad.springbootncsevaluation.domain.exams._papers.service.ExamPapersService;
import lab.nomad.springbootncsevaluation.model.users._enums.UserRole;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/exam-paper")
@Slf4j
public class ExamPapersRestController {

    // DI
    private final ExamPapersService examPapersService;


    // 시험지 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@AuthenticationPrincipal CustomUserDetails customUserDetails, @PathVariable Long id) {
        // 권한 체크
        // 강사 본인 것만 수정 가능하게. + 관리자?
        AuthorityCheckUtils.authorityCheck(customUserDetails,
                List.of(UserRole.ROLE_ADMIN.name(), UserRole.ROLE_TEACHER.name()));

        // 서비스 호출
        ExamPaperDeleteResponseDTO responseDTO = examPapersService.delete(id, customUserDetails.user());

        return ResponseEntity.ok(APIUtils.success(responseDTO));
    }

    // 시험지 상세 조회
    @GetMapping("/{id}")
    public ResponseEntity<?> one(@AuthenticationPrincipal CustomUserDetails customUserDetails, @PathVariable Long id) {

        // 권한 체크
        // 관리자, 강사 조회 가능
        AuthorityCheckUtils.authorityCheck(customUserDetails,
                List.of(UserRole.ROLE_ADMIN.name(), UserRole.ROLE_TEACHER.name()));

        ExamPaperOneResponseDTO responseDTO = examPapersService.one(id, customUserDetails.user());

        return ResponseEntity.ok(APIUtils.success(responseDTO));
    }


    // 시험지 목록 페이징 및 검색
    @GetMapping
    public ResponseEntity<?> page(@AuthenticationPrincipal CustomUserDetails customUserDetails, Pageable pageable,
                                  @RequestParam(required = false) String searchValue) {

        // 권한 체크
        // 관리자, 강사 조회 가능
        AuthorityCheckUtils.authorityCheck(customUserDetails,
                List.of(UserRole.ROLE_ADMIN.name(), UserRole.ROLE_TEACHER.name()));

        // 서비스 호출
        ExamPaperPageResponseDTO responseDTO = examPapersService.page(pageable, searchValue, customUserDetails.user());

        return ResponseEntity.ok(APIUtils.success(responseDTO));
    }

    /**
     * ExamPaper 등록은 AbilityUnit Id가 필요해서
     * {@link lab.nomad.springbootncsevaluation.domain.ability_units.AbilityUnitsRestController} 에서
     *  PostMapping("/{abilityUnitId}/exam-paper") 처리
     */
}
