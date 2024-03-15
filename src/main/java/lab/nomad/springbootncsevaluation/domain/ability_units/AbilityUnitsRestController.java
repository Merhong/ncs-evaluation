package lab.nomad.springbootncsevaluation.domain.ability_units;

import jakarta.validation.Valid;
import lab.nomad.springbootncsevaluation._core.exception.Exception400;
import lab.nomad.springbootncsevaluation._core.exception.ExceptionMessage;
import lab.nomad.springbootncsevaluation._core.security.CustomUserDetails;
import lab.nomad.springbootncsevaluation._core.utils.APIUtils;
import lab.nomad.springbootncsevaluation.domain.ability_units.dto.AbilityUnitSaveRequestDTO;
import lab.nomad.springbootncsevaluation.domain.ability_units.dto.AbilityUnitSaveResponseDTO;
import lab.nomad.springbootncsevaluation.domain.ability_units.service.AbilityUnitsService;
import lab.nomad.springbootncsevaluation.model.users._enums.UserRole;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/ability-units")
@Slf4j
public class AbilityUnitsRestController {
    private final AbilityUnitsService abilityUnitsService;

    @PostMapping
    public ResponseEntity<?> save(@RequestBody @Valid AbilityUnitSaveRequestDTO requestDTO, Errors errors,
                                  @AuthenticationPrincipal CustomUserDetails customUserDetails
    ) {

        // 권한 체크
        if (!customUserDetails.getAuthorities().stream()
                .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals(UserRole.ROLE_ADMIN.name()))) {

            throw new Exception400(ExceptionMessage.COMMON_FORBIDDEN.getMessage());
        }

        // 입력값 유효성 체크
        if (errors.hasErrors()) {
            log.warn(errors.getAllErrors().get(0).getDefaultMessage());
            throw new Exception400(errors.getAllErrors().get(0).getDefaultMessage());
        }

        AbilityUnitSaveResponseDTO responseDTO = abilityUnitsService.save(requestDTO);

        return ResponseEntity.ok(APIUtils.success(responseDTO));
    }
}
