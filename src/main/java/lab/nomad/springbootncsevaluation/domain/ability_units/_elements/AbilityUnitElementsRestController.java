package lab.nomad.springbootncsevaluation.domain.ability_units._elements;

import jakarta.validation.Valid;
import lab.nomad.springbootncsevaluation._core.exception.Exception400;
import lab.nomad.springbootncsevaluation._core.security.CustomUserDetails;
import lab.nomad.springbootncsevaluation._core.utils.APIUtils;
import lab.nomad.springbootncsevaluation._core.utils.AuthorityCheckUtils;
import lab.nomad.springbootncsevaluation.domain.ability_units._elements.dto.AbilityUnitElementSaveRequestDTO;
import lab.nomad.springbootncsevaluation.domain.ability_units._elements.dto.AbilityUnitElementSaveResponseDTO;
import lab.nomad.springbootncsevaluation.domain.ability_units._elements.service.AbilityUnitElementsService;
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
@RequestMapping("/api/v1/ability-units")
@Slf4j
public class AbilityUnitElementsRestController {
    private final AbilityUnitElementsService abilityUnitElementsService;

    @PostMapping("/{id}")
    public ResponseEntity<?> save(@RequestBody @Valid List<AbilityUnitElementSaveRequestDTO> requestDTOList, Errors errors,
                                  @AuthenticationPrincipal CustomUserDetails customUserDetails,
                                  @PathVariable Long id
    ) {

        // 권한 체크
        AuthorityCheckUtils.authorityCheck(customUserDetails, UserRole.ROLE_ADMIN.name());

        // 입력값 유효성 체크
        if (errors.hasErrors()) {
            log.warn(errors.getAllErrors().get(0).getDefaultMessage());
            throw new Exception400(errors.getAllErrors().get(0).getDefaultMessage());
        }

        AbilityUnitElementSaveResponseDTO responseDTO = abilityUnitElementsService.save(id, requestDTOList);

        return ResponseEntity.ok(APIUtils.success(responseDTO));
    }
}
