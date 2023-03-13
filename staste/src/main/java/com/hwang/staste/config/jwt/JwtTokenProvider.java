package com.hwang.staste.config.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
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

    public String createToken(Authentication authentication) {
        PrincipalDetails principalDetails = (PrincipalDetails) authentication.getPrincipal();

        String accessToken = JWT.create()
                .withSubject(authentication.getName())
                .withClaim("id", principalDetails.getUser().getId())
                .withClaim("username", principalDetails.getUser().getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + JwtProperties.EXPIRATION_TIME))
                .sign(Algorithm.HMAC256(JwtProperties.SECRET));

        String refreshToken = JWT.create()
                .withExpiresAt(new Date(System.currentTimeMillis() + JwtProperties.EXPIRATION_TIME * 36))
                .sign(Algorithm.HMAC256(JwtProperties.SECRET));


        return JWT.create()
                .withClaim("token_type", "Bearer")
                .withClaim("access_token", accessToken)
                .withClaim("refresh_token", refreshToken)
                .sign(Algorithm.none());
    }

    public Authentication getAuthentication(String accessToken) {
        DecodedJWT decodedJWT = JWT.decode(accessToken);

        if (!decodedJWT.getClaim("auth").isNull()) {
            Collection<? extends GrantedAuthority> authorities = Arrays.stream(decodedJWT.getClaim("auth").asArray(String.class))
                    .map(SimpleGrantedAuthority::new)
                    .collect(Collectors.toList());

            PrincipalDetails principalDetails = new PrincipalDetails(decodedJWT.getSubject(), "", authorities);
            return new UsernamePasswordAuthenticationToken(principalDetails, "", authorities);
        }
        return null;
    }
    public DecodedJWT decodeToken(String token) throws JWTDecodeException {
        return JWT.decode(token);
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

