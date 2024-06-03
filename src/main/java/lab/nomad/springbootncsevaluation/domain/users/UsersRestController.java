package lab.nomad.springbootncsevaluation.domain.users;

import lab.nomad.springbootncsevaluation._core.exception.Exception400;
import lab.nomad.springbootncsevaluation._core.exception.ExceptionMessage;
import lab.nomad.springbootncsevaluation._core.security.CustomUserDetails;
import lab.nomad.springbootncsevaluation._core.utils.APIUtils;
import lab.nomad.springbootncsevaluation._core.utils.AuthorityCheckUtils;
import lab.nomad.springbootncsevaluation.domain.courses.dto.CoursesPageResponseDTO;
import lab.nomad.springbootncsevaluation.domain.users.dto.UsersSaveRequestDTO;
import lab.nomad.springbootncsevaluation.domain.users.service.UsersService;
import lab.nomad.springbootncsevaluation.model.users._enums.UserRole;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
@Slf4j
public class UsersRestController {
    private final UsersService usersService;

    @GetMapping
    public ResponseEntity<?> page(@AuthenticationPrincipal CustomUserDetails customUserDetails, Pageable pageable,
                                  @RequestParam(required = false) String searchValue,
                                  @RequestParam(required = false) String role) {

        // 관리자만 조회 가능
        AuthorityCheckUtils.authorityCheck(customUserDetails, List.of(UserRole.ROLE_ADMIN.name()));

        var responseDTO = usersService.page(searchValue, role, pageable);

        return ResponseEntity.ok(APIUtils.success(responseDTO));
    }
    @PostMapping()
    public ResponseEntity<?> save(@RequestBody UsersSaveRequestDTO requestDTO) {
        try {
            // 회원가입 로직 수행
            usersService.save(requestDTO);
            return ResponseEntity.ok(APIUtils.success("회원가입이 성공적으로 완료되었습니다."));
        } catch (Exception e) {
            log.error("회원가입 중 오류 발생", e);
            throw new Exception400(ExceptionMessage.JOIN_FAIL.getMessage());
        }
    }

}
