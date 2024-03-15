package lab.nomad.springbootncsevaluation._core.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import lab.nomad.springbootncsevaluation._core.exception.Exception500;
import lab.nomad.springbootncsevaluation.model.users.Users;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

// 토큰 발행 및 유효성 확인 유틸
@Component
public class JWTProvider {
    private static final Long ACCESS_EXP = 1000L * 60 * 60 * 3;
    private static final Long REFRESH_EXP = 1000L * 60 * 60 * 72;
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER = "Authorization";
    private static final String SECRET = "MySecretKey"; // TODO 시크릿키 환경변수로 등록 필수!!

    // 토큰 발행 메서드
    public String create(Users user, JWTType jwtType) {

        // 토큰 생성
        String jwt = JWT.create()
                .withSubject(user.getEmail())
                .withExpiresAt(makeExpiresAt(jwtType))
                .withClaim("id", user.getId())
                .withClaim("role", user.getRole().toString())
                .withClaim("token-type", jwtType.name())
                .sign(Algorithm.HMAC512(SECRET));

        return TOKEN_PREFIX + jwt;
    }

    private Date makeExpiresAt(JWTType jwtType) {
        if (jwtType.equals(JWTType.ACCESS_TOKEN)) {
            return new Date(System.currentTimeMillis() + ACCESS_EXP);
        } else if (jwtType.equals(JWTType.REFRESH_TOKEN)) {
            return new Date(System.currentTimeMillis() + REFRESH_EXP);
        } else {
            throw new Exception500("올바르지 않은 토큰 타입입니다.");
        }
    }

    // 토큰 유효성 확인 메서드
    public static DecodedJWT verify(String jwt) throws SignatureVerificationException, TokenExpiredException {
        return JWT.require(Algorithm.HMAC512(SECRET))
                .build().verify(jwt);
    }

}
