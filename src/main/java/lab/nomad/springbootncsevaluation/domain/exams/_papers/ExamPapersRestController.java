package lab.nomad.springbootncsevaluation.domain.exams._papers;

import lab.nomad.springbootncsevaluation._core.security.CustomUserDetails;
import lab.nomad.springbootncsevaluation._core.utils.APIUtils;
import lab.nomad.springbootncsevaluation._core.utils.AuthorityCheckUtils;
import lab.nomad.springbootncsevaluation.domain.exams._papers.dto.ExamPaperPageResponseDTO;
import lab.nomad.springbootncsevaluation.domain.exams._papers.service.ExamPapersService;
import lab.nomad.springbootncsevaluation.model.users._enums.UserRole;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/exam-paper")
@Slf4j
public class ExamPapersRestController {

    // DI
    private final ExamPapersService examPapersService;


    @GetMapping
    public ResponseEntity<?> page(@AuthenticationPrincipal CustomUserDetails customUserDetails,
            Pageable pageable,
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
