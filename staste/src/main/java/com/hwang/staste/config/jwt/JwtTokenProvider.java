package com.hwang.staste.config.jwt;

import com.auth0.jwt.JWT;
import com.hwang.staste.DTO.JwtToken;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class JwtTokenProvider {

    public String createToken(Authentication authentication) {
        String authorities = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(" "));

        String accessToken = JWT.create()
                .withClaim()

        String refreshToken;


        return JwtToken.builder()
                .grantType("Bearer")
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }

}

//    Strint jwtToken = Jwts.builder().addClaims()
//            .Map.of(
//                    "exp",
//                    "key1", "value1",
//                    "key2", "value2", ...
//        )
//        .signWith(SignatureAlgorithm.HS256, "JWT_SECRET_KEY")
//        .compact();;
//
//        Jws<Claims> jwt = Jwts.parser()
//        .setSigningKey("JWT_SECRET_KEY")
//        .parseClaimsJws(jwtToken);
//
//    String jwtToken = JWT.create()
//            .withSubject("subject"
//                    .withExpiresAt(new Date(System.currentTimeMillis() + ... )
//        .withClaim("key2", "value2")
//        .sign(Algorithm.HMAC256("JWT_SECRET_KEY"));
//        Payload 파싱방법 (토큰 디코딩)
//
//        DecodedJWT jwt = JWT.require(Algorithm.HMAC256("JWT_SECRET_KEY"))
//        .build()
//        .verify(jwtToken);
