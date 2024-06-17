package lab.nomad.springbootncsevaluation.domain.exams._results._multiple_items;

import jakarta.validation.Valid;
import lab.nomad.springbootncsevaluation._core.exception.Exception400;
import lab.nomad.springbootncsevaluation._core.security.CustomUserDetails;
import lab.nomad.springbootncsevaluation._core.utils.APIUtils;
import lab.nomad.springbootncsevaluation._core.utils.AuthorityCheckUtils;
import lab.nomad.springbootncsevaluation.domain.exams._results._multiple_items.dto.ExamResultMultipleItemsPageResponseDTO;
import lab.nomad.springbootncsevaluation.domain.exams._results._multiple_items.dto.ExamResultMultipleItemsSaveRequestDTO;
import lab.nomad.springbootncsevaluation.domain.exams._results._multiple_items.dto.ExamResultMultipleItemsSaveResponseDTO;
import lab.nomad.springbootncsevaluation.domain.exams._results._multiple_items.service.ExamResultMultipleItemsService;
import lab.nomad.springbootncsevaluation.model.users._enums.UserRole;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/v1/exam-results")
public class ExamResultMultipleItemsRestController {

    // DI
    private final ExamResultMultipleItemsService itemsService;

    // TODO : 채점 문제 리스트(페이지) 조회
    // 채점 문제 리스트 조회
    @GetMapping("/{resultId}/items")
    public ResponseEntity<?> page(@AuthenticationPrincipal CustomUserDetails customUserDetails, Pageable pageable,
            @RequestParam(required = false) String searchValue, @PathVariable Long resultId) {

        // 권한 체크
        // 관리자, 강사, 직원이 조회 가능
        AuthorityCheckUtils.authorityCheck(customUserDetails,
                List.of(UserRole.ROLE_ADMIN.name(), UserRole.ROLE_TEACHER.name(), UserRole.ROLE_EMP.name()));


        // 서비스 호출
        ExamResultMultipleItemsPageResponseDTO responseDTO = itemsService.page(resultId, pageable, searchValue,
                customUserDetails.user());

        return ResponseEntity.ok(APIUtils.success(responseDTO));
    }

    // 채점 결과 저장
    @PostMapping("/item")
    public ResponseEntity<?> save(@AuthenticationPrincipal CustomUserDetails customUserDetails,
            @RequestBody @Valid ExamResultMultipleItemsSaveRequestDTO requestDTO, Errors errors) {

        // 권한 체크
        AuthorityCheckUtils.authorityCheck(customUserDetails,
                List.of(UserRole.ROLE_ADMIN.name(), UserRole.ROLE_TEACHER.name()));

        // 입력값 유효성 체크
        if (errors.hasErrors()) {
            log.warn(errors.getAllErrors()
                    .get(0)
                    .getDefaultMessage());
            throw new Exception400(errors.getAllErrors()
                    .get(0)
                    .getDefaultMessage());
        }

        ExamResultMultipleItemsSaveResponseDTO responseDTO = itemsService.save(requestDTO.getResultId(),
                requestDTO.getQuestionId(), requestDTO.getAnswerId(), requestDTO);

        return ResponseEntity.ok(APIUtils.success(responseDTO));
    }
}
