package com.hwang.staste.config.jwt;

import java.security.Key;

public interface JwtProperties {
    int EXPIRATION_TIME = 864000000; // 10일 (1/1000초)
    String TOKEN_PREFIX = "Bearer ";
    String HEADER_STRING = "Authorization";
}

