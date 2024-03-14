package lab.nomad.springbootncsevaluation._core.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import lab.nomad.springbootncsevaluation.model.users.Users;
import org.springframework.stereotype.Component;

import java.util.Date;

// 토큰 발행 및 유효성 확인 유틸
@Component
public class JWTProvider {
    private static final Long EXP = 1000L * 60 * 60 * 48; // 테스트용 시간
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER = "Authorization";
    private static final String SECRET = "MySecretKey"; // TODO 시크릿키 환경변수로 등록 필수!!

    // 토큰 발행 메서드
    public static String create(Users user) {
        // Enum 타입 String 으로 변환
        String role = user.getRole().toString();

        // 토큰 생성
        String jwt = JWT.create()
                .withSubject(user.getEmail())
                .withExpiresAt(new Date(System.currentTimeMillis() + EXP))
                .withClaim("id", user.getId())
                .withClaim("role", role)
                .sign(Algorithm.HMAC512(SECRET));

        return TOKEN_PREFIX + jwt;
    }

    // 토큰 유효성 확인 메서드
    public static DecodedJWT verify(String jwt) throws SignatureVerificationException, TokenExpiredException {
        return JWT.require(Algorithm.HMAC512(SECRET))
                .build().verify(jwt);
    }

}
