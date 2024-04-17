package lab.nomad.springbootncsevaluation._core.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import lab.nomad.springbootncsevaluation._core.exception.Exception401;
import lab.nomad.springbootncsevaluation._core.exception.Exception403;
import lab.nomad.springbootncsevaluation._core.exception.Exception500;
import lab.nomad.springbootncsevaluation.model.users.Users;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
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

    private final CustomUserDetailsService userDetailsService;

    public JWTProvider(CustomUserDetailsService userDetailsService
    ) {
        this.userDetailsService = userDetailsService;
    }


    // 토큰 발행 메서드
    public String create(Users user, JWTType jwtType) {

        // 토큰 생성
        String jwt = JWT.create()
                .withSubject(String.valueOf(user.getId()))
                .withExpiresAt(makeExpiresAt(jwtType))
                .withClaim("username", user.getUsername())
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

    // 토큰 디코딩 코드
    public static DecodedJWT verify(String jwt) throws SignatureVerificationException, TokenExpiredException {

        if (jwt.startsWith(TOKEN_PREFIX)) {
            jwt = jwt.substring(TOKEN_PREFIX.length());
        }

        return JWT.require(Algorithm.HMAC512(SECRET))
                .build().verify(jwt);
    }

    public boolean validateToken(String jwt) {
        try {
            var verify = verify(jwt);

            if (new Date().after(verify.getExpiresAt()) ) {
                throw new Exception401("세션이 만료되었습니다.");
            }

            if (!verify.getClaim("token-type").asString().equals(JWTType.ACCESS_TOKEN.name())) {
                throw new Exception401("올바르지 않은 토큰입니다.");
            }

        } catch (JWTVerificationException e) {
            return false;
        }
        return true;
    }



    public Authentication getAuthentication(String token) {

        var decodedJWT = verify(token);
        var username = decodedJWT.getClaim("username").asString();
        var userDetails = userDetailsService.loadUserByUsername(username);
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

}
