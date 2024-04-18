package lab.nomad.springbootncsevaluation.domain.courses;

import lab.nomad.springbootncsevaluation._core.security.CustomUserDetails;
import lab.nomad.springbootncsevaluation._core.utils.APIUtils;
import lab.nomad.springbootncsevaluation._core.utils.AuthorityCheckUtils;
import lab.nomad.springbootncsevaluation.domain.courses.dto.CoursesOneResponseDTO;
import lab.nomad.springbootncsevaluation.domain.courses.dto.CoursesPageResponseDTO;
import lab.nomad.springbootncsevaluation.domain.courses.dto.CoursesSaveRequestDTO;
import lab.nomad.springbootncsevaluation.domain.courses.dto.CoursesSaveResponseDTO;
import lab.nomad.springbootncsevaluation.domain.courses.sercvice.CoursesService;
import lab.nomad.springbootncsevaluation.model.users._enums.UserRole;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/courses")
public class CoursesRestController {
    private final CoursesService coursesService;

    // 검색 및 과정 목록 조회 (페이징)
    @GetMapping("/")
    public ResponseEntity<?> page(@AuthenticationPrincipal CustomUserDetails customUserDetails, Pageable pageable, @RequestParam(required = false) String searchValue) {

        // 권한 체크(능력단위 컨트롤러 참조)
        // 관리자, 강사, 직원이 조회 가능
        AuthorityCheckUtils.authorityCheck(customUserDetails, List.of(UserRole.ROLE_ADMIN.name(), UserRole.ROLE_TEACHER.name(), UserRole.ROLE_EMP.name()));

        // 서비스 호출
        CoursesPageResponseDTO responseDTO = coursesService.과정목록조회(pageable, searchValue, customUserDetails.user());

        return ResponseEntity.ok(APIUtils.success(responseDTO));
    }

    // 과정 단일 조회
    @GetMapping("/{id}")
    public ResponseEntity<?> one(@AuthenticationPrincipal CustomUserDetails customUserDetails, @PathVariable Long id) {

        // 권한 체크(능력단위 컨트롤러 참조)
        // 관리자, 강사, 직원이 조회 가능
        AuthorityCheckUtils.authorityCheck(customUserDetails, List.of(UserRole.ROLE_ADMIN.name(), UserRole.ROLE_TEACHER.name(), UserRole.ROLE_EMP.name()));

        CoursesOneResponseDTO responseDTO = coursesService.과정단일조회(id, customUserDetails.user());

        return ResponseEntity.ok(APIUtils.success(responseDTO));
    }

    // 과정 등록
    @PostMapping("")
    public ResponseEntity<?> save(@AuthenticationPrincipal CustomUserDetails customUserDetails, @RequestBody CoursesSaveRequestDTO requestDTO) {

        // customUserDetails.user()는 요청한 사람(강사)
        CoursesSaveResponseDTO responseDTO = coursesService.과정등록(customUserDetails.user(), requestDTO);

        return ResponseEntity.ok(APIUtils.success(responseDTO));
    }
}
