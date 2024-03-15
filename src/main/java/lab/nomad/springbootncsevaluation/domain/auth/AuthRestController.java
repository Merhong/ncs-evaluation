package lab.nomad.springbootncsevaluation.domain.auth;

import jakarta.validation.Valid;
import lab.nomad.springbootncsevaluation._core.exception.Exception400;
import lab.nomad.springbootncsevaluation._core.utils.APIUtils;
import lab.nomad.springbootncsevaluation.domain.auth.dto.*;
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
            log.warn(errors.getAllErrors().get(0).getDefaultMessage());
            throw new Exception400(errors.getAllErrors().get(0).getDefaultMessage());
        }

        JoinResponseDTO responseDTO = authService.join(requestDTO);

        return ResponseEntity.ok(APIUtils.success(responseDTO));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid LoginRequestDTO requestDTO, Errors errors) {

        if (errors.hasErrors()) {
            log.warn(errors.getAllErrors().get(0).getDefaultMessage());
            throw new Exception400(errors.getAllErrors().get(0).getDefaultMessage());
        }

        LoginResponseDTO responseDTO = authService.login(requestDTO);

        return ResponseEntity.ok(APIUtils.success(responseDTO));
    }

    @PostMapping("/re-login")
    public ResponseEntity<?> reLogin(@RequestBody @Valid ReLoginRequestDTO requestDTO, Errors errors) {

        if (errors.hasErrors()) {
            log.warn(errors.getAllErrors().get(0).getDefaultMessage());
            throw new Exception400(errors.getAllErrors().get(0).getDefaultMessage());
        }

        ReLoginResponseDTO responseDTO = authService.reLogin(requestDTO);

        return ResponseEntity.ok(APIUtils.success(responseDTO));
    }
}
