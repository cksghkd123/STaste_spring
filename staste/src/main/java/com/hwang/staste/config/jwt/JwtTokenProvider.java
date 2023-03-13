package com.hwang.staste.config.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.hwang.staste.DTO.JwtToken;
import com.hwang.staste.config.auth.PrincipalDetails;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class JwtTokenProvider {

    public JwtToken createToken(PrincipalDetails principalDetails) {
        String accessToken = JWT.create()
                .withSubject(principalDetails.getUsername())
                .withClaim("id", principalDetails.getUser().getId())
                .withExpiresAt(new Date(System.currentTimeMillis() + JwtProperties.EXPIRATION_TIME))
                .sign(Algorithm.HMAC256(JwtProperties.SECRET));

        String refreshToken = JWT.create()
                .withSubject(principalDetails.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + JwtProperties.EXPIRATION_TIME * 36))
                .sign(Algorithm.HMAC256(JwtProperties.SECRET));

        return new JwtToken(JwtProperties.TOKEN_PREFIX,accessToken,refreshToken);
    }

    public boolean validateToken(String token) {
        try {
            JWT.require(Algorithm.HMAC256(JwtProperties.SECRET)).build().verify(token);
            return true;
        } catch (JWTVerificationException e) {
            System.out.println(e);
        }
        return false;
    }

}

