package lab.nomad.springbootncsevaluation._core.security;

import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lab.nomad.springbootncsevaluation._core.exception.Exception401;
import lab.nomad.springbootncsevaluation._core.exception.ExceptionMessage;
import lab.nomad.springbootncsevaluation.model.users.Users;
import lab.nomad.springbootncsevaluation.model.users._enums.UserRole;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import java.io.IOException;

// JWT를 통한 인증 처리 필터
@Slf4j
public class JWTAuthenticationFilter extends BasicAuthenticationFilter {

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    // 필터 생성 메서드
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        // 헤더에 있는 Authorization 가져오기
        String prefixJWT = request.getHeader(JWTProvider.HEADER);

        // 헤더에 Authorization 가 존재하지 않을 경우 필터 통과
        if (prefixJWT == null) {
            chain.doFilter(request, response);
            return;
        }

        // Authorization의 값에서 'Bearer ' 부분 제거
        String jwt = prefixJWT.replace(JWTProvider.TOKEN_PREFIX, "");
        try {
            log.debug("디버그 : 토큰 있음");
            // 토큰 유효성 검사 (만약 유효하지 않다면, 예외가 발생한다.)
            DecodedJWT decodedJWT = new JWTProvider().verify(jwt);

            // 토큰으로부터 Claim 부분을 변수에 저장
            Long id = decodedJWT.getClaim("id").asLong();
            String role = decodedJWT.getClaim("role").asString();

            // User 객체 인스턴스 (User.role은 Enum 타입이기 때문에 변환 필요)
            Users user = Users.builder().id(id).role(UserRole.valueOf(role)).build();

            // CustomUserDetails 객체 인스턴스
            CustomUserDetails myUserDetails = new CustomUserDetails(user);

            // 스프링 시큐리티를 통하여 인증 처리 진행
            Authentication authentication =
                    new UsernamePasswordAuthenticationToken(
                            myUserDetails,
                            myUserDetails.getPassword(),
                            myUserDetails.getAuthorities()
                    );
            SecurityContextHolder.getContext().setAuthentication(authentication);
            log.debug("디버그 : 인증 객체 만들어짐");

            // 해당 토큰이 유효하지 않을 경우 발생하는 예외
        } catch (SignatureVerificationException sve) {
            log.warn(ExceptionMessage.INVALID_TOKEN.getMessage());

            // 예외 throw
            throw new Exception401(ExceptionMessage.INVALID_TOKEN.getMessage());

            // 해당 토큰의 유효 기간이 만료될 경우 발생하는 예외
        } catch (TokenExpiredException tee) {
            log.debug(ExceptionMessage.EXPIRED_TOKEN.getMessage());

            // 예외 throw
            throw new Exception401(ExceptionMessage.EXPIRED_TOKEN.getMessage());
        } finally {
            chain.doFilter(request, response);
        }
    }
}
