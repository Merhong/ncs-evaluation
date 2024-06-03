package lab.nomad.springbootncsevaluation.domain.auth;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
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

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
@Slf4j
public class AuthRestController {
    private final AuthService authService;

    @PostMapping("/join")
    public ResponseEntity<?> join(@RequestBody @Valid JoinRequestDTO requestDTO, Errors errors) {

        if (errors.hasErrors()) {
            log.warn(errors.getAllErrors()
                    .get(0)
                    .getDefaultMessage());
            throw new Exception400(errors.getAllErrors()
                    .get(0)
                    .getDefaultMessage());
        }

        JoinResponseDTO responseDTO = authService.join(requestDTO);

        return ResponseEntity.ok(APIUtils.success(responseDTO));
    }


    // View의 Form 태그에서 POST 요청시 @RequestBody를 사용하면 오류가 난다.
    @PostMapping("/login")
    public ResponseEntity<?> login(HttpServletResponse response, @RequestBody @Valid LoginRequestDTO requestDTO,
            Errors errors) {

        if (errors.hasErrors()) {
            log.warn(errors.getAllErrors()
                    .get(0)
                    .getDefaultMessage());
            throw new Exception400(errors.getAllErrors()
                    .get(0)
                    .getDefaultMessage());
        }

        LoginResponseDTO responseDTO = authService.login(requestDTO);

        // JWT를 쿠키에 설정
        setJwtCookies(responseDTO.getToken()
                .getAccessToken(), responseDTO.getToken()
                .getRefreshToken(), response);

        return ResponseEntity.ok(APIUtils.success(responseDTO));
    }

    @PostMapping("/re-login")
    public ResponseEntity<?> reLogin(@RequestBody @Valid ReLoginRequestDTO requestDTO, Errors errors) {

        if (errors.hasErrors()) {
            log.warn(errors.getAllErrors()
                    .get(0)
                    .getDefaultMessage());
            throw new Exception400(errors.getAllErrors()
                    .get(0)
                    .getDefaultMessage());
        }

        ReLoginResponseDTO responseDTO = authService.reLogin(requestDTO);

        return ResponseEntity.ok(APIUtils.success(responseDTO));
    }

    private void setJwtCookies(String accessToken, String refreshToken, HttpServletResponse response) {
        try {
            String encodedAccessToken = URLEncoder.encode(accessToken, StandardCharsets.UTF_8.toString());
            String encodedRefreshToken = URLEncoder.encode(refreshToken, StandardCharsets.UTF_8.toString());

            Cookie accessTokenCookie = new Cookie("accessToken", encodedAccessToken);
            accessTokenCookie.setHttpOnly(true);
            accessTokenCookie.setPath("/");
            accessTokenCookie.setMaxAge(3 * 60 * 60); // 3시간

            Cookie refreshTokenCookie = new Cookie("refreshToken", encodedRefreshToken);
            refreshTokenCookie.setHttpOnly(true);
            refreshTokenCookie.setPath("/");
            refreshTokenCookie.setMaxAge(72 * 60 * 60); // 3일

            response.addCookie(accessTokenCookie);
            response.addCookie(refreshTokenCookie);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("Failed to encode JWT token", e);
        }
    }
}
