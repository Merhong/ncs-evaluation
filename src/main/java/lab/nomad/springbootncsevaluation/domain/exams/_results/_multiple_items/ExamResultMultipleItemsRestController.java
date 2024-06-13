package lab.nomad.springbootncsevaluation.domain.exams._results._multiple_items;

import jakarta.validation.Valid;
import lab.nomad.springbootncsevaluation._core.exception.Exception400;
import lab.nomad.springbootncsevaluation._core.security.CustomUserDetails;
import lab.nomad.springbootncsevaluation._core.utils.APIUtils;
import lab.nomad.springbootncsevaluation._core.utils.AuthorityCheckUtils;
import lab.nomad.springbootncsevaluation.domain.exams._results._multiple_items.dto.ExamResultMultipleItemsSaveRequestDTO;
import lab.nomad.springbootncsevaluation.domain.exams._results._multiple_items.dto.ExamResultMultipleItemsSaveResponseDTO;
import lab.nomad.springbootncsevaluation.domain.exams._results._multiple_items.service.ExamResultMultipleItemsService;
import lab.nomad.springbootncsevaluation.model.users._enums.UserRole;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/v1/exams/result")
public class ExamResultMultipleItemsRestController {

    // DI
    private final ExamResultMultipleItemsService examResultMultipleItemsService;

    // TODO : 채점지 상세조회
    // 채점지 상세조회
    @GetMapping("/item/{id}")
    public ResponseEntity<?> one(@AuthenticationPrincipal CustomUserDetails customUserDetails, @PathVariable Long id) {

        // 권한 체크
        // 관리자, 강사, 직원이 조회 가능
        AuthorityCheckUtils.authorityCheck(customUserDetails, List.of(UserRole.ROLE_ADMIN.name(), UserRole.ROLE_TEACHER.name(), UserRole.ROLE_EMP.name()));



        return null;
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

        ExamResultMultipleItemsSaveResponseDTO responseDTO = examResultMultipleItemsService.save(
                requestDTO.getResultId(), requestDTO.getQuestionId(), requestDTO.getAnswerId(), requestDTO);

        return ResponseEntity.ok(APIUtils.success(responseDTO));
    }
}
