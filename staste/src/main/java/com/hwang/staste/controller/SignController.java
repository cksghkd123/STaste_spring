package com.hwang.staste.controller;

import com.hwang.staste.config.auth.PrincipalDetails;
import com.hwang.staste.model.entity.User;
import com.hwang.staste.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class SignController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @GetMapping("/signIn")
    public String signIn(Authentication authentication, @AuthenticationPrincipal PrincipalDetails userDetails) { //의존성 주입
        System.out.println("========= /login ==============");
        PrincipalDetails principalDetails = (PrincipalDetails) authentication.getPrincipal();
        System.out.println("authentication:" + principalDetails.getUser());
        System.out.println("userDetails:" + userDetails.getUser());
        return "세션 정보 확인하기";
    }

    @GetMapping("/oauth/signIn")
    public @ResponseBody String oAuthSignIn(Authentication authentication, @AuthenticationPrincipal OAuth2User oAuth) { //의존성 주입
        System.out.println("======== /oauth/login ==============");
        OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();
        System.out.println("authentication:" + oAuth2User.getAttributes());
        System.out.println("oauth2User:" + oAuth.getAttributes());

        return "OAuth 세션 정보 확인하기";
    }

    // OAuth 로그인이든 일반 로그인이든 PrincipalDetails!!
    @GetMapping("/user")
    public @ResponseBody String user(@AuthenticationPrincipal PrincipalDetails principalDetails) {
        System.out.println("principalDetails:" + principalDetails.getUser());
        return "user";
    }

    @PostMapping("/signUp")
    public String signUp(@RequestBody User user) {
        System.out.println(user);
        String rawPassword = user.getPassword();
        String encPassword = bCryptPasswordEncoder.encode(rawPassword);
        user.setPassword(encPassword);
        userRepository.save(user);
        return "회원가입완료";
    }

}
