package lab.nomad.springbootncsevaluation.domain.auth;

import jakarta.validation.Valid;
import lab.nomad.springbootncsevaluation._core.exception.Exception400;
import lab.nomad.springbootncsevaluation._core.utils.APIUtils;
import lab.nomad.springbootncsevaluation.domain.auth.dto.JoinRequestDTO;
import lab.nomad.springbootncsevaluation.domain.auth.dto.JoinResponseDTO;
import lab.nomad.springbootncsevaluation.domain.auth.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
@Slf4j
public class AuthRestController {
    private final AuthService authService;

    @PostMapping("/join")
    public ResponseEntity<?> join(@RequestBody @Valid JoinRequestDTO requestDTO, Errors errors) {

        if (errors.hasErrors()) {
            log.error(errors.getAllErrors().get(0).getDefaultMessage());
            throw new Exception400(errors.getAllErrors().get(0).getDefaultMessage());
        }

        JoinResponseDTO responseDTO = authService.join(requestDTO);

        return ResponseEntity.ok(APIUtils.success(responseDTO));
    }
}
