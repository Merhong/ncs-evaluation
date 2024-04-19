package lab.nomad.springbootncsevaluation.domain.courses;

import jakarta.validation.Valid;
import lab.nomad.springbootncsevaluation._core.exception.Exception400;
import lab.nomad.springbootncsevaluation._core.exception.ValidExceptionMessage;
import lab.nomad.springbootncsevaluation._core.security.CustomUserDetails;
import lab.nomad.springbootncsevaluation._core.utils.APIUtils;
import lab.nomad.springbootncsevaluation._core.utils.AuthorityCheckUtils;
import lab.nomad.springbootncsevaluation.domain.courses.dto.*;
import lab.nomad.springbootncsevaluation.domain.courses.service.CoursesService;
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
@RequestMapping("/api/v1/courses")
@Slf4j
public class CoursesRestController {
    private final CoursesService coursesService;

    // 과정 수정
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@AuthenticationPrincipal CustomUserDetails customUserDetails, @PathVariable Long id,
                                    @RequestBody @Valid CoursesUpdateRequestDTO requestDTO, Errors errors)  {

        // 권한 체크 -> 강사 본인 것만 수정 가능하게. + 관리자?
        AuthorityCheckUtils.authorityCheck(customUserDetails,
                                           List.of(UserRole.ROLE_ADMIN.name(), UserRole.ROLE_TEACHER.name()));

        if (errors.hasErrors()) {
            log.warn(errors.getAllErrors()
                           .get(0)
                           .getDefaultMessage());
            throw new Exception400(errors.getAllErrors()
                                         .get(0)
                                         .getDefaultMessage());
        }

        // 서비스 호출
        CoursesUpdateResponseDTO responseDTO = coursesService.update(id, customUserDetails.user(), requestDTO);

        return ResponseEntity.ok(APIUtils.success(responseDTO));
    }


    // 검색 및 과정 목록 조회 (페이징)
    @GetMapping
    public ResponseEntity<?> page(@AuthenticationPrincipal CustomUserDetails customUserDetails, Pageable pageable,
                                  @RequestParam(required = false) String searchValue) {

        // 권한 체크(능력단위 컨트롤러 참조)
        // 관리자, 강사, 직원이 조회 가능
        AuthorityCheckUtils.authorityCheck(customUserDetails,
                                           List.of(UserRole.ROLE_ADMIN.name(), UserRole.ROLE_TEACHER.name(),
                                                   UserRole.ROLE_EMP.name()));

        // 서비스 호출
        CoursesPageResponseDTO responseDTO = coursesService.page(pageable, searchValue, customUserDetails.user());

        return ResponseEntity.ok(APIUtils.success(responseDTO));
    }

    // 과정 단일 조회
    @GetMapping("/{id}")
    public ResponseEntity<?> one(@AuthenticationPrincipal CustomUserDetails customUserDetails, @PathVariable Long id) {

        // 권한 체크(능력단위 컨트롤러 참조)
        // 관리자, 강사, 직원이 조회 가능
        AuthorityCheckUtils.authorityCheck(customUserDetails,
                                           List.of(UserRole.ROLE_ADMIN.name(), UserRole.ROLE_TEACHER.name(),
                                                   UserRole.ROLE_EMP.name()));

        CoursesOneResponseDTO responseDTO = coursesService.one(id, customUserDetails.user());

        return ResponseEntity.ok(APIUtils.success(responseDTO));
    }

    // 과정 등록
    @PostMapping
    public ResponseEntity<?> save(@AuthenticationPrincipal CustomUserDetails customUserDetails,
                                  @RequestBody @Valid CoursesSaveRequestDTO requestDTO, Errors errors) {

        if (errors.hasErrors()) {
            log.warn(errors.getAllErrors()
                           .get(0)
                           .getDefaultMessage());
            throw new Exception400(errors.getAllErrors()
                                         .get(0)
                                         .getDefaultMessage());
        }

        // customUserDetails.user()는 요청한 사람(강사)
        CoursesSaveResponseDTO responseDTO = coursesService.save(customUserDetails.user(), requestDTO);

        return ResponseEntity.ok(APIUtils.success(responseDTO));
    }
}
