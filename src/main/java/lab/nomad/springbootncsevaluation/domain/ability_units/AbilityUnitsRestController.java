package lab.nomad.springbootncsevaluation.domain.ability_units;

import jakarta.validation.Valid;
import lab.nomad.springbootncsevaluation._core.exception.Exception400;
import lab.nomad.springbootncsevaluation._core.exception.ExceptionMessage;
import lab.nomad.springbootncsevaluation._core.security.CustomUserDetails;
import lab.nomad.springbootncsevaluation._core.utils.APIUtils;
import lab.nomad.springbootncsevaluation._core.utils.AuthorityCheckUtils;
import lab.nomad.springbootncsevaluation.domain.ability_units.dto.*;
import lab.nomad.springbootncsevaluation.domain.ability_units.service.AbilityUnitsService;
import lab.nomad.springbootncsevaluation.domain.exams._papers.dto.ExamPaperSaveRequestDTO;
import lab.nomad.springbootncsevaluation.domain.exams._papers.dto.ExamPaperSaveResponseDTO;
import lab.nomad.springbootncsevaluation.domain.exams._papers.service.ExamPapersService;
import lab.nomad.springbootncsevaluation.model.ability_units._enums.ExamType;
import lab.nomad.springbootncsevaluation.model.users._enums.UserRole;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/ability-units")
@Slf4j
public class AbilityUnitsRestController {
    private final AbilityUnitsService abilityUnitsService;
    private final ExamPapersService examPapersService;

    // 시험지 등록
    @PostMapping("/{abilityUnitId}/exam-paper")
    public ResponseEntity<?> save(@AuthenticationPrincipal CustomUserDetails customUserDetails,
            @PathVariable Long abilityUnitId,
            @RequestBody @Valid ExamPaperSaveRequestDTO requestDTO,
            Errors errors) {

        // 권한 체크
        AuthorityCheckUtils.authorityCheck(customUserDetails,
                                           List.of(UserRole.ROLE_ADMIN.name(), UserRole.ROLE_TEACHER.name()));

        // 유효성 체크
        if (errors.hasErrors()) {
            log.warn(errors.getAllErrors()
                             .get(0)
                             .getDefaultMessage());
            throw new Exception400(errors.getAllErrors()
                                           .get(0)
                                           .getDefaultMessage());
        }

        // Request에 담긴 examType을 대문자로 변환, 서비스에서도 마찬가지로 처리함.
        String examTypeUpperCase = requestDTO.getExamType().toUpperCase();

        // String으로 받아온 examType이 정확한 값이 아니라면 예외처리
        if (examTypeUpperCase.equals("MULTIPLE_CHOICE") || examTypeUpperCase.equals("TASK")) { }

        else {
            throw new Exception400(ExceptionMessage.NOT_FOUND_EXAM_TYPE.getMessage());
        }

        // 시험지 등록
        ExamPaperSaveResponseDTO responseDTO = examPapersService.save(customUserDetails.user(),
                                                                      abilityUnitId,
                                                                      requestDTO);

        return ResponseEntity.ok(APIUtils.success(responseDTO));
    }

    @GetMapping("/check-code")
    public ResponseEntity<Map<String, Boolean>> checkCodeUnique(@RequestParam("code") String code) {
        boolean isUnique = abilityUnitsService.isCodeUnique(code);
        Map<String, Boolean> responseDTO = new HashMap<>();
        responseDTO.put("isUnique", isUnique);
        return ResponseEntity.ok(responseDTO);
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody @Valid AbilityUnitSaveRequestDTO requestDTO,
            Errors errors,
            @AuthenticationPrincipal CustomUserDetails customUserDetails) {

        // 권한 체크
        AuthorityCheckUtils.authorityCheck(customUserDetails, UserRole.ROLE_ADMIN.name());

        // 입력값 유효성 체크
        if (errors.hasErrors()) {
            log.warn(errors.getAllErrors()
                             .get(0)
                             .getDefaultMessage());
            throw new Exception400(errors.getAllErrors()
                                           .get(0)
                                           .getDefaultMessage());
        }

        AbilityUnitSaveResponseDTO responseDTO = abilityUnitsService.save(requestDTO);

        return ResponseEntity.ok(APIUtils.success(responseDTO));
    }

    @GetMapping
    public ResponseEntity<?> page(@AuthenticationPrincipal CustomUserDetails customUserDetails,
            Pageable pageable,
            @RequestParam(required = false) String searchValue) {

        // 권한 체크
        AuthorityCheckUtils.authorityCheck(customUserDetails,
                                           List.of(UserRole.ROLE_ADMIN.name(),
                                                   UserRole.ROLE_TEACHER.name(),
                                                   UserRole.ROLE_EMP.name()));

        AbilityUnitPageResponseDTO responseDTO = abilityUnitsService.page(pageable, searchValue);

        return ResponseEntity.ok(APIUtils.success(responseDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> one(@AuthenticationPrincipal CustomUserDetails customUserDetails, @PathVariable Long id) {

        // 권한 체크
        AuthorityCheckUtils.authorityCheck(customUserDetails,
                                           List.of(UserRole.ROLE_ADMIN.name(),
                                                   UserRole.ROLE_TEACHER.name(),
                                                   UserRole.ROLE_EMP.name()));

        AbilityUnitOneResponseDTO responseDTO = abilityUnitsService.one(id);

        return ResponseEntity.ok(APIUtils.success(responseDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@AuthenticationPrincipal CustomUserDetails customUserDetails,
            @PathVariable Long id,
            @RequestBody @Valid AbilityUnitUpdateRequestDTO requestDTO) {

        // 권한 체크
        AuthorityCheckUtils.authorityCheck(customUserDetails, UserRole.ROLE_ADMIN.name());

        AbilityUnitUpdateResponseDTO responseDTO = abilityUnitsService.update(id, requestDTO);

        return ResponseEntity.ok(APIUtils.success(responseDTO));
    }
}
